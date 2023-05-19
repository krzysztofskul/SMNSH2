package pl.krzysztofskul.sensit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Controller
@RequestMapping("/sensit")
public class SensitController {

	private SensitService sensitService;
	
	/**
	 * @param sensitService
	 */
	@Autowired
	public SensitController(SensitService sensitService) {
		this.sensitService = sensitService;
	}

	@GetMapping("/test")
	@ResponseBody
	public String sensitTest() {
		sensitService.save(new Project("test project name 1"));
		sensitService.save(new Project("test project name 2"));
		return sensitService.loadAll().toString();
	}
	
}
