package pl.krzysztofskul.sensit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Repository
public interface SensitRepo extends JpaRepository<Project, Long>{

}
