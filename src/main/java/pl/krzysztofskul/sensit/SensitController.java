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
	public String home() {
		return "redirect:/smnsh/home";
	}
	
	@GetMapping({"/smnsh/home"})
	public String smnshHome() {
		System.out.println("Test controller /smnsh/home -> /test/home");
		return "/test/home";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String smnshTest() {
		System.out.println("Test controller /test");
		return "test sensit";
	}	
}
