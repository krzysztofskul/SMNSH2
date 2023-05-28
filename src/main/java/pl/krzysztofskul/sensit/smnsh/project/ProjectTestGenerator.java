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
import pl.krzysztofskul.sensit.smnsh.project.status.Status;
import pl.krzysztofskul.sensit.smnsh.user.User;
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
		
		for (User userSalesRep : userService.loadAllSalesReps()) {
						
			for (User userPM : userService.loadAllProjectManagers()) {
				Project project = new Project();
				project.setName("Projekt demo/test " + loremIpsum.getTitle(1));
				project.setCode("TES" + (new Random().nextInt(8999)+1000));
				
				project.setBackground(loremIpsum.getParagraphs(1, 2));
				project.setGoals(loremIpsum.getWords(5, 5));
				project.setRisks(loremIpsum.getWords(5, 10));
				
				project.setSalesRep(userService.loadByEmail(userSalesRep.getEmail()));
				project.setInvestor(loremIpsum.getTitle(1) + " Med Investments sp. z o.o.");
				project.setLocation("09-199 Warszawa, ul. Testowa 99");
				project.setUser("Pracownia demonstracyjna w szpitalu klinicznym");				
				project.setProjectManager(userService.loadByEmail(userPM.getEmail()));				
				List<Stakeholder> stakeholders = new ArrayList<>();
				stakeholders.add(new Stakeholder("Dyrektor ds. technicznych", project));
				stakeholders.add(new Stakeholder("Dyrektor ds. administracyjnych", project));
				stakeholders.add(new Stakeholder("Ordynator oddziału", project));
				stakeholders.add(new Stakeholder("Pielęgniarka oddziałowa", project));
				stakeholders.add(new Stakeholder("Sekretariat", project));
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
				project.setContractNo("U-TES-2023-" + new Random().nextInt(99));
				project.setDevice("MAGNESO " + loremIpsum.getTitle(1));
				project.setDeadline(LocalDate.now().plusWeeks(new Random().nextInt(12 * 4)).toString());
				project.setStatus(Status.EXECUTION);
				projectList.add(project);
			}
			
			
		}
		
		return projectList;
	}
	
}
