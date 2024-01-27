package pl.krzysztofskul.smnsh2.project.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePortfolioRepo extends JpaRepository<DevicePortfolio, Long>{
	
}
