package pl.krzysztofskul.smnsh2.filestorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import pl.krzysztofskul.smnsh2.project.attachment.Attachment;

@Service
public class FileStorageService implements FileStorageServiceInterface {

	private FileStorageRepo fileStorageRepo;
	
	/**
	 * @param fileStorageRepo
	 */
	@Autowired
	public FileStorageService(FileStorageRepo fileStorageRepo) {
		this.fileStorageRepo = fileStorageRepo;
	}

	  private final Path root = Paths.get("uploads");

	  @Override
	  public void init() {
	    try {
	      Files.createDirectories(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	@Override
	public void store(MultipartFile multipartFile) throws IOException {
		
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        File fileToStore = new File();

        fileToStore.setFileName(fileName);
        fileToStore.setFileType(multipartFile.getContentType());
        fileToStore.setData(multipartFile.getBytes());

        fileStorageRepo.save(fileToStore);
	}

	@Override
	public Stream<Path> loadAll() {
	    try {
	        return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	      } catch (IOException e) {
	        throw new RuntimeException("Could not load the files!");
	      }
	}

	@Override
	public Path load(String filename) {
		return null;
	}

	public File loadById(Long id) {
		return fileStorageRepo.findById(id).get();
	}
	
	
	@Override
	public Resource loadAsResource(String filename) {
		return null;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
		
	}

}
