package pl.krzysztofskul.sensit.smnsh.project.device;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.project.installation.Installation;
import pl.krzysztofskul.sensit.smnsh.project.installation.configuration.ConfigurationDevice;
import pl.krzysztofskul.sensit.smnsh.project.training.Training;

@Entity
public class DeviceInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String serialNo;	
	
	@ManyToOne
	private DevicePortfolio devicePortfolio;

	private String dateOfDelivery;
	
	@OneToOne(mappedBy="deviceInstance")
	private Installation installation;

	//private Calculation calculation;
	//private ConfigurationGeneral configurationGeneral;
	@OneToOne(cascade = CascadeType.ALL)
	private ConfigurationDevice configurationDevice = new ConfigurationDevice();
	//private ConfigurationAccessories configurationAccessories;
	
	@OneToMany
	private List<Training> trainingList = new ArrayList<Training>();
	
	/**
	 * CONSTRUCTOR
	 */
	public DeviceInstance() {
	}

	/**
	 * CONSTRUCTOR
	 * @param devicePortfolio
	 */
	public DeviceInstance(DevicePortfolio devicePortfolio) {
		this.devicePortfolio = devicePortfolio;
	}

	/**
	 * @param devicePortfolio
	 * @param project
	 */
	public DeviceInstance(DevicePortfolio devicePortfolio, Project project) {
		this.devicePortfolio = devicePortfolio;
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
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * @return the dateOfDelivery
	 */
	public String getDateOfDelivery() {
		return dateOfDelivery;
	}

	/**
	 * @param dateOfDelivery the dateOfDelivery to set
	 */
	public void setDateOfDelivery(String dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
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
	 * @return the configurationDevice
	 */
	public ConfigurationDevice getConfigurationDevice() {
		return configurationDevice;
	}

	/**
	 * @param configurationDevice the configurationDevice to set
	 */
	public void setConfigurationDevice(ConfigurationDevice configurationDevice) {
		this.configurationDevice = configurationDevice;
	}

	/**
	 * @return the trainingList
	 */
	public List<Training> getTrainingList() {
		return trainingList;
	}

	/**
	 * @param trainingList the trainingList to set
	 */
	public void setTrainingList(List<Training> trainingList) {
		this.trainingList = trainingList;
	}



	
	
}
