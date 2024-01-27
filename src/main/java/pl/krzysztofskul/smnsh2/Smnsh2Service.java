package pl.krzysztofskul.smnsh2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.smnsh2.project.Project;

@Service
public class Smnsh2Service {

	private Smnsh2Repo smnsh2Repo;

	/**
	 * @param smnshRepo
	 */
	@Autowired
	public Smnsh2Service(Smnsh2Repo smnshRepo) {
		this.smnsh2Repo = smnshRepo;
	}
	
	public Project save(Project project) {
		return smnsh2Repo.save(project);
	}
	
	public List<Project> loadAll() {
		return smnsh2Repo.findAll();
	}
	
}
