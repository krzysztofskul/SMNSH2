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

	private List<DevicePortfolio> devicePortfolioListTest = new ArrayList<DevicePortfolio>();
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
	 * Creates test list of portfolio devices;
	 * Save to private parameter and to the DB;
	 * @return List of portfolio devices: List<DevicePortfolio>
	 */
	public List<DevicePortfolio> initDevicePortfolioListTest() {
		
		if (devicePortfolioListTest.size() == 0) {
			for (Modality modality : modalityService.loadAll()) {
				for (int i = 0; i < 3; i++) {
					DevicePortfolio dp = new DevicePortfolio(modality.getNameTrade(), LoremIpsum.getInstance().getTitle(1));
					devicePortfolioListTest.add(dp);
					devicePortfolioService.saveAndReturn(dp);
				}
			}
		}
		
		return devicePortfolioListTest;
	}

	public DevicePortfolio getRandomDevicePortfolioTest() {	
		return devicePortfolioListTest.get(new Random().nextInt(devicePortfolioListTest.size()));
	}

	
	/**
	 * Creates demo list of portfolio devices;
	 * Save to private parameter and to the DB;
	 * @return List of portfolio devices: List<DevicePortfolio>
	 */
	public List<DevicePortfolio> initDevicePortfolioListDemo() {
		
		if (devicePortfolioListDemo.size() == 0) {
			for (Modality modality : modalityService.loadAll()) {
				switch (modality.getCode()) {
					case "AT" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Ohm"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Z floor"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Z ceiling"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Q floor"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Q ceiling"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Phenomeno"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Zea 2-plane"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Ionic floor"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Ionic ceiling"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Ionic 2-plane"));
						break;
					}
					case "CT" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "gN"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "gT"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "gA"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "gU"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "gS"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "gO"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Xct"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Xcd"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "ConF"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Def"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "E"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "DF"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "F"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "D"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Em"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "S"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "P"));
						break;
					}
					case "MR" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Fee M"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Fee S"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Aer"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Alt"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Am"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Soulo"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Aviant"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Sempre"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumix"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Presmo"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Sky"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Spec"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "V"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Trr"));
						break;
					}
					case "SU" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Cyoss"));
						break;
					}
					case "MI" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Symbioso"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Inteo"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "T"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "S"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Harizo"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Visyo"));
						break;
					}
					case "XPF" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno Max"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno A"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno L"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno F"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno I"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno S"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Lumeeno S"));
						break;
					}
					case "XPR" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Multi Rx"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Multi Implict"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Multi Implict C"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Multi Implict E"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Y xee"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Y max"));
						break;
					}
					case "XPM" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "B"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "R"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "I"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "F"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "S"));
						break;
					}
					case "XPU" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Modulo"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Omnic"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Modulix"));
						break;
					}
					case "US" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Acso"));
						break;
					}
					case "RO" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "X"));
						break;
					}
					case "SY" : {
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Vye"));
						devicePortfolioListDemo.add(new DevicePortfolio(modality.getNameTrade(), "Plie"));
						break;
					}
				}
			}
		}
		for (DevicePortfolio devicePortfolio : devicePortfolioListDemo) {
			devicePortfolioService.saveAndReturn(devicePortfolio);
		}
		devicePortfolioListDemo = devicePortfolioService.loadAll();
		return devicePortfolioListDemo;
	}
	
	public DevicePortfolio getRandomDevicePortfolioDemo() {	
		return devicePortfolioListDemo.get(new Random().nextInt(devicePortfolioListDemo.size()));
	}
	
}
