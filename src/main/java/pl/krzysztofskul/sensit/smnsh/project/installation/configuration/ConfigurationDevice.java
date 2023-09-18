package pl.krzysztofskul.sensit.smnsh.project.installation.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import pl.krzysztofskul.sensit.smnsh.project.device.DeviceInstance;

@Entity
public class ConfigurationDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
				name = "configurationdevice_part",
				joinColumns = @JoinColumn(name = "configurationdevice_id"),
				inverseJoinColumns = @JoinColumn(name = "part_id")
			)
	private List<Part> partList = new ArrayList<Part>();
	
	@OneToOne(mappedBy = "configurationDevice")
	private DeviceInstance deviceInstance;

	public ConfigurationDevice() {
	}
	
	/**
	 * @param deviceInstance
	 */
	public ConfigurationDevice(DeviceInstance deviceInstance) {
		this.deviceInstance = deviceInstance;
	}
	
	/**
	 * @param partList
	 */
	public ConfigurationDevice(List<Part> partList) {
		this.partList = partList;
	}

	/**
	 * @param partList
	 * @param deviceInstance
	 */
	public ConfigurationDevice(List<Part> partList, DeviceInstance deviceInstance) {
		this.partList = partList;
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
	 * @return the partList
	 */
	public List<Part> getPartList() {
		return partList;
	}

	/**
	 * @param partList the partList to set
	 */
	public void setPartList(List<Part> partList) {
		this.partList = partList;
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
