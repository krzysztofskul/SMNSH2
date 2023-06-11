package pl.krzysztofskul.sensit.smnsh.project.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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



	@GetMapping("/smnsh/projects/{id}/milestones/{milestoneId}/set-status")
	public String setMilestoneStatus(
				@PathVariable(name="id") Long projectId,
				@PathVariable Long milestoneId,
				@RequestParam(name = "status", required = true) String status,
				Model model
			) {

		MilestoneInstance milestoneInstance = milestoneService.loadById(milestoneId);

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
		
		milestoneService.saveMilestoneInstance(milestoneInstance);
		
		return "redirect:/smnsh/projects/"+projectId+"/milestones";
	}
	
}
