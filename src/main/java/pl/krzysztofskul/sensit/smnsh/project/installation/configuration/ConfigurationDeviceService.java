package pl.krzysztofskul.sensit.smnsh.project.installation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationDeviceService {

	private ConfigurationDeviceRepo configurationDeviceRepo;

	/**
	 * @param configurationDeviceRepo
	 */
	@Autowired
	public ConfigurationDeviceService(ConfigurationDeviceRepo configurationDeviceRepo) {
		this.configurationDeviceRepo = configurationDeviceRepo;
	}

	public void deleteConfigurationDeviceById(Long configurationDeviceId) {
		configurationDeviceRepo.deleteById(configurationDeviceId);
	}
	
	
}
