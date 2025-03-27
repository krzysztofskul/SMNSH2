package pl.krzysztofskul.smnsh2.project.training;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.device.DeviceInstance;
import pl.krzysztofskul.smnsh2.project.installation.Installation;

@Entity
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Project project;
	
	private String description;
	
	private Long amountOfDays;
	
	@ManyToOne
	private DeviceInstance deviceInstance;

	public Training() {}
	
	/**
	 * @param project
	 * @param description
	 * @param amountOfDays
	 */
	public Training(Project project, String description, Long amountOfDays) {
		super();
		this.project = project;
		this.description = description;
		this.amountOfDays = amountOfDays;
	}

	/**
	 * @param description
	 * @param amountOfDays
	 * @param installation
	 */
	public Training(String description, Long amountOfDays) {
		this.description = description;
		this.amountOfDays = amountOfDays;
	}

	public Training(String description) {
		this.description = description;
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
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

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
	 * @return the amountOfDays
	 */
	public Long getAmountOfDays() {
		return amountOfDays;
	}

	/**
	 * @param amountOfDays the amountOfDays to set
	 */
	public void setAmountOfDays(Long amountOfDays) {
		this.amountOfDays = amountOfDays;
	}

	/**
	 * @return the deviceInstance
	 */
	public DeviceInstance getDeviceInstance() {
		return deviceInstance;
	}

	/**
	 * @param deviceInstance the deviceInstance to set
	 */
	public void setDeviceInstance(DeviceInstance deviceInstance) {
		this.deviceInstance = deviceInstance;
	}
	
}
