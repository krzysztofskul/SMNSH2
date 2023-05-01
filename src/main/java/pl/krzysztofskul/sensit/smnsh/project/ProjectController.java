package pl.krzysztofskul.sensit.smnsh.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

@Controller
public class ProjectController {

	private ProjectService projectService;
	
	
	
	/**
	 * @param projectService
	 */
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}



	@GetMapping("/test/projects")
	@ResponseBody
	public String getTestProjects() {
		projectService.save(new Project(LoremIpsum.getInstance().getName()));		
		return projectService.loadAll().toString();
	}
	
}
