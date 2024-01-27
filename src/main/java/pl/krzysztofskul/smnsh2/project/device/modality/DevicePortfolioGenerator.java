package pl.krzysztofskul.smnsh2.project.device.modality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.smnsh2.project.device.DevicePortfolio;

@Service
public class DevicePortfolioGenerator {

	private List<DevicePortfolio> devicePortfolioListDemo = new ArrayList<DevicePortfolio>();
	
	private ModalityService modalityService;
	private DevicePortfolioService devicePortfolioService;
	
	/**
	 * @param modalityService
	 */
	@Autowired
	public DevicePortfolioGenerator(ModalityService modalityService, DevicePortfolioService devicePortfolioService) {
		this.modalityService = modalityService;
		this.devicePortfolioService = devicePortfolioService;
	}



	/**
	 * Creates demo/test list of portfilio devices;
	 * Save to private parameter and to the DB;
	 * @return List of portfolio devices: List<DevicePortfolio>
	 */
	public List<DevicePortfolio> initDevicePortfolioListDemo() {
		
		if (devicePortfolioListDemo.size() == 0) {
			for (Modality modality : modalityService.loadAll()) {
				for (int i = 0; i < 3; i++) {
					DevicePortfolio dp = new DevicePortfolio(modality.getNameTrade(), LoremIpsum.getInstance().getTitle(1));
					devicePortfolioListDemo.add(dp);
					devicePortfolioService.saveAndReturn(dp);
				}
			}
		}
		
		return devicePortfolioListDemo;
	}



	public DevicePortfolio getRandomDevicePortfolio() {	
		return devicePortfolioListDemo.get(new Random().nextInt(devicePortfolioListDemo.size()));
	}
	
}
