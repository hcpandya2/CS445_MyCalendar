import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Schedule_Test {
	
	@Test
	public void test_delete(){
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		Appointment ap = new Appointment("testtitle", "testdesc", Calendar.getInstance(), 4 , "Daily");
		aps.add(ap);
		Schedule.appointments = aps;
		assertEquals(1, aps.size());
		assertTrue(aps.contains(ap));
		Schedule.delete(ap);
		assertEquals(0, aps.size());
		assertFalse(aps.contains(ap));
	}
	
	@Test
	public void add_appointment_test(){
		
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		Appointment ap1 = new Appointment("title2", "description2", Calendar.getInstance(), 5, "Weekly");
		Appointment ap2 = new Appointment("title3", "description3", Calendar.getInstance(), 6, "Daily");
		aps.add(ap1);
		aps.add(ap2);
		Schedule.appointments = aps;
		
		assertTrue(aps.contains(ap1));
		assertTrue(aps.contains(ap2));
		assertEquals(2, aps.size());
		
		Schedule.add_new("title4", "summary4", "description4", Calendar.getInstance(), Calendar.getInstance(), 50, "once");
		assertEquals(3,aps.size());
		
		Schedule.appointments = null;
		Schedule.add_new("title4", "summary4", "description4", Calendar.getInstance(), Calendar.getInstance(), 50, "once");
		assertEquals(1,Schedule.appointments.size());
		Schedule.add_new("title4", "summary4", "description4", Calendar.getInstance(), Calendar.getInstance(), 50, "once");
		assertEquals(2,Schedule.appointments.size());
	}
	
	@Test
	public void end_date_test() throws ParseException{
		Appointment ap = new Appointment("title1", "description1",null, 5,"once");
		assertNull(ap.getEnd_date());
		Calendar c = Calendar.getInstance();
		Schedule.end(ap, c);
		assertEquals(c, ap.getEnd_date());
	}
	
	@Test
	public void testGetDatesAppointments(){
		ArrayList<Appointment> apl = new ArrayList<Appointment>();
		Schedule.appointments = apl;
		Appointment ap1 = mock(Appointment.class);
		Appointment ap2 = mock(Appointment.class);
		Appointment ap3 = mock(Appointment.class);
		Appointment ap4 = mock(Appointment.class);
		Appointment ap5 = mock(Appointment.class);
		apl.add(ap1);
		apl.add(ap2);
		apl.add(ap3);
		apl.add(ap4);
		apl.add(ap5);
		Calendar testDate = Calendar.getInstance();
		when(ap1.IsOndate(testDate)).thenReturn(true);
		when(ap2.IsOndate(testDate)).thenReturn(false);
		when(ap3.IsOndate(testDate)).thenReturn(true);
		when(ap4.IsOndate(testDate)).thenReturn(false);
		when(ap5.IsOndate(testDate)).thenReturn(true);
		assertEquals(4, Schedule.getDatesAppointments(testDate).size());
		assertTrue(Schedule.getDatesAppointments(testDate).contains(ap1));
		assertFalse(Schedule.getDatesAppointments(testDate).contains(ap2));
	}
	
	@Test
	public void find_app_test() throws ParseException{
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		Appointment ap1 = new Appointment("title2", "description2", Calendar.getInstance(), 5, "Weekly");
		Appointment ap2 = new Appointment("title3", "description3", Calendar.getInstance(), 6, "Daily");
		Appointment ap3 = new Appointment(null, "description3", Calendar.getInstance(), 6, "Daily");
		Appointment ap = new Appointment("testtitle", "testdesc", Calendar.getInstance(), 4 , "Daily");
		aps.add(ap1);
		aps.add(ap2);
		aps.add(ap);
		aps.add(ap3);
		Schedule.appointments = aps;

		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:03:04"));
		
		result = Schedule.find_app("title2");
		assertTrue(result.contains(ap1));
		assertEquals(1,result.size());
		result = Schedule.find_app("title2");
		assertTrue(result.contains(ap1));
	}
	
	@Test
	public void testGetWeeksAppointments(){
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		Schedule.appointments = aps;
		Calendar startDate1 = Calendar.getInstance();
		startDate1.set(2015, 4, 26);
		Calendar startDate2 = Calendar.getInstance();
		startDate2.set(2015, 4, 21);
		Appointment ap1 = new Appointment("title2", "description2", startDate1, 5, "Daily");
		Appointment ap2 = new Appointment("title3", "description3", startDate2, 6, "Weekly");
		Appointment ap3 = new Appointment("title3", "description3", startDate1, 6, "Monthly");
		aps.add(ap1);
		aps.add(ap2);
		aps.add(ap3);
		assertEquals(8, Schedule.getWeeksAppointments(startDate2).size());
	}
	
	@Test
	public void testGetMonthsAppointments(){
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		Schedule.appointments = aps;
		Calendar startDate1 = Calendar.getInstance();
		startDate1.set(2015, 3, 26);
		Calendar startDate2 = Calendar.getInstance();
		startDate2.set(2015, 4, 21);
		Calendar testDate = Calendar.getInstance();
		testDate.set(2015, 4, 15);
		Appointment ap1 = new Appointment("title2", "description2", startDate1, 5, "Daily");
		Appointment ap2 = new Appointment("title3", "description3", startDate2, 6, "Weekly");
		aps.add(ap1);
		aps.add(ap2);
		assertEquals(64, Schedule.getMonthsAppointments(testDate).size());
	}
	
	@Test
	public void testGetYearsAppointments(){
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		Schedule.appointments = aps;
		Calendar startDate1 = Calendar.getInstance();
		startDate1.set(2014, 3, 26);
		Calendar startDate2 = Calendar.getInstance();
		startDate2.set(2015, 11, 6);
		Calendar testDate = Calendar.getInstance();
		testDate.set(2015, 4, 15);
		Appointment ap1 = new Appointment("title2", "description2", startDate1, 5, "Daily");
		Appointment ap2 = new Appointment("title3", "description3", startDate2, 6, "Weekly");
		aps.add(ap1);
		aps.add(ap2);
		assertEquals(734, Schedule.getYearsAppointments(testDate).size());
	}
	
	@Test
	public void testConstructor(){
		//Schedule is completely static - this makes
		//the coverage calculation happy
		Schedule s = new Schedule();
		assertNotNull(s);
	}
}
