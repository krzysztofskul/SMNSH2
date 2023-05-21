package pl.krzysztofskul.sensit.smnsh.project.stakeholder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Entity
@Table(name = "stakeholders")
public class Stakeholder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nameFirst;
	private String nameLast;
	private String company;
	private String businessPosition;
	private String contactDetails;
	
	@ManyToOne
	private Project project;
	
	/**
	 * Constructor
	 */
	public Stakeholder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param businessPosition
	 */
	public Stakeholder(String businessPosition) {
		this.businessPosition = businessPosition;
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
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
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
	 * @return the contactDetails
	 */
	public String getContactDetails() {
		return contactDetails;
	}

	/**
	 * @param contactDetails the contactDetails to set
	 */
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
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
