package pl.krzysztofskul.sensit;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SensitController implements ErrorController {

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
		return new ModelAndView("smnsh/login");
	}
	
	@GetMapping("/error")
	public String error(
			HttpServletRequest request,
			Model model
			) {
		
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            model.addAttribute("errorCode", "404");
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	model.addAttribute("errorCode", "500");
	        } else {
	        	model.addAttribute("errorCode", "NIEZNANY");
	        }
	    }
		
		return "smnsh/error";
	}

}
