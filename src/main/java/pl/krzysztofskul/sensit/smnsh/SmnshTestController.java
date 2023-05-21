package pl.krzysztofskul.sensit.smnsh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.project.ProjectTestGenerator;
import pl.krzysztofskul.sensit.smnsh.project.ProjectService;

@Controller
@RequestMapping("/smnsh/test")
public class SmnshTestController {

	private ProjectTestGenerator projectTestGenerator;
	private ProjectService projectService;
	
	/**
	 * @param projectTestGenerator
	 * @param projectService
	 */
	public SmnshTestController(ProjectTestGenerator projectTestGenerator, ProjectService projectService) {
		this.projectTestGenerator = projectTestGenerator;
		this.projectService = projectService;
	}


	@GetMapping("/initData")
	@ResponseBody
	public String initDemoData() {
		List<Project> projectList = projectTestGenerator.getDemoProjects();
		for (Project project : projectList) {
			projectService.save(project);
		}
		return "Test Data initializated!";
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
