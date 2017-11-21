import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExamCriteria {
	private ArrayList<String> type;
	private ArrayList<Integer> max;
	private ArrayList<Integer> percent;
	private int size;

	public ExamCriteria() {
		type = new ArrayList<>();
		max = new ArrayList<>();
		percent = new ArrayList<>();
	}

	public void add(String s, int max, int per) {

		this.type.add(s);
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

	public int size() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String[][] getTable() {
		String[][] table = new String[size][3];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 3; j++) {
				table[i][0] = " ";
				table[i][1] = " ";
				table[i][2] = " ";
			}
		}

		return table;

	}

}
