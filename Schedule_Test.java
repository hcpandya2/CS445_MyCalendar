import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.*;

import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.beans.binding.When;

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
		
	}
	
	public void end_date_test() throws ParseException{
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		Appointment ap1 = new Appointment("title2", "description2", Calendar.getInstance(), 5, "Weekly");
		Appointment ap2 = new Appointment("title3", "description3", Calendar.getInstance(), 6, "Daily");
		Appointment ap = new Appointment("testtitle", "testdesc", Calendar.getInstance(), 4 , "Daily");
		aps.add(ap1);
		aps.add(ap2);
		aps.add(ap);
		Schedule.appointments = aps;

		SimpleDateFormat SDF = new SimpleDateFormat("MMDDYYYY HH:mm:ss");
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:03:04"));
		Schedule.end(ap, enddate);
	}
	
	public void getDatesappointments_test() throws ParseException{
		
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		
		Appointment ap1 = new Appointment("title2", "description2", Calendar.getInstance(), 5, "Weekly");
		Appointment ap2 = new Appointment("title3", "description3", Calendar.getInstance(), 6, "Daily");
		Appointment ap = new Appointment("testtitle", "testdesc", Calendar.getInstance(), 4 , "Daily");
		aps.add(ap1);
		aps.add(ap2);
		aps.add(ap);
		Schedule.appointments = aps;
		
		SimpleDateFormat SDF = new SimpleDateFormat("MMDDYYYY HH:mm:ss");
		Calendar inputdate = Calendar.getInstance();
		inputdate.setTime(SDF.parse("02/03/2013 02:03:04"));
		
		System.out.println("Result");
		System.out.println(result);
		result = Schedule.getDatesAppointments(inputdate);
		
		System.out.println(result);
		System.out.println("size: " + result.size());
		assertEquals(0, result.size());
		
		Appointment ap3 = new Appointment("title4", "Desc4", inputdate,10,"once");
		Schedule.appointments.add(ap3);
		assertEquals(4,Schedule.appointments.size());
		
		result = Schedule.getDatesAppointments(inputdate);
		assertEquals(1,result.size());
		assertTrue(result.contains(ap3));
		System.out.println(result);
		
	}
	
	public void find_app_test() throws ParseException{
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		Appointment ap1 = new Appointment("title2", "description2", Calendar.getInstance(), 5, "Weekly");
		Appointment ap2 = new Appointment("title3", "description3", Calendar.getInstance(), 6, "Daily");
		Appointment ap = new Appointment("testtitle", "testdesc", Calendar.getInstance(), 4 , "Daily");
		aps.add(ap1);
		aps.add(ap2);
		aps.add(ap);
		Schedule.appointments = aps;

		SimpleDateFormat SDF = new SimpleDateFormat("MMDDYYYY HH:mm:ss");
		Calendar enddate = Calendar.getInstance();
		enddate.setTime(SDF.parse("02/03/2013 02:03:04"));
		
		result = Schedule.find_app("title2");
		assertTrue(result.contains(ap1));
		assertEquals(1,result.size());
		
		
	}
	
}
