package pl.krzysztofskul.smnsh2.logger;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/smnsh2/logs")
public class LogController {

	private LoggerService loggerService;
	
	public LogController(LoggerService loggerService) {
		this.loggerService = loggerService;
	}

	@GetMapping
	public String getLogs(Model model) {
		List<Log> logs = loggerService.loadAll();
		model.addAttribute("logs", logs);
		return "smnsh2/logs";
	}
	
}
