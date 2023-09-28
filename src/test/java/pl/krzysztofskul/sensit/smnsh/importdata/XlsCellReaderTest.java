package pl.krzysztofskul.sensit.smnsh.importdata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class XlsCellReaderTest {

	private static String filePath;
	private static XlsCellReader xlsCellReader;
	
	@BeforeAll
	static void selecFile() {
		filePath = FileSelector.select("file");
		xlsCellReader = XlsCellReader.getXlsCellReader(filePath);
	}
	
	@Test
	@Order(value = 1)
	void testGetCellsValuesInRow() {
		assertDoesNotThrow(() -> xlsCellReader.getCellsValuesInRow(filePath, "SCON-1-1", 3, 1, false));
		List<String> cellsValues = xlsCellReader.getCellsValuesInRow(filePath, "SCON-1-1", 3, 1, false);
		//System.out.println("@Test: "+cellsValues);
		assertTrue(cellsValues != null && cellsValues.size() > 0);
	}

	@Test
	@Order(value = 2)
	void testGetCellVallue() {
		//System.out.println("@Test: "+xlsCellReader.getCellValue(filePath, "Kontrolka Umowy", 8, 5, false));
		assertDoesNotThrow(() -> xlsCellReader.getCellValue(filePath, "Kontrolka Umowy", 8, 5, false));
	}
	
	@Test
	@Order(value = 3)
	void testGetCellValueAsDate() {
		//System.out.println("@Test: "+xlsCellReader.getCellValueAsDate(filePath, "Kontrolka Umowy", 8, 5, false));
		assertDoesNotThrow(() -> xlsCellReader.getCellValueAsDate(filePath, "Kontrolka Umowy", 8, 5, false));
	}
	
}
