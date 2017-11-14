import java.io.FileNotFoundException;

public class FileFormatException extends FileNotFoundException {

	public FileFormatException() {
		super("Please insert file in .xlsx form");
	}

}
