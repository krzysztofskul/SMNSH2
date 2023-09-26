package pl.krzysztofskul.sensit.smnsh.importdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsCellReader {

	private static XlsCellReader xlsCellReader = null;
	private static FileInputStream fileInputStream = null;
	private static Workbook workbook = null;
	
	private XlsCellReader() {};
	
	private XlsCellReader(String filePath) {
		try {
			fileInputStream = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (FileNotFoundException e) {
			System.err.println("App. ERROR! Xls file not found: " + filePath);
			clearStaticData();
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("App. ERROR! IOExeption while creating the workbook from xls file: " + filePath);
			clearStaticData();
		}
	}
	
	private static XlsCellReader getXlsCellReader() {
		if (xlsCellReader == null) {
			xlsCellReader = new XlsCellReader();
		}
		return xlsCellReader;
	}
	/**
	 * Creates XlsCellReader singleton class which allows to read cells from given xls file.
	 * @param filePath to the xls file.
	 * @return new XlsCellReader with FileInputStream and Workbook initially set.
	 */
	public static XlsCellReader getXlsCellReader(String filePath) {
		if (xlsCellReader == null) {
			xlsCellReader = new XlsCellReader(filePath);
		}
		return xlsCellReader;
	}
	/**
	 * Gets cells values row by row until next cell is null or empty. Data is get from the given sheet, column and row of the xls file from the file path.
	 * @param filePath
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 * @param isEncrypted
	 * @return Cells values as a List of String
	 */
	public List<String> getCellsValuesInRow(String filePath, String sheetName, int rowNo, int colNo, boolean isEncrypted) {
		List<String> cellsValluesList = new ArrayList<String>();
		while 
			(	null != getCellValue(filePath, sheetName, rowNo, colNo, false) 
				&& 
				getCellValue(filePath, sheetName, rowNo, colNo, false) != ""
			) {		
			cellsValluesList.add(getCellValue(filePath, sheetName, rowNo, colNo, isEncrypted));
			rowNo = rowNo+1;
		}
		
		return cellsValluesList;
	}

	/**
	 * Gets the value of the cell from the given sheet, column and row of the xls file from the file path.
	 * @param filePath
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 * @param isEncrypted
	 * @return the value of the cell as String
	 */
	public String getCellValue(String filePath, String sheetName, int rowNo, int colNo, boolean isEncrypted) {
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
			
			Sheet sheet=wb.getSheet(sheetName);
			Row row=sheet.getRow(rowNo); 
			Cell cell=row.getCell(colNo);
			CellType cellType = cell.getCellType();
			if (cellType == CellType.STRING) {
				cellValue=cell.getStringCellValue();
				if (isEncrypted) {
					Encryptor encryptor = new Encryptor();
					cellValue = encryptor.encrypt(cellValue);
					//System.out.println(cellValue);
				}
			} else if (cellType == CellType.NUMERIC) {
				cellValue = String.valueOf(cell.getNumericCellValue());
			}
		} catch (FileNotFoundException e) {
			System.err.println("App. ERROR! Not found file for specified xls file: "+ filePath);
		} catch (IOException e) {
			System.err.println("App. ERROR! IOException while creating workbook from the file: "+ filePath);
			return null;
		} catch (NullPointerException e) {
			System.err.println("App. ERROR! Not found sheet/row/col/cell for specified xls file: "+ filePath);
			return null;
		}
		
		return cellValue;
	}
	
	/**
	 * Gets cell value and converts it into LocalDate.toString().
	 * @param filePath
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 * @param isEncrypted
	 * @return LocalDate as String
	 */
	public String getCellValueAsDate(String filePath, String sheetName, int rowNo, int colNo, boolean isEncrypted) {
		String cellValue = this.getCellValue(filePath, sheetName, rowNo, colNo, isEncrypted);
		long x = Double.valueOf(cellValue).longValue();
		cellValue = LocalDate.of(2008, 1, 1).plusDays(x-39448).toString();
		return cellValue;
	}
	
	private void clearStaticData() {
		xlsCellReader = null;
		fileInputStream = null;
		workbook = null;
		
	}
	
}
