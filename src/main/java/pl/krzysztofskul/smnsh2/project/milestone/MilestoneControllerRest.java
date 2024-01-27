package pl.krzysztofskul.smnsh2.project.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smnsh2/restapi")
public class MilestoneControllerRest {

	private MilestoneService milestoneService;
	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public MilestoneControllerRest(MilestoneService milestoneService) {
		this.milestoneService = milestoneService;
	}

	@DeleteMapping("/milestones/{id}")
	public void deleteMilestoneInstance(
				@PathVariable("id") Long milestoneId
			) {
		milestoneService.deleteMilestoneById(milestoneId);
	}
	
}
