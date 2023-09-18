package pl.krzysztofskul.sensit.smnsh.project.training;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.sensit.smnsh.project.installation.Installation;

@Entity
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private Long amountOfDays;
	
	@ManyToOne
	private Installation installation;

	/**
	 * CONSTRUCTOR
	 * @param description
	 * @param amountOfDays
	 * @param installation
	 */
	public Training(String description, Long amountOfDays, Installation installation) {
		this.description = description;
		this.amountOfDays = amountOfDays;
		this.installation = installation;
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
	
	
	
}
