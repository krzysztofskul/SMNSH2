package pl.krzysztofskul.smnsh2.project.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krzysztofskul.smnsh2.project.Project;

@Controller
@RequestMapping("/smnsh2/training")
public class TrainingController {

	private TrainingService trainingService;
	
	/**
	 * @param trainingService
	 */
	@Autowired
	public TrainingController(TrainingService trainingService) {
		super();
		this.trainingService = trainingService;
	}

	@PostMapping("/{id}")
	public String postTrainingById(
				@PathVariable Long id,
				@ModelAttribute Training training,
				Model model
			) {
		Project project = training.getProject();
		training = trainingService.saveAndReturn(training);
		model.addAttribute("edite", "false");
		return "redirect:/smnsh2/projects/"+project.getId()+"/training/"+training.getId();
	}
	
	@GetMapping("/{id}/delete")
	public String deleteTrainingById(@PathVariable Long id) {
		Long projectId = trainingService.loadById(id).getProject().getId();
		trainingService.deleteById(id);
		return "redirect:/smnsh2/projects/"+projectId+"/training";
	}
	
}
