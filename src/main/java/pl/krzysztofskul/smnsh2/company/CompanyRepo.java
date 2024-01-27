package pl.krzysztofskul.smnsh2.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategoryEnum;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{

	List<Company> findAllByCompanyCategoryEnum(CompanyCategoryEnum companyCategoryEnum);
	
}
