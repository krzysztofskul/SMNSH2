package pl.krzysztofskul.sensit.smnsh.importdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Singleton
public class ImportData {
	
	/*
	 * PARAMS.
	 */
	
	private static ImportData importData;
	private static FileInputStream fileInputStream = null;
	private static Workbook workbook = null;
	
	/**
	 * Map<String, String[]> (sheetName, {rowNo, colNo})
	 */
	private Map<String, String[]> cellsToImportFromCalculationXlsFile = new HashMap<String, String[]>();
	private Map<String, String[]> cellsToImportFromDeviceTestPortfolio= new HashMap<String, String[]>();
	
	private String pathProjectsToImport = "D:\\SMNSH\\karta_projektu\\Projekty";
	private String pathInitDataDevices = "D:\\SMNSH\\karta_projektu\\Init\\device_test_portfolio.xlsm";
	private Map<LocalDateTime, String> logs = new HashMap<LocalDateTime, String>();
	
	/*
	 * CONSTR.
	 */
	
	private ImportData() {
		super();
		
		setCellsToImportFromCalculationXlsFile();		
		setCellsToImportFromDeviceTestPortfolio();		
		
	}

	public static ImportData getImportDataSingleton() {
		if (importData == null) {
			importData = new ImportData();
		}
		
		return importData;
	}

	/*
	 * GETTERS AND SETTERS
	 */
	
	public String getPathProjectsToImport() {
		return pathProjectsToImport;
	}

	public void setPathProjectsToImport(String pathProjectsToImport) {
		this.pathProjectsToImport = pathProjectsToImport;
	}
	
	public static FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public static void setFileInputStream(FileInputStream fileInputStream) {
		ImportData.fileInputStream = fileInputStream;
	}

	public static Workbook getWorkbook() {
		return workbook;
	}

	public static void setWorkbook(Workbook workbook) {
		ImportData.workbook = workbook;
	}


	/*
	 * METHODS
	 */
	
	/**
	 * 
	 * @param path: path to the folder with projects created by sales rep. (or copy of them)
	 * @return: list of all project codes using by sales rep.
	 */
	public List<String> importProjectsFolderNames(String path) {
		List<String> folderNameList = new ArrayList<String>();
		
		File rootFolder = new File(path);
		
		File[] fileList = rootFolder.listFiles();
		
		for (File file : fileList) {
			folderNameList.add(file.getName());
		}
		
		return folderNameList;
	}
	
	/**
	 * 
	 * @return list of all paths to calculation files from default location
	 */
	public List<String> getCalculationFilesFullPath() {
		
		List<String> pathsToCalculationFiles = new ArrayList<String>();
		
			for (String projectFolder : this.importProjectsFolderNames(ImportData.getImportDataSingleton().pathProjectsToImport)) {
				
				/*
				 * declaring folder with sls documentation
				 */
				String folder01name;
				try {
					File folder01 = new File(
							ImportData.getImportDataSingleton().pathProjectsToImport+"//"+projectFolder+"//01. Dokumenty_SLS KFP"
							); // project folder
					File[] files = folder01.listFiles();
					
					/*
					 * search and get calculation file for array files
					 */
					
					 for (int i = 0; i < files.length; i++) { 
						 if (files[i].getName().contains("Kalkulacja") && files[i].getName().contains(".xls")) {
							 pathsToCalculationFiles.add(files[i].getPath());
							 System.out.println("Imported "+projectFolder+" succesfully!");
							 logs.put(LocalDateTime.now(), "imported calculation file for:" + projectFolder);
							 break; 
						 }
						 if (i == files.length - 1) {
							 System.err.println("Not found calculation XLS file for: " + projectFolder);
							 logs.put(LocalDateTime.now(), "Not found calculation XLS file for:" + projectFolder);
						 }
					 }					 
					
				} catch (Exception e){
					System.err.println("Not found folder: 01. Dokumenty_SLS KFP for: " + projectFolder);
					logs.put(LocalDateTime.now(), "Not found folder: 01. Dokumenty_SLS KFP for: " + projectFolder);
				}

				
			}
		
		//System.out.println("************** LOGS FROM IMPORTS *********************");
		//System.out.println(logs);
		
		return pathsToCalculationFiles;

	}
	
