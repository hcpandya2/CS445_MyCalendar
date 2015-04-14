import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;


public class ReoccuranceRuleTest {

	@Test
	public void recurrancerule_test() throws ParseException {
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		Once_occurance once_r = new Once_occurance(startdate);
		/*once_r.setDuration(3);
		once_r.setlastAppointment(enddate);
		*/
		
		assertEquals(once_r.getFirstAppointment(), startdate);
		
	}
	
	public void duration_test() throws ParseException{
		
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		Once_occurance once_r = new Once_occurance(startdate);
		once_r.setDuration(3);
		assertEquals(once_r.getFirstAppointment(), startdate);
		assertEquals(once_r.getDuration(), 3);
		
	}
	
	public void lastdate_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
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

}
