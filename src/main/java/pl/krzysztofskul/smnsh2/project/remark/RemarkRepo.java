package pl.krzysztofskul.smnsh2.project.remark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarkRepo extends JpaRepository<Remark, Long>{

	List<Remark> findAllByProjectId(Long projectId);
	
}
