import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.Test;


public class User_commandsTest {

	@Test
	public void testGetDatesAppointments() {
		User_commands uc = new User_commands();
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
		assertEquals(4, uc.GetDatesAppointments(testDate).size());
		assertTrue(uc.GetDatesAppointments(testDate).contains(ap1));
		assertFalse(uc.GetDatesAppointments(testDate).contains(ap2));
	}
	
	@Test
	public void testGetWeeksAppointments(){
		User_commands uc = new User_commands();
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
		assertEquals(8, uc.GetWeeksAppointments(startDate2).size());
	}
	
	@Test
	public void testGetMonthsAppointments(){
		User_commands uc = new User_commands();
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
		assertEquals(64, uc.GetMonthsAppointments(testDate).size());
	}
	
	@Test
	public void testGetYearsAppointments(){
		User_commands uc = new User_commands();
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
		assertEquals(734, uc.GetYearsAppointments(testDate).size());
	}
	
	@Test
	public void testImport_appointment(){
		User_commands uc = new User_commands();
		BufferedReader r = mock(BufferedReader.class);
		uc.reader = r;
		try {
			when(r.readLine()).thenReturn("test.ics").thenReturn("asdfjlkasdfjklsadfjlk");
		} catch (IOException e) {}
		assertTrue(uc.Import_Appointment().size() > 3);
		assertTrue(uc.Import_Appointment().size() == 0);
	}
	
	@Test
	public void testread_file(){
		//note: "read_file" actually writes file
		User_commands uc = new User_commands();
		BufferedReader r = mock(BufferedReader.class);
		uc.reader = r;
		try {
			when(r.readLine()).thenReturn("test.ics").thenReturn("asdfjlkasdfjklsadfjlk.ics");
		} catch (IOException e) {}
		Schedule.appointments = uc.Import_Appointment();
		uc.read_file();
		try {
			verify(r, times(2)).readLine();
		} catch (IOException e) {}
	}
	
	@Test
	public void testPrintArrayListOfAppointments(){
		User_commands uc = new User_commands();
		ArrayList<Appointment> aps = new ArrayList<Appointment>();
		//Schedule.appointments = aps;
		Appointment ap1 = mock(Appointment.class);
		header_date_occur ap2 = mock(header_date_occur.class);
		aps.add(ap1);
		aps.add(ap2);
		when(ap1.toStringTestFriendly()).thenReturn("header can print");
		when(ap2.toStringTestFriendly()).thenReturn("appt can print");
		uc.printArrayListOfAppointments(aps);
		verify(ap1).toStringTestFriendly();
		verify(ap2).toStringTestFriendly();
	}
}
