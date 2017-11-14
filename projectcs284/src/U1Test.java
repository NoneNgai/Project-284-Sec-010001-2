import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class U1Test {

	ExamResult e = new ExamResult();
	
	@Test
	void testPositiveNum() {
		
		e.add("1");
		e.add("-1");
		
		assertFalse(Integer.valueOf(e.getScore(1))>0,"correct");
		assertTrue(Integer.valueOf(e.getScore(0))>0,"error");
	}

	@Test
	void testMaxScore()
	{
		int max = 10;
		int score = 11;
		e.add("11");
		
		assertFalse(Integer.valueOf(e.getScore(0))<max,"error");
	}

}
