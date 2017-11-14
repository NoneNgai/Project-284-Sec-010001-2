
import java.io.File;
import java.io.FileNotFoundException;

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

	public int size() {
		return score.size();
	}

	public void saveScore(ArrayList<Long> id, ArrayList<String> score)
			throws IOException, FileNotFoundException, NumberFormatException {

	}

	public static void main(String[] args) {
		ExamResult e = new ExamResult();

	}
}
