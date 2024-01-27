package pl.krzysztofskul.smnsh2.project.device;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.installation.Installation;
import pl.krzysztofskul.smnsh2.project.installation.configuration.ConfigurationDevice;
import pl.krzysztofskul.smnsh2.project.training.Training;

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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "deviceInstance", orphanRemoval = true)
	private List<ConfigurationDevice> configurationDeviceList = new ArrayList<ConfigurationDevice>();
	//private ConfigurationAccessories configurationAccessories;
	
	@OneToMany(mappedBy = "deviceInstance", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
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
	 * @return the configurationDeviceList
	 */
	public List<ConfigurationDevice> getConfigurationDeviceList() {
		return configurationDeviceList;
	}

	/**
	 * @param configurationDeviceList the configurationDeviceList to set
	 */
	public void setConfigurationDeviceList(List<ConfigurationDevice> configurationDeviceList) {
		this.configurationDeviceList = configurationDeviceList;
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

	public void addConfigurationDevice(ConfigurationDevice configurationDevice) {
		this.configurationDeviceList.add(configurationDevice);
		configurationDevice.setDeviceInstance(this);
	}

	public void removeConfigurationDevice(ConfigurationDevice configurationDevice) {
		this.configurationDeviceList.remove(configurationDevice);
		configurationDevice.setDeviceInstance(null);
		
	}

	public void addTraining(Training training) {
		this.trainingList.add(training);
		training.setDeviceInstance(this);	
	}



	
	
}
