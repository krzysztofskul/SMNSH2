package pl.krzysztofskul.smnsh2.project.milestone;

import java.util.ArrayList;
import java.util.List;

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
	
	public MilestoneInstance loadById(Long milestoneId) {
		return milestoneInstnceRepo.findById(milestoneId).get();
	}
	
	public List<MilestoneTemplate> loadAllMilestonesFromTemplates() {
		return milestoneTemplateRepo.findAll();
	}

	public void deleteMilestoneById(Long milestoneId) {
		milestoneInstnceRepo.deleteById(milestoneId);
		
	}
	
}
