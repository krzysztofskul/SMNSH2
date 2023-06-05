package pl.krzysztofskul.sensit.smnsh.company.CompanyCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCategoryRepo extends JpaRepository<CompanyCategory, Long>{

	CompanyCategory findByCompanyCategoryEnum(CompanyCategoryEnum comCatEnum);
	
}
