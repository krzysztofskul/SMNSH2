package pl.krzysztofskul.smnsh2.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.smnsh2.Smnsh2Controller;
import pl.krzysztofskul.smnsh2.company.Company;
import pl.krzysztofskul.smnsh2.company.CompanyService;
import pl.krzysztofskul.smnsh2.filestorage.File;
import pl.krzysztofskul.smnsh2.filestorage.FileStorageService;
import pl.krzysztofskul.smnsh2.importdata.FileSelector;
import pl.krzysztofskul.smnsh2.importdata.ImportData;
import pl.krzysztofskul.smnsh2.importdata.XlsCellReader;
import pl.krzysztofskul.smnsh2.logger.Log;
import pl.krzysztofskul.smnsh2.logger.LogTypeEnum;
import pl.krzysztofskul.smnsh2.logger.LoggerService;
import pl.krzysztofskul.smnsh2.project.attachment.Attachment;
import pl.krzysztofskul.smnsh2.project.attachment.AttachmentCategory;
import pl.krzysztofskul.smnsh2.project.attachment.AttachmentCategoryService;
import pl.krzysztofskul.smnsh2.project.device.DevicePortfolio;
import pl.krzysztofskul.smnsh2.project.device.modality.DevicePortfolioService;
import pl.krzysztofskul.smnsh2.project.installation.configuration.ConfigurationDevice;
import pl.krzysztofskul.smnsh2.project.installation.configuration.ConfigurationDeviceService;
import pl.krzysztofskul.smnsh2.project.installation.configuration.Part;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneInstance;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneService;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneTemplate;
import pl.krzysztofskul.smnsh2.project.milestone.MilestoneTemplateGenerator;
import pl.krzysztofskul.smnsh2.project.remark.Remark;
import pl.krzysztofskul.smnsh2.project.stakeholder.Stakeholder;
import pl.krzysztofskul.smnsh2.project.stakeholder.StakeholderService;
import pl.krzysztofskul.smnsh2.project.training.Training;
import pl.krzysztofskul.smnsh2.user.User;
import pl.krzysztofskul.smnsh2.user.UserBusinessPosition;
import pl.krzysztofskul.smnsh2.user.UserService;

@Controller
@RequestMapping("/smnsh2")
public class ProjectController {

	private ProjectService projectService;
	private AttachmentCategoryService attachmentCategoryService;
	private CompanyService companyService;
	private UserService userService;
	private DevicePortfolioService devicePortfolioService;
	private FileSelector fileSelector;
	private MilestoneService milestoneService;
	private ConfigurationDeviceService configurationDeviceService;
	private LoggerService loggerService;
	private StakeholderService stakeholderService;
	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public ProjectController(
			ProjectService projectService,
			AttachmentCategoryService attachmentCategoryService,
			CompanyService companyService,
			UserService userService,
			DevicePortfolioService devicePortfolioService,
			FileSelector fileSelector,
			MilestoneService milestoneService,
			ConfigurationDeviceService configurationDeviceService,
			LoggerService loggerService,
			StakeholderService stakeholderService
			) {
		this.projectService = projectService;
		this.attachmentCategoryService = attachmentCategoryService;
		this.companyService = companyService;
		this.userService = userService;
		this.devicePortfolioService = devicePortfolioService;
		this.fileSelector = fileSelector;
		this.milestoneService = milestoneService;
		this.configurationDeviceService = configurationDeviceService;
		this.loggerService = loggerService;
		this.stakeholderService = stakeholderService;
	}
	
	@ModelAttribute(name = "investorList")
	public List<Company> getInvestorListAll() {
		return companyService.loadAllInvestors();
	}
	@ModelAttribute(name = "customerList")
	public List<Company> getCustomerListAll() {
		return companyService.loadAllCustomers();
	}
	@ModelAttribute(name = "subcontractorList")
	public List<Company> getSubcontractorListAll() {
		return companyService.loadAllSubcontractors();
	}
	@ModelAttribute(name = "devicePortfolioListAll")
	public List<DevicePortfolio> getDevicePortfolioListAll() {
		return devicePortfolioService.loadAll();
	}
	@ModelAttribute(name = "salesRepListAll")
	public List<User> getSalesRepListAll() {
		return userService.loadAllSalesReps();
	}
	@ModelAttribute(name = "projectManagerListAll")
	public List<User> getProjectManagerListAll() {
		return userService.loadAllProjectManagers();
	}
	
