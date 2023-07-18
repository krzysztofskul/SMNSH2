package pl.krzysztofskul.sensit.smnsh.project.stakeholder;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.company.Company;
import pl.krzysztofskul.sensit.smnsh.project.Project;

@Entity
@Table(name = "stakeholders")
public class Stakeholder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nameFirst;
	private String nameLast;
	@ManyToOne
	private Company company;
	private String businessPosition;
	
	private String phoneNumber;
	
	private String email;
	
	/*
	 * TODO phone numbers and emails as a map with titles
	 * 
	@ElementCollection
	@CollectionTable(name = "stakeholder_emails_mapping", joinColumns = @JoinColumn(name = "stakeholder_id", referencedColumnName = "id"))
	@MapKeyColumn(name = "email_name")
	@Column(name = "email")
	private Map<String, String> emails = new HashMap<String, String>();

	@ElementCollection
	@CollectionTable(name = "stakeholder_phoneNumbers_mapping", joinColumns = @JoinColumn(name = "stakeholder_id", referencedColumnName = "id"))
	@MapKeyColumn(name = "phoneNumber_name")
	@Column(name = "phoneNumber")
	private Map<String, String> phoneNumbers = new HashMap<String, String>();
	*/
	
	private String description;
	
	@ManyToOne
	private Project project;
	
	/**
	 * Constructor
	 */
	public Stakeholder() {
	}
	
	/**
	 * @param nameFirst
	 * @param nameLast
	 * @param businessPosition
	 * @param contactDetails
	 * @param project
	 */
	public Stakeholder(String nameFirst, String nameLast, String businessPosition, String contactDetails,
			Project project) {
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.businessPosition = businessPosition;
		this.project = project;
	}

	/**
	 * Constructor
	 * @param businessPosition
	 */
	public Stakeholder(String businessPosition) {
		this.businessPosition = businessPosition;
	}
	
	/**
	 * Constructor
	 * @param project
	 */
	public Stakeholder(Project project) {
		this.project = project;
	}

	/**
	 * Constructor
	 * @param businessPosition
	 * @param project
	 */
	public Stakeholder(String businessPosition, Project project) {
		this.businessPosition = businessPosition;
		this.project = project;
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
	 * @return the nameFirst
	 */
	public String getNameFirst() {
		return nameFirst;
	}

	/**
	 * @param nameFirst the nameFirst to set
	 */
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	/**
	 * @return the nameLast
	 */
	public String getNameLast() {
		return nameLast;
	}

	/**
	 * @param nameLast the nameLast to set
	 */
	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
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

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*

	public Map<String, String> getEmails() {
		return emails;
	}

	public void setEmails(Map<String, String> emails) {
		this.emails = emails;
	}

	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	*/

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}
