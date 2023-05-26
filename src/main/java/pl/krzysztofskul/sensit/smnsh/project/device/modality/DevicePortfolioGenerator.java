package pl.krzysztofskul.sensit.smnsh.project.device.modality;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.sensit.smnsh.project.device.DevicePortfolio;

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
					devicePortfolioService.saveAndReturn(new DevicePortfolio(modality.getName(), LoremIpsum.getInstance().getTitle(1)));
				}
			}
		}
		
		return devicePortfolioListDemo;
	}
	
}