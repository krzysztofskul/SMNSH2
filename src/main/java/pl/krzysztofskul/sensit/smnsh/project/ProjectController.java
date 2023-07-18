package pl.krzysztofskul.sensit.smnsh.project;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.sensit.smnsh.company.CompanyService;
import pl.krzysztofskul.sensit.smnsh.filestorage.File;
import pl.krzysztofskul.sensit.smnsh.filestorage.FileStorageService;
import pl.krzysztofskul.sensit.smnsh.project.attachment.Attachment;
import pl.krzysztofskul.sensit.smnsh.project.attachment.AttachmentCategory;
import pl.krzysztofskul.sensit.smnsh.project.attachment.AttachmentCategoryService;
import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;

@Controller
@RequestMapping("/smnsh")
public class ProjectController {

	private ProjectService projectService;
	private AttachmentCategoryService attachmentCategoryService;
	private CompanyService companyService;
	
	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public ProjectController(
			ProjectService projectService,
			AttachmentCategoryService attachmentCategoryService,
			CompanyService companyService
			) {
		this.projectService = projectService;
		this.attachmentCategoryService = attachmentCategoryService;
		this.companyService = companyService;
	}

	@GetMapping("/projects")
	public String getTestProjects() {		
		return "smnsh/projects/all";
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
	
	@GetMapping("/projects/{id}/remarks")
	public String getProjectByIdWithRemarks(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.loadByIdWithRemarks(id));
		return "smnsh/projects/idDetailsAndRemarks";
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
	
}
