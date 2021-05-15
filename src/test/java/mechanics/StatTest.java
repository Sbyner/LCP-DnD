package mechanics;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class StatTest {
	


	@Test
	public void permModifyTest() {
		var stat = new Stat<Integer>(Integer.valueOf(10));
		stat.permModify((x)->15);
		assertEquals(15, stat.getValue().intValue());
	}
	
	@Test
	public void tempModifyTest() {
		var stat = new Stat<Integer>(Integer.valueOf(10));
		stat.modify((x)->15);
		assertEquals(15, stat.getValue().intValue());
		stat.restoreValue();
		assertEquals(10, stat.getValue().intValue());

	}

}
