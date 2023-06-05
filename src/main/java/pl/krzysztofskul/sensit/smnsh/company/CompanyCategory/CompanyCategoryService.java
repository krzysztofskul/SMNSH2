package pl.krzysztofskul.sensit.smnsh.company.CompanyCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyCategoryService {

	private CompanyCategoryRepo companyCategoryRepo;

	/**
	 * @param companyCategoryRepo
	 */
	@Autowired
	public CompanyCategoryService(CompanyCategoryRepo companyCategoryRepo) {
		this.companyCategoryRepo = companyCategoryRepo;
	}
	
	/**
	 * Save and return company category to database
	 * @param comCat
	 * @return CompanyCategory with id
	 */
	public CompanyCategory save(CompanyCategory comCat) {
		return companyCategoryRepo.save(comCat);
	}
	
	/**
	 * Load company category by company category Enum
	 * @param comCatEnum
	 * @return CompanyCategory
	 */
	public CompanyCategory loadByCompanyCategoryEnum(CompanyCategoryEnum comCatEnum) {
		CompanyCategory companyCategory = companyCategoryRepo.findByCompanyCategoryEnum(comCatEnum);
		return companyCategory;
	}
	
}
