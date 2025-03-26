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

@RestController
@RequestMapping("/smnsh2/rest/kpds")
public class KpdsControllerRest {

	private static final String FILE_PATH = "src/main/resources/uploads/kpds/";
	
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
	
}
