package pl.krzysztofskul.sensit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sensit")
public class SensitController {

	@GetMapping("/test")
	@ResponseBody
	public String sensitTest() {
		return "sensit test page";
	}
	
}
