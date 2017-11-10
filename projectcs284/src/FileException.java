import java.io.FileNotFoundException;

public class FileException extends FileNotFoundException {

	public FileException() {
		super("File not Found");
	}

}
