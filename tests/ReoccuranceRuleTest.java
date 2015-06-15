import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;


public class ReoccuranceRuleTest {

	@Test
	public void recurrancerule_test() throws ParseException {
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		Once_occurance once_r = new Once_occurance(startdate);
		/*once_r.setDuration(3);
		once_r.setlastAppointment(enddate);
		*/
		
		assertEquals(once_r.getFirstAppointment(), startdate);
		
	}
	
	@Test
	public void duration_test() throws ParseException{
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		Once_occurance once_r = new Once_occurance(startdate);
		once_r.setDuration(3);
		assertEquals(once_r.getFirstAppointment(), startdate);
		assertEquals(once_r.getDuration(), 3);
		
	}
	
	@Test
	public void lastdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:02:02"));
		
		Once_occurance once_r = new Once_occurance(startdate);
		once_r.setlastAppointment(enddate);
		once_r.setDuration(80);
		
		assertEquals(once_r.getFirstAppointment(),startdate);
		assertEquals(once_r.getLastAppintment(), enddate);
		assertEquals(once_r.getDuration(), 80);
	}
	
	@Test
	public void withindaterange_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		//use monthly, but any type would work
		Monthly_occurance mon_r = new Monthly_occurance(startdate);
		
		Calendar date = Calendar.getInstance();
		date.setTime(SDF.parse("02/02/2012 02:02:02"));
		assertTrue(mon_r.withinDateRange(date));
		date.setTime(SDF.parse("02/03/2012 02:02:02"));
		assertTrue(mon_r.withinDateRange(date));
		date.setTime(SDF.parse("02/02/2012 01:01:01"));
		assertTrue(mon_r.withinDateRange(date));
		date.setTime(SDF.parse("02/01/2012 02:02:02"));
		assertFalse(mon_r.withinDateRange(date));
		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/02/2013 02:02:02"));
		
		mon_r.setlastAppointment(enddate);
		date.setTime(SDF.parse("02/05/2012 01:01:01"));
		assertTrue(mon_r.withinDateRange(date));
		date.setTime(SDF.parse("02/02/2013 05:01:01"));
		assertTrue(mon_r.withinDateRange(date));
		date.setTime(SDF.parse("02/04/2013 05:01:01"));
		assertFalse(mon_r.withinDateRange(date));
	}

}
