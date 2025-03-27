package pl.krzysztofskul.smnsh2.project.device3rd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectRepo;

@Service
public class Device3rdService {

    private final Device3rdRepo device3rdRepo;
    private final ProjectRepo projectRepo;

    @Autowired
    public Device3rdService(
    		Device3rdRepo device3rdRepo
    		, ProjectRepo projectRepo
    		) {
        this.device3rdRepo = device3rdRepo;
        this.projectRepo = projectRepo;
    }

    public List<Device3rd> getAll() {
        return device3rdRepo.findAll();
    }
	
    public List<Device3rd> getAllByProjectId(Long projectId) {
		return device3rdRepo.findByProjectId(projectId);
	}
	
    public Optional<Device3rd> getById(Long id) {
        return device3rdRepo.findById(id);
    }

    public Device3rd save(Device3rd device) {
        return device3rdRepo.save(device);
    }

    public void delete(Long id) {
        device3rdRepo.deleteById(id);
    }
    
    public void addDeviceToProject(Long projectId, Device3rd device3rd) {
        // Fetch the project by ID
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Set the project on the device
        device3rd.setProject(project);

        // Save the device
        device3rdRepo.save(device3rd);
    }
    
}