	/**
	 * 
	 * @param projectSlsCode: specific project code / folder name created by sls 
	 * @return path to calculation xls file (first found)
	 */
	public String getCalculationFileFullPath(String projectSlsCode) {
		String calculationFileFullPath = null;
		File folderSlsDoc = new File(ImportData.getImportDataSingleton().getPathProjectsToImport()+"//"+projectSlsCode+"//01. Dokumenty_SLS KFP");
		
		File[] files = folderSlsDoc.listFiles();
		
		/*
		 * searching calculation file
		 */
		for (File file : files) {
			if (file.getName().contains("Kalkulacja") && file.getName().contains(".xls")) {
				calculationFileFullPath = file.getPath();
				break;
			}
		}
		
		return calculationFileFullPath;
	}
	/**
	 * 
	 * @return map of projects' codes (long and short) from default location for all of project folders
	 */
	public Map<String, String> getMapWithProjectsSlsCodes() {
		Map<String, String> projectsSlsCodes = new HashMap<String, String>();

		String slsCodeShort = null;
		String slsCodeFull = null;
		
		for (String calculationFilePath : this.getCalculationFilesFullPath()) {
			
			try {
				FileInputStream fis=new FileInputStream(calculationFilePath);  
				Workbook wb = new XSSFWorkbook(fis);
				Sheet sheet;  
				Row row;
				Cell cell;

//				sheet=wb.getSheetAt(1);   //getting the XSSFSheet object at given index  
				sheet=wb.getSheet(cellsToImportFromCalculationXlsFile.get("slsCodeFull")[0]);   //getting the XSSFSheet object at given index  
				row=sheet.getRow((Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsCodeFull")[1]))); //returns the logical row  
				cell=row.getCell((Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsCodeFull")[2]))); //getting the cell representing the given column  
				slsCodeFull=cell.getStringCellValue();    //getting cell value  

				slsCodeShort=slsCodeFull.substring(0, 7);
				slsCodeShort=slsCodeFull.substring(0, slsCodeFull.indexOf("/"));
		
			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println("Błąd odczytu danych z pliku: "+calculationFilePath);
			} finally {
				projectsSlsCodes.put(slsCodeShort, slsCodeFull);
				
				//TODO 2022-10-29
				//createAndSaveProjectsFromMappedSlsData(projectsSlsCodes);
			}
		}
		
		return projectsSlsCodes;
	}

	/**
	 * 
	 * @param calculationFilePath to specific xls file
	 * @return map of parameters imported from given path to xls file
	 */
	public Map<String, String> importProjectDataFromXls (String calculationFilePath) {
		Map<String, String> dataImported = new HashMap<String,String>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(calculationFilePath);
			Workbook wb = new XSSFWorkbook(fis);
			
			dataImported.put("calculationFilePath", calculationFilePath);
			
			dataImported.put("slsCodeShort", getSlsCodeShort(wb));
			
			dataImported.put("deviceCategory", getCellValue(wb, "SRF", 2, 6, calculationFilePath));
			
			dataImported.put("deviceModelName", 
					getCellValue(
							wb, 
							cellsToImportFromCalculationXlsFile.get("slsDevicePrototypeModelName")[0], 
							Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsDevicePrototypeModelName")[1]), 
							Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsDevicePrototypeModelName")[2]), 

							calculationFilePath)
					
					);
			
			
			dataImported.put("projectManager", getCellValue(wb, "SRF", 3, 11, calculationFilePath));
			
			dataImported.put("slsInvestorSapNo", 
					getCellValue(
							wb, 
							cellsToImportFromCalculationXlsFile.get("slsInvestorSapNo")[0], 
							Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsInvestorSapNo")[1]), 
							Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsInvestorSapNo")[2]), 

							calculationFilePath)
					
					);
			
//			String[] arr = new String[3];
//			arr[0] = cellsToImportFromCalculationXlsFile.get("slsConfiguration")[0].toString();
//			arr[1] = cellsToImportFromCalculationXlsFile.get("slsConfiguration")[1].toString();
//			arr[2] = cellsToImportFromCalculationXlsFile.get("slsConfiguration")[2].toString();
//			dataImported.put("configuration", 
//						getCellsValuesInRow(
//								calculationFilePath, 
//								arr
//						)
//					);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (Exception e) {
			System.err.println("ERROR: "+calculationFilePath);
			dataImported = null;
		}
		return dataImported;
	}
	
	public Workbook getWorkbook(File file) {
		
		FileInputStream fis;
		Workbook wb;
		try {
			fis = new FileInputStream(file.getAbsolutePath());
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			wb = null;
			e.printStackTrace();
		} catch (IOException e) {
			wb = null;
			e.printStackTrace();
		}
		return wb;
	}
	
	public String getFilePathFromFile(File file) {
		return file.getPath();
	}
	
	public String getCellValue(Workbook wb, String sheetName, int rowNo, int colNo, String calculationFilePath) {
		String cellValue = null;
		try {
			Sheet sheet=wb.getSheet(sheetName);   //getting the XSSFSheet object at given index  
			Row row=sheet.getRow(rowNo); //returns the logical row  
			Cell cell=row.getCell(colNo); //getting the cell representing the given column  
			cellValue=cell.getStringCellValue();    //getting cell value  
		} catch (NullPointerException e) {
			System.err.println("Not found sheet/row/col/cell for specified calculation xls file! "+ calculationFilePath);
			return null;
		}
		
		return cellValue;
	}
	
	public String getCellsValuesInRow(String filePath, String[] sheetRowCol, boolean isEncrypted) {
		String cellsVallues = null;
		
		while (null != getCellValue(filePath, sheetRowCol, isEncrypted) && getCellValue(filePath, sheetRowCol, isEncrypted) != "") {
			if (null == cellsVallues) {
				cellsVallues = getCellValue(filePath, sheetRowCol, isEncrypted)+";";
			} else {
				cellsVallues = cellsVallues + getCellValue(filePath, sheetRowCol, isEncrypted)+";";
			}
			
			
			sheetRowCol[1] = String.valueOf(Integer.parseInt(sheetRowCol[1])+1);
		}
		
		return cellsVallues;
	}

	
	public String getCellValue(String filePath, String[] sheetRowCol, boolean isEncrypted) {
		String cellValue = null;

		try {
			FileInputStream fis = fileInputStream;
			if (null == fis) {
				fis = new FileInputStream(filePath);				
			}
			Workbook wb = workbook;
			if (null == wb) {
				wb = new XSSFWorkbook(fis);	
			}
			
			String sheetName = sheetRowCol[0];
			int rowNo = Integer.parseInt(sheetRowCol[1]);
			int colNo = Integer.parseInt(sheetRowCol[2]);
			Sheet sheet=wb.getSheet(sheetName);   //getting the XSSFSheet object at given index  
			Row row=sheet.getRow(rowNo); //returns the logical row  
			Cell cell=row.getCell(colNo); //getting the cell representing the given column  
			try {
				cellValue=cell.getStringCellValue();    //getting cell value
				if (isEncrypted) {
					Encryptor encryptor = new Encryptor();
					cellValue = encryptor.encrypt(cellValue);
					System.out.println(cellValue);
				}
			} catch (IllegalStateException e) {
				double x1 = cell.getNumericCellValue();
				long x2 = Double.valueOf(x1).longValue();
				cellValue = LocalDate.of(2008, 1, 1).plusDays(x2-39448).toString();
				return cellValue;
			}
		} catch (IOException e) {
			System.err.println("App. ERROR! Not found file for specified xls file! "+ filePath);
			return null;
		} catch (NullPointerException e) {
			System.err.println("App. ERROR! Not found sheet/row/col/cell for specified xls file! "+ filePath);
			return null;
		}
		
		return cellValue;
	}

	private String getSlsCodeShort(Workbook wb) {
//		Sheet sheet=wb.getSheet(cell);   //getting the XSSFSheet object at given index  
//		Row row=sheet.getRow(2); //returns the logical row  
//		Cell cell=row.getCell(2); //getting the cell representing the given column  
		Sheet sheet=wb.getSheet(cellsToImportFromCalculationXlsFile.get("slsCodeFull")[0]);   //getting the XSSFSheet object at given index  
		Row row=sheet.getRow((Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsCodeFull")[1]))); //returns the logical row  
		Cell cell=row.getCell((Integer.parseInt(cellsToImportFromCalculationXlsFile.get("slsCodeFull")[2]))); //getting the cell representing the given column  

		
		String slsCodeFull=cell.getStringCellValue();    //getting cell value  

		//String slsCodeShort=slsCodeFull.substring(0, 7);
		String slsCodeShort=slsCodeFull.substring(0, slsCodeFull.indexOf("/"));
		return slsCodeShort;
	}

	/**
	 * list of projects imported from default location
	 */
	
//	public List<Project> importSlsProjects() {
//		List<Project> projectList = new ArrayList<Project>();
//		
//		/**
//		 * get the default path to the folder with projects created by SLS
//		 */
//		String defaultPath = this.getPathProjectsToImport();
//		
//		/**
//		 * get the list of calculation files for SLS projects
//		 */
//		List<String> calculationXlsFiles = this.getCalculationFilesFullPath();
//		
//		/**
//		 * get the Map of projects' data imported from SLS
//		 */
//		List<Map<String, String>> dataImported = new ArrayList<Map<String,String>>();
//		
//		for (String caluclationFile : calculationXlsFiles) {
//			if (null != importProjectDataFromXls(caluclationFile)) {
//				dataImported.add(importProjectDataFromXls(caluclationFile));
//			}
//		}
//		
//		for (Map<String, String> di : dataImported) {
//			/*projectList.add(new Project(
//						di.get("slsCodeShort"),
//						di.get("deviceCategory"),
//						di.get("deviceModelName"),
//						di.get("projectManager"),
//						di.get("investorSapNo")
//					));*/
//			if (di != null) {
//				Project project = new Project();
//				project.getDetailsSls().setSlsCodeShort(di.get("slsCodeShort"));
//				project.getDetailsSls().setImportedDeviceModality(di.get("deviceCategory"));
//				project.getDetailsSls().setImportedDeviceModelName(di.get("deviceModelName"));
//				project.getDetailsSls().setImportedProjectManager(di.get("projectManager"));
//				project.getDetailsSls().setImportedCustomer(di.get("slsInvestorSapNo"));
//				project.getDetailsSls().setPathToXls(di.get("calculationFilePath"));
//				
//				projectList.add(project);
//			}
//		}
//		
//		return projectList;
//	}
	
	
	/**
	 * Imports data about Investor from given path to xls file
	 * @param path to xls file
	 * @return Map of data about Investor (sapNo, customer - full data name and address)
	 */
	public Map<String, String> importInvestorFromXls(String path) {
		Map<String, String> investorData = new HashMap<String, String>();
		//import SAP no
		investorData.put("sapNo", this.importCellFromXls(path, "HCALC-1", 4, 9));
		//import NIP no
		//import investor name
		investorData.put("customer", this.importCellFromXls(path, "HCALC-1", 2, 2));
		//import investor address
		//import investor contact details
		return investorData;
	}
	
	/**
	 * Imports cell value from XLS file specified by path, sheet name, row number and col number
	 * @param path
	 * @param sheetName
	 * @param row
	 * @param col
	 * @return (String) imported value from given cell
	 */
	@SuppressWarnings("finally")
	private String importCellFromXls(String path, String sheetName, int rowNo, int colNo) {
		String cellValue = null;
		
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(sheetName);   //getting the XSSFSheet object at given index  
			Row row = sheet.getRow(rowNo); //returns the logical row  
			Cell cell = row.getCell(colNo); //getting the cell representing the given column  
			cellValue = cell.getStringCellValue();    //getting cell value  
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.err.println("App. ERROR! FileNotFoundException while trying to import cell from xls file: "+ path);
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println("App. ERROR! IOException while trying to import cell from xls file: "+ path);
		} finally {
			return cellValue;
		}
		
		
	}
	
	public String importSlsData(String calculationFilePath, String dataToImport) {
		String dataImported;
		
		switch (dataToImport) {
		
		case "slsTrainingsOther": {
			dataImported = getCellsValuesInRow(calculationFilePath, cellsToImportFromCalculationXlsFile.get(dataToImport), false);
			System.out.println("Zaimportowano szkolenia do ImportData:" + dataImported);
			return dataImported;
		}
		
			default: {
				dataImported = getCellValue(calculationFilePath, cellsToImportFromCalculationXlsFile.get(dataToImport), false);
				return dataImported;	
			}
		}
		
	}	
	public String importSlsStakeholderContactPerson(String calculationFilePath) {
		String slsStakeholderContactPerson = null;
		slsStakeholderContactPerson = getCellValue(calculationFilePath, cellsToImportFromCalculationXlsFile.get("slsStakeholderContactPerson"), false);
		return slsStakeholderContactPerson;
	}	
	public String importSlsDeadline(String calculationFilePath) {
		String slsDeadline = null;
		slsDeadline = getCellValue(calculationFilePath, cellsToImportFromCalculationXlsFile.get("slsDeadline"), false);
		return slsDeadline;		
	}
	public String importSlsProjectManager(String calculationFilePath) {
		String slsProjectManager = null;
		slsProjectManager = getCellValue(calculationFilePath, cellsToImportFromCalculationXlsFile.get("slsProjectManager"), false);
		return slsProjectManager;
	}
	public String importSlsDevicePrototypeModelName(String calculationFilePath) {
		String slsDevicePrototypeModelName = null;
		slsDevicePrototypeModelName = getCellValue(calculationFilePath, cellsToImportFromCalculationXlsFile.get("slsDevicePrototypeModelName"), false);
		return slsDevicePrototypeModelName;
	}
	public String importSlsInvestorSapNo(String calculationFilePath) {
		String slsInvestorSapNo = null;
		slsInvestorSapNo = getCellValue(calculationFilePath, cellsToImportFromCalculationXlsFile.get("slsInvestorSapNo"), false);
		return slsInvestorSapNo;
	}
	
	public List<String> importInitDevicesNames() {
		String initDevicesNames = null;
		initDevicesNames = this.getCellsValuesInRow(pathInitDataDevices, this.cellsToImportFromDeviceTestPortfolio.get("deviceListStart"), false);
		
		List<String> listOfInitDeviceNames = Arrays.asList(initDevicesNames.split(";"));
		
		return listOfInitDeviceNames;
	}
	
	/**
	 * Initially set cells to import data from xls calculation file
	 */
	private void setCellsToImportFromCalculationXlsFile() {
		cellsToImportFromCalculationXlsFile.put("slsCodeFull", new String[] {"Kontrolka Umowy", "2", "2"});
//		cellsToImportFromCalculationXlsFile.put("slsCodeFull", new String[] {"HCALC-1", "4", "11"});
		cellsToImportFromCalculationXlsFile.put("slsModalityCode", new String[] {"HCALC-1", "11", "2"});
		cellsToImportFromCalculationXlsFile.put("slsDevicePrototypeModelName", new String[] {"HCALC-1", "11", "1"});
		cellsToImportFromCalculationXlsFile.put("slsDevicePrototypeCpqNo", new String[] {"SCON-1-1", "4", "2"});
		cellsToImportFromCalculationXlsFile.put("slsProjectManager", new String[] {"SRF", "3", "11"});
		cellsToImportFromCalculationXlsFile.put("slsDeadline", new String[] {"Kontrolka Umowy", "8", "5"});
		cellsToImportFromCalculationXlsFile.put("slsInvestorSapNo", new String[] {"HCALC-1", "4", "9"});
		cellsToImportFromCalculationXlsFile.put("slsCustomer", new String[] {"HCALC-1", "2", "2"});
		cellsToImportFromCalculationXlsFile.put("slsCosts", new String[] {"SRF", "6", "3"});
		cellsToImportFromCalculationXlsFile.put("slsTrainingsOther", new String[] {"Szkolenia", "9", "2"});
		cellsToImportFromCalculationXlsFile.put("slsAdditionalsSIWZ", new String[] {"rekomendacja PUR", "2", "1"});
		cellsToImportFromCalculationXlsFile.put("slsStakeholderContactPerson", new String[] {"Kontrolka Umowy", "16", "3"});
		cellsToImportFromCalculationXlsFile.put("slsConfiguration", new String[] {"SCON-1-2", "3", "1"});
	}
	
	/**
	 * Initially set cells to import data from the xls file containing test device portfolio
	 */
	private void setCellsToImportFromDeviceTestPortfolio() {
		cellsToImportFromDeviceTestPortfolio.put("deviceListStart", new String[] {"Sheet-01", "0", "0"});
		
	}
	
}
