import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class U5Test {

	@Test
	void testId() throws IOException, FileNotFoundException {
		
		File file = new File("cs284_result");
		FileReader r = new FileReader(file);
		BufferedReader read = new BufferedReader(r);
		
		String idPattern = "[0-9]{10}";
		boolean tf = false;
		
		String s = read.readLine();
		while (s != null) {

			String spt[] = s.split("\t");
			tf = spt[0].matches(idPattern);
			assertTrue(tf, "error");
			s =read.readLine();
		}
		read.close();
		r.close();
	

	}
	@Test
	void testGrade() throws IOException, FileNotFoundException {
		
		File file = new File("cs284_result");
		FileReader r = new FileReader(file);
		BufferedReader read = new BufferedReader(r);
		
		String gradePattern = "F||[A-D]||[B-D]\\+";
		boolean tf = false;
		
		String s = read.readLine();
		while (s != null) {

			String spt[] = s.split("\t");
			tf = spt[1].matches(gradePattern);
			assertTrue(tf, "error");
			s =read.readLine();
		}
		read.close();
		r.close();
	

	}
	@Test
	void testCompleteFile() throws IOException, FileNotFoundException {
		
		File file = new File("cs284_result");
		FileReader r = new FileReader(file);
		BufferedReader read = new BufferedReader(r);
		
		String s = read.readLine();
		while (s != null) {

			String spt[] = s.split("\t");
			assertTrue(spt.length==2, "error");
			s =read.readLine();
		}
		read.close();
		r.close();
	

	}

}
