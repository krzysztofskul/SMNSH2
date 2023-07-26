package pl.krzysztofskul.sensit.smnsh.project.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/smnsh")
public class MilestoneController {

	private MilestoneService milestoneService;
	
	
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param milestoneService
	 */
	@Autowired
	public MilestoneController(MilestoneService milestoneService) {
		this.milestoneService = milestoneService;
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
		
		return "redirect:/smnsh/projects/"+projectId+"/milestones";
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
