import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelFile {
	
	private StudentList list = new StudentList();
	
	public void readXlsxFile (String filePath) throws IOException {
		list.clearList();
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFWorkbook test = new XSSFWorkbook(); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		int countrow = 0;
		int countcol = 0;
		
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
						/*if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
						{
							list.addName(cell.getStringCellValue());
						}
						else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						{
							list.addID(cell.getNumericCellValue());
						}*/
						switch (countcol) {
							case 2 : list.addID(cell.getNumericCellValue()); break;
							case 3 : list.addName(cell.getStringCellValue()); break;
						}
					}
				}
			}
			countcol =0;
		}
	}

	public boolean UpdateFileStatus() {
		return list.isFilled();
	}
	
	public String[][] getTable() {
		return list.getTable();
	}
	
}