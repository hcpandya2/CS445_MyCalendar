import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;


public class header_date_occurTest {

	@Test
	public void testGetDateFor() {
		Calendar c = Calendar.getInstance();
		header_date_occur h = new header_date_occur(c);
		assertEquals(c, h.getDateFor());
	}
	
	@Test
	public void testToString(){
		Calendar c = Calendar.getInstance();
		c.set(1234, 2, 6);
		header_date_occur h = new header_date_occur(c);
		assertTrue("Appointments for: 3/6/1234".equalsIgnoreCase(h.toString()));
	}
	
}
