package pl.krzysztofskul.sensit.smnsh.project.installation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallationRepo extends JpaRepository<Installation, Long>{

}
