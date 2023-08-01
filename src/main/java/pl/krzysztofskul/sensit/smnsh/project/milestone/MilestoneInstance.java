package pl.krzysztofskul.sensit.smnsh.project.milestone;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import pl.krzysztofskul.sensit.smnsh.project.Project;

/**
 * @author z0041nhm
 *
 */
/**
 * @author z0041nhm
 *
 */
@Entity
public class MilestoneInstance extends Milestone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = Long.valueOf(0);
	
	@ManyToOne
	private Project project;
	
	@ManyToOne
	private MilestoneTemplate milestoneTemplate;
	
	private LocalDate deadline = LocalDate.now();
	
	private MilestoneStatusEnum status;


	/**
	 * CONSTRUCTOR 
	 */
	public MilestoneInstance() {
	}

	/**
	 * 	CONSTRUCTOR
	 * Creates new milestone from template list
	 * @param milestoneTemplate
	 * @param deadline
	 * @param status
	 */
	public MilestoneInstance(MilestoneTemplate milestoneTemplate) {
		this.milestoneTemplate = milestoneTemplate;
	}
	
	/**
	 * 	CONSTRUCTOR
	 * Creates new milestone from template list
	 * @param project
	 * @param milestoneTemplate
	 * @param deadline
	 * @param status
	 */
	public MilestoneInstance(Project project, MilestoneTemplate milestoneTemplate, LocalDate deadline,
			MilestoneStatusEnum status) {
		this.project = project;
		this.milestoneTemplate = milestoneTemplate;
		this.deadline = deadline;
		this.status = status;
	}
	
	/**
	 * 	CONSTRUCTOR
	 * Creates new milestone from template list
	 * @param milestoneTemplate
	 * @param deadline
	 * @param status
	 */
	public MilestoneInstance(MilestoneTemplate milestoneTemplate, LocalDate deadline,
			MilestoneStatusEnum status) {
		this.milestoneTemplate = milestoneTemplate;
		this.deadline = deadline;
		this.status = status;
	}

	/**
	 * CONSTRUCTOR
	 * Creates a new specific (not from template list) milestone to the project
	 * @param namePl
	 * @param nameEn
	 * @param description
	 */
	public MilestoneInstance(Project project, String namePl, String nameEn, String description, LocalDate deadline, MilestoneStatusEnum status) {
		this.milestoneTemplate = null;
		this.setNamePl(namePl);
		this.setNameEn(nameEn);
		this.setDescription(description);
		this.deadline = deadline;
		this.status = status;
	}

	
	/**
	 * CONSTRUCTOR
	 * Creates a new specific (not from template list) milestone to the project
	 * @param project
	 * @param status
	 */
	public MilestoneInstance(Project project, MilestoneStatusEnum status) {
		this.project = project;
		this.status = status;
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

	/**
	 * @return the milestoneTemplate
	 */
	public MilestoneTemplate getMilestoneTemplate() {
		return milestoneTemplate;
	}

	/**
	 * @param milestoneTemplate the milestoneTemplate to set
	 */
	public void setMilestoneTemplate(MilestoneTemplate milestoneTemplate) {
		this.milestoneTemplate = milestoneTemplate;
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
	public MilestoneStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(MilestoneStatusEnum status) {
		this.status = status;
	}	
	
	
}
