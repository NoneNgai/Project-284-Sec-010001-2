import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JOptionPane;

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

	public void readFile(String filePath) throws IOException {
		list.clearList();
		String extension = null;
		InputStream ExcelFileToRead = null;
		try {

			extension = FilenameUtils.getExtension(filePath);
			ExcelFileToRead = new FileInputStream(filePath);
			if (extension.equals("xlsx")) {
				System.out.println(extension);
			} else {
				throw new FileFormatException();
			}
		} catch (FileFormatException f) {
			JOptionPane.showMessageDialog(null, f.getMessage());
		}
		int countrow = 0;
		int countcol = 0;

		if (extension.equalsIgnoreCase("xlsx")) {
			XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				countrow++;
				row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {
					countcol++;
					cell = (XSSFCell) cells.next();
					if (countrow > 7) {
						if (countcol > 1 && countcol < 4) {
							switch (countcol) {
							case 2:
								list.addID((long) cell.getNumericCellValue());
								break;
							case 3:
								list.addName(cell.getStringCellValue());
								break;
							}
						}
					}
				}
				countcol = 0;
			}
			list.saveList(list.getIDList(),"cs284");
			wb.close();
		}

		else if (extension.equalsIgnoreCase("xls")) {
			HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()) {
				countrow++;
				row = (HSSFRow) rows.next();
				Iterator cells = row.cellIterator();

				while (cells.hasNext()) {
					countcol++;
					cell = (HSSFCell) cells.next();
					if (countrow > 7) {
						if (countcol > 1 && countcol < 4) {
							switch (countcol) {
							case 2:
								list.addID((long) cell.getNumericCellValue());
								break;
							case 3:
								list.addName(cell.getStringCellValue());
								break;
							}
						}
					}
				}
				countcol = 0;
			}
			
			
			wb.close();
		}
		extension = null;
		ExcelFileToRead.close();;
	}

	public boolean UpdateFileStatus() {
		return list.isFilled();
	}

	public String[][] getTable() {
		return list.getTable();
	}

}