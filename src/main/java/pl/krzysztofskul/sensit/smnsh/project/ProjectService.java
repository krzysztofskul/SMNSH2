package pl.krzysztofskul.sensit.smnsh.project;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	private ProjectRepo projectRepo;
	
	/**
	 * @param projectRepo
	 */
	@Autowired
	public ProjectService(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}

	public Project save(Project project) {
		return projectRepo.save(project);
	}

	public List<Project> loadAll() {
		return projectRepo.findAll();
	}

	public Project loadById(Long id) {
		return projectRepo.findById(id).get();
	}

	public Project loadByIdWithStakeholders(Long id) {
		Project project = this.loadById(id);
		Hibernate.initialize(project.getStakeholders());
		return project;
	}
	
}
