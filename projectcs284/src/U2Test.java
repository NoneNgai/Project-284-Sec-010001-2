import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class U2Test {

	StudentList std = new StudentList();
	@Test
	void testTenID() {
		String s = "5909610163";
		std.addID(Long.valueOf(s));
		assertTrue( s.length() == 10,"error");
	}
	@Test
	void checkID() {
		long[] number;
		int cnt = 0;
		number = new long[std.getIDList().size()];
		String s = "5909610163";
		number[0] = Long.valueOf(s);	
		assertTrue(cnt==1);
		
	}

}
