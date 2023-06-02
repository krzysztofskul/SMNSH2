package pl.krzysztofskul.sensit.smnsh.company.Employee;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.company.Company;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(mappedBy = "employeeList")
	private List<Company> company = new ArrayList<Company>();
	
//	//@OneToOne
//	private Person person;
	
	private String department;
	
	private String businessPosition;
	
//	//@OneToOne
//	private ContactDetails contactDetails;

	/**
	 * CONSTRUCTOR
	 */
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * CONSTRUCTOR
	 * 
	 * @param company
	 * @param person
	 * @param department
	 * @param businessPosition
	 * @param contactDetails
	 */
	public Employee(List<Company> company, 
//			Person person, 
			String department, String businessPosition
//			ContactDetails contactDetails
			) {
		this.company = company;
//		this.person = person;
		this.department = department;
		this.businessPosition = businessPosition;
//		this.contactDetails = contactDetails;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the company
	 */
	public List<Company> getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(List<Company> company) {
		this.company = company;
	}

//	/**
//	 * @return the person
//	 */
//	public Person getPerson() {
//		return person;
//	}
//
//	/**
//	 * @param person the person to set
//	 */
//	public void setPerson(Person person) {
//		this.person = person;
//	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the businessPosition
	 */
	public String getBusinessPosition() {
		return businessPosition;
	}

	/**
	 * @param businessPosition the businessPosition to set
	 */
	public void setBusinessPosition(String businessPosition) {
		this.businessPosition = businessPosition;
	}

//	/**
//	 * @return the contactDetails
//	 */
//	public ContactDetails getContactDetails() {
//		return contactDetails;
//	}
//
//	/**
//	 * @param contactDetails the contactDetails to set
//	 */
//	public void setContactDetails(ContactDetails contactDetails) {
//		this.contactDetails = contactDetails;
//	}
	
	
	
	
}
