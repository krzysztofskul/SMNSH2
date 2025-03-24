package pl.krzysztofskul.smnsh2.kpds;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectService;
import pl.krzysztofskul.smnsh2.project.device3rd.Device3rd;
import pl.krzysztofskul.smnsh2.project.device3rd.Device3rdService;
import pl.krzysztofskul.smnsh2.project.training.Training;

@Service
@Transactional
public class KpdsService {
	
	private static String path_kpds_generated = "src/main/resources/static/kpds";
	private KpdsRepo kpdsRepo;
	private ProjectService projectService;
	private Device3rdService device3rdService;
	
	@Autowired
	public KpdsService(
			KpdsRepo kpdsRepo
			, ProjectService projectService
			, Device3rdService device3rdService
			) {
		super();		
		this.kpdsRepo = kpdsRepo;
		this.projectService = projectService;
		this.device3rdService = device3rdService;
	}

	public void generateKpds(Long projectId) {
		//load project connected with kpds
		Project project = projectService.loadById(projectId);
		
		//create new kpds entity and save to DB
		Kpds kpds = new Kpds(project);
		kpds = this.save(kpds);
		
		String inwestor = kpds.getProject().getInvestor().getName();
		inwestor = inwestor.replace("\n", " ").replace("\r", " ");
		
//		List<String> devices = new ArrayList<String>();
//		for (Device device : kpds.getProject().getDeviceList()) {
//			devices.add(device.getDeviceCategory().getName() + " "+ device.getModel());
//		}
		 
		
		// create and save new kpds.pdf document from kpds entity
		try {	
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);
//			PDType0Font font = PDType0Font.load(document, new File("c:/windows/fonts/times.ttf"));
			PDType0Font font = PDType0Font.load(document, new File("c:/windows/fonts/SiemensSans_Prof_Roman.ttf"));
			PDType0Font fontItalic = PDType0Font.load(document, new File("c:/windows/fonts/SiemensSans_Prof_Italic.ttf"));
			PDType0Font fontBold = PDType0Font.load(document, new File("c:/windows/fonts/SiemensSans_Prof_Bold.ttf"));
			PDType0Font fontBoldItalic = PDType0Font.load(document, new File("c:/windows/fonts/SiemensSans_Prof_BoldItalic.ttf"));

			PDImageXObject logo = PDImageXObject.createFromFile("src/main/resources/static/pics/logo/logo00.jpg", document);
			contentStream.drawImage(logo, 10, 750);
			
			writeText(contentStream, fontBold, 20, 275, 740, "KPDS");			

			
			/*
			 * section 1
			 */
			drawLine(contentStream, 10, 730, 595, 730);
			writeText(contentStream, fontBold, 14, 150, 710, "1. INFORMACJE OGÓLNE");
			drawLine(contentStream, 10, 700, 595, 700);
			
			/*
			 * section 1a
			 */		
			this.writeText(contentStream, fontBold, 10, 20, 680, "Nazwa użytownika: ");
			this.writeText(contentStream, font, 10, 170, 680, kpds.getProject().getCustomer().getName());
			
			this.writeText(contentStream, fontBold, 10, 20, 660, "Ulica: ");
			this.writeText(contentStream, font, 10, 170, 660, kpds.getProject().getCustomer().getContactDetails().getAddress().getStreetName() + " " + kpds.getProject().getCustomer().getContactDetails().getAddress().getStreetNo());
			
			
			this.writeText(contentStream, fontBold, 10, 20, 640, "Kod pocztowy / Miejscowość: ");
			String zipcode = kpds.getProject().getCustomer().getContactDetails().getAddress().getZipCode();
			zipcode = zipcode.substring(0, 2) + "-" + zipcode.substring(2);
			this.writeText(contentStream, font, 10, 170, 640,  zipcode + " " + kpds.getProject().getCustomer().getContactDetails().getAddress().getCity());
			
			this.writeText(contentStream, fontBold, 10, 20, 620, "Płatnik (inwestor): ");
			this.writeTextWithMaxLength(contentStream, font, 10, 170, 620, inwestor, 36);
			
			/*
			 * section 1b
			 */
			this.writeText(contentStream, fontBold, 10, 325, 680, "Nr projektu: ");
			this.writeText(contentStream, font, 10, 435, 680, kpds.getProject().getCode());

			this.writeText(contentStream, fontBold, 10, 325, 660, "Kierownik projektu: ");
			this.writeText(contentStream, font, 10, 435, 660, kpds.getProject().getProjectManager().getNameFirst()+" "+kpds.getProject().getProjectManager().getNameLast());
			
//			this.writeText(contentStream, fontBold, 10, 325, 640, "Z-ca projektu: ");
//			this.writeText(contentStream, font, 10, 435, 640, kpds.getProject().getProjectManagerAdd().getNameFirst()+" "+kpds.getProject().getProjectManagerAdd().getNameLast());
			
			//this.writeText(contentStream, fontBold, 10, 325, 640, "SAP: ");
			//this.writeText(contentStream, font, 10, 435, 640, kpds.getProject().getInvestor().getSapInfo().getSapNo());
			

			
			
//			contentStream.beginText();
//			contentStream.setFont(font, 11);
//			contentStream.newLineAtOffset(25, 600);
//			if (kpds.getProject().getAgreementNo() == null ) {
//				contentStream.showText("Numer umowy: B/D");
//			} else {
//				contentStream.showText("Numer umowy: " + kpds.getProject().getAgreementNo());	
//			}
//			contentStream.endText();

			/*
			 * section 2
			 */
			drawLine(contentStream, 10, 560, 595, 560);
			writeText(contentStream, fontBold, 14, 150, 540, "2. DANE APARATU");
			drawLine(contentStream, 10, 530, 595, 530);

			contentStream.beginText();
			contentStream.setFont(font, 11);
			contentStream.newLineAtOffset(25, 510);
			contentStream.showText(kpds.getProject().getDevicePortfolio().getModality() + " " + kpds.getProject().getDevicePortfolio().getModelName());
			contentStream.endText();
			
			this.writeText(contentStream, fontBold, 10, 325, 510, "Gwarancja: ");
			this.writeText(contentStream, font, 10, 435, 510, "b.d.");

			this.writeText(contentStream, fontBold, 10, 325, 490, "Data odbioru: ");
			this.writeText(contentStream, font, 10, 435, 490, "b.d.");
			
			this.writeText(contentStream, fontBold, 10, 325, 470, "Czas reakcji/naprawy: ");
			this.writeText(contentStream, font, 10, 435, 470, "b.d.");
			
			/*
			 * section 3
			 */
			drawLine(contentStream, 10, 450, 595, 450);
			writeText(contentStream, fontBold, 14, 150, 430, "3. SPRZĘT OBCY");
			drawLine(contentStream, 10, 420, 595, 420);
			
			int y = 400;
			for (Device3rd device3rd : device3rdService.getAllByProjectId(projectId)) {
				
				contentStream.beginText();
				contentStream.setFont(font, 11);
				contentStream.newLineAtOffset(25, y);
				contentStream.showText(device3rd.getCategoryNamePl()+ " " + device3rd.getManufacturer() + " " + device3rd.getModelName());
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 10);
				contentStream.newLineAtOffset(325, y);
				contentStream.showText("nr seryjny: ");
				contentStream.endText();
				
				contentStream.beginText();
				contentStream.setFont(font, 10);
				contentStream.newLineAtOffset(325, y-15);
				contentStream.showText("gwarancja: ");
				contentStream.endText();
				
				contentStream.beginText();
				contentStream.setFont(font, 10);
				contentStream.newLineAtOffset(325, y-30);
				contentStream.showText("data przekazania: ");
				contentStream.endText();
				
				y = y - 50;
			}
			
