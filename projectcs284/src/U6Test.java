import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class U6Test {

	@Test
	void testFile() {
		File file = new File("result.xlsx");
	assertTrue(file.exists(),"error");
	}

}
