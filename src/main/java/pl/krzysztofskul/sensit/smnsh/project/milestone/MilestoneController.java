package pl.krzysztofskul.sensit.smnsh.project.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
				@PathVariable Long id,
				@PathVariable Long milestoneId,
				@RequestParam(name = "status", required = true) String status
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
				case "FINISHED": {
					milestoneInstance.setStatus(MilestoneStatusEnum.FINISHED);
					break;
				}	
				case "CANCELED": {
					milestoneInstance.setStatus(MilestoneStatusEnum.CANCELED);
					break;
				}	
					
				default:
					break;
				}
		return "redirect:/projects/{id}/milestones";
	}
	
}
