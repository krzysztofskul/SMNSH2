package pl.krzysztofskul.sensit;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/smnsh/login");
	}

}
