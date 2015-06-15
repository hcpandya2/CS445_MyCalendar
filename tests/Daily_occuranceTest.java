import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class Daily_occuranceTest {

	@Test
	public void monthly_occurance_test() throws ParseException {
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Daily_occurance r = new Daily_occurance(startdate);
		
		assertEquals(r.getFirstAppointment(),startdate);
		assertEquals(r.getName(),"Daily");
		
	}
	
	@Test
	public void duration_test() throws ParseException{
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		
		Daily_occurance mon_r = new Daily_occurance(startdate);
	    mon_r.setDuration(5);
		
	    assertEquals(mon_r.getFirstAppointment(), startdate);
		assertEquals(mon_r.getDuration(), 5);
		
	}
	
	@Test
	public void lastdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:02:02"));
		
		Daily_occurance mon_r = new Daily_occurance(startdate);
		mon_r.setlastAppointment(enddate);
		mon_r.setDuration(80);
		
		assertEquals(mon_r.getFirstAppointment(),startdate);
		assertEquals(mon_r.getLastAppintment(), enddate);
		assertEquals(mon_r.getDuration(), 80);
	}
	
	@Test
	public void checkdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/01/2012 02:02:02"));
		Daily_occurance mon_r = new Daily_occurance(startdate);
		
		assertFalse(mon_r.CheckDate(1, 31, 2012));
		assertTrue(mon_r.CheckDate(2, 1, 2012));
		assertTrue(mon_r.CheckDate(2, 2, 2012));
		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:02:02"));
		mon_r.setlastAppointment(enddate);
		assertTrue(mon_r.CheckDate(2, 3, 2013));
		assertFalse(mon_r.CheckDate(2, 4, 2013));
	}
	
	@Test
	public void checkGetEventFreq(){
		assertTrue("DAILY".equals(new Daily_occurance(Calendar.getInstance()).Get_eventfreq()));
	}

	@Test
	public void withindaterange_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		
		Daily_occurance mon_r = new Daily_occurance(startdate);
		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/02/2013 02:02:02"));
		
		Calendar date = Calendar.getInstance();
		date.setTime(SDF.parse("15/03/2012 02:02:02"));
		
		assertTrue(mon_r.withinDateRange(date));
	}

}
