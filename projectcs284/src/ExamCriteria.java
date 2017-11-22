import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	
	public void saveList(ArrayList<String> type,ArrayList<Integer> max,ArrayList<Integer> percent,String nameSubject,String manyQuiz) throws IOException,FileException {
		
		File file = new File ("cs284_Criteria");
		FileWriter f = new FileWriter(file);
		BufferedWriter write= new BufferedWriter(f);
		
		write.write(nameSubject+"\n");
		write.write(manyQuiz+"\n");
		for (int i=0;i<type.size();i++) 
		{
			write.write(type.get(i)+"\t"+max.get(i)+"\t"+percent.get(i)+"\n");
		}
		write.close();
		f.close();

	}
	public String[][] getTable() {
		String[][] table = new String[size][3];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 3; j++) {
				table[i][0] = null;
				table[i][1] = null;
				table[i][2] = null;
			}
		}

		return table;

	}

}
