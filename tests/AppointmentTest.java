import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class AppointmentTest {

	@Test
	public void appointment_test() {
		
		Calendar calendar = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",calendar, 5,"once");
		ap.setEnd_date(calendar);
		
		assertEquals(ap.getDescription(),"description1");
		assertEquals(ap.getTitle(),"title1");
		assertEquals(ap.getStart_date(),calendar);
		assertEquals(ap.getDuration(),5);
		assertEquals(ap.getEnd_date(),calendar);
	}
	
	@Test
	public void setReoccuranceRule_test(){
		
		Calendar startdate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		ReoccuranceRule r = new Once_occurance(startdate);
		ap.setReoccuranceRule(r);
		assertEquals(ap.r, r);
	}

	@Test
	public void isondate_test(){
		Calendar calendar = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",calendar, 5,"once");
		boolean result = ap.IsOndate(calendar);
		assertTrue(result);
	}

	@Test
	public void testSetDuration(){
		Appointment ap = new Appointment("title1", "description1", Calendar.getInstance(), 5,"once");
		ap.setDuration(12542);
		assertEquals(12542, ap.r.duration);
	}
	
	@Test
	public void appointment_test_2() {
		
		Calendar startdate = Calendar.getInstance();
		Calendar enddate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		ap.setEnd_date(enddate);
		
		assertEquals(ap.getDescription(),"description1");
		ap.setDescription("dummy_description");
		equals(ap.getDescription().equalsIgnoreCase("dummy_description"));
		
		assertEquals(ap.getTitle(),"title1");
		ap.setTitle("dummy_title");
		equals(ap.title.equalsIgnoreCase("dummy_title"));
		
		Calendar newsd = Calendar.getInstance();
		newsd.set(2015,2,10,10,2,2);
		ap.setStart_date(newsd);

		assertEquals(ap.getDuration(),5);
		ap.setDuration(10);
		assertEquals(ap.getDuration(),10);
		
		assertEquals(ap.getEnd_date(),enddate);
		assertEquals(ap.r.getName(),"once");
		assertFalse(ap.r.getName().equalsIgnoreCase("Weekly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("Yearly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("Error - BRRRRrrrr!"));
		
		ap.setSummary("dummy_summary");
		equals(ap.getSummary().equalsIgnoreCase("dummy_summary"));
	}
	
	@Test
	public void testUpdateReoccurancetype(){
		Calendar startdate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		assertTrue(ap.r instanceof Once_occurance);
		ap.UpdateReoccurancetype("Daily");
		assertTrue(ap.r instanceof Daily_occurance);
		ap.UpdateReoccurancetype("Weekly");
		assertTrue(ap.r instanceof Weekly_occurance);
		ap.UpdateReoccurancetype("Monthly");
		assertTrue(ap.r instanceof Monthly_occurance);
		ap.UpdateReoccurancetype("Yearly");
		assertTrue(ap.r instanceof Yearly_occurance);
		ap.UpdateReoccurancetype("Once");
		System.out.println("expect error on following line:");
		ap.UpdateReoccurancetype("asdfjklaser");
		assertTrue(ap.r instanceof Once_occurance);
	}
	
}
