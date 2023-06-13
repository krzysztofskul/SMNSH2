package pl.krzysztofskul.sensit.smnsh.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.krzysztofskul.sensit.smnsh.company.CompanyCategory.CompanyCategory;
import pl.krzysztofskul.sensit.smnsh.company.CompanyCategory.CompanyCategoryEnum;

@Service
public class CompanyService {

	private CompanyRepo companyRepo;

	/**
	 * Constructor
	 */
	@Autowired
	public CompanyService(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}

	public void save(Company company) {
		companyRepo.save(company);
	}
	
	public Company saveAndReturn(Company company) {
		return companyRepo.save(company);
	}

	public Company loadRandomInvestor() {
		List<Company> investorList = companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.INVESTOR);
		Company companyInvestor = investorList.get(new Random().nextInt(investorList.size()));
		
		return companyInvestor;
	}

	public List<Company> loadAllSubcontrsctorsForRoomAdaptation() {
		return companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.SUBCONTRACTOR_ROOM_ADAPTATION);
		
	}
	
//	public List<Company> loadAllByCompanyCategoryEnum(CompanyCategoryEnum comCatEnum) {
//		List<Company> allCompanies = companyRepo.findAll();
//		List<Company> companiesFiltered = new ArrayList<Company>();
//		for (Company company : allCompanies) {
//			Hibernate.initialize(company.getCompanyCategoryList());
//		}
//		for (Company company : allCompanies) {
//			for (CompanyCategory companyCategory : company.getCompanyCategoryList()) {
//				if (companyCategory.getCompanyCategoryEnum().equals(comCatEnum)) {
//					companiesFiltered.add(company);
//				}
//			}
//		}
//		return companiesFiltered;
//	}
	
}
