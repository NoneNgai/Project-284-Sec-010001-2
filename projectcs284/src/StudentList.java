import java.util.ArrayList;

public class StudentList {
	private ArrayList<Integer> id;
	private ArrayList<String> email;
	private ExamResult e;

	public StudentList() {

		id = new ArrayList<>();

		email = new ArrayList<>();
		e = new ExamResult();

	}

	public ArrayList<Integer> getIDList() {
		return id;

	}

	public int searchByID(int index) {
		return e.getScore(index);

	}

	public void add(int index, int id, String name, String email) {
		this.id.add(index, id);
	
		this.email.add(index, email);

	}

}
