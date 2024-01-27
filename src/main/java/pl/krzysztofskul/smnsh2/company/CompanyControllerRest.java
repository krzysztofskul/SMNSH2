package pl.krzysztofskul.smnsh2.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smnsh2/companies/rest")
public class CompanyControllerRest {

	private CompanyService companyService;
	
	/**
	 * Constructor
	 */
	@Autowired
	public CompanyControllerRest(CompanyService companyService) {
		this.companyService = companyService;
	}
		
	@PostMapping("/{companyId}")
	public Company setNewComapnyLabel(
				@PathVariable Long companyId,
				@RequestParam(name = "labelEnumString") String labelEnumString
			) {
		Company company = companyService.loadById(companyId);
		LabelEnum labelEnum = null;
		switch (labelEnumString) {
			case "LabelEnum.GREEN":
				{
					labelEnum = LabelEnum.GREEN;
					break;
				}
			case "LabelEnum.RED":
				{
					labelEnum = LabelEnum.RED;
					break;
				}	
			default:
				break;
		}
		company.setLabelEnum(labelEnum);
		return companyService.saveAndReturn(company);
	}
	
}
