package pl.krzysztofskul.smnsh2.project.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectService;

@Controller
@RequestMapping("/smnsh")
public class MilestoneController {

	private MilestoneService milestoneService;
	private ProjectService projectService;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param milestoneService
	 */
	@Autowired
	public MilestoneController(
			MilestoneService milestoneService,
			ProjectService projectService
			) {
		this.milestoneService = milestoneService;
		this.projectService = projectService;
	}
	
	@GetMapping("/projects/{id}/milestones/{milestoneId}/set-status")
	public String setMilestoneStatus(
				@PathVariable(name="id") Long projectId,
				@PathVariable Long milestoneId,
				@RequestParam(name = "status", required = true) String status,
				Model model
			) {

		MilestoneInstance milestoneInstance = milestoneService.loadById(milestoneId);

		milestoneInstance = milestoneInstanceUpdateStatus(status, milestoneInstance);
		
		milestoneService.saveMilestoneInstance(milestoneInstance);
		
		return "forward:/smnsh2/projects/"+projectId+"/milestones";
	}
	
	@GetMapping("/projects/{projectId}/milestones/instances/new")
	public String getMilestoneInstanceNew(
				@PathVariable Long projectId,
				Model model
			) {
		Project project = projectService.loadByIdWithMilestones(projectId);		
		MilestoneInstance milestoneInstanceNew = new MilestoneInstance(project, MilestoneStatusEnum.WAITING);
		model.addAttribute("milestoneInstanceNew", milestoneInstanceNew);
		model.addAttribute("project", project);
		return "smnsh2/projects/idDetailsAndMilestones";
	}

	@PostMapping("/projects/{projectId}/milestones/instances/new")
	public String postMilestoneInstanceNew(
				@PathVariable Long projectId,
				@ModelAttribute @Validated MilestoneInstance milestoneInstanceNew, 
				BindingResult bindingResult
			) {
		milestoneService.saveMilestoneInstance(milestoneInstanceNew);
		return "redirect:/smnsh2/projects/"+projectId+"/milestones";
	}
	
	@PostMapping("/restapi/projects/{id}/milestones/{milestoneId}/set-status")
	@ResponseBody
	public void setMilestoneStatus(
			@PathVariable(name="id") Long projectId,
			@PathVariable Long milestoneId,
			@RequestParam(name = "status", required = true) String status
		) {
		MilestoneInstance milestoneInstance = milestoneService.loadById(milestoneId);

		milestoneInstance = milestoneInstanceUpdateStatus(status, milestoneInstance);
		
		milestoneService.saveMilestoneInstance(milestoneInstance);
	}

	/**
	 * Updates the status of milestone instance
	 * @param status
	 * @param milestoneInstance
	 * @return MilestoneInstance milesoneInstance
	 */
	private MilestoneInstance milestoneInstanceUpdateStatus(String status, MilestoneInstance milestoneInstance) {
		switch (status) {				
				case "WAITING": {
					milestoneInstance.setStatus(MilestoneStatusEnum.WAITING);
					break;
				}	
				case "IN_PROGRESS": {
					milestoneInstance.setStatus(MilestoneStatusEnum.IN_PROGRESS);
					break;
				}	
				case "COMPLETED": {
					milestoneInstance.setStatus(MilestoneStatusEnum.COMPLETED);
					break;
				}	
				case "CANCELED": {
					milestoneInstance.setStatus(MilestoneStatusEnum.CANCELED);
					break;
				}	
					
				default:
					break;
		}
		return milestoneInstance;
	}
	
}
