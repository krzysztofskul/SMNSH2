package pl.krzysztofskul.sensit.smnsh.project.milestone;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MilestoneTemplate extends Milestone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="milestoneTemplate")
	private List<MilestoneInstance> milestoneInstance;

	/**
	 * 
	 */
	public MilestoneTemplate() {
	}

	/**
	 * @param namePl
	 * @param nameEn
	 * @param description
	 */
	public MilestoneTemplate(String namePl, String nameEn, String description) {
		super(namePl, nameEn, description);
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
	 * @return the milestoneInstance
	 */
	public List<MilestoneInstance> getMilestoneInstance() {
		return milestoneInstance;
	}

	/**
	 * @param milestoneInstance the milestoneInstance to set
	 */
	public void setMilestoneInstance(List<MilestoneInstance> milestoneInstance) {
		this.milestoneInstance = milestoneInstance;
	}


	
	
	
}
