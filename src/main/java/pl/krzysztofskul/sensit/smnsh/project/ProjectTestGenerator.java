package pl.krzysztofskul.sensit.smnsh.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;
import pl.krzysztofskul.sensit.smnsh.user.UserService;

@Service
public class ProjectTestGenerator {

	private ProjectRepo projectRepo;
	private UserService userService;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param projectRepo
	 * @param userService
	 */
	public ProjectTestGenerator(ProjectRepo projectRepo, UserService userService) {
		this.projectRepo = projectRepo;
		this.userService = userService;
	}

	/**
	 * Creates and return demo projects
	 * @return project list
	 */
	public List<Project> getDemoProjects() {
		List<Project> projectList = new ArrayList<Project>();
		
		LoremIpsum loremIpsum = LoremIpsum.getInstance();
		
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Demo project " + loremIpsum.getTitle(1));					
			project.setCode("TES000"+i);
			
			project.setSalesRep(userService.loadByEmail("wojciech.g@example.com"));
			
			project.setInvestor(loremIpsum.getTitle(1) + " Med-Investments sp. z o.o.");
			project.setLocation("09-199 Warszawa, ul. Testowa 99");
			project.setUser("Pracownia demonstracyjna w szpitalu klinicznym");
			
			project.setProjectManager("Piotr W.");
			
			List<Stakeholder> stakeholders = new ArrayList<>();
			stakeholders.add(new Stakeholder("Dyrektor ds. technicznych"));
			stakeholders.add(new Stakeholder("Dyrektor ds. administracyjnych"));
			stakeholders.add(new Stakeholder("Ordynator oddziału"));
			stakeholders.add(new Stakeholder("Pielęgniarka oddziałowa"));
			stakeholders.add(new Stakeholder("Skretariat"));
 			project.setStakeholders(stakeholders);
 			
 			project.setDesigner("Krzysztof K.");
 			
 			List<String> milestones = new ArrayList<String>();
 			milestones.add("Podpisanie Umowy");
 			milestones.add("Zlecenie wykonania koncepcji");
 			milestones.add("Akceptacja koncepcji");
 			milestones.add("Checklista");
 			milestones.add("Zlecenie wykonania wytycznych");
 			milestones.add("Przekazanie wytycznych"); 			
 			milestones.add("Rozpoczęcie adaptacji pomiesczeń");
 			milestones.add("Zakończenie adaptacji pomiesczeń");
 			milestones.add("Dostawa");
 			milestones.add("Instalacja");
 			milestones.add("Szkolenia");
 			milestones.add("Odbiór");
 			project.setMilestones(milestones);
 			
 			project.setContractNo("U-TST-2023-"+i);
 			project.setDevice("MAGNESO "+loremIpsum.getTitle(1));
 			
 			project.setDeadline(LocalDate.now().plusWeeks(new Random().nextInt(12*4)).toString());
 			
			projectList.add(project);
		}
		
		return projectList;
	}
	
}
