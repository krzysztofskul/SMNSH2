package pl.krzysztofskul.sensit.smnsh.project.installation.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Part {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private String partNo;
	
	private Long quantity;
	
	@ManyToMany(mappedBy = "partList")
	private List<ConfigurationDevice> configurationDeviceList = new ArrayList<ConfigurationDevice>();
	
	/**
	 * 
	 */
	public Part() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param description
	 */
	public Part(String description) {
		this.description = description;
	}

	/**
	 * @param description
	 * @param partNo
	 * @param quantity
	 * @param configurationDevice
	 */
	public Part(String description, String partNo, Long quantity) {
		this.description = description;
		this.partNo = partNo;
		this.quantity = quantity;
	}

	public Part(ConfigurationDevice configurationDevice, String description) {
		this.configurationDeviceList.add(configurationDevice);
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
	 * @return the partNo
	 */
	public String getPartNo() {
		return partNo;
	}

	/**
	 * @param partNo the partNo to set
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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




	
}
