package pl.krzysztofskul.smnsh2;


import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.smnsh2.logger.Log;
import pl.krzysztofskul.smnsh2.logger.LogTypeEnum;
import pl.krzysztofskul.smnsh2.logger.LoggerService;
import pl.krzysztofskul.smnsh2.user.User;
import pl.krzysztofskul.smnsh2.user.UserService;

@Controller
public class Smnsh2ControllerErr implements ErrorController {
	
	private LoggerService loggerService;
	private UserService userService;
	
	
	@Autowired
	public Smnsh2ControllerErr(
			LoggerService loggerService,
			UserService userService
		) {
		this.loggerService = loggerService;
		this.userService = userService;
	}

	@GetMapping({"/home", "/"})
	public String home() {
		return "redirect:/smnsh2/home";
	}
	
	@GetMapping({"/smnsh2/home"})
	public String smnshHome() {
		Log log = new Log(userService.loadByUserSpringSecurityName(SecurityContextHolder.getContext().getAuthentication().getName()), null, LogTypeEnum.USER_VIEW_HOME_PAGE, LocalDateTime.now());
		loggerService.save(log);
		return "smnsh2/home";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("smnsh2/login");
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
		
		return "smnsh2/error";
	}

}
