package pl.krzysztofskul.sensit.smnsh.company.ContactDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.sensit.smnsh.company.ContactDetails.Address.Address;
import pl.krzysztofskul.sensit.smnsh.init.InitDataGenerator;

@Service
public class ContactDetailsDemoGenerator implements InitDataGenerator<ContactDetails> {

	private List<ContactDetails> demoContactDetailsList = new ArrayList<ContactDetails>();
	
	@Override
	public List<ContactDetails> initDataAndReturn() {
		
		demoContactDetailsList.clear();
		
		demoContactDetailsList.add(
					
					new ContactDetails(
								Long.valueOf("0"),
								getDemoAddress(),
								LoremIpsum.getInstance().getPhone(),
								getDemoPhoneNumbers(),
								"example@example.com",
								getDemoEmailAdresses(),
								"www.example.com"
							)
				
				);
		
		return demoContactDetailsList;
	}
	
	public ContactDetails getDemoContactDetails() {
		initDataAndReturn();
		return demoContactDetailsList.get(new Random().nextInt(this.demoContactDetailsList.size()));
	}
	
	private Address getDemoAddress() {
		Address address = new Address();
		address.setCountry(LoremIpsum.getInstance().getCountry());
		address.setZipCode(LoremIpsum.getInstance().getZipCode());
		address.setCity(LoremIpsum.getInstance().getCity());
		address.setStreetName(LoremIpsum.getInstance().getName());
		address.setStreetNo(String.valueOf(new Random().nextInt(200)+1));
		return address;
	}
	
	private Map<String, String> getDemoPhoneNumbers() {
		Map<String, String> phoneNumbers = new HashMap<String, String>();
		phoneNumbers.put("Nr tel. stacjonarny", LoremIpsum.getInstance().getPhone());
		phoneNumbers.put("Nr tel. komórkowy", LoremIpsum.getInstance().getPhone());
		return phoneNumbers;
	}
	
	private Map<String, String> getDemoEmailAdresses() {
		Map<String, String> emailAddresses = new HashMap<String, String>();
		emailAddresses.put("Email sekretariat zarządu", LoremIpsum.getInstance().getEmail());
		emailAddresses.put("Email dział techniczny", LoremIpsum.getInstance().getEmail());
		return emailAddresses;
	}
	
}
