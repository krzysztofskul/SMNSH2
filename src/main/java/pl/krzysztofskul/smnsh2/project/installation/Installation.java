package pl.krzysztofskul.smnsh2.project.installation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import pl.krzysztofskul.smnsh2.company.Company;
import pl.krzysztofskul.smnsh2.company.ContactDetails.Address.Address;
import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.device.Device;
import pl.krzysztofskul.smnsh2.project.device.DeviceInstance;
import pl.krzysztofskul.smnsh2.project.device.DevicePortfolio;
import pl.krzysztofskul.smnsh2.project.installation.configuration.ConfigurationDevice;
import pl.krzysztofskul.smnsh2.project.training.Training;
import pl.krzysztofskul.smnsh2.user.User;

@Entity
public class Installation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy="installation")
	private Project project;
	
	/*
	 * Device delivered for installation to the customer
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private DeviceInstance deviceInstance = new DeviceInstance();
	
	/*
	 * Customer - company which is owner of the place of installation
	 */
	@ManyToOne
	private Company customer;
	
	/*
	 * The place of the installation - if is different or specified more precisely than in company data 
	 */
	@OneToOne
	private Address address;
	
	/*
	 * The main project manager
	 */
	@ManyToOne
	private User projectManager;
	
	@ManyToMany(mappedBy = "projectManagersAdd")
	private List<User> projectManagersAdditional = new ArrayList<User>();
	
	//private Status status;
	//private Milestones status; //?
	
	/**
	 * CONSTRUCTOR
	 */
	public Installation() {
	}
	
	/**
	 * CONSTRUCTOR
	 * @param deviceInstance
	 */
	public Installation(DeviceInstance deviceInstance) {
		this.deviceInstance = deviceInstance;
	}

	/**
	 * CONSTRUCTOR
	 * @param project
	 * @param deviceInstance
	 */
	public Installation(Project project, DeviceInstance deviceInstance) {
		this.project = project;
		this.deviceInstance = deviceInstance;
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
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	 * @return the projectManagersAdditional
	 */
	public List<User> getProjectManagersAdditional() {
		return projectManagersAdditional;
	}

	/**
	 * @param projectManagersAdditional the projectManagersAdditional to set
	 */
	public void setProjectManagersAdditional(List<User> projectManagersAdditional) {
		this.projectManagersAdditional = projectManagersAdditional;
	}
	
}
