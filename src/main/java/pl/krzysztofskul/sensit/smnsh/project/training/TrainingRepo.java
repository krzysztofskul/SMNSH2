package pl.krzysztofskul.sensit.smnsh.project.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepo extends JpaRepository<Training, Long> {

}
