package pl.krzysztofskul.sensit.smnsh.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.krzysztofskul.sensit.smnsh.company.CompanyCategory.CompanyCategoryEnum;
import pl.krzysztofskul.sensit.smnsh.project.Project;

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

	@ModelAttribute("labelEnumList")
	public List<LabelEnum> getLabelEnums() {
		return List.of(LabelEnum.values());
	}
	
	@GetMapping("")
	public String all(
			@RequestParam(name = "companyCategory") String companyCategory,
			Model model
			) {
		switch (companyCategory) {
		case "subcontractors-room-adaptation":
			{
				model.addAttribute("companies", companyService.loadAllSubcontrsctorsForRoomAdaptation());
				break;
			}
		case "investors":
		{
			model.addAttribute("companies", companyService.loadAllInvestors());
			break;
		}

		default:
			break;
		}
		
		return "smnsh/companies/all";
	}
	
	@GetMapping("/setLabel/{companyId}")
	public String setNewComapnyLabel(
				@PathVariable Long companyId,
				@RequestParam(name = "label") String label
			) {
		Company company = companyService.loadById(companyId);
		LabelEnum labelEnum = null;
		switch (label) {
			case "green":
				{
					labelEnum = LabelEnum.GREEN;
					break;
				}
			case "yellow":
				{
					labelEnum = LabelEnum.YELLOW;
					break;
				}
			case "red":
				{
					labelEnum = LabelEnum.RED;
					break;
				}	
			case "black":
				{
					labelEnum = LabelEnum.BLACK;
					break;
				}	
			case "gray":
				{
					labelEnum = LabelEnum.GRAY;
					break;
				}	
			case "blue":
				{
					labelEnum = LabelEnum.BLUE;
					break;
				}	
			default:
				{
					labelEnum = LabelEnum.GRAY;
					break;
				}	
		}
		company.setLabelEnum(labelEnum);
		companyService.save(company);
		return "redirect:/smnsh/companies/"+companyId;
	}

	@GetMapping("/{companyId}")
	public String getPageByCompanyId(
				@PathVariable Long companyId,
				@RequestParam(required = false) boolean edit,
				Model model,
				HttpServletRequest httpServletRequest
				) {
		model.addAttribute(companyService.loadById(companyId));
		if (edit == true) {
			httpServletRequest.setAttribute("edit", true);
			return "smnsh/companies/idDetails";
		}
		
		return "smnsh/companies/idDetails";
	}
	
	@GetMapping("/new")
	public String getNewCompany(
				@RequestParam(name = "companyCategoryEnumString", required = true) CompanyCategoryEnum companyCategoryEnum,
				Model model
			) {
		Company company = new Company();
		
		if (companyCategoryEnum != null) {
			company.setCompanyCategoryEnum(companyCategoryEnum);
			company.setLabelEnum(LabelEnum.GREEN);

		}
		model.addAttribute(company);
		return "smnsh/companies/idDetails";
	}
	
	@PostMapping("/new")
	public String postNewCompany(
				@RequestParam(required = false) String backToPage,
				@ModelAttribute("company") @Validated Company company, BindingResult result,
				Model model
			) {
		model.addAttribute(companyService.saveAndReturn(company));
		return "smnsh/companies/idDetails";
	}
	
}
