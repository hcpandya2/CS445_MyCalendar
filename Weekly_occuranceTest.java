import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;


public class Weekly_occuranceTest {

	@Test
	public void checkdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("01/09/2012 02:02:02"));
		Weekly_occurance mon_r = new Weekly_occurance(startdate);
		assertFalse(mon_r.CheckDate(1, 2, 2012));
		assertTrue(mon_r.CheckDate(1, 9, 2012));
		assertTrue(mon_r.CheckDate(1, 16, 2012));
		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("01/23/2012 02:02:02"));
		mon_r.setlastAppointment(enddate);
		assertTrue(mon_r.CheckDate(1, 23, 2012));
		assertFalse(mon_r.CheckDate(1, 30, 2012));
	}
	
	@Test
	public void checkGetEventFreq(){
		assertTrue("WEEKLY".equals(new Weekly_occurance(Calendar.getInstance()).Get_eventfreq()));
	}

}
