package pl.krzysztofskul.sensit.smnsh.project.installation.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo extends JpaRepository<Part, Long>{

}
