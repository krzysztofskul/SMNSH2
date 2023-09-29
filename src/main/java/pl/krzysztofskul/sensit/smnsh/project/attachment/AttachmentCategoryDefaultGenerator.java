package pl.krzysztofskul.sensit.smnsh.project.attachment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.krzysztofskul.sensit.smnsh.init.InitDataGenerator;

@Service
public class AttachmentCategoryDefaultGenerator implements InitDataGenerator<AttachmentCategory> {
	
	public static List<AttachmentCategory> defaultAttachemntCategories;
	
	@Override
	/**
	 * Creates and returns default attachment categories
	 * @return list of default attachment categories
	 */
	public List<AttachmentCategory> initDataAndReturn() {
		
		List<AttachmentCategory> acList = new ArrayList<AttachmentCategory>();
		
		acList.add(new AttachmentCategory("DOC-GENERAL", "DOKUMENT OGÓLNY", "GENERAL DOCUMENT"));
		acList.add(new AttachmentCategory("DOC-OFFER", "OFERTA", "OFFER"));
		acList.add(new AttachmentCategory("DOC-CONTRACT", "UMOWA", "CONTRACT"));
		acList.add(new AttachmentCategory("PROJECT", "PROJEKT", "PROJECT"));
		acList.add(new AttachmentCategory("DOC-PROTOCOL-ACCEPT", "PROTOKÓŁ ODBIORU", "ACCEPTANCE PROTOCOL"));
		acList.add(new AttachmentCategory("DOC-INVOICE", "FAKTURA", "INVOICE"));
		acList.add(new AttachmentCategory("DOC-ORDER", "ZAMÓWIENIE", "ORDER"));
		acList.add(new AttachmentCategory("KPDS", "KPDS", "KPDS"));
		
		defaultAttachemntCategories = acList;
		
		return defaultAttachemntCategories;
		
	}

	
}
