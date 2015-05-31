import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class AppointmentTest {

	
	@Test
	public void appointment_test() {
		
		Calendar startdate = Calendar.getInstance();
		Calendar enddate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		ap.setEnd_date(enddate);
		
		assertEquals(ap.getDescription(),"description1");
		ap.setDescription("dummy_description");
		equals(ap.description.equalsIgnoreCase("dummy_description"));
		
		assertEquals(ap.getTitle(),"title1");
		ap.setTitle("dummy_title");
		equals(ap.title.equalsIgnoreCase("dummy_title"));
		
		Calendar newsd = Calendar.getInstance();
		newsd.set(2015,2,10,10,2,2);
		ap.setStart_date(newsd);
		ap.getStart_date();
		assertEquals(ap.getStart_date().DATE,10);
		assertEquals(ap.getStart_date().MONTH,2);
		assertEquals(ap.getStart_date().YEAR,2015);
		assertEquals(ap.getStart_date(),startdate);
		
		assertEquals(ap.getDuration(),5);
		ap.setDuration(10);
		assertEquals(ap.getDuration(),10);
		
		assertEquals(ap.getEnd_date(),enddate);
		assertEquals(ap.r.firstAppointment,startdate);
		assertEquals(ap.r.getLastAppintment(),null);
		assertEquals(ap.r.getName(),"once");
		assertFalse(ap.r.getName().equalsIgnoreCase("Weekly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("Yearly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("Error - BRRRRrrrr!"));
		
		ap.setSummary("dummy_summary");
		equals(ap.summary.equalsIgnoreCase("dummy_summary"));
		equals(ap.getSummary().equalsIgnoreCase("dummy_summary"));
	}

	@Test
	public void isondate_test(){
		Calendar startdate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		Calendar dummy = Calendar.getInstance();
		dummy.set(2015,2,10,10,2,2);
		ReoccuranceRule r = mock(ReoccuranceRule.class);
		when(r.CheckDate(dummy.MONTH, dummy.DATE, dummy.YEAR)).thenReturn(true);		
		assertTrue(ap.IsOndate(dummy));
	}
	
	@Test
	public void updatereoccurancetype_test() throws ParseException{
		
		Calendar startdate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		ap.UpdateReoccurancetype("daily");
		equals(ap.r.getName().equalsIgnoreCase("daily"));
		assertFalse(ap.r.getName().equalsIgnoreCase("weekly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("monthly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("yearly"));
		assertFalse(ap.r.getName().equalsIgnoreCase("once"));
	}
	
	@Test
	public void setReoccuranceRule_test(){
		/*
		Calendar sd = Calendar.getInstance();
		sd.set(2015,2,10,10,2,2);
		Calendar ed = Calendar.getInstance();
		ed.set(2015,2,12,10,2,2);
		ReoccuranceRule new_r = mock(ReoccuranceRule.class);
		when(new_r.getFirstAppointment()).thenReturn(sd);
		when(new_r.getLastAppintment()).thenReturn(ed);
		when(new_r.getName()).thenReturn("daily");
		when(new_r.getDuration()).thenReturn(50);
		
		Calendar startdate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		ap.setReoccuranceRule(new_r);
		assertEquals(ap.r.getFirstAppointment(),sd);
		assertEquals(ap.r.getLastAppintment(),ed);
		assertEquals(ap.r.getName(),"daily");
		assertEquals(ap.r.getDuration(),50);
		*/
		Calendar startdate = Calendar.getInstance();
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		ReoccuranceRule r = new Once_occurance(startdate);
		ap.setReoccuranceRule(r);
		assertEquals(ap.r, r);
	}
}
