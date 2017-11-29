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

	@Test
	void testOneHundred() {
		
		e.add("QUIZ1",100,25);
		e.add("QUIZ1",100,25);
		e.add("Midterm",100,25);
		e.add("Final",100,25);
		
		int q1 = Integer.valueOf(e.getPercent(0));
		int q2 = Integer.valueOf(e.getPercent(1));
		int mid = Integer.valueOf(e.getPercent(2));
		int fi = Integer.valueOf(e.getPercent(3));
		
		assertTrue(q1+q2+mid+fi ==100,"error");
	}
	
	@Test
	void testFifty() {
		
		
		e.add("Midterm",100,25);
		e.add("Final",100,25);
		
		int mid = Integer.valueOf(e.getPercent(0));
		int fi = Integer.valueOf(e.getPercent(1));
		
		assertTrue(mid+fi >=50,"error");
	}
	@Test
	void testEachTypeHasCriteria() { 
		
		
		e.add("Midterm",100,25);
		e.add("Final",100,25);
		
		int type = e.getTypeList().size();
		int max = e.getMaxList().size();
		int percent = e.getPercentList().size();
		
		
		assertTrue(type == max && max == percent && percent == type ,"error");
	}

}
