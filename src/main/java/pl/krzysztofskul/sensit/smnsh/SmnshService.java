package pl.krzysztofskul.sensit.smnsh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Service
public class SmnshService {

	private SmnshRepo smnshRepo;

	/**
	 * @param smnshRepo
	 */
	@Autowired
	public SmnshService(SmnshRepo smnshRepo) {
		this.smnshRepo = smnshRepo;
	}
	
	public Project save(Project project) {
		return smnshRepo.save(project);
	}
	
	public List<Project> loadAll() {
		return smnshRepo.findAll();
	}
	
}
