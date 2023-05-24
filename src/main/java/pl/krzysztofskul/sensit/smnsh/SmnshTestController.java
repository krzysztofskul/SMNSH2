package pl.krzysztofskul.sensit.smnsh;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	private ProjectTestGenerator projectTestGenerator;
	private ProjectService projectService;
	private UserGenerator userGenerator;
	private UserService userService;
	private ModalityGenerator modalityGenerator;
	private DevicePortfolioGenerator devicePortfolioGenerator;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param projectTestGenerator
	 * @param projectService
	 * @param userGenerator
	 * @param userService
	 */
	public SmnshTestController(ProjectTestGenerator projectTestGenerator, ProjectService projectService,
			UserGenerator userGenerator, UserService userService, ModalityGenerator modalityGenerator, DevicePortfolioGenerator devicePortfolioGenerator) {
		this.projectTestGenerator = projectTestGenerator;
		this.projectService = projectService;
		this.userGenerator = userGenerator;
		this.userService = userService;
		this.modalityGenerator = modalityGenerator;
		this.devicePortfolioGenerator = devicePortfolioGenerator;
	}


	@GetMapping("/initData")
	public String initDemoData() {
		
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
		 * init. demo projects
		 */
		List<Project> projectList = projectTestGenerator.getDemoProjects();
		for (Project project : projectList) {
			projectService.save(project);
		}
		
		System.out.println("Data initialization to databse has been finished.");
		
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
