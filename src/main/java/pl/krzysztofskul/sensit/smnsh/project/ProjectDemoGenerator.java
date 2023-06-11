package pl.krzysztofskul.sensit.smnsh.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.sensit.smnsh.company.CompanyService;
import pl.krzysztofskul.sensit.smnsh.init.InitDataGenerator;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneStatusEnum;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneTemplate;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneTemplateGenerator;
import pl.krzysztofskul.sensit.smnsh.project.remark.Remark;
import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;
import pl.krzysztofskul.sensit.smnsh.project.status.Status;
import pl.krzysztofskul.sensit.smnsh.user.User;
import pl.krzysztofskul.sensit.smnsh.user.UserService;

@Service
public class ProjectDemoGenerator implements InitDataGenerator<Project> {

	private ProjectRepo projectRepo;
	private UserService userService;
	private CompanyService companyService;
	private MilestoneTemplateGenerator milestoneTemplateGenerator;

	
	
	/**
	 * CONSTRUCTOR
	 * @param projectRepo
	 * @param userService
	 * @param companyService
	 * @param milestoneTemplateGenerator
	 */
	public ProjectDemoGenerator(ProjectRepo projectRepo, UserService userService, CompanyService companyService,
			MilestoneTemplateGenerator milestoneTemplateGenerator) {
		this.projectRepo = projectRepo;
		this.userService = userService;
		this.companyService = companyService;
		this.milestoneTemplateGenerator = milestoneTemplateGenerator;
	}



	/**
	 * Creates and return demo projects
	 * @return project list
	 */
	@Override
	public List<Project> initDataAndReturn() {
		List<Project> projectList = new ArrayList<Project>();
		
		LoremIpsum loremIpsum = LoremIpsum.getInstance();
		
		for (User userSalesRep : userService.loadAllSalesReps()) {
						
			for (User userPM : userService.loadAllProjectManagers()) {
				Project project = new Project();
				project.setName("Demo " + loremIpsum.getTitle(1));
				project.setCode("TES" + (new Random().nextInt(8999)+1000));
				
				project.setBackground(loremIpsum.getParagraphs(1, 2));
				project.setGoals(loremIpsum.getWords(5, 5));
				project.setRisks(loremIpsum.getWords(5, 10));
				
				project.setDateTimeOfCreation(LocalDateTime.now().minusMinutes(new Random().nextInt(60*24*7*4)));
				
				project.setSalesRep(userService.loadByEmail(userSalesRep.getEmail()));
				project.setInvestor(companyService.loadRandomInvestor());
				project.setProjectManager(userService.loadByEmail(userPM.getEmail()));				
				
				List<Stakeholder> stakeholders = new ArrayList<>();
				stakeholders.add(new Stakeholder(LoremIpsum.getInstance().getFirstName(), LoremIpsum.getInstance().getLastName(), "Dyrektor ds. technicznych", "dyrekcja@example.com", project));
				stakeholders.add(new Stakeholder(LoremIpsum.getInstance().getFirstName(), LoremIpsum.getInstance().getLastName(), "Dyrektor ds. administracyjnych", "administracja@example.com", project));
				stakeholders.add(new Stakeholder(LoremIpsum.getInstance().getFirstName(), LoremIpsum.getInstance().getLastName(), "Ordynator oddziału", "tel. +099 99 00 99", project));
				stakeholders.add(new Stakeholder(LoremIpsum.getInstance().getFirstName(), LoremIpsum.getInstance().getLastName(), "Pielęgniarka oddziałowa", "email: oddzialowa@example.com", project));
				stakeholders.add(new Stakeholder(LoremIpsum.getInstance().getFirstName(), LoremIpsum.getInstance().getLastName(), "Sekretariat", "sekretariat@example.com", project));
				project.setStakeholders(stakeholders);
				project.setDesigner("Krzysztof K.");
				project.setDeadline(LocalDate.now().plusWeeks(new Random().nextInt(12 * 4)).toString());				
				/*
				 * add milestones <String>
				 */
//				List<String> milestones = new ArrayList<String>();
//				milestones.add("Podpisanie Umowy");
//				milestones.add("Zlecenie wykonania koncepcji");
//				milestones.add("Akceptacja koncepcji");
//				milestones.add("Checklista");
//				milestones.add("Zlecenie wykonania wytycznych");
//				milestones.add("Przekazanie wytycznych");
//				milestones.add("Rozpoczęcie adaptacji pomiesczeń");
//				milestones.add("Zakończenie adaptacji pomiesczeń");
//				milestones.add("Dostawa");
//				milestones.add("Instalacja");
//				milestones.add("Szkolenia");
//				milestones.add("Odbiór");
				
				/*
				 * add milestones <MilestoneInstance> from template
				 */
				List<MilestoneTemplate> milestoneTemplateList = milestoneTemplateGenerator.initDataAndReturn();
				for (MilestoneTemplate milestoneTemplate : milestoneTemplateList) {
					switch (milestoneTemplate.getNameEn()) {
					case "Signing a contract": {
						project.addMilestoneFromTemplate(milestoneTemplate, project.getDateTimeOfCreation().toLocalDate(), MilestoneStatusEnum.COMPLETED);
						break;
					}
					case "Receipt": {
						project.addMilestoneFromTemplate(milestoneTemplate, LocalDate.parse(project.getDeadline()), MilestoneStatusEnum.WAITING);
						break;
					}
					

					default:
						project.addMilestoneFromTemplate(milestoneTemplate, LocalDate.parse(project.getDeadline()).minusDays(7), MilestoneStatusEnum.IN_PROGRESS);
					}
					
				}
				
				
				project.setContractNo("U-TES-2023-" + new Random().nextInt(99));
				project.setDevice("MAGNESO " + loremIpsum.getTitle(1));

				project.setStatus(Status.EXECUTION);
				
				project.addRemark(new Remark(project.getSalesRep(), project.getDateTimeOfCreation().plusSeconds(new Random().nextInt(60*60*24*7)), LoremIpsum.getInstance().getParagraphs(1, 1)));
				project.addRemark(new Remark(project.getProjectManager(), project.getDateTimeOfCreation().plusSeconds(new Random().nextInt(60*60*24*7)), LoremIpsum.getInstance().getWords(5, 10)));
				
				projectList.add(project);
			}
			
			
		}
		
		return projectList;
	}

	
}
