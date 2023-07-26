package pl.krzysztofskul.sensit.smnsh.project;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import pl.krzysztofskul.sensit.smnsh.company.Company;
import pl.krzysztofskul.sensit.smnsh.company.CompanyService;
import pl.krzysztofskul.sensit.smnsh.filestorage.File;
import pl.krzysztofskul.sensit.smnsh.filestorage.FileStorageService;
import pl.krzysztofskul.sensit.smnsh.project.attachment.Attachment;
import pl.krzysztofskul.sensit.smnsh.project.attachment.AttachmentCategory;
import pl.krzysztofskul.sensit.smnsh.project.attachment.AttachmentCategoryService;
import pl.krzysztofskul.sensit.smnsh.project.device.DevicePortfolio;
import pl.krzysztofskul.sensit.smnsh.project.device.modality.DevicePortfolioService;
import pl.krzysztofskul.sensit.smnsh.project.remark.Remark;
import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;
import pl.krzysztofskul.sensit.smnsh.user.User;
import pl.krzysztofskul.sensit.smnsh.user.UserBusinessPosition;
import pl.krzysztofskul.sensit.smnsh.user.UserService;

@Controller
@RequestMapping("/smnsh")
public class ProjectController {

	private ProjectService projectService;
	private AttachmentCategoryService attachmentCategoryService;
	private CompanyService companyService;
	private UserService userService;
	private DevicePortfolioService devicePortfolioService;
	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public ProjectController(
			ProjectService projectService,
			AttachmentCategoryService attachmentCategoryService,
			CompanyService companyService,
			UserService userService,
			DevicePortfolioService devicePortfolioService
			) {
		this.projectService = projectService;
		this.attachmentCategoryService = attachmentCategoryService;
		this.companyService = companyService;
		this.userService = userService;
		this.devicePortfolioService = devicePortfolioService;
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
	public String getTestProjects() {		
		return "smnsh/projects/all";
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
		return "smnsh/projects/idDetails";
	}
	
	@PostMapping("/projects/save")
	public String postProjectsNew(
				@RequestParam(required = false) String backToPage,
				@ModelAttribute("project") @Validated Project project, BindingResult result,
				HttpSession httpSession
			) {
		
		project = projectService.saveAndReturn(project);
		return "redirect:/smnsh/projects/"+project.getId();
	}
	
	@GetMapping("/projects/{id}")
	public String getProjectById(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadById(id));
		return "smnsh/projects/idDetails";
	}
	
	@GetMapping("/projects/{id}/milestones")
	public String getProjectByIdWithMilestones(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithMilestones(id));
		return "smnsh/projects/idDetailsAndMilestones";
	}
	
	@GetMapping("/projects/{id}/stakeholders")
	public String getProjectByIdWithStakeholders(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithStakeholders(id));
		return "smnsh/projects/idDetailsAndStakeholders";
	}
	
	@GetMapping("/projects/{id}/stakeholders/add")
	public String getAddStakeholderForm(
				@PathVariable Long id,
				Model model
			) {
		Stakeholder newStakeholder = new Stakeholder(projectService.loadById(id));
		Project project = projectService.loadByIdWithStakeholders(id);
		newStakeholder.setCompany(project.getInvestor());
		model.addAttribute("project", project);
		model.addAttribute("newStakeholder", newStakeholder);
		model.addAttribute("companies", companyService.loadAllSubcontrsctorsForRoomAdaptation());
		return "smnsh/projects/forms/addStakeholderForm";
	}
	
	@PostMapping("/projects/{id}/stakeholders/add")
	public String postAddStakeholderForm(
				@PathVariable Long id,
				Stakeholder newStakeholder,
				Model model
			) {

		Project project = projectService.loadByIdWithStakeholders(id);
		project.addStakeholder(newStakeholder);
		project = projectService.saveAndReturn(project);
		
		model.addAttribute("project", project);
		return "smnsh/projects/idDetailsAndStakeholders";
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
		return "smnsh/projects/idDetailsAndRemarks";
	}
	@PostMapping("/projects/{id}/remarks")
	public String postProjectByIdWithRemarks(
			@PathVariable("id") Long projectId,
			@ModelAttribute("newRemark") @Validated Remark newRemark
			) {
		Project project = projectService.loadByIdWithRemarks(projectId);
		project.addRemark(newRemark);
		projectService.save(project);
		return "redirect:/smnsh/projects/"+projectId+"/remarks";
	}
	
	@GetMapping("/projects/{id}/attachments")
	public String getProjectByIdWithAttachments(
			@PathVariable Long id,
			Model model
			) {
		model.addAttribute("project", projectService.loadByIdWithAttachments(id));
		return "smnsh/projects/idDetailsAndAttachments";
	}
	
	@PostMapping("/projects/{id}/attachments")
	public String handleFileUpload(
				@PathVariable(name = "id") Long projectId,
				@RequestParam(name = "category") String attachmentCategory,
				@RequestParam("multipartFile") MultipartFile multipartFile,
				RedirectAttributes redirectAttributes
			) throws IOException {

		if (StringUtils.cleanPath(multipartFile.getOriginalFilename()) == null || StringUtils.cleanPath(multipartFile.getOriginalFilename()).length() == 0) {
			return "redirect:/smnsh/projects/"+projectId+"/attachments";
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
		

		return "redirect:/smnsh/projects/"+projectId+"/attachments";
	}
	
	@GetMapping("/projects/user/springsecurityname/{userNameBySpringSecurity}")
	public String getProjectsByUserName(
				@PathVariable String userNameBySpringSecurity
			) {
		String userNameFirst = userNameBySpringSecurity.split("_")[0];
		String userNameLast = userNameBySpringSecurity.split("_")[1];
		User user = userService.loadByUserNames(userNameFirst, userNameLast);
		String userId = user.getId().toString();
		return "redirect:/smnsh/projects/user/"+userId;
	}
	
	@GetMapping("/projects/user/{userId}")
	public String getProjectsByUserId(
				@PathVariable(name = "userId") String userId,
				Model model
			) {
		List<Project> projects = projectService.loadProjectsByUserId(Long.parseLong(userId) );
		model.addAttribute("projectList", projects);
		return "smnsh/projects/all";
	}
	

	
}
