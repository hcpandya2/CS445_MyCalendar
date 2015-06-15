import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class Yearly_occuranceTest {

	@Test
	public void checkdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("01/09/2012 02:02:02"));
		Yearly_occurance mon_r = new Yearly_occurance(startdate);
		assertFalse(mon_r.CheckDate(1, 9, 2011));
		assertTrue(mon_r.CheckDate(1, 9, 2012));
		assertTrue(mon_r.CheckDate(1, 9, 2013));
		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("01/09/2014 02:02:02"));
		mon_r.setlastAppointment(enddate);
		assertTrue(mon_r.CheckDate(1, 9, 2014));
		assertFalse(mon_r.CheckDate(1, 9, 2015));
	}
	
	@Test
	public void checkGetEventFreq(){
		assertTrue("YEARLY".equals(new Yearly_occurance(Calendar.getInstance()).Get_eventfreq()));
	}
	
	@Test
	public void weekly_occurance_test() throws ParseException {
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Yearly_occurance mon_r = new Yearly_occurance(startdate);
		
		assertEquals(mon_r.getFirstAppointment(),startdate);
		assertEquals(mon_r.getName(),"Yearly");
	}
	
	@Test
	public void duration_test() throws ParseException{
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"yearly");
		
		Yearly_occurance mon_r = new Yearly_occurance(startdate);
		ap.r = mon_r;
		
		mon_r.setDuration(5);
		assertEquals(mon_r.getFirstAppointment(), startdate);
		assertEquals(mon_r.getDuration(), 5);
		assertEquals(ap.getDuration(),5);
	}
	
	@Test
	public void lastdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));		
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/16/2012 02:02:02"));
		
		Yearly_occurance mon_r = new Yearly_occurance(startdate);
		mon_r.setlastAppointment(enddate);
		mon_r.setDuration(80);
		
		assertEquals(mon_r.getFirstAppointment(),startdate);
		assertEquals(mon_r.getLastAppintment(), enddate);
		assertEquals(mon_r.getDuration(), 80);
		
		
	}
	
}
