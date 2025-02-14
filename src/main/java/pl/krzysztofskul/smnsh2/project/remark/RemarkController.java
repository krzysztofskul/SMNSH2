package pl.krzysztofskul.smnsh2.project.remark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RemarkController {

	private RemarkService remarkService;

	/**
	 * @param remarkService
	 */
	public RemarkController(RemarkService remarkService) {
		super();
		this.remarkService = remarkService;
	}
	
	@GetMapping("/remarks/delete/{remarkId}")
	public String deleteRemarkById(
				@PathVariable Long remarkId,
				@RequestParam(required = false) String backToPage
			) {
		remarkService.deleteById(remarkId);
		return "redirect:"+backToPage;
	}
	
}
