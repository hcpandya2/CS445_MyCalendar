import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class AppointmentTest {

	@Test
	public void appointment_test() {
		
		Calendar calendar = Calendar.getInstance();
		/*
		ReoccuranceRule r = mock(ReoccuranceRule.class);
		when(r.getDuration()).thenReturn(3);
		when(r.getName()).thenReturn("once");
		when(r.getFirstAppointment()).thenReturn(calendar);
		*/
		Appointment ap = new Appointment("title1", "description1",calendar, 5,"once");
		ap.setEnd_date(calendar);
		
		assertEquals(ap.getDescription(),"description1");
		assertEquals(ap.getTitle(),"title1");
		assertEquals(ap.getStart_date(),calendar);
		assertEquals(ap.getDuration(),5);
		assertEquals(ap.getEnd_date(),calendar);
	}

	public void isondate_test(){
		//ReoccuranceRule r = mock(ReoccuranceRule.class);
		Calendar calendar = Calendar.getInstance();
		/*when(r.getDuration()).thenReturn(3);
		when(r.getName()).thenReturn("once");
		when(r.getFirstAppointment()).thenReturn(calendar);
		*/
		Appointment ap = new Appointment("title1", "description1",calendar, 5,"once");
		//ap.r = r;
		boolean result = ap.IsOndate(calendar);
		
		assertTrue(result);
			
		
	}
	
	public void updatereoccurancetype_test() throws ParseException{
		Calendar startdate = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		startdate.setTime(SDF.parse("02/02/2012 02:02:02"));
		Appointment ap = new Appointment("title1", "description1",startdate, 5,"once");
		
		ap.UpdateReoccurancetype("daily");
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:02:02"));
		
		assertEquals(ap.r,"daily");
		assertEquals(ap.getEnd_date(),enddate);
	}
	
	
	
}
