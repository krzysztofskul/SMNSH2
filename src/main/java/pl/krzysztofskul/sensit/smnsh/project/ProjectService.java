package pl.krzysztofskul.sensit.smnsh.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.project.installation.configuration.ConfigurationDevice;
import pl.krzysztofskul.sensit.smnsh.project.installation.configuration.ConfigurationDeviceService;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneComparator;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneInstance;
import pl.krzysztofskul.sensit.smnsh.project.status.Status;
import pl.krzysztofskul.sensit.smnsh.user.User;
import pl.krzysztofskul.sensit.smnsh.user.UserService;

@Service
public class ProjectService {

	private ProjectRepo projectRepo;
	private UserService userService;
	private ConfigurationDeviceService configurationDeviceService;
	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public ProjectService(
				ProjectRepo projectRepo,
				UserService userService,
				ConfigurationDeviceService configurationDeviceService
			) {
		this.projectRepo = projectRepo;
		this.userService = userService;
		this.configurationDeviceService = configurationDeviceService;
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
		List<MilestoneInstance> milestones = project.getMilestones();
		milestones.sort(new MilestoneComparator());
		project.setMilestones(milestones);
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

	public List<Project> loadProjectsByUserId(Long userId) {
		User user = userService.loadById(userId);
		List<Project> projectList = new ArrayList<Project>();
		for (Project project : projectRepo.findAllByProjectManager(user)) {
			projectList.add(project);
		}
		projectList.addAll(projectRepo.findAllByProjectManagerAdd(user));
		for (Project project : projectRepo.findAllBySalesRep(user)) {
			projectList.add(project);	
		}
		
		return projectList;
	}

	public void deleteById(Long projectId) {
		projectRepo.deleteById(projectId);
		
	}

	public Project addLinkToConfigurationFile(Project project, String filePath) {
		List<String> configurationLinkList = project.getConfigurationLinks();
		configurationLinkList.add(filePath);
		project.setConfigurationLinks(configurationLinkList);
		return project;
	}

	public Project removeLinkToConfigurationFile(Project project, String configurationLink) {
		List<String> configurationLinkList = project.getConfigurationLinks();
		configurationLinkList.removeIf(s -> s.equals(configurationLink));
		project.setConfigurationLinks(configurationLinkList);
		return project;
	}

	public void removeConfigurationDevice(Project project, ConfigurationDevice configurationDevice) {
		project.getInstallation().getDeviceInstance().removeConfigurationDevice(configurationDevice);
		this.save(project);
	}
	
}
