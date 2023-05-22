package pl.krzysztofskul.sensit.smnsh.project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;
import pl.krzysztofskul.sensit.smnsh.user.User;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String code;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User salesRep;
	private String contractNo;
	
	private String investor;
	private String location;
	private String user;
	
	private String device;
	
	private String projectManager;
	
    @OneToMany(
    		mappedBy = "project",
    		cascade = CascadeType.PERSIST
    		)
	private List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
	
	private String subcontractor;
	private String designer;
	
	private String deadline;
	
	@ElementCollection
//	@CollectionTable(
//			name = "Milestones", 
//			joinColumns = @JoinColumn(name="project_id"))
//	@Column(name = "milestone_id")
	private List<String> milestones = new ArrayList<String>();
	

	/**
	 * Constructor
	 */
	public Project() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param name
	 */
	public Project(String name) {
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the salesRep
	 */
	public User getSalesRep() {
		return salesRep;
	}

	/**
	 * @param salesRep the salesRep to set
	 */
	public void setSalesRep(User salesRep) {
		this.salesRep = salesRep;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the investor
	 */
	public String getInvestor() {
		return investor;
	}

	/**
	 * @param investor the investor to set
	 */
	public void setInvestor(String investor) {
		this.investor = investor;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the projectManager
	 */
	public String getProjectManager() {
		return projectManager;
	}

	/**
	 * @param projectManager the projectManager to set
	 */
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	/**
	 * @return the stakeholders
	 */
	public List<Stakeholder> getStakeholders() {
		return stakeholders;
	}

	/**
	 * @param stakeholders the stakeholders to set
	 */
	public void setStakeholders(List<Stakeholder> stakeholders) {
		this.stakeholders = stakeholders;
	}

	/**
	 * @return the subcontractor
	 */
	public String getSubcontractor() {
		return subcontractor;
	}

	/**
	 * @param subcontractor the subcontractor to set
	 */
	public void setSubcontractor(String subcontractor) {
		this.subcontractor = subcontractor;
	}

	/**
	 * @return the designer
	 */
	public String getDesigner() {
		return designer;
	}

	/**
	 * @param designer the designer to set
	 */
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	
	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the milestones
	 */
	public List<String> getMilestones() {
		return milestones;
	}

	/**
	 * @param milestones the milestones to set
	 */
	public void setMilestones(List<String> milestones) {
		this.milestones = milestones;
	}


}
