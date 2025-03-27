package pl.krzysztofskul.smnsh2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.smnsh2.company.Company;
import pl.krzysztofskul.smnsh2.company.CompanyDemoGenerator;
import pl.krzysztofskul.smnsh2.company.CompanyService;
import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectDemoGenerator;
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
	
	@GetMapping("/admin/resetDB")
	public String resetDB() {
		for (Project project : projectService.loadAll()) {
			projectService.deleteById(project.getId());
		}
		for (Company company : companyService.loadAll()) {
			companyService.delete(company);
		}
		modalityGenerator.createAndSaveToDbEssentialModalities();
		devicePortfolioGenerator.initDevicePortfolioListDemo();
		setInitDataDemoDone(false);
		return "redirect:/home";
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
			initEssentialUserList();
			initEssentialModalityList();
			initEssentialAttachmentCategoryList();
			isInitDataEssentialsDone = true;
		}
		return "redirect:/smnsh2/home";
	}

	private void initEssentialAttachmentCategoryList() {
		for (AttachmentCategory attCat: attachmentCategoryDefaultGenerator.initDataAndReturn()) {
			attachmentCategoryService.save(attCat);
		}
	}

	private void initEssentialModalityList() {
		modalityGenerator.createAndSaveToDbEssentialModalities();
	}

	private void initEssentialUserList() {
		List<User> userList = userGenerator.createAndGetEssentialUsers();
		for (User user : userList) {
			userService.save(user);
		}
	}

	@GetMapping("/initDataDemo")
	public String initDataDemo() {
		if (isInitDataDemoDone == false) {		
			initDemoCompanyList();		
			initDemoDevicePortfolioList();		
			initDemoProjectList();
			isInitDataDemoDone = true;
		}
		return "redirect:/smnsh2/projects";
	}

	private void initDemoProjectList() {
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
			device3rdService.addDeviceToProject(project.getId(), new Device3rd("Lampa zabiegowa", "Examination Light", LoremIpsum.getInstance().getTitle(1), LoremIpsum.getInstance().getTitle(1), "SN-DEMO-000", 24));
			device3rdService.addDeviceToProject(project.getId(), new Device3rd("Kolumna anestezjologiczna", "Anaesthesia ceiling supply unit", LoremIpsum.getInstance().getTitle(1), LoremIpsum.getInstance().getTitle(1), "SN-DEMO-000", 48));

		}
	}

	private void initDemoDevicePortfolioList() {
		devicePortfolioGenerator.initDevicePortfolioListDemo();
	}

	private void initDemoCompanyList() {
		List<Company> companyList = companyDemoGenerator.initDataAndReturn();
		for (Company company : companyList) {
			companyService.save(company);
		}
	}
	
	@GetMapping("/thymeleaf/projects")
	public String homeThymeleaf(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		return "smnsh2/projects/all";
	}
	
	//TODO: to delete
	@GetMapping("/jsp/projects")
	public ModelAndView homeJsp(Model model) {
		model.addAttribute("projectList", projectService.loadAll());
		ModelAndView modelAndView = new ModelAndView("");
		return modelAndView;
	}
	
}