			/*
			 * section 4
			 */
			drawLine(contentStream, 10, 250, 595, 250);
			writeText(contentStream, fontBold, 14, 150, 230, "4. WYKONAWCY ADAPTACJI");
			drawLine(contentStream, 10, 220, 595, 220);
			
			contentStream.beginText();
			contentStream.setFont(font, 11);
			contentStream.newLineAtOffset(25, 200);
			contentStream.showText(kpds.getProject().getSubcontractorForRoomAdaptation().getName());
			contentStream.endText();

//			/*
//			 * section 5
//			 */
//			drawLine(contentStream, 10, 260, 585, 260);
//			writeText(contentStream, fontBold, 14, 150, 240, "5. INFORMACJE DODATKOWE");
//			drawLine(contentStream, 10, 230, 585, 230);

			/*
			 * section 5
			 */
			drawLine(contentStream, 10, 180, 595, 180);
			writeText(contentStream, fontBold, 14, 150, 160, "5. SZKOLENIA");
			drawLine(contentStream, 10, 150, 595, 150);

			y = 130;
			for (Training training : kpds.getProject().getTrainingList()) {
				
				contentStream.beginText();
				contentStream.setFont(font, 11);
				contentStream.newLineAtOffset(25, y);
				contentStream.showText(training.getDescription());
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 10);
				contentStream.newLineAtOffset(325, y);
				contentStream.showText("liczba dni: "+ training.getAmountOfDays());
				contentStream.endText();
				
