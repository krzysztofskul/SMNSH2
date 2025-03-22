package pl.krzysztofskul.smnsh2.project.device3rd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.krzysztofskul.smnsh2.project.Project;

public interface Device3rdRepo extends JpaRepository<Device3rd, Long> {
	List<Device3rd> findByProjectId(Long projectId);
}
