package pl.krzysztofskul.sensit.smnsh.company.CompanyCategory;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompanyCategoryEnumConverter implements Converter<String, CompanyCategoryEnum> {

	@Override
	public CompanyCategoryEnum convert(String companyCategoryEnumString) {
		switch (companyCategoryEnumString) {
			case "investor":{
					return CompanyCategoryEnum.INVESTOR;
				}
			case "customer":{
				return CompanyCategoryEnum.CUSTOMER;
			}
			case "subcontractor":{
				return CompanyCategoryEnum.SUBCONTRACTOR;
			}
			case "subcontractor-general":{
				return CompanyCategoryEnum.SUBCONTRACTOR_GENERAL;
			}
			case "subcontractor-room-adaptation":{
				return CompanyCategoryEnum.SUBCONTRACTOR_ROOM_ADAPTATION;
			}
			case "supplier":{
				return CompanyCategoryEnum.SUPPLIER;
			}
	
			default:{
				return CompanyCategoryEnum.SUBCONTRACTOR;
			}
		}
	}

	
	
}
