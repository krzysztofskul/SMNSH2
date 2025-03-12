package pl.krzysztofskul.smnsh2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krzysztofskul.smnsh2.Smnsh2Controller;

@Controller
@RequestMapping("/smnsh2")
public class UserController {

	private UserService userService;
	private UserGenerator userGenerator;
	private Smnsh2Controller smnsh2Controller;

	/**
	 * @param userService
	 */
	@Autowired
	public UserController(
			UserService userService
			, UserGenerator userGenerator
			, Smnsh2Controller smnsh2Controller
			) {
		super();
		this.userService = userService;
		this.userGenerator = userGenerator;
		this.smnsh2Controller = smnsh2Controller;
	}
	
	@GetMapping("/admin")
	public String getAdmin(
				Model model
			) {
		model.addAttribute("isInitDataEssentialsDone", smnsh2Controller.isInitDataEssentialsDone());
		model.addAttribute("isInitDataDemoDone", smnsh2Controller.isInitDataDemoDone());
		return "smnsh2/users/admin";
	}
	
	@GetMapping("/admin/new-user-guest")
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
