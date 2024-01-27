package pl.krzysztofskul.smnsh2.company.CompanyCategory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import pl.krzysztofskul.smnsh2.company.Company;

@Entity
@Table(name = "companycategories")
public class CompanyCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private CompanyCategoryEnum companyCategoryEnum;

//	@ManyToMany(mappedBy = "companyCategoryList")
//	private List<Company> companyList = new ArrayList<Company>();
	
	/*
	 * constr.
	 */
	public CompanyCategory() {
	}

	/*
	 * constr.
	 */
	public CompanyCategory(CompanyCategoryEnum companyCategoryEnum) {
		this.companyCategoryEnum = companyCategoryEnum;
	}

	/*
	 * getters and setters
	 */
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public CompanyCategoryEnum getCompanyCategoryEnum() {
		return companyCategoryEnum;
	}
	
	public void setCompanyCategoryEnum(CompanyCategoryEnum companyCategoryEnum) {
		this.companyCategoryEnum = companyCategoryEnum;
	}
	
//	public List<Company> getCompanyList() {
//		return companyList;
//	}
//	
//	public void setCompanyList(List<Company> companyList) {
//		this.companyList = companyList;
//	}
	
}