				y = y - 15;
			}
			
			/*
			 * footer of the page
			 */
			drawLine(contentStream, 10, 50, 595, 50);
			
			contentStream.beginText();
			contentStream.setFont(fontItalic, 11);
			contentStream.newLineAtOffset(475, 35);
			contentStream.showText(kpds.getDateTimeGenerated().toLocalDate().toString());
			contentStream.endText();
			
			contentStream.close();

			document.save(path_kpds_generated+"/kpds-projectId_"+kpds.getProject().getId()+".pdf");
			document.close();
			
//			loggerProjectService.log(project, LocalDateTime.now(ZoneId.of("Europe/Warsaw")), "KPDS created", "Utworzono KPDS", null);
//			
//			emailServiceImpl.sendHtmlMessageWithAttachment(kpdsEmailSendTo, "KPDS", "Test kpds email.", "kpds-projectId_"+kpds.getProject().getId()+".pdf", new java.io.File(path_kpds_generated+"/kpds-projectId_"+kpds.getProject().getId()+".pdf"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeTextWithMaxLength(
			PDPageContentStream contentStream, 
			PDType0Font fontType,
			int fontSize,
			int xStart,
			int yStart,
			String text,
			int maxLength
			) throws IOException 
		{		
		String textMaxLength = null;
//		String textLeft = null;
		if (text.length() > maxLength) {
			textMaxLength = text.substring(0, maxLength);
			text = text.substring(maxLength);
			writeText(contentStream, fontType, fontSize, xStart, yStart, textMaxLength);	
			writeTextWithMaxLength(contentStream, fontType, fontSize, xStart, yStart-15, text, maxLength);
		} else {
			writeText(contentStream, fontType, fontSize, xStart, yStart, text);	
		}
		
	}
	
	private void writeText(
			PDPageContentStream contentStream, 
			PDType0Font fontType,
			int fontSize,
			int xStart,
			int yStart,
			String text
			) throws IOException {
		contentStream.beginText();
		contentStream.setFont(fontType, fontSize);
		contentStream.newLineAtOffset(xStart, yStart);
		contentStream.showText(text);
		contentStream.endText();
	}

	private void drawLine(PDPageContentStream contentStream, int xStart, int yStart, int xEnd, int yEnd) throws IOException {
		contentStream.moveTo(xStart, yStart);
		contentStream.lineTo(xEnd, yEnd);
		contentStream.stroke();
	}

	public Kpds save(Kpds kpds) {
		return kpdsRepo.save(kpds);
	}

	public Kpds loadById(Long kpdsId) {
		Kpds kpds = kpdsRepo.findById(kpdsId).get();
		Hibernate.initialize(kpds.getProject());
		return kpds;
	}
	
}
