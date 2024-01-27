package pl.krzysztofskul.smnsh2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.smnsh2.project.Project;

@Repository
public interface Smnsh2Repo extends JpaRepository<Project, Long>{

}
