package pl.krzysztofskul.sensit.smnsh.company;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.company.CompanyCategory.CompanyCategory;
import pl.krzysztofskul.sensit.smnsh.company.CompanyCategory.CompanyCategoryEnum;
import pl.krzysztofskul.sensit.smnsh.company.ContactDetails.ContactDetails;
import pl.krzysztofskul.sensit.smnsh.company.Employee.Employee;
import pl.krzysztofskul.sensit.smnsh.project.Project;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "subcontractor")
	private List<Project> projects = new ArrayList<Project>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private ContactDetails contactDetails;
	
//	@ManyToMany
//	@JoinTable(
//			name = "company_category",
//			joinColumns = @JoinColumn(name  = "company_id"),
//			inverseJoinColumns = @JoinColumn(name = "companyCategory_id")
//	)
//	private List<CompanyCategory> companyCategoryList = new ArrayList<CompanyCategory>();
	
	private CompanyCategoryEnum companyCategoryEnum;
	
	@ManyToMany
	@JoinTable(
			name = "company_employee",
			joinColumns = @JoinColumn(name  = "company_id"),
			inverseJoinColumns = @JoinColumn(name = "employee_id")
	)
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	/**
	 * CONSTRUCTORS
	 */
	public Company() {}	
	
	public Company(Long id, String name,
			/* List<CompanyCategory> companyCategoryList, */
			CompanyCategoryEnum companyCategoryEnum,
			List<Employee> employeeList) {
		super();
		this.id = id;
		this.name = name;
//		this.companyCategoryList = companyCategoryList;
		this.companyCategoryEnum = companyCategoryEnum;
		this.employeeList = employeeList;
		this.contactDetails = new ContactDetails();
	}
	
	public Company(Long id, String name,
			CompanyCategoryEnum companyCategoryEnum,
			List<Employee> employeeList, ContactDetails contactDetails) {
		super();
		this.id = id;
		this.name = name;
		this.companyCategoryEnum = companyCategoryEnum;
		this.employeeList = employeeList;
		this.contactDetails = contactDetails;
	}

	/**
	 * GETTERS AND SETTERS
	 */
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

//	public List<CompanyCategory> getCompanyCategoryList() {
//		return companyCategoryList;
//	}
//
//	public void setCompanyCategoryList(List<CompanyCategory> companyCategoryList) {
//		this.companyCategoryList = companyCategoryList;
//	}

	/**
	 * @return the companyCategory
	 */
	public CompanyCategoryEnum getCompanyCategoryEnum() {
		return companyCategoryEnum;
	}

	/**
	 * @param companyCategory the companyCategory to set
	 */
	public void setCompanyCategoryEnum(CompanyCategoryEnum companyCategoryEnum) {
		this.companyCategoryEnum = companyCategoryEnum;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	/**
	 * METHODS
	 */
	
}
