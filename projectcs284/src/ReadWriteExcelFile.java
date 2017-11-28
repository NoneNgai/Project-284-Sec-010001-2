import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
			list.saveList(list.getIDList(),list.getNameList(),"cs284");
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
		ExcelFileToRead.close();
		
	}
	
	
	public boolean writeFile (String filePath) throws NumberFormatException, IOException {
		
		String extension = null;
		extension = FilenameUtils.getExtension(filePath);
		
		if (extension.equals("")) {
			filePath = filePath + ".xlsx";
		}
		else if (extension.equals("xlsx")) {
			
		}
		else {
			System.out.println("else condition");
			System.out.println(extension);
			return false;
		}
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Student Grade");
		ArrayList<Long> idTable = new ArrayList<Long>();
		ArrayList<String> gradeTable = new ArrayList<String>();
		
		if (new File("cs284_result").isFile()) {
			File file = new File("cs284_result");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\t");
				idTable.add(Long.parseLong(split[0]));
				gradeTable.add(split[1]);
			}
			
			int countRow = 0;
			for (int i=0;i<idTable.size();i++) {
				Row row = sheet.createRow(countRow++);
				int countCol = 0;
				for (int j=1; j<3; j++) {
					Cell cell = row.createCell(countCol++);
					switch (countCol) {
					case 1: cell.setCellValue(idTable.get(i)); break;
					case 2: cell.setCellValue(gradeTable.get(i)); break;
					}
				}
			}
			
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			workbook.close();
			
			return true;
		}

		System.out.println("file not found");
		return false;
		
	}

	public boolean UpdateFileStatus() {
		return list.isFilled();
	}

	public String[][] getTable() {
		return list.getTable();
	}

}