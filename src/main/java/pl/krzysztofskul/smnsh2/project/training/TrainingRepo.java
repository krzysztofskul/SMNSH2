package pl.krzysztofskul.smnsh2.project.training;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepo extends JpaRepository<Training, Long> {

	List<Training> findAllByProjectId(Long projectId);

}
