package pl.krzysztofskul.smnsh2.project.device.modality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.smnsh2.project.device.DevicePortfolio;
import pl.krzysztofskul.smnsh2.project.device.DevicePortfolioRepo;

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

	public List<DevicePortfolio> loadAll() {
		return devicePortfolioRepo.findAll();
	}
	
}
