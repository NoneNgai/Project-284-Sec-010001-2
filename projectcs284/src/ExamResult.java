
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class ExamResult {
	private ArrayList<String> score;
	private StudentList s;
	private ArrayList<String> finalResult;
	public ExamResult() {
		score = new ArrayList<>();
		finalResult = new ArrayList<>();
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

	public void clear() {
		score.clear();
	
	}

	public void saveList(ArrayList<Long> id, ArrayList<String> score, String nameType)
			throws IOException, FileException {

		File file = new File("cs284_" + nameType);
		FileWriter f = new FileWriter(file);
		BufferedWriter write = new BufferedWriter(f);

		write.write(nameType);
		write.write("\n");
		for (int i = 0; i < id.size(); i++) {
			write.write(id.get(i) + "\t" + score.get(i) + "\n");
		}
		write.close();
		f.close();

	}

	public void calculate() throws NumberFormatException, IOException {
		
		ExamCriteria ec = new ExamCriteria();
		finalResult.clear();
		ec.loadList();
		ArrayList<ArrayList<Integer>> score = new ArrayList<>();
		ArrayList<ArrayList<Double>> allScore = new ArrayList<>();
		ArrayList<Double> all = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();

		for (int i = 0; i < ec.getTypeList().size(); i++) {
			score.add(new ArrayList<>());
			allScore.add(new ArrayList<>());
		}

		for (int i = 0; i < ec.getTypeList().size(); i++) {
			File file = new File("cs284_" + ec.getType(i));
			FileReader f = new FileReader(file);
			BufferedReader read = new BufferedReader(f);
			read.readLine();
			String s = read.readLine();

			while (s != null) {
				String[] spt = s.split("\t");
				name.add(spt[0]);
				score.get(i).add(Integer.parseInt(spt[1]));
				s = read.readLine();
			}

		}

		for (int i = 0; i < score.size(); i++) {
			for (int j = 0; j < score.get(i).size(); j++) {
				allScore.get(i).add(score.get(i).get(j) * (ec.getPercent(i) / 100.00));

			}
		}

		for (int j = 0; j < allScore.get(0).size(); j++) {
			double sum = 0;
			for (int k = 0; k < allScore.size(); k++) {
				sum = sum + allScore.get(k).get(j);
			}
			all.add(sum);

		}

		for (int i = 0; i < all.size()-1; i++) {
			if (all.get(i) >= ec.getA()) {
				result.add(name.get(i) + "\tA");
			} else if (all.get(i) < ec.getA() && all.get(i) >= ec.getbPlus()) {
				result.add(name.get(i) + "\tB+");
			} else if (all.get(i) < ec.getbPlus() && all.get(i) >= ec.getB()) {
				result.add(name.get(i) + "\tB");
			} else if (all.get(i) < ec.getB() && all.get(i) >= ec.getcPlus()) {
				result.add(name.get(i) + "\tC+");
			} else if (all.get(i) < ec.getcPlus() && all.get(i) >= ec.getC()) {
				result.add(name.get(i) + "\tC");
			} else if (all.get(i) < ec.getC() && all.get(i) >= ec.getdPlus()) {
				result.add(name.get(i) + "\tD+");
			} else if (all.get(i) < ec.getdPlus() && all.get(i) >= ec.getD()) {
				result.add(name.get(i) + "\tD");
			} else {
				result.add(name.get(i) + "\tF");
			}
		}
		
		finalResult = result;
		
		FileWriter wr = new FileWriter(new File("cs284_result"));
		BufferedWriter write = new BufferedWriter(wr);
		for (int i = 0; i < result.size(); i++) {
			write.write(result.get(i));
			write.newLine();
		}
		write.close();
		wr.close();
		
		FileWriter writes  = new FileWriter(new File("cs284_resultScore"));
		BufferedWriter writer = new BufferedWriter(writes);
		for (int i = 0; i <all.size()-1; i++) {
			writer.write(name.get(i)+"\t"+all.get(i));
			writer.newLine();
		}
		writer.close();
		writes.close();
		
		

	}

	public ArrayList<String> getFinalResult() {
		return finalResult;
	}

	public static void main(String[] args) {

		ExamResult r = new ExamResult();
		try {
			r.calculate();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
