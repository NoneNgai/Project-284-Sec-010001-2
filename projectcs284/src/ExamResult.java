import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class ExamResult {
	private ArrayList<String > score;
	private File file;
	private StudentList s;

	public ExamResult() {
		score = new ArrayList<>();
		s = new StudentList();
	}

	public void add(String s) {

		this.score.add(s);

	}

	public String getScore(int  index) {
		return score.get(index);
	}

	public int size() {
		return score.size();
	}

	public void saveScore() throws IOException, FileNotFoundException, NumberFormatException {
		JFileChooser choose = new JFileChooser(".");
		int state = choose.showSaveDialog(null);

		if (state == JFileChooser.APPROVE_OPTION) {

			file = choose.getSelectedFile();

			FileWriter w = new FileWriter(file, false);
			PrintWriter writer = new PrintWriter(w);

			for (int i = 0; i < s.getIDList().size(); i++) {
				writer.println(s.getIDList().get(i) + "," + score.get(i));
			}

			writer.close();
			w.close();
		}

	}

	public static void main(String[] args) {
		ExamResult e = new ExamResult();

	}
}
