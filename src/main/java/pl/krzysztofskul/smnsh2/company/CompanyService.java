package pl.krzysztofskul.smnsh2.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategory;
import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategoryEnum;

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
	
	public Company loadRandomCustomer() {
		List<Company> customerList = companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.CUSTOMER);
		Company companyCustomer = customerList.get(new Random().nextInt(customerList.size()));
		return companyCustomer;
	}

	public Company loadRandomSubcontractorForRoomAdaptation() {
		List<Company> subcontractorList = this.loadAllSubcontrsctorsForRoomAdaptation();
		Company subcontractor = subcontractorList.get(new Random().nextInt(subcontractorList.size())); 
		return subcontractor;
	}
	
	
	public List<Company> loadAllSubcontrsctorsForRoomAdaptation() {
		return companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.SUBCONTRACTOR_ROOM_ADAPTATION);
		
	}
	public List<Company> loadAllInvestors() {
		return companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.INVESTOR);
	}
	
	public List<Company> loadAllCustomers() {
		return companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.CUSTOMER);
	}

	public List<Company> loadAllSubcontractors() {
		List<Company> subcontractorList = new ArrayList<Company>();
		subcontractorList.addAll(companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.SUBCONTRACTOR));
		subcontractorList.addAll(companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.SUBCONTRACTOR_GENERAL));
		subcontractorList.addAll(companyRepo.findAllByCompanyCategoryEnum(CompanyCategoryEnum.SUBCONTRACTOR_ROOM_ADAPTATION));
		return subcontractorList;
	}
	
	
	public Company loadById(Long companyId) {
		return companyRepo.findById(companyId).get();
	}

	public List<Company> loadAll() {
		return companyRepo.findAll();
	}

	public void delete(Company company) {
		companyRepo.delete(company);
	}
	
}
