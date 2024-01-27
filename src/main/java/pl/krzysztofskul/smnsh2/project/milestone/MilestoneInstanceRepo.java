package pl.krzysztofskul.smnsh2.project.milestone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneInstanceRepo extends JpaRepository<MilestoneInstance, Long> {

}
