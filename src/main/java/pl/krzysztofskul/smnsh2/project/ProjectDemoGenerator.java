package pl.krzysztofskul.smnsh2.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.smnsh2.company.CompanyService;
import pl.krzysztofskul.smnsh2.init.InitDataGenerator;
import pl.krzysztofskul.smnsh2.project.device.modality.DevicePortfolioGenerator;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneStatusEnum;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneTemplate;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneTemplateGenerator;
import pl.krzysztofskul.smnsh2.project.remark.Remark;
import pl.krzysztofskul.smnsh2.project.stakeholder.Stakeholder;
import pl.krzysztofskul.smnsh2.project.status.Status;
import pl.krzysztofskul.smnsh2.user.User;
import pl.krzysztofskul.smnsh2.user.UserService;

@Service
public class ProjectDemoGenerator implements InitDataGenerator<Project> {

	private ProjectRepo projectRepo;
	private UserService userService;
	private CompanyService companyService;
	private MilestoneTemplateGenerator milestoneTemplateGenerator;
	private DevicePortfolioGenerator devicePortfolioGenerator;

	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public ProjectDemoGenerator(ProjectRepo projectRepo, UserService userService, CompanyService companyService,
			MilestoneTemplateGenerator milestoneTemplateGenerator, DevicePortfolioGenerator devicePortfolioGenerator) {
		this.projectRepo = projectRepo;
		this.userService = userService;
		this.companyService = companyService;
		this.milestoneTemplateGenerator = milestoneTemplateGenerator;
		this.devicePortfolioGenerator = devicePortfolioGenerator;
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
				project.setCustomer(companyService.loadRandomCustomer());
				project.setSubcontractorForRoomAdaptation(companyService.loadRandomSubcontractorForRoomAdaptation());
				project.setProjectManager(userService.loadByEmail(userPM.getEmail()));		
				
				project.setInstallationPlaceDetails("New  building no. A; Floor 0; Room no. A010");
				
				List<Stakeholder> stakeholders = generateAndReturnDemoStakeholders(project);
				
				project.setStakeholders(stakeholders);
				project.setDesigner("Krzysztof K.");
				project.setDeadline(LocalDate.now().plusWeeks(new Random().nextInt(12 * 4)));				
				
				/*
				 * add milestones <MilestoneInstance> from template
				 */
				List<MilestoneTemplate> milestoneTemplateList = milestoneTemplateGenerator.initDataAndReturn();
				for (MilestoneTemplate milestoneTemplate : milestoneTemplateList) {
					switch (milestoneTemplate.getNameEn()) {
					case "Signing a contract": {
						project.addMilestoneFromTemplate(milestoneTemplate, null, MilestoneStatusEnum.IN_PROGRESS);
						break;
					}
					case "Receipt": {
						project.addMilestoneFromTemplate(milestoneTemplate, null, MilestoneStatusEnum.WAITING);
						break;
					}
					

					default:
						project.addMilestoneFromTemplate(milestoneTemplate, null, MilestoneStatusEnum.WAITING);
					}
					
				}
				
				
				project.setContractNo("U-TES-2023-" + new Random().nextInt(99));
				project.setDevicePortfolio(devicePortfolioGenerator.getRandomDevicePortfolio());

				project.setStatus(Status.EXECUTION);
				
				project.addRemark(new Remark(project.getSalesRep(), project.getDateTimeOfCreation().plusSeconds(new Random().nextInt(60*60*24*7)), LoremIpsum.getInstance().getParagraphs(1, 1)));
				project.addRemark(new Remark(project.getProjectManager(), project.getDateTimeOfCreation().plusSeconds(new Random().nextInt(60*60*24*7)), LoremIpsum.getInstance().getWords(5, 10)));
				
				projectList.add(project);
			}
			
			
		}
		
		return projectList;
	}

	private List<Stakeholder> generateAndReturnDemoStakeholders(Project project) {
		List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		Set<String> businessPostionSet = new HashSet<String>(Arrays.asList("Prezes zarządu", "Sekretarka zarządu", "Dyrektor techniczny", "Technik radiolog", "Lekarz radiolog"));
		for (String businessPosition : businessPostionSet) {
			Stakeholder newStakeholder = new Stakeholder();
			LoremIpsum loremIpsum = LoremIpsum.getInstance();
			newStakeholder.setProject(project);
			newStakeholder.setBusinessPosition(businessPosition);
			newStakeholder.setNameFirst(loremIpsum.getFirstName());
			newStakeholder.setNameLast(loremIpsum.getLastName());
			if (businessPosition.contains("zarządu")) {
				newStakeholder.setCompany(project.getInvestor());
			} else {
				newStakeholder.setCompany(project.getCustomer());
				
			}
			newStakeholder.setDescription(loremIpsum.getWords(5, 10));
			newStakeholder.setPhoneNumber(loremIpsum.getPhone());
			newStakeholder.setEmail(loremIpsum.getEmail());
			stakeholders.add(newStakeholder);

		}


		return stakeholders;
	}

	private Map<String, String> getDemoPhoneNumbersForStakeholder() {
		Map<String, String> demoPhoneNumbers = new HashMap<String, String>();
		demoPhoneNumbers.put("Tel. komórkowy służbowy", LoremIpsum.getInstance().getPhone());
		demoPhoneNumbers.put("Tel. stacjonarny służbowy", LoremIpsum.getInstance().getPhone());
		if (new Random().nextBoolean()) {
			demoPhoneNumbers.put("Tel. komórkowy prywatny", LoremIpsum.getInstance().getPhone());
		}
		return demoPhoneNumbers;
	}
	
	private Map<String, String> getDemoEmailsForStakeholder() {
		Map<String, String> demoEmails = new HashMap<String, String>();
		demoEmails.put("Email służbowy", LoremIpsum.getInstance().getEmail());
		if (new Random().nextBoolean()) {
			demoEmails.put("Emails prywatny", LoremIpsum.getInstance().getEmail());
		}
		return demoEmails;
	}

	
}
