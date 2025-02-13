package pl.krzysztofskul.smnsh2.project.stakeholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StakeholderService {

	private StakeholderRepo stakeholderRepo;

	/**
	 * @param stakeholderRepo
	 */
	@Autowired
	public StakeholderService(StakeholderRepo stakeholderRepo) {
		super();
		this.stakeholderRepo = stakeholderRepo;
	}
	
	public void save(Stakeholder stakeholder) {
		stakeholderRepo.save(stakeholder);
	}
	
	public Stakeholder loadById(Long id) {
		return stakeholderRepo.findById(id).get();
	}
	
	public void deleteById(Long id) {
		stakeholderRepo.deleteById(id);
	}
	
}
