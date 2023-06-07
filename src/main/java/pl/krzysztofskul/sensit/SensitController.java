package pl.krzysztofskul.sensit;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensitController {

	@GetMapping({"/home", "/"})
	public String home() {
		return "redirect:/smnsh/home";
	}
	
	@GetMapping({"/smnsh/home"})
	public String smnshHome() {
		return "smnsh/home";
	}

}
