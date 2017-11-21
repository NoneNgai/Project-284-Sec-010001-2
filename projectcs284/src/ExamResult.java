
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class ExamResult {
	private ArrayList<String> score;
	private File file;
	private StudentList s;

	public ExamResult() {
		score = new ArrayList<>();

	}

	public void add(String s) {

		this.score.add(s);

	}

	public String getScore(int index) {
		return score.get(index);
	}
	public ArrayList<String> getScoreList() {
		return score;
	}
	public int size() {
		return score.size();
	}

	public void saveList(ArrayList<Long> id,ArrayList<String> score,String nameType) throws IOException,FileException {
		
		File file = new File ("cs284_"+nameType);
		FileWriter f = new FileWriter(file);
		BufferedWriter write= new BufferedWriter(f);
		
		write.write(nameType);
		write.write("\n");
		for (int i=0;i<id.size();i++) 
		{
			write.write(id.get(i)+"\t"+score.get(i)+"\n");
		}
		write.close();
		f.close();

	}

}
