package pl.krzysztofskul.smnsh2.project.stakeholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pl.krzysztofskul.smnsh2.company.CompanyService;

@Controller
public class StakeholderController {

	private StakeholderService stakeholderService;
	private CompanyService companyService;
	
	/**
	 * @param stakeholderService
	 */
	@Autowired
	public StakeholderController(
				StakeholderService stakeholderService,
				CompanyService companyService
			) {
		super();
		this.companyService = companyService;
		this.stakeholderService = stakeholderService;
	}

	@GetMapping("/smnsh2/stakeholders/edit/{id}")
	public String editById(
			@PathVariable Long id,
			Model model
			) {
		Stakeholder stakeholder = stakeholderService.loadById(id);
		model.addAttribute(stakeholder);
		model.addAttribute("edit", true);
		model.addAttribute("companies", companyService.loadAllSubcontrsctorsForRoomAdaptation());
		model.addAttribute("project", stakeholder.getProject());
		return "smnsh2/projects/forms/addStakeholderForm";
	}
	
	@GetMapping("/smnsh2/stakeholders/delete/{id}")
	public String deleteById(
				@PathVariable(name = "id") Long stakeholderId,
				@RequestParam(name = "backToPage", required = false) String backToPage
			) {
		this.stakeholderService.deleteById(stakeholderId);
		return "redirect:"+backToPage;
	}
	
}
