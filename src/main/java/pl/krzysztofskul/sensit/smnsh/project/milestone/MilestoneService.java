package pl.krzysztofskul.sensit.smnsh.project.milestone;

import org.springframework.stereotype.Service;

@Service
public class MilestoneService {

	private MilestoneTemplateRepo milestoneTemplateRepo;
	private MilestoneInstanceRepo milestoneInstnceRepo;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param milestoneTemplateRepo
	 * @param milestoneInstnceRepo
	 */
	public MilestoneService(MilestoneTemplateRepo milestoneTemplateRepo, MilestoneInstanceRepo milestoneInstnceRepo) {
		this.milestoneTemplateRepo = milestoneTemplateRepo;
		this.milestoneInstnceRepo = milestoneInstnceRepo;
	}
	
	public void saveMilestoneTemplate(MilestoneTemplate milestoneTemplate) {
		milestoneTemplateRepo.save(milestoneTemplate);
	}
	
	public void saveMilestoneInstance(MilestoneInstance milestoneInstance) {
		milestoneInstnceRepo.save(milestoneInstance);
	}
	
}
