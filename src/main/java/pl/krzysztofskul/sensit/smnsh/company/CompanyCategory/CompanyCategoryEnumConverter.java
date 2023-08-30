package pl.krzysztofskul.sensit.smnsh.company.CompanyCategory;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompanyCategoryEnumConverter implements Converter<String, CompanyCategoryEnum> {

	@Override
	public CompanyCategoryEnum convert(String companyCategoryEnumString) {
		switch (companyCategoryEnumString) {
			case "INVESTOR":{
				return CompanyCategoryEnum.INVESTOR;
				}
			case "CUSTOMER":{
				return CompanyCategoryEnum.CUSTOMER;
			}
			case "SUBCONTRACTOR":{
				return CompanyCategoryEnum.SUBCONTRACTOR;
			}
			case "SUBCONTRACTOR_GENERAL":{
				return CompanyCategoryEnum.SUBCONTRACTOR_GENERAL;
			}
			case "SUBCONTRACTOR_ROOM_ADAPTATION":{
				return CompanyCategoryEnum.SUBCONTRACTOR_ROOM_ADAPTATION;
			}
			case "SUPPLIER":{
				return CompanyCategoryEnum.SUPPLIER;
			}
			default:{
				return CompanyCategoryEnum.SUBCONTRACTOR;
			}
		}
	}

	
	
}
