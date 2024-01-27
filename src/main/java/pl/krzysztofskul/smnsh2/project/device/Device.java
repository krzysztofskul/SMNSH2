package pl.krzysztofskul.smnsh2.project.device;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import pl.krzysztofskul.smnsh2.project.device.modality.Modality;

/*
 * TODO 2023-05-25 create Device entity as a superclass for DevicePortfolio and DeviceInstance
 */
@MappedSuperclass
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Modality modality;
	
	private String modelName;
	
}
