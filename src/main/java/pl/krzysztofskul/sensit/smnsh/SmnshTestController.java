package pl.krzysztofskul.sensit.smnsh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.sensit.smnsh.company.Company;
import pl.krzysztofskul.sensit.smnsh.company.CompanyDemoGenerator;
import pl.krzysztofskul.sensit.smnsh.company.CompanyService;
import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.project.ProjectTestGenerator;
import pl.krzysztofskul.sensit.smnsh.project.device.DevicePortfolio;
import pl.krzysztofskul.sensit.smnsh.project.device.modality.DevicePortfolioGenerator;
import pl.krzysztofskul.sensit.smnsh.project.device.modality.ModalityGenerator;
import pl.krzysztofskul.sensit.smnsh.user.User;
import pl.krzysztofskul.sensit.smnsh.user.UserGenerator;
import pl.krzysztofskul.sensit.smnsh.user.UserService;
import pl.krzysztofskul.sensit.smnsh.project.ProjectService;

@Controller
@RequestMapping("/smnsh/test")
public class SmnshTestController {

	private boolean isInitDataDone = false;
	
	private ProjectTestGenerator projectTestGenerator;
	private ProjectService projectService;
	private UserGenerator userGenerator;
	private UserService userService;
	private ModalityGenerator modalityGenerator;
	private DevicePortfolioGenerator devicePortfolioGenerator;
	private CompanyDemoGenerator companyDemoGenerator;
	private CompanyService companyService;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param isInitDataDone
	 * @param projectTestGenerator
	 * @param projectService
	 * @param userGenerator
	 * @param userService
	 * @param modalityGenerator
	 * @param devicePortfolioGenerator
	 * @param companyDemoGenerator
	 * @param companyService
	 */
	@Autowired
	public SmnshTestController(ProjectTestGenerator projectTestGenerator,
			ProjectService projectService, UserGenerator userGenerator, UserService userService,
			ModalityGenerator modalityGenerator, DevicePortfolioGenerator devicePortfolioGenerator,
			CompanyDemoGenerator companyDemoGenerator, CompanyService companyService) {
		this.projectTestGenerator = projectTestGenerator;
		this.projectService = projectService;
		this.userGenerator = userGenerator;
		this.userService = userService;
		this.modalityGenerator = modalityGenerator;
		this.devicePortfolioGenerator = devicePortfolioGenerator;
		this.companyDemoGenerator = companyDemoGenerator;
		this.companyService = companyService;
	}


	@GetMapping("/initData")
	public String initDemoData() {
		
		if (!isInitDataDone) {
			
			System.out.println("Data initialization to databse has started...");
			
			/*
			 * init. essential modality list
			 */
			modalityGenerator.createAndSaveToDbEssentialModalities();
			/*
			 * init. demo portfolio devices
			 */
			devicePortfolioGenerator.initDevicePortfolioListDemo();
			/*
			 * init. essential users
			 */
			List<User> userList = userGenerator.createAndGetEssentialUsers();
			for (User user : userList) {
				userService.save(user);
			}
			/*
			 * init demo companies
			 */
			List<Company> companyList = companyDemoGenerator.initDataAndReturn();
			for (Company company : companyList) {
				//companyService.saveAndReturn(company);
				companyService.save(company);
			}
			/*
			 * init. demo projects
			 */
			List<Project> projectList = projectTestGenerator.getDemoProjects();
			for (Project project : projectList) {
				projectService.save(project);
			}
			

			
			isInitDataDone = true;
			System.out.println("Data initialization to databse has been finished.");
		}
		/*
		 * return to page
		 */
		return "redirect:/smnsh/test/thymeleaf/projects";
	}
	
	
	@GetMapping("/thymeleaf/projects")
	public String homeThymeleaf(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		return "/test/projects/all";
	}
	
	/*
	 * TODO: create MVC/JSP functionality
	 */
	@GetMapping("/jsp/projects")
	public ModelAndView homeJsp(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		ModelAndView modelAndView = new ModelAndView("");
		return modelAndView;
	}
	
}
