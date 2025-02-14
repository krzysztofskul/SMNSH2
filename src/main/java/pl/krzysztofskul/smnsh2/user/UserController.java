package pl.krzysztofskul.smnsh2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/smnsh2/admin")
public class UserController {

	private UserService userService;
	private UserGenerator userGenerator;

	/**
	 * @param userService
	 */
	@Autowired
	public UserController(UserService userService, UserGenerator userGenerator) {
		super();
		this.userService = userService;
		this.userGenerator = userGenerator;
	}
	
	@GetMapping("/new-user-guest")
	public String getUserNew(
				Model model
			) {
		User user = userGenerator.createUserGuest();
		if (userService.loadByUserSpringSecurityName(user.getNameFirst()+"_"+user.getNameLast()) == null) {
			userService.save(user);
		}
		return "redirect:/";
	}
	
}
