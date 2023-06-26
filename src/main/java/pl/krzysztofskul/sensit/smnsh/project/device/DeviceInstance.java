package pl.krzysztofskul.sensit.smnsh.project.device;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pl.krzysztofskul.sensit.smnsh.project.Project;

@Entity
public class DeviceInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String serialNo;	
	
	@ManyToOne
	private DevicePortfolio devicePortfolio;

	private String dateOfDelivery;
	
	@OneToOne
	private Project project;

	
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
		this.project = project;
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
	
}
