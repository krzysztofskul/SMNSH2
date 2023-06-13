package pl.krzysztofskul.sensit.smnsh.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/smnsh/companies")
public class CompanyController {

	private CompanyService companyService;
	
	/**
	 * Constructor
	 */
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/subcontractors-room-adaptation")
	public String allSubcontractorsForRoomAdaptation(Model model) {
		model.addAttribute("companies", companyService.loadAllSubcontrsctorsForRoomAdaptation());
		return "smnsh/companies/allSubcontractors";
	}
	
}
