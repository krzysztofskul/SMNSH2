package pl.krzysztofskul.smnsh2.company;

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

import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategory;
import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategoryEnum;
import pl.krzysztofskul.smnsh2.company.ContactDetails.ContactDetails;
import pl.krzysztofskul.smnsh2.company.Employee.Employee;
import pl.krzysztofskul.smnsh2.project.Project;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<Project> projectsAsInvestor = new ArrayList<Project>();
	
	@OneToMany
	private List<Project> projectsAsConsumer = new ArrayList<Project>();
	
	@ManyToMany
	private List<Project> projectsAsSubcontractor = new ArrayList<Project>();
	
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
	
	private LabelEnum labelEnum;
	
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

	/**
	 * @return the projectsAsInvestor
	 */
	public List<Project> getProjectsAsInvestor() {
		return projectsAsInvestor;
	}

	/**
	 * @param projectsAsInvestor the projectsAsInvestor to set
	 */
	public void setProjectsAsInvestor(List<Project> projectsAsInvestor) {
		this.projectsAsInvestor = projectsAsInvestor;
	}

	/**
	 * @return the projectsAsConsumer
	 */
	public List<Project> getProjectsAsConsumer() {
		return projectsAsConsumer;
	}

	/**
	 * @param projectsAsConsumer the projectsAsConsumer to set
	 */
	public void setProjectsAsConsumer(List<Project> projectsAsConsumer) {
		this.projectsAsConsumer = projectsAsConsumer;
	}

	/**
	 * @return the projectsAsSubcontractor
	 */
	public List<Project> getProjectsAsSubcontractor() {
		return projectsAsSubcontractor;
	}

	/**
	 * @param projectsAsSubcontractor the projectsAsSubcontractor to set
	 */
	public void setProjectsAsSubcontractor(List<Project> projectsAsSubcontractor) {
		this.projectsAsSubcontractor = projectsAsSubcontractor;
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
	 * @return the labelEnum
	 */
	public LabelEnum getLabelEnum() {
		return labelEnum;
	}

	/**
	 * @param labelEnum the labelEnum to set
	 */
	public void setLabelEnum(LabelEnum labelEnum) {
		this.labelEnum = labelEnum;
	}
	
	/**
	 * METHODS
	 */
	
}
