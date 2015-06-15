import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class Once_occurance_test {

	@Test
	public void once_occurance_test() throws ParseException {
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Once_occurance onc_r = new Once_occurance(startdate);
		
		assertEquals(onc_r.getFirstAppointment(),startdate);
		assertEquals(onc_r.getName(),"once");
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
	public void TestCheckDate() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		startdate.setTime(SDF.parse("05/09/2012 02:02:02"));
		Once_occurance onc_r = new Once_occurance(startdate);
	
		assertFalse(onc_r.CheckDate(5, 9, 2013));
		assertFalse(onc_r.CheckDate(4, 9, 2012));
		assertFalse(onc_r.CheckDate(5, 8, 2012));
		assertTrue(onc_r.CheckDate(5, 9, 2012));
	}
	
	@Test
	public void checkGetEventFreq(){
		assertTrue("".equals(new Once_occurance(Calendar.getInstance()).Get_eventfreq()));
	}
}
