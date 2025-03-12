package pl.krzysztofskul.smnsh2.project.training;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

	private TrainingRepo trainingRepo;

	/**
	 * @param trainingRepo
	 */
	@Autowired
	public TrainingService(TrainingRepo trainingRepo) {
		super();
		this.trainingRepo = trainingRepo;
	}
	
	public void save(Training training) {
		trainingRepo.save(training);
	}
	
	public Training saveAndReturn(Training training) {
		return trainingRepo.save(training);
	}
	
	public List<Training> loadAllByProjectId(Long projectId) {
		return trainingRepo.findAllByProjectId(projectId);
	}
	
	public Training loadById(Long trainingId) {
		return trainingRepo.findById(trainingId).get();
	}

	public void deleteById(Long id) {
		trainingRepo.deleteById(id);
	}
	
}
