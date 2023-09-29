package pl.krzysztofskul.sensit.smnsh.project.milestone;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class Milestone implements Serializable {
	
	private String namePl;
	
	private String nameEn;
	
	private String description;

	/**
	 * CONSTRUCTOR
	 */
	public Milestone() {
	}
	
	/**
	 * CONSTRUCTOR
	 * @param namePl
	 * @param nameEn
	 * @param description
	 */
	public Milestone(String namePl, String nameEn, String description) {
		this.namePl = namePl;
		this.nameEn = nameEn;
		this.description = description;
	}

	/**
	 * @return the namePl
	 */
	public String getNamePl() {
		return namePl;
	}

	/**
	 * @param namePl the namePl to set
	 */
	public void setNamePl(String namePl) {
		this.namePl = namePl;
	}

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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
	
}
