package pl.krzysztofskul.smnsh2.project.milestone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smnsh/restapi")
public class MilestoneControllerRest {

	private MilestoneService milestoneService;
	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public MilestoneControllerRest(MilestoneService milestoneService) {
		this.milestoneService = milestoneService;
	}
	
	@PutMapping("/milestones/{id}")
	public void putMilestoneInstance(
				@PathVariable Long id,
				@RequestParam(required = false) String namePl,
				@RequestParam(required = false) String nameEn,
				@RequestParam(required = false) String deadline
			) {
		MilestoneInstance milestone = milestoneService.loadById(id);
		if (namePl != null) {
			milestone.setNamePl(namePl);
		}
		if (nameEn != null) {
			milestone.setNameEn(nameEn);
		}
		if (deadline != null) {
			if (deadline == "") {
				milestone.setDeadline(null);
			} else {
				milestone.setDeadline(LocalDate.parse(deadline));	
			}
			
		}
		
		milestoneService.saveMilestoneInstance(milestone);
	}

	@DeleteMapping("/milestones/{id}")
	public void deleteMilestoneInstance(
				@PathVariable("id") Long milestoneId
			) {
		milestoneService.deleteMilestoneById(milestoneId);
	}
	
}
