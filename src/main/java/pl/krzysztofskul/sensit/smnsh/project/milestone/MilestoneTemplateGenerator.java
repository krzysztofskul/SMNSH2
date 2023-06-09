package pl.krzysztofskul.sensit.smnsh.project.milestone;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.init.InitDataGenerator;

@Service
public class MilestoneTemplateGenerator implements InitDataGenerator<MilestoneTemplate> {

	private List<MilestoneTemplate> milestoneTemplateList = new ArrayList<MilestoneTemplate>();
	
	private MilestoneTemplateRepo milestoneTemplateRepo;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param milestoneTemplateRepo
	 */
	@Autowired
	public MilestoneTemplateGenerator(MilestoneTemplateRepo milestoneTemplateRepo) {
		this.milestoneTemplateRepo = milestoneTemplateRepo;
	}



	@Override
	public List<MilestoneTemplate> initDataAndReturn() {
		
		if (milestoneTemplateList.size() == 0) {
			milestoneTemplateList.add(new MilestoneTemplate("Podpisanie umowy", "Signing a contract", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Zlecenie wykonania projektu koncepcyjnego",
					"Order the preliminary design", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Przekazanie projektu koncepcyjnego",
					"Send the preliminary design for apporval", ""));
			milestoneTemplateList
					.add(new MilestoneTemplate("Akceptacja projektu koncepcyjnego", "Preliminary design approved", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Wypełnienie checklist'y", "Fill in the checklist", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Zlecenie wykonania projektu wytycznych instalacyjnych",
					"Order the final planning design", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Przekazanie projektu wytycznych instalacyjnych klientowi",
					"Send the final planning design to the client", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Dostawa", "Delivery", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Instalacja", "Instalation", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Szkolenia", "Trainings", ""));
			milestoneTemplateList.add(new MilestoneTemplate("Odbiór", "Reception", ""));
			for (MilestoneTemplate milestoneTemplate : milestoneTemplateList) {
				milestoneTemplateRepo.save(milestoneTemplate);
			} 
		}
		return milestoneTemplateList;
	}

	
	
}
