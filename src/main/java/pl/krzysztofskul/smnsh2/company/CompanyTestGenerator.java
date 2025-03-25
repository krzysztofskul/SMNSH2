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
public class CompanyTestGenerator implements InitDataGenerator<Company> {

	private ContactDetailsDemoGenerator contactDetailsDemoGenerator;
	
	private List<Company> companyTestList = new ArrayList<Company>();	
	
	/**
	 * @param contactDetailsDemoGenerator
	 */
	@Autowired
	public CompanyTestGenerator(ContactDetailsDemoGenerator contactDetailsDemoGenerator) {
		this.contactDetailsDemoGenerator = contactDetailsDemoGenerator;
	}



	@Override
	public List<Company> initDataAndReturn() {
		for (CompanyCategoryEnum companyCatEnum : CompanyCategoryEnum.values()) {
			for (int i = 0; i < new Random().nextInt(5)+5; i++) {
				
				String companyName = LoremIpsum.getInstance().getTitle(1);
				switch (companyCatEnum) {
				case INVESTOR: {
					companyName = companyName + " Investments Co.";
					break;
					}
				case CUSTOMER: {
					companyName = companyName + "-MED S.A.";
					break;
					}
				case SUPPLIER: {
					companyName = companyName + " TRANS GmbH";
					break;
					}
				case SUBCONTRACTOR: {
					companyName = companyName + " Service Ltd.";
					break;
					}
				case SUBCONTRACTOR_GENERAL: {
					companyName = companyName + " Services Sp. z o.o.";
					break;
					}
				case SUBCONTRACTOR_ROOM_ADAPTATION: {
					companyName = companyName + " BUD S.C.";
					break;
					}
				default:
					break;
				}

				Company company = new Company(
						Long.valueOf("0"),
						companyName,
						companyCatEnum,
						new ArrayList<Employee>(),
						contactDetailsDemoGenerator.getDemoContactDetails()
					);
				
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
				companyTestList.add(company);
			}
		}
		return companyTestList;
	}

	
	
}
