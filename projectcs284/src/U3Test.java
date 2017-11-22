import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class U3Test {

ExamCriteria e = new  ExamCriteria ();
	
	@Test
	void testPositiveNum() {
		
		e.add("QUIZ1",10,20);
		e.add("QUIZ1",-10,-20);
		
		assertFalse(Integer.valueOf(e.getMax(1))>0,"correct");
		assertTrue(Integer.valueOf(e.getMax(0))>0,"error");
	}


}
