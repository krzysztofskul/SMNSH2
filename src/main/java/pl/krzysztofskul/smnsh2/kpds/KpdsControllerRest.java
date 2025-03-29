package pl.krzysztofskul.smnsh2.kpds;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import java.io.*;

@RestController
@RequestMapping("/smnsh2/rest/kpds")
public class KpdsControllerRest {

	private static final String FILE_PATH = "src/main/resources/uploads/kpds/";
	private static final String FILE_PATH_2 = "static/kpds/"; // Path inside src/main/resources
	
	@GetMapping("/download/{projectId}")
    public ResponseEntity<byte[]> downloadFile(
    			@PathVariable String projectId
    		) {
    	try {
            Path filePath = Path.of(FILE_PATH+"kpds-projectId_"+projectId+".pdf");
            byte[] fileContent = Files.readAllBytes(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=kpds-projectId_"+projectId+".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            // Delete file after download
            deleteFileAfterDownload(filePath.toFile());

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    private void deleteFileAfterDownload(File file) {
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("File deleted successfully: " + file.getAbsolutePath());
            } else {
                System.err.println("Failed to delete file: " + file.getAbsolutePath());
            }
        }
    }
    
    @GetMapping("/download2")
    public ResponseEntity<Resource> downloadFile2(@RequestParam(defaultValue = "kdps-projectId_1.pdf") String filename) throws IOException {
        // Load the file from classpath
        ClassPathResource pdfFile = new ClassPathResource(FILE_PATH_2);

        // Copy the file to a temporary location (since classpath files are read-only)
        File tempFile = File.createTempFile("download_", ".pdf");
        try (InputStream in = pdfFile.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }

        // Serve the file as a download
        Resource resource = new FileSystemResource(tempFile);
        ResponseEntity<Resource> response = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);

        // Delete the temp file after serving
        tempFile.delete();

        return response;
    }

    @GetMapping("/download3")
    public ResponseEntity<InputStreamResource> downloadFile3(@RequestParam String filename) {
        try {
            // Load file from classpath
            ClassPathResource resource = new ClassPathResource("static/kpds/" + filename);

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            InputStream inputStream = resource.getInputStream();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(inputStreamResource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
}
