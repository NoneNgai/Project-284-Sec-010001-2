
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class StudentList {
	private ArrayList<Long> id;
	private ArrayList<String> name;
	private ArrayList<String> email;
	private ExamResult e;

	public StudentList() {

		id = new ArrayList<>();
		name = new ArrayList<>();
		email = new ArrayList<>();

	}

	public ArrayList<Long> getIDList() {
		return id;
	}

	public void addID(long id) {

		this.id.add(id);

	}

	public int size() {
		return id.size();

	}

	public Long getId(int index) {
		return id.get(index);
	}

	public void addName(String name) {
		this.name.add(name);
	}

	public String getName(int index) {
		return name.get(index);
	}
	
	public boolean isFilled() {
		if (id.isEmpty()) {
			return false;
		}
		if (name.isEmpty()) {
			return false;
		}
		return true;
	}

	public ArrayList<String> getNameList() {
		return name;
	}

	public void clearList() {
		id.clear();
		name.clear();
		email.clear();
	}

	public void saveList(ArrayList<Long> id, ArrayList<String> name, String nameFile)
			throws IOException, FileException {

		File file = new File("cs284_StudentList");
		FileWriter f = new FileWriter(file);
		BufferedWriter write = new BufferedWriter(f);

		write.write(nameFile);
		write.write("\n");
		for (int i = 0; i < id.size(); i++) {
			write.write(id.get(i) + "  " + name.get(i) + "\n");
		}
		write.close();
		f.close();

	}

	public String[][] getTable() {
		String[][] table = new String[id.size()][3];

		for (int i = 0; i < id.size(); i++) {
			for (int j = 0; j < 3; j++) {
				table[i][0] = "" + id.get(i);
				table[i][1] = name.get(i);
				table[i][2] = "0";
			}
		}
		return table;

	}

}
