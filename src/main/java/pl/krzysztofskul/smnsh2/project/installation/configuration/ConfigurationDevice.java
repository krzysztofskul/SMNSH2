package pl.krzysztofskul.smnsh2.project.installation.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import pl.krzysztofskul.smnsh2.project.device.DeviceInstance;

@Entity
public class ConfigurationDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate created;
	
	/*
	 * TODO: move to calculation class
	 */
	@Column(length = 1024)
	private String linkToHdd;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(
				name = "configurationdevice_part",
				joinColumns = @JoinColumn(name = "configurationdevice_id"),
				inverseJoinColumns = @JoinColumn(name = "part_id")
			)
	private List<Part> partList = new ArrayList<Part>();
	
	@ManyToOne
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

	public ConfigurationDevice(LocalDate created, String linkToHdd, List<Part> partList) {
		this.created = created;
		this.linkToHdd = linkToHdd;
		this.partList = partList;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the created
	 */
	public LocalDate getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDate created) {
		this.created = created;
	}

	/**
	 * @return the linkToHdd
	 */
	public String getLinkToHdd() {
		return linkToHdd;
	}

	/**
	 * @param linkToHdd the linkToHdd to set
	 */
	public void setLinkToHdd(String linkToHdd) {
		this.linkToHdd = linkToHdd;
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
