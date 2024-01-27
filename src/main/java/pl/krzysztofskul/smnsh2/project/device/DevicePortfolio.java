package pl.krzysztofskul.smnsh2.project.device;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pl.krzysztofskul.smnsh2.project.Project;

/*
 * TODO 2023-06-21 create DevicesGenerator and Default/Essential list of devices for demo/test/real app.
 */
@Entity
public class DevicePortfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String modality;
	
	private String modelName;

	
	
	/**
	 * CONSTRUCTOR
	 */
	public DevicePortfolio() {
	}

	/**
	 * CONSTRUCTOR
	 * @param modality
	 * @param modelName
	 */
	public DevicePortfolio(String modality, String modelName) {
		this.modality = modality;
		this.modelName = modelName;
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
	 * @return the modality
	 */
	public String getModality() {
		return modality;
	}

	/**
	 * @param modality the modality to set
	 */
	public void setModality(String modality) {
		this.modality = modality;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
