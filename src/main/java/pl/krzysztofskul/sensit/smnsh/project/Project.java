package pl.krzysztofskul.sensit.smnsh.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.company.Company;
import pl.krzysztofskul.sensit.smnsh.project.milestone.Milestone;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneInstance;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneStatusEnum;
import pl.krzysztofskul.sensit.smnsh.project.milestone.MilestoneTemplate;
import pl.krzysztofskul.sensit.smnsh.project.remark.Remark;
import pl.krzysztofskul.sensit.smnsh.project.stakeholder.Stakeholder;
import pl.krzysztofskul.sensit.smnsh.project.status.Status;
import pl.krzysztofskul.sensit.smnsh.user.User;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String code;
	
	@Column(length = 1024)
	private String background;
	private String goals;
	private String risks;
	
	private LocalDateTime dateTimeOfCreation = LocalDateTime.now();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User salesRep;
	private String contractNo;
	
	@ManyToOne
	private Company investor;

	/*
	 * TODO create relation with customer
	 * TODO update project demo generator with customer
	 * TODO update front-end projects and project by id
	 */
	@ManyToOne
	private Company customer;
	
	/*
	 * TODO create relation with subcontractors
	 * update project demo generator with subcontractors
	 * update front-end projects and project by id
	 */
	@ManyToMany
	private List<Company> subcontractors = new ArrayList<Company>();
	
	/*
	 * TODO change Sring device to the DevicePrototype class and relation
	 */
	private String device;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User projectManager;
	
    @OneToMany(
    		mappedBy = "project",
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    		)
	private List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
	
	private String subcontractor;
	private String designer;
	
	private String deadline;
	
	private Status status;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Remark> remarks = new ArrayList<Remark>();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<MilestoneInstance> milestones = new ArrayList<MilestoneInstance>();

	/**
	 * Constructor
	 */
	public Project() {
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
	 * @return the background
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}

	/**
	 * @return the risks
	 */
	public String getRisks() {
		return risks;
	}

	/**
	 * @param risks the risks to set
	 */
	public void setRisks(String risks) {
		this.risks = risks;
	}

	/**
	 * @return the dateTimeOfCreation
	 */
	public LocalDateTime getDateTimeOfCreation() {
		return dateTimeOfCreation;
	}

	/**
	 * @param dateTimeOfCreation the dateTimeOfCreation to set
	 */
	public void setDateTimeOfCreation(LocalDateTime dateTimeOfCreation) {
		this.dateTimeOfCreation = dateTimeOfCreation;
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
	public Company getInvestor() {
		return investor;
	}

	/**
	 * @param investor the investor to set
	 */
	public void setInvestor(Company investor) {
		this.investor = investor;
	}
	
	/**
	 * @return the customer
	 */
	public Company getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Company customer) {
		this.customer = customer;
	}

	/**
	 * @return the subcontractor
	 */
	public List<Company> getSubcontractors() {
		return subcontractors;
	}

	/**
	 * @param sucontractors the subcontractors to set
	 */
	public void setSubcontractors(List<Company> subcontractors) {
		this.subcontractors = subcontractors;
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
	public User getProjectManager() {
		return projectManager;
	}

	/**
	 * @param projectManager the projectManager to set
	 */
	public void setProjectManager(User projectManager) {
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
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the remarks
	 */
	public List<Remark> getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the milestones
	 */
	public List<MilestoneInstance> getMilestones() {
		return milestones;
	}

	/**
	 * @param milestones the milestones to set
	 */
	public void setMilestones(List<MilestoneInstance> milestones) {
		this.milestones = milestones;
	}

	/**
	 * Method adds remark to the list of remarks connected with project
	 */
	public void addRemark(Remark remark) {
		this.remarks.add(remark);
		remark.setProject(this);
	}

	/**
	 * Adds stakeholder to the project list of stakeholders
	 * @param newStakeholder
	 */
	public void addStakeholder(Stakeholder newStakeholder) {
		this.stakeholders.add(newStakeholder);
		newStakeholder.setProject(this);
		
	}

	/**
	 * 
	 */
	public void addMilestoneFromTemplate(MilestoneTemplate milestoneTemplate) {
		MilestoneInstance milestoneInstance = new MilestoneInstance(milestoneTemplate);
		this.milestones.add(milestoneInstance);
		milestoneInstance.setProject(this);
	}

	public void addMilestoneFromTemplate(MilestoneTemplate milestoneTemplate, LocalDate deadline, MilestoneStatusEnum status) {
		MilestoneInstance milestoneInstance = new MilestoneInstance(milestoneTemplate, deadline, status);
		this.milestones.add(milestoneInstance);
		milestoneInstance.setProject(this);
		
	}
}
