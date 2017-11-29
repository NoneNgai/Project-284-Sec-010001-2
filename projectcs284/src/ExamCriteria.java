import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExamCriteria {
	private ArrayList<String> type;
	private ArrayList<Integer> max;
	private ArrayList<Integer> percent;
	private int size, a, b, c, d, bPlus, cPlus, dPlus;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getcPlus() {
		return cPlus;
	}

	public void setcPlus(int cPlus) {
		this.cPlus = cPlus;
	}

	public int getdPlus() {
		return dPlus;
	}

	public void setdPlus(int dPlus) {
		this.dPlus = dPlus;
	}

	public int getbPlus() {
		return bPlus;
	}

	public void setbPlus(int bPlus) {
		this.bPlus = bPlus;
	}

	public ExamCriteria() {
		type = new ArrayList<>();
		max = new ArrayList<>();
		percent = new ArrayList<>();
	}

	public void add(String type, int max, int per) {

		this.type.add(type);
		this.max.add(max);
		this.percent.add(per);

	}

	public String getType(int index) {
		return type.get(index);
	}

	public Integer getMax(int index) {
		return max.get(index);
	}

	public Integer getPercent(int index) {
		return percent.get(index);
	}

	public ArrayList<String> getTypeList() {
		return type;
	}

	public ArrayList<Integer> getMaxList() {
		return max;
	}

	public ArrayList<Integer> getPercentList() {
		return percent;
	}

	public int size() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void saveList(ArrayList<String> type, ArrayList<Integer> max, ArrayList<Integer> percent, String nameSubject,
			String manyQuiz, int a, int b, int c, int d, int BPlus, int cPlus, int dPlus)
			throws IOException, FileException {

		File file = new File("cs284_Criteria");
		FileWriter f = new FileWriter(file);
		BufferedWriter write = new BufferedWriter(f);

		write.write(nameSubject + "\n");
		write.write(manyQuiz + "\n");
		for (int i = 0; i < type.size(); i++) {
			write.write(type.get(i) + "\t" + max.get(i) + "\t" + percent.get(i) + "\n");
		}
		write.write("A" + "\t" + a + "\n");
		write.write("B" + "\t" + b + "\n");
		write.write("B+" + "\t" + bPlus + "\n");
		write.write("C" + "\t" + c + "\n");
		write.write("C+" + "\t" + cPlus + "\n");
		write.write("D" + "\t" + d + "\n");
		write.write("D+" + "\t" + dPlus + "\n");

		write.close();
		f.close();

	}

	public ArrayList<String> loadList() throws NumberFormatException, IOException {
		clearList();
		File file = null;
		try {
			file = new File("cs284_Criteria");
			FileReader f = new FileReader(file);
			BufferedReader read = new BufferedReader(f);
			if (file.exists()) {
				read.readLine();

				int sizeType = Integer.parseInt(read.readLine());

				for (int i = 0; i < sizeType; i++) {
					String[] spt = read.readLine().split("\t");
					type.add(spt[0]);
					max.add(Integer.parseInt(spt[1]));
					percent.add(Integer.parseInt(spt[2]));
				}

				ArrayList<Integer> grade = new ArrayList<>();
				for (int i = 0; i < 7; i++) {
					String[] spt = read.readLine().split("\t");
					grade.add(Integer.valueOf(spt[1]));
				}

				setA(grade.get(0));
				setB(grade.get(1));
				setbPlus(grade.get(2));
				setC(grade.get(3));
				setcPlus(grade.get(4));
				setD(grade.get(5));
				setdPlus(grade.get(6));

				read.close();
				f.close();
			}
		} catch (FileNotFoundException f) {
			System.out.println();

		}

		return type;

	}

	public void clearList() {
		type.clear();
		max.clear();
		percent.clear();

	}

	public String[][] getTable() {
		String[][] table = new String[size][3];

		for (int i = 0; i < size - 2; i++) {
			for (int j = 0; j < 3; j++) {

				table[i][1] = null;
				table[i][2] = null;
			}
		}
		table[size - 1][0] = "Final";
		table[size - 2][0] = "Midterm";

		return table;

	}

}
