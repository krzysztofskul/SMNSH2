package pl.krzysztofskul.sensit.smnsh.project.device.modality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.project.device.DevicePortfolio;
import pl.krzysztofskul.sensit.smnsh.project.device.DevicePortfolioRepo;

@Service
public class DevicePortfolioService {

	private DevicePortfolioRepo devicePortfolioRepo;
		
	/**
	 * @param devicePortfolioRepo
	 */
	@Autowired
	public DevicePortfolioService(DevicePortfolioRepo devicePortfolioRepo) {
		this.devicePortfolioRepo = devicePortfolioRepo;
	}

	public DevicePortfolio saveAndReturn(DevicePortfolio devicePortfolio) {
		return devicePortfolioRepo.save(devicePortfolio);
	}
	
}