	@GetMapping("/projects")
	public String getTestProjects(
				@RequestParam(required = false) String sort,
				Model model
			) {
		
		List<Project> projectList = projectService.loadAll();
		
		projectList = sortProjectList(sort, projectList); 
		
		model.addAttribute("projectList", projectList);
		loggerService.save(new Log(userService.loadByUserSpringSecurityName(SecurityContextHolder.getContext().getAuthentication().getName()), null, LogTypeEnum.USER_VIEW_PROJECTS_PAGE, LocalDateTime.now()));
		return "smnsh2/projects/all";
	}
	
	@GetMapping("/projects/edit")
	public String getNewProject(
				Model model,
				@RequestParam(required = false) Long userId,
				@RequestParam(required = true) Long projectId,
				@RequestParam(required = false) String userNameBySpringSecurity
			) {
		Project project;
		if (projectId == 0) {
			project = new Project();	
			project.setDateTimeOfCreation(LocalDateTime.now());
			project.setDeadline(project.getDateTimeOfCreation().plusMonths(3).toLocalDate());
			
			if (userNameBySpringSecurity != null) {
				User user = userService.loadByUserSpringSecurityName(userNameBySpringSecurity);
				if (user.getBusinessPosition() == UserBusinessPosition.PROJECT_MANAGER) {
					project.setProjectManager(user);
				}
			}
		} else {
			project = projectService.loadById(projectId);
		}
		
		model.addAttribute("project", project);
		model.addAttribute("edit", true);
		return "smnsh2/projects/idDetails";
	}
	
	@PostMapping("/projects/save")
	public String postProjectsNew(
				@RequestParam(required = false) String backToPage,
				@ModelAttribute("project") @Validated Project project, BindingResult result,
				HttpSession httpSession
			) {
		
		if (project.getId() == 0) {
			List<MilestoneInstance> milestoneInstanceList = new ArrayList<MilestoneInstance>();
			for (MilestoneTemplate milestoneTemplate : milestoneService.loadAllMilestonesFromTemplates()) {
				milestoneInstanceList.add(new MilestoneInstance(milestoneTemplate));
			}
			project.setMilestones(milestoneInstanceList);
		}
		
		project = projectService.saveAndReturn(project);
		return "redirect:/smnsh2/projects/"+project.getId();
	}
	
	@GetMapping("/projects/{id}")
	public String getProjectById(@PathVariable Long id, Model model) {
		Project project = projectService.loadById(id);
		model.addAttribute("project", project);
		loggerService.save(new Log(userService.loadByUserSpringSecurityName(SecurityContextHolder.getContext().getAuthentication().getName()), project, LogTypeEnum.USER_VIEW_PROJECT_PAGE, LocalDateTime.now()));
		return "smnsh2/projects/idDetails";
	}
	
