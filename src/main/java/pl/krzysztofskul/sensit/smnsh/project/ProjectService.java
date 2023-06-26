package pl.krzysztofskul.sensit.smnsh.project;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.project.status.Status;

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

	public void save(Project project) {
		projectRepo.save(project);
	}
	
	public Project saveAndReturn(Project project) {
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

	public Project loadByIdWithMilestones(Long id) {
		Project project = this.loadById(id);
		Hibernate.initialize(project.getMilestones());
		return project;
	}

	public Project loadByIdWithRemarks(Long id) {
		Project project = this.loadById(id);
		Hibernate.initialize(project.getRemarks());
		return project;
	}

	public Project loadByIdWithAttachments(Long id) {
		Project project = this.loadById(id);
		Hibernate.initialize(project.getAttachments());
		return project;
	}

	public Project setStatus(Long projectId, Status status) {
		Project project = this.loadById(projectId);
		project.changeStatusTo(status);
		return project;
	}
	
}
