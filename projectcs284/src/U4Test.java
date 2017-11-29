import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class U4Test {
	private ArrayList<Double> score = new ArrayList<>();

	@Test
	void testResultScore() throws FileNotFoundException {
		File file = new File("cs284_resultScore");
		FileReader r = new FileReader(file);
		BufferedReader read = new BufferedReader(r);
		String s;
		try {
			s = read.readLine();
			while (s != null) {
				String[] spt = s.split("\t");
				score.add(Double.valueOf(spt[1]));
				assertTrue(Double.valueOf(spt[1]) <= 100, "ERROR");
				s = read.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testResultGrade() throws IOException {
		File file = new File("cs284_resultScore");
		FileReader r = new FileReader(file);
		BufferedReader read = new BufferedReader(r);
		String s;
		try {
			s = read.readLine();
			while (s != null) {
				String[] spt = s.split("\t");
				score.add(Double.valueOf(spt[1]));
				assertTrue(Double.valueOf(spt[1]) <= 100, "ERROR");
				s = read.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file1 = new File("cs284_result");
		FileReader r1 = new FileReader(file1);
		BufferedReader read1 = new BufferedReader(r1);
		String s1;
		try {
			boolean check = false;
			s1 = read.readLine();
			int i = 0;
			while (s1 != null) {
				String[] spt = s1.split("\t");
				if (score.get(i) >= 80) {
					if(spt[1].equals("A")) {
						check = true;
					}else {
						check = false;
					}
					
				} else if (score.get(i) < 80 && score.get(i) >= 75) {
					if(spt[1].equals("B+")) {
						check = true;
					}else {
						check = false;
					}
				} else if (score.get(i) < 75 && score.get(i) >= 70) {
					if(spt[1].equals("B")) {
						check = true;
					}else {
						check = false;
					}
				} else if (score.get(i) < 70 && score.get(i) >= 65) {
					if(spt[1].equals("C+")) {
						check = true;
					}else {
						check = false;
					}
				} else if (score.get(i) < 65 && score.get(i) >= 60) {
					if(spt[1].equals("C")) {
						check = true;
					}else {
						check = false;
					}
				} else if (score.get(i) < 60 && score.get(i) >= 55) {
					if(spt[1].equals("D+")) {
						check = true;
					}else {
						check = false;
					}
				} else if (score.get(i) < 55 && score.get(i) >= 50) {
					if(spt[1].equals("D")) {
						check = true;
					}else {
						check = false;
					}
				} else {
					if(spt[1].equals("F")) {
						check = true;
					}else {
						check = false;
					}
				}
				assertTrue(check, "ERROR");
				i++;
				s1 = read.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
read.close();
r.close();
	}
	
	@Test
	void testResult() throws FileNotFoundException {
		File file = new File("cs284_result");
		FileReader r = new FileReader(file);
		BufferedReader read = new BufferedReader(r);
		String s;
		try {
			s = read.readLine();
			while (s != null) {
				String[] spt = s.split("\t");
				
				assertTrue(spt.length==2, "ERROR");
				s = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
