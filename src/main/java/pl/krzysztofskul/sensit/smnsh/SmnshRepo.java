package pl.krzysztofskul.sensit.smnsh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Repository
public interface SmnshRepo extends JpaRepository<Project, Long>{

}
