package pl.krzysztofskul.smnsh2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.smnsh2.company.Company;
import pl.krzysztofskul.smnsh2.company.CompanyDemoGenerator;
import pl.krzysztofskul.smnsh2.company.CompanyService;
import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectDemoGenerator;
import pl.krzysztofskul.smnsh2.project.device.DevicePortfolio;
import pl.krzysztofskul.smnsh2.project.device.modality.DevicePortfolioGenerator;
import pl.krzysztofskul.smnsh2.project.device.modality.ModalityGenerator;
import pl.krzysztofskul.smnsh2.project.device3rd.Device3rd;
import pl.krzysztofskul.smnsh2.project.device3rd.Device3rdService;
import pl.krzysztofskul.smnsh2.user.User;
import pl.krzysztofskul.smnsh2.user.UserGenerator;
import pl.krzysztofskul.smnsh2.user.UserService;
import pl.krzysztofskul.smnsh2.project.ProjectService;
import pl.krzysztofskul.smnsh2.project.attachment.AttachmentCategory;
import pl.krzysztofskul.smnsh2.project.attachment.AttachmentCategoryDefaultGenerator;
import pl.krzysztofskul.smnsh2.project.attachment.AttachmentCategoryService;

@Controller
@RequestMapping("/smnsh2")
public class Smnsh2Controller {

	private boolean isInitDataEssentialsDone = false;
	private boolean isInitDataDemoDone = false;
	
	private ProjectDemoGenerator projectDemoGenerator;
	private ProjectService projectService;
	private UserGenerator userGenerator;
	private UserService userService;
	private ModalityGenerator modalityGenerator;
	private DevicePortfolioGenerator devicePortfolioGenerator;
	private CompanyDemoGenerator companyDemoGenerator;
	private CompanyService companyService;
	private AttachmentCategoryDefaultGenerator attachmentCategoryDefaultGenerator;
	private AttachmentCategoryService attachmentCategoryService;
	private Device3rdService device3rdService;

	/**
	 * CONSTRUCTOR
	 * 
	 */
	@Autowired
	public Smnsh2Controller(ProjectDemoGenerator projectDemoGenerator,
			ProjectService projectService, UserGenerator userGenerator, UserService userService,
			ModalityGenerator modalityGenerator, DevicePortfolioGenerator devicePortfolioGenerator,
			CompanyDemoGenerator companyDemoGenerator, CompanyService companyService,
			AttachmentCategoryDefaultGenerator attachmentCategoryDefaultGenerator,
			AttachmentCategoryService attachmentCategoryService,
			Device3rdService device3rdService
			) {
		this.projectDemoGenerator = projectDemoGenerator;
		this.projectService = projectService;
		this.userGenerator = userGenerator;
		this.userService = userService;
		this.modalityGenerator = modalityGenerator;
		this.devicePortfolioGenerator = devicePortfolioGenerator;
		this.companyDemoGenerator = companyDemoGenerator;
		this.companyService = companyService;
		this.attachmentCategoryDefaultGenerator = attachmentCategoryDefaultGenerator;
		this.attachmentCategoryService = attachmentCategoryService;
		this.device3rdService = device3rdService;
	}
	
	public boolean isInitDataEssentialsDone() {
		return this.isInitDataEssentialsDone;
	}
	public boolean isInitDataDemoDone() {
		return this.isInitDataDemoDone;
	}
	
	@GetMapping("/admin/setInitDataEssentialsDone")
	public String setInitDataEssentialsDone(
				@RequestParam(required = true) boolean isInitDataEssentialsDone
			) {
		
		this.isInitDataEssentialsDone = isInitDataEssentialsDone;
		return "redirect:/smnsh2/admin";
	}
	@GetMapping("/admin/setInitDataDemoDone")
	public String setInitDataDemoDone(
			@RequestParam(required = true) boolean isInitDataDemoDone
			) {
		
		this.isInitDataDemoDone = isInitDataDemoDone;
		return "redirect:/smnsh2/admin";
	}
	
	@GetMapping("/initDataEssentials")
	public String initDataEssentials() {
		if (isInitDataEssentialsDone == false) {
			/*
			 * init. essential users
			 */
			List<User> userList = userGenerator.createAndGetEssentialUsers();
			for (User user : userList) {
				userService.save(user);
			}
			/*
			 * init. essential modality list
			 */
			modalityGenerator.createAndSaveToDbEssentialModalities();
			/*
			 * init. attachemnt categories
			 */
			for (AttachmentCategory attCat: attachmentCategoryDefaultGenerator.initDataAndReturn()) {
				attachmentCategoryService.save(attCat);
			}
			isInitDataEssentialsDone = true;
		}
		return "redirect:/smnsh2/home";
	}

	@GetMapping("/initDataDemo")
	public String initDataDemo() {
		if (isInitDataDemoDone == false) {
			
			/*
			 * init demo companies
			 */
			List<Company> companyList = companyDemoGenerator.initDataAndReturn();
			for (Company company : companyList) {
				//companyService.saveAndReturn(company);
				companyService.save(company);
			}
			
			/*
			 * init. demo portfolio devices
			 */
			devicePortfolioGenerator.initDevicePortfolioListDemo();
			
			/*
			 * init. demo projects
			 */
			List<Project> projectList = projectDemoGenerator.initDataAndReturn();
			for (Project project : projectList) {
				projectService.save(project);
			}
			
			/*
			 * Add demo devices3rd to the projects
			 */
			projectList = projectService.loadAll();
			for (Project project : projectList) {
				device3rdService.addDeviceToProject(project.getId(), new Device3rd("Lampa zabiegowa", "Examination Light", LoremIpsum.getInstance().getName(), LoremIpsum.getInstance().getName(), "SN-DEMO-000", 24));
				device3rdService.addDeviceToProject(project.getId(), new Device3rd("Kolumna anestezjologiczna", "Anaesthesia ceiling supply unit", LoremIpsum.getInstance().getName(), LoremIpsum.getInstance().getName(), "SN-DEMO-000", 48));

			}
			// set initDataDemoDone true
			isInitDataDemoDone = true;
		}
		return "redirect:/smnsh2/projects";
	}
	
	@GetMapping("/thymeleaf/projects")
	public String homeThymeleaf(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		return "smnsh2/projects/all";
	}
	
	/*
	 * TODO 2023-06-15 create MVC/JSP functionality
	 */
	@GetMapping("/jsp/projects")
	public ModelAndView homeJsp(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		ModelAndView modelAndView = new ModelAndView("");
		return modelAndView;
	}
	
}
