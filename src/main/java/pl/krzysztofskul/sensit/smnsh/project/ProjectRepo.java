package pl.krzysztofskul.sensit.smnsh.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.sensit.smnsh.user.User;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>{

	List<Project> findAllByProjectManager(User user);
	List<Project> findAllBySalesRep(User user);
	List<Project> findAllByProjectManagerAdd(User user);

}