package pl.krzysztofskul.smnsh2.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategory;
import pl.krzysztofskul.smnsh2.company.CompanyCategory.CompanyCategoryEnum;
import pl.krzysztofskul.smnsh2.company.ContactDetails.ContactDetails;
import pl.krzysztofskul.smnsh2.company.ContactDetails.ContactDetailsDemoGenerator;
import pl.krzysztofskul.smnsh2.company.Employee.Employee;
import pl.krzysztofskul.smnsh2.init.InitDataGenerator;

@Service
public class CompanyDemoGenerator implements InitDataGenerator<Company> {

	private ContactDetailsDemoGenerator contactDetailsDemoGenerator;
	
	private List<Company> companyDemoList = new ArrayList<Company>();	
	
	/**
	 * @param contactDetailsDemoGenerator
	 */
	@Autowired
	public CompanyDemoGenerator(ContactDetailsDemoGenerator contactDetailsDemoGenerator) {
		this.contactDetailsDemoGenerator = contactDetailsDemoGenerator;
	}



	@Override
	public List<Company> initDataAndReturn() {
		companyDemoList.clear();
		
		for (CompanyCategoryEnum companyCatEnum : CompanyCategoryEnum.values()) {
			
				switch (companyCatEnum) {
				case INVESTOR: {
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"ENL-MED SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"LX-MED SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"MED-CVR SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"AHOPE-MED SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
							));
					break;
					}
				case CUSTOMER: {
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"ENL-MED SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"LX-MED SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"MED-CVR SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"AHOPE-MED SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					break;
					}
				case SUPPLIER: {
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"GEES GmbH",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					break;
					}
				case SUBCONTRACTOR: {
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"WR-BUD MEDICAL S.A.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"MQ MEDICAL S.A.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"G MEDICAL S.A.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
							));
					break;
					}
				case SUBCONTRACTOR_GENERAL: {
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"WR-BUD S.A.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"BUDIMED S.A.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					break;
					}
				case SUBCONTRACTOR_ROOM_ADAPTATION: {
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"REM-BUD S.C.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"FLEX-MEDICAL SP. Z O.O.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
					));
					companyDemoList.add(new Company(
							Long.valueOf("0"),
							"FEEGO S.C.",
							companyCatEnum,
							new ArrayList<Employee>(),
							contactDetailsDemoGenerator.getDemoContactDetails()
							));
					break;
					}
				default:
					break;
				}
		}
		for (Company company : companyDemoList) {
			Random random = new Random();
			switch (random.nextInt(LabelEnum.values().length)) {
				case 0:
					company.setLabelEnum(LabelEnum.GREEN);
					break;
				case 1:
					company.setLabelEnum(LabelEnum.YELLOW);
					break;
				case 2:
					company.setLabelEnum(LabelEnum.RED);
					break;
				case 3:
					company.setLabelEnum(LabelEnum.BLACK);
					break;
				default:
					company.setLabelEnum(LabelEnum.GRAY);
					break;
			}
		}
		return companyDemoList;
	}

	
	
}
