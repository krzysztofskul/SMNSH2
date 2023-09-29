package pl.krzysztofskul.sensit.smnsh.filestorage;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class FileUploadController {

	private final FileStorageService fileStorageService;

	@Autowired
	public FileUploadController(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	@GetMapping("/smnsh/test/file-upload")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", fileStorageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/smnsh/test/file-upload/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = fileStorageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@GetMapping(value = "/smnsh/files/download/{fileId}")
	public ResponseEntity<ByteArrayResource> getDownloadAttachment(
				@PathVariable Long fileId
			) {
		File file = fileStorageService.loadById(fileId);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(file.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+file.getFileName())
				.body(new ByteArrayResource(file.getData()));
		
	}

	@PostMapping("/smnsh/test/file-upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {

		fileStorageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

//	@ExceptionHandler(StorageFileNotFoundException.class)
//	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//		return ResponseEntity.notFound().build();
//	}

}
