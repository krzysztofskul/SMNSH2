package pl.krzysztofskul.smnsh2.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.krzysztofskul.smnsh2.project.status.Status;

@RestController
@RequestMapping("/smnsh2/restapi")
public class ProjectControllerRest {

	private ProjectService projectService;

	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public ProjectControllerRest(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@PutMapping("/projects/{id}")
	public void testPost(@PathVariable Long id, @RequestParam(name = "btn-status") String status) {
		Project project = projectService.loadById(id);
		
		switch (status) {
			case "btn-undefined":{
				project.changeStatusTo(Status.UNDEFINED);
				break;
			}
			case "btn-precontract":{
				project.changeStatusTo(Status.PRECONTRACT);
				break;
			}
			case "btn-contract":{
				project.changeStatusTo(Status.CONTRACT);
				break;
			}
			case "btn-execution":{
				project.changeStatusTo(Status.EXECUTION);
				break;
			}
			case "btn-completed":{
				project.changeStatusTo(Status.COMPLETED);
				break;
			}
			case "btn-nobid":{
				project.changeStatusTo(Status.NOBID);
				break;
			}
			default:
				project.changeStatusTo(Status.UNDEFINED);
				break;
		}
		
		project = projectService.saveAndReturn(project);
	}
	
}
