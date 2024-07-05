package pl.krzysztofskul.sensit.smnsh.project;

import java.io.Serializable;
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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.krzysztofskul.sensit.smnsh.company.Company;
import pl.krzysztofskul.sensit.smnsh.project.attachment.Attachment;
import pl.krzysztofskul.sensit.smnsh.project.device.Device;
import pl.krzysztofskul.sensit.smnsh.project.device.DeviceInstance;
import pl.krzysztofskul.sensit.smnsh.project.device.DevicePortfolio;
import pl.krzysztofskul.sensit.smnsh.project.installation.Installation;
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
public class Project /*TODO 2023-06-26 1555 implement serializable? also for related classes?*/ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = Long.valueOf(0);
	
	private String name;
	private String code;
	
	@Column(length = 1024)
	private String background;
	private String goals;
	private String risks;
	
	private LocalDateTime dateTimeOfCreation = LocalDateTime.now();
	
	@ManyToOne
	private User salesRep;
	private String contractNo;
	
	@ManyToOne
	private Company investor;

	/*
	 * TODO 2023-06-19 create relation with customer
	 * update project demo generator with customer
	 * update front-end projects and project by id
	 */
	@ManyToOne
	private Company customer;
	
	/*
	 * TODO 2023-06-19 create relation with subcontractors
	 * update project demo generator with subcontractors
	 * update front-end projects and project by id
	 */
	@ManyToMany
	private List<Company> subcontractors = new ArrayList<Company>();
	
	/*
	 * TODO 2023-06-25 Update front-end because of changes in device parameter from the String device to the Device devicePortfolio
	 */
	@ManyToOne
	private DevicePortfolio devicePortfolio;
	
	private String installationPlaceDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Installation installation;
	
	/*
	 * TODO (?) move to the installation class
	 */
	@ManyToOne
	private User projectManager;

	/*
	 * TODO (?) move to the installation class
	 */
	@ManyToOne
	private User projectManagerAdd;
	
    @OneToMany(
    		mappedBy = "project",
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    		)
	private List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
	
    @ManyToOne
	private Company subcontractorForRoomAdaptation;
	private String designer;
	
	private LocalDate deadline = LocalDate.now().plusMonths(3);
	
	private Status status = Status.UNDEFINED;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Remark> remarks = new ArrayList<Remark>();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<MilestoneInstance> milestones = new ArrayList<MilestoneInstance>();

	@OneToMany(mappedBy ="project", cascade = CascadeType.ALL)
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
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
	 * @return the devicePortfolio
	 */
	public DevicePortfolio getDevicePortfolio() {
		return devicePortfolio;
	}

	/**
	 * @param devicePortfolio the devicePortfolio to set
	 */
	public void setDevicePortfolio(DevicePortfolio devicePortfolio) {
		this.devicePortfolio = devicePortfolio;
	}

	/**
	 * @return the installationPlaceDetails
	 */
	public String getInstallationPlaceDetails() {
		return installationPlaceDetails;
	}

	/**
	 * @param installationPlaceDetails the installationPlaceDetails to set
	 */
	public void setInstallationPlaceDetails(String installationPlaceDetails) {
		this.installationPlaceDetails = installationPlaceDetails;
	}

	/**
	 * @return the installation
	 */
	public Installation getInstallation() {
		return installation;
	}

	/**
	 * @param installation the installation to set
	 */
	public void setInstallation(Installation installation) {
		this.installation = installation;
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
		List<Project> projectsAsPM = projectManager.getProjectListAsProjectManager();
		projectsAsPM.add(this);
	}

	/**
	 * @return the projectManagerAdd
	 */
	public User getProjectManagerAdd() {
		return projectManagerAdd;
	}

	/**
	 * @param projectManagerAdd the projectManagerAdd to set
	 */
	public void setProjectManagerAdd(User projectManagerAdd) {
		this.projectManagerAdd = projectManagerAdd;
		List<Project> projectsAsPmAdd = projectManager.getProjectListAsProjectManagerAdd();
		projectsAsPmAdd.add(this);
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
	 * @return the subcontractorForRoomAdaptation
	 */
	public Company getSubcontractorForRoomAdaptation() {
		return subcontractorForRoomAdaptation;
	}

	/**
	 * @param subcontractorForRoomAdaptation the subcontractorForRoomAdaptation to set
	 */
	public void setSubcontractorForRoomAdaptation(Company subcontractorForRoomAdaptation) {
		this.subcontractorForRoomAdaptation = subcontractorForRoomAdaptation;
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
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(LocalDate deadline) {
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
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
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
	 * Methods which allows to add to the project a new milestone from template
	 * @param MilestoneTemplate milestoneTemplate
	 */
	public void addMilestoneFromTemplate(MilestoneTemplate milestoneTemplate) {
		MilestoneInstance milestoneInstance = new MilestoneInstance(milestoneTemplate);
		this.milestones.add(milestoneInstance);
		milestoneInstance.setProject(this);
	}

	
	/** Methods which allows to add to the project new milestone from template, with specified deadline and status
	 * @param milestoneTemplate
	 * @param deadline
	 * @param status
	 */
	public void addMilestoneFromTemplate(MilestoneTemplate milestoneTemplate, LocalDate deadline, MilestoneStatusEnum status) {
		MilestoneInstance milestoneInstance = new MilestoneInstance(milestoneTemplate, deadline, status);
		this.milestones.add(milestoneInstance);
		milestoneInstance.setProject(this);
	}
	
	/** Methods which allows to add new attachment to the project
	 * @param attachment
	 */
	public void addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
		attachment.setProject(this);
	}

	/**
	 * Methods which allows to change the status of the project. It also create a installation instance related with the project.
	 * @param status
	 */
	public void changeStatusTo(Status status) {
		switch (status) {
			case UNDEFINED: {
				this.setStatus(status);
				break;
			}
			case PRECONTRACT: {
				this.setStatus(status);
				break;
			}
			case CONTRACT: {
				this.setStatus(status);
				break;
			}
			case IMPLEMENTATION: {
				this.setStatus(status);
				this.installation = new Installation(this, new DeviceInstance(this.devicePortfolio, this));
				break;
			}
			case NOBID: {
				this.setStatus(status);
				break;
			}
			case COMPLETED: {
				this.setStatus(status);
				break;
			}
			default:
				break;
			}
	}
}
