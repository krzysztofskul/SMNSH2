package pl.krzysztofskul.smnsh2.project.device3rd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectService;

@Controller
@RequestMapping("/smnsh2")
public class Device3rdController {

	private final Device3rdService device3rdService;
	private final ProjectService projectService;

	@Autowired
    public Device3rdController(
    		Device3rdService device3rdService
    		, ProjectService projectService
    		) {
        this.device3rdService = device3rdService;
        this.projectService = projectService;
    }

    // Show all devices by project
    @GetMapping("/devices3rd/project/{projectId}")
    public String getAllByProject(Model model, @PathVariable Long projectId) {
        List<Device3rd> devices3rd = device3rdService.getAllByProjectId(projectId);
        model.addAttribute("devices3rd", devices3rd);
        model.addAttribute("project", projectService.loadById(projectId));
        return "smnsh2/projects/idDetailsAndDevice3rd";
    }

    // Show form to add a new device
    @GetMapping("/devices3rd/project/{projectId}/add")
    public String showAddDevice3rdForm(
    		@PathVariable Long projectId,
    		Model model
    		) {
    	Project project = projectService.loadById(projectId);
        model.addAttribute("device3rd", new Device3rd(project));
        model.addAttribute("project", project);
        return "smnsh2/projects/forms/addDevice3rdForm";
    }

    // Create a new device
    @PostMapping("/projects/{projectId}/device3rd/save")
    public String createDevice3rd(
    		@PathVariable Long projectId,
    		@ModelAttribute Device3rd device3rd) {
        device3rdService.save(device3rd);
        return "redirect:/smnsh2/devices3rd/project/"+projectId;
    }

    // Show device details
    @GetMapping("/{id}")
    public String getDevice3rdById(@PathVariable Long id, Model model) {
        Optional<Device3rd> device = device3rdService.getById(id);
        if (device.isPresent()) {
            model.addAttribute("device", device.get());
            return ""; // Return view name
        }
        return "redirect:/"; // If not found, redirect
    }

    // Show form to edit an existing device
    @GetMapping("/{id}/edit")
    public String showEditDevice3rdForm(@PathVariable Long id, Model model) {
        Optional<Device3rd> device = device3rdService.getById(id);
        if (device.isPresent()) {
            model.addAttribute("device", device.get());
            return ""; // Return view name
        }
        return "redirect:/"; // If not found, redirect
    }

    // Update an existing device
    @PostMapping("/{id}/edit")
    public String updateDevice3rd(@PathVariable Long id, @ModelAttribute Device3rd device) {
        device.setId(id); // Ensure the ID stays the same for update
        device3rdService.save(device);
        return "redirect:/"; // Redirect
    }

    // Delete a device
    @GetMapping("/device3rd/{id}/delete")
    public String deleteDevice3rd(
    		@PathVariable Long id,
    		@RequestParam String projectId
    		) {
        device3rdService.delete(id);
        return "redirect:/smnsh2/devices3rd/project/"+projectId;
    }
    
    // Add device to the project
    @PostMapping("/projects/{projectId}/addDevice")
    public String addDeviceToProject(@PathVariable Long projectId, @ModelAttribute Device3rd device3rd) {
        // Call the service to add the device to the project
        device3rdService.addDeviceToProject(projectId, device3rd);

        // Redirect or return view
        return "redirect:/smnsh2/devices3rd/project/"+projectId;
    }
	
}
