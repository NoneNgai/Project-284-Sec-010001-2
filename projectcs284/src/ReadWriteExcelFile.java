import java.io.FileInputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelFile {
	
	private StudentList list = new StudentList();
	
	public void readFile (String filePath) throws IOException {
		list.clearList();
		String extension = FilenameUtils.getExtension(filePath);
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		System.out.println(extension);
		int countrow = 0;
		int countcol = 0;
		
		if (extension.equalsIgnoreCase("xlsx")) {
			XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row; 
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext())
			{
				countrow++;
				row=(XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext())
				{
					countcol++;
					cell=(XSSFCell) cells.next();
					if (countrow>7) {
						if (countcol>1&&countcol<4) {
							switch (countcol) {
								case 2 : list.addID((long) cell.getNumericCellValue()); break;
								case 3 : list.addName(cell.getStringCellValue()); break;
							}
						}
					}
				}
				countcol =0;
			}
		}
		
		else if (extension.equalsIgnoreCase("xls")) {
			HSSFWorkbook  wb = new HSSFWorkbook(ExcelFileToRead);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row; 
			HSSFCell cell;
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext())
			{
				countrow++;
				row=(HSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext())
				{
					countcol++;
					cell=(HSSFCell) cells.next();
					if (countrow>7) {
						if (countcol>1&&countcol<4) {
							switch (countcol) {
								case 2 : list.addID((long) cell.getNumericCellValue()); break;
								case 3 : list.addName(cell.getStringCellValue()); break;
							}
						}
					}
				}
				countcol =0;
			}
		}
	}
	
	

	public boolean UpdateFileStatus() {
		return list.isFilled();
	}
	
	public String[][] getTable() {
		return list.getTable();
	}
	
}