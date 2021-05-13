package mechanics;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void calculateBonusTest() {
		assertEquals(-5,Utils.calculateBonus(1));
		assertEquals(-4,Utils.calculateBonus(2));
		assertEquals(-4,Utils.calculateBonus(3));
		assertEquals(-3,Utils.calculateBonus(4));
		assertEquals(-3,Utils.calculateBonus(5));
		assertEquals(-2,Utils.calculateBonus(6));
		assertEquals(-2,Utils.calculateBonus(7));
		assertEquals(-1,Utils.calculateBonus(8));
		assertEquals(-1,Utils.calculateBonus(9));
		assertEquals(0,Utils.calculateBonus(10));
		assertEquals(0,Utils.calculateBonus(11));
		assertEquals(1,Utils.calculateBonus(12));
		assertEquals(1,Utils.calculateBonus(13));
		assertEquals(2,Utils.calculateBonus(14));
		assertEquals(2,Utils.calculateBonus(15));
		assertEquals(3,Utils.calculateBonus(16));
		assertEquals(3,Utils.calculateBonus(17));
		assertEquals(4,Utils.calculateBonus(18));
		assertEquals(4,Utils.calculateBonus(19));
		assertEquals(5,Utils.calculateBonus(20));
		assertEquals(5,Utils.calculateBonus(21));
		assertEquals(6,Utils.calculateBonus(22));
		assertEquals(6,Utils.calculateBonus(23));
		assertEquals(7,Utils.calculateBonus(24));
		assertEquals(7,Utils.calculateBonus(25));
		assertEquals(8,Utils.calculateBonus(26));
		assertEquals(8,Utils.calculateBonus(27));
		assertEquals(9,Utils.calculateBonus(28));
		assertEquals(9,Utils.calculateBonus(29));
		assertEquals(10,Utils.calculateBonus(30));
		
	}

}