	@GetMapping("/projects/{id}/milestones")
	public String getProjectByIdWithMilestones(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithMilestones(id));
		return "smnsh2/projects/idDetailsAndMilestones";
	}
	
	@GetMapping("/projects/{id}/configurations")
	public String getProjectByIdWithConfigurations(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithAttachments(id));
		return "smnsh2/projects/idDetailsAndConfigurations";
	}

	@GetMapping("/projects/{id}/configurations/new")
	public String getProjectByIdWithConfigurationsNew(@PathVariable Long id) {
		String filePath = fileSelector.select("file");
		Project project = projectService.loadByIdWithAttachments(id);
		project = projectService.addLinkToConfigurationFile(project, filePath);
		project = projectService.saveAndReturn(project);
		return "redirect:/smnsh2/projects/"+id+"/configurations";
	}
	
	@GetMapping("/projects/{projectId}/configurations/delete")
	public String deleteConfigurationLinkById(
				@PathVariable Long projectId,
				@RequestParam(required = false) String configurationLink,
				@RequestParam Long configurationDeviceId
			) {
		Project project = projectService.loadById(projectId);
		if (configurationLink != null) {
			project = projectService.removeLinkToConfigurationFile(project, configurationLink);
			projectService.save(project);
		}
		projectService.removeConfigurationDevice(project, configurationDeviceService.loadById(configurationDeviceId));

		
		return "redirect:/smnsh2/projects/"+projectId+"/configurations";
	}
	
	@PostMapping("/projects/{id}/configurationLinks")
	public String postConfigurationLinks(
			@PathVariable(name="id") Long projectId,
			@RequestParam(name = "filePath", required = true) String filePath,
			@RequestParam(name = "fileName", required = false) String fileName,
			@RequestParam(name = "multipartFile", required = false) MultipartFile multipartFile,
			Model model
		) throws FileNotFoundException, IOException {
		
		java.io.File file = new java.io.File("src/main/resources/targetFile.tmp");

		try (OutputStream os = new FileOutputStream(file)) {
		    os.write(multipartFile.getBytes());
		}
		
		ImportData.getImportDataSingleton();
		ImportData.setFileInputStream(new FileInputStream(file));
		ImportData.setWorkbook(new XSSFWorkbook(ImportData.getFileInputStream()));
		
		Project project = projectService.loadById(projectId);
		
		/*
		 * import configurations
		 */
		List<ConfigurationDevice> configurationDeviceList = ImportData.getImportDataSingleton().importConfigurationFromXls(filePath);
		StringBuilder sb = new StringBuilder();
		String configurationFilePath = sb.append(filePath+"\\"+multipartFile.getOriginalFilename()).toString();
		for (ConfigurationDevice configurationDevice : configurationDeviceList) {
			configurationDevice.setLinkToHdd(configurationFilePath);
			project.getInstallation().getDeviceInstance().addConfigurationDevice(configurationDevice);	
		}
		
		
		//String slsConfigurationString = ImportData.getImportDataSingleton().getCellsValuesInRow(file.getAbsolutePath(), new String[]{"SCON-1-2", "3", "1"}, true);
		//List<String> slsConfigurationList = Arrays.asList(slsConfigurationString.split(";"));
		//List<Part> partList = new ArrayList<Part>();
		//for (String slsConfiguration : slsConfigurationList) {
		//	partList.add(new Part(project.getInstallation().getDeviceInstance().getConfigurationDevice(), slsConfiguration));
		//}
				
		/*
		 * import trainings
		 */
		//String slsTrainings = ImportData.getImportDataSingleton().getCellsValuesInRow(file.getAbsolutePath(), new String[]{"Szkolenia", "9", "2"}, false);
		XlsCellReader xlsCellReader = XlsCellReader.getXlsCellReader(configurationFilePath);
		List<String> trainingList = xlsCellReader.getCellsValuesInRow(configurationFilePath, "Szkolenia", 9, 2, true);
		for (String training : trainingList) {
			project.getInstallation().getDeviceInstance().addTraining(new Training(training));
		}
		
		file.delete();
		projectService.save(project);
		return "redirect:/smnsh2/projects/"+projectId+"/configurations";
	}
	
	@GetMapping("/projects/{id}/stakeholders")
	public String getProjectByIdWithStakeholders(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithStakeholders(id));
		return "smnsh2/projects/idDetailsAndStakeholders";
	}
	
	@GetMapping("/projects/{id}/stakeholders/add")
	public String getAddStakeholderForm(
				@PathVariable Long id,
				Model model
			) {
		Stakeholder stakeholder = new Stakeholder(projectService.loadById(id));
		Project project = projectService.loadByIdWithStakeholders(id);
		stakeholder.setCompany(project.getInvestor());
		model.addAttribute("project", project);
		model.addAttribute("stakeholder", stakeholder);
		model.addAttribute("companies", companyService.loadAllSubcontrsctorsForRoomAdaptation());
		return "smnsh2/projects/forms/addStakeholderForm";
	}
	
	@PostMapping("/projects/{id}/stakeholders/save")
	public String postAddStakeholderForm(
				@PathVariable Long id,
				Stakeholder stakeholder,
				Model model
			) {

		if (stakeholder.getId() != null && stakeholder.getId() != 0) {
			stakeholderService.save(stakeholder);
		}
		
		Project project = projectService.loadByIdWithStakeholders(id);
		project.addStakeholder(stakeholder);
		project = projectService.saveAndReturn(project);
		
		model.addAttribute("project", project);
		return "redirect:/smnsh2/projects/"+project.getId()+"/stakeholders";
	}
	
	/*
	 * TODO 2023-06-26 NEW REMARK FUNCTIONALITY
	 */
	@GetMapping("/projects/{id}/remarks")
	public String getProjectByIdWithRemarks(
			@PathVariable Long id, 
			@RequestParam(name = "edit", required = false) boolean edit,
			@RequestParam(name = "author", required = false) String author,
			Model model
		) {
		model.addAttribute("project", projectService.loadByIdWithRemarks(id));
		if (edit == true) {
			User user = userService.loadByUserSpringSecurityName(author);
			Remark newRemark = new Remark(user, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "...");
			model.addAttribute("newRemark", newRemark);
		}
		return "smnsh2/projects/idDetailsAndRemarks";
	}
	@PostMapping("/projects/{id}/remarks")
	public String postProjectByIdWithRemarks(
			@PathVariable("id") Long projectId,
			@ModelAttribute("newRemark") @Validated Remark newRemark
			) {
		Project project = projectService.loadByIdWithRemarks(projectId);
		project.addRemark(newRemark);
		projectService.save(project);
		return "redirect:/smnsh2/projects/"+projectId+"/remarks";
	}
	
	@GetMapping("/projects/{id}/attachments")
	public String getProjectByIdWithAttachments(
			@PathVariable Long id,
			Model model
			) {
		model.addAttribute("project", projectService.loadByIdWithAttachments(id));
		return "smnsh2/projects/idDetailsAndAttachments";
	}
	
	@PostMapping("/projects/{id}/attachments")
	public String handleFileUpload(
				@PathVariable(name = "id") Long projectId,
				@RequestParam(name = "category") String attachmentCategory,
				@RequestParam("multipartFile") MultipartFile multipartFile,
				RedirectAttributes redirectAttributes
			) throws IOException {

		if (StringUtils.cleanPath(multipartFile.getOriginalFilename()) == null || StringUtils.cleanPath(multipartFile.getOriginalFilename()).length() == 0) {
			return "redirect:/smnsh2/projects/"+projectId+"/attachments";
		}
		
		Attachment attachment = new Attachment();
		switch (attachmentCategory) {
		case "contract":
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("DOC-CONTRACT"));
			break;
		case "offer":
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("DOC-OFFER"));
			break;
		case "project":
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("PROJECT"));
			break;
		case "order":
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("DOC-ORDER"));
			break;
		case "invoice":
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("DOC-INVOICE"));
			break;
		case "protocol":
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("DOC-PROTOCOL-ACCEPT"));
			break;

		default:
			attachment.setAttachmentCategory(attachmentCategoryService.loadByCode("DOC-GENERAL"));
			break;
		}
		
        File fileToStore = new File();
        fileToStore.setFileName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        fileToStore.setFileType(multipartFile.getContentType());
        fileToStore.setData(multipartFile.getBytes());
		attachment.setFile(fileToStore);
		
		Project project = projectService.loadById(projectId);
		project.addAttachment(attachment);
		projectService.save(project);
		

		return "redirect:/smnsh2/projects/"+projectId+"/attachments";
	}
	
	@GetMapping("/projects/user/springsecurityname/{userNameBySpringSecurity}")
	public String getProjectsByUserName(
				@PathVariable String userNameBySpringSecurity
			) {
		String userNameFirst = userNameBySpringSecurity.split("_")[0];
		String userNameLast = userNameBySpringSecurity.split("_")[1];
		User user = userService.loadByUserNames(userNameFirst, userNameLast);
		String userId = user.getId().toString();
		return "redirect:/smnsh2/projects/user/"+userId;
	}
	
	@GetMapping("/projects/user/{userId}")
	public String getProjectsByUserId(
				@PathVariable(name = "userId") String userId,
				@RequestParam(required = false) String sort,
				Model model
			) {
		List<Project> projects = projectService.loadProjectsByUserId(Long.parseLong(userId) );
		
		projects = sortProjectList(sort, projects); 
		
		model.addAttribute("projectList", projects);
		loggerService.save(new Log(userService.loadByUserSpringSecurityName(SecurityContextHolder.getContext().getAuthentication().getName()), null, LogTypeEnum.USER_VIEW_PROJECTS_PAGE, LocalDateTime.now()));
		return "smnsh2/projects/all";
	}
	
	@GetMapping("/projects/delete/{projectId}")
	public String getDeleteProjectById(
				@PathVariable Long projectId,
				@RequestParam(required = false) String backToPage
			) {
		
		projectService.deleteById(projectId);
		
		if (backToPage != null) {
			return "redirect:"+backToPage;
		}
		
		return "redirect:/smnsh2/projects";
	}
	
	/**
	 * Sort list of projects ascending or descending depends of the sort parameter
	 * @param sort
	 * @param projectList
	 * @return projectList
	 */
	private List<Project> sortProjectList(String sort, List<Project> projectList) {
		if (sort != null) {
			if (sort.equals("byCodeAsc") ) {
				projectList.sort((p1, p2) -> p1.getCode().compareTo(p2.getCode()));
			}
			if (sort.equals("byCodeDes")) {
				projectList.sort((p1, p2) -> p2.getCode().compareTo(p1.getCode()));
			}
			if (sort.equals("deadlineAsc") ) {
				projectList.sort((p1, p2) -> p1.getDeadline().compareTo(p2.getDeadline()));
			}
			if (sort.equals("deadlineDes")) {
				projectList.sort((p1, p2) -> p2.getDeadline().compareTo(p1.getDeadline()));
			}
		}
		return projectList;
	}
	
}
