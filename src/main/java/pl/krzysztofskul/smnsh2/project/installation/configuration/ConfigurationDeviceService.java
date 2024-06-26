package pl.krzysztofskul.smnsh2.project.installation.configuration;

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

	public ConfigurationDevice loadById(Long configurationDeviceId) {
		return configurationDeviceRepo.findById(configurationDeviceId).get();

	}
	
	
}
