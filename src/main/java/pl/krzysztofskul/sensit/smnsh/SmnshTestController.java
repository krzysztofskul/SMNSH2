package pl.krzysztofskul.sensit.smnsh;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.project.ProjectTestGenerator;
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

	/**
	 * CONSTRUCTOR
	 * 
	 * @param projectTestGenerator
	 * @param projectService
	 * @param userGenerator
	 * @param userService
	 */
	public SmnshTestController(ProjectTestGenerator projectTestGenerator, ProjectService projectService,
			UserGenerator userGenerator, UserService userService) {
		this.projectTestGenerator = projectTestGenerator;
		this.projectService = projectService;
		this.userGenerator = userGenerator;
		this.userService = userService;
	}


	@GetMapping("/initData")
	public String initDemoData() {
		
		List<User> userList = userGenerator.createAndGetEssentialUsers();
		for (User user : userList) {
			userService.save(user);
		}
		
		List<Project> projectList = projectTestGenerator.getDemoProjects();
		for (Project project : projectList) {
			projectService.save(project);
		}
		return "redirect:/smnsh/test/thymeleaf/projects";
	}
	
	
	@GetMapping("/thymeleaf/projects")
	public String homeThymeleaf(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		return "/test/projects";
	}
	
	/*
	 * TODO: create MVC/JSP functionality
	 */
	@GetMapping("/jsp/projects")
	public ModelAndView homeJsp(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		ModelAndView modelAndView = new ModelAndView("/test/projects");
		return modelAndView;
	}
	
}
