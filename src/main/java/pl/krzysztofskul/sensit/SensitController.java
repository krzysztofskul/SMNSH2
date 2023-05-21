package pl.krzysztofskul.sensit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.krzysztofskul.sensit.smnsh.project.ProjectTestGenerator;
import pl.krzysztofskul.sensit.smnsh.project.ProjectService;

@Controller
public class SensitController {

	@GetMapping({"/home", "/"})
	@ResponseBody
	public String home() {
		return "sensit home page";
	}

}
