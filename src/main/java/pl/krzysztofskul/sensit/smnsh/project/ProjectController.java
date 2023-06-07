package pl.krzysztofskul.sensit.smnsh.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;

@Controller
@RequestMapping("/smnsh")
public class ProjectController {

	private ProjectService projectService;
	
	/**
	 * @param projectService
	 */
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping("/projects")
	public String getTestProjects() {		
		return "smnsh/projects/all";
	}
	
	@GetMapping("/projects/{id}")
	public String getProjectById(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadById(id));
		return "smnsh/projects/idDetails";
	}
	
	@GetMapping("/projects/{id}/milestones")
	public String getProjectByIdWithMilestones(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithMilestones(id));
		return "smnsh/projects/idDetailsAndMilestones";
	}
	
	@GetMapping("/projects/{id}/stakeholders")
	public String getProjectByIdWithStakeholders(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithStakeholders(id));
		return "smnsh/projects/idDetailsAndStakeholders";
	}
	
	@GetMapping("/projects/{id}/stakeholders/add")
	public String getAddStakeholderForm(
				@PathVariable Long id,
				Model model
			) {
		Stakeholder newStakeholder = new Stakeholder(projectService.loadById(id));
		model.addAttribute("project", projectService.loadByIdWithStakeholders(id));
		model.addAttribute("newStakeholder", newStakeholder);
		return "smnsh/projects/forms/addStakeholderForm";
	}
	
	@PostMapping("/projects/{id}/stakeholders/add")
	public String postAddStakeholderForm(
				@PathVariable Long id,
				Stakeholder newStakeholder,
				Model model
			) {

		Project project = projectService.loadByIdWithStakeholders(id);
		project.addStakeholder(newStakeholder);
		project = projectService.saveAndReturn(project);
		
		model.addAttribute("project", project);
		return "smnsh/projects/idDetailsAndStakeholders";
	}
	
	@GetMapping("/projects/{id}/remarks")
	public String getProjectByIdWithRemarks(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithRemarks(id));
		return "smnsh/projects/idDetailsAndRemarks";
	}
	
}
