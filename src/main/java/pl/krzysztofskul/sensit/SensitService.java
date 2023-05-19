package pl.krzysztofskul.sensit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Service
public class SensitService {

	private SensitRepo sensitRepo;

	/**
	 * @param sensitRepo
	 */
	@Autowired
	public SensitService(SensitRepo sensitRepo) {
		this.sensitRepo = sensitRepo;
	}
	
	public Project save(Project project) {
		return sensitRepo.save(project);
	}
	
	public List<Project> loadAll() {
		return sensitRepo.findAll();
	}
	
}
