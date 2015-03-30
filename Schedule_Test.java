import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.text.SimpleDateFormat;
import java.util.*;

import javafx.beans.binding.When;

public class Schedule_Test {
	
	
	@Test
	public void test_delelete(){
		String title = "Test Title 1";
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		
		Appointment appointment = mock(Appointment.class);
		Mockito.when(appointment.getTitle()).thenReturn(title);
		Mockito.when(appointment.getDescription()).thenReturn("Description01");
		Mockito.when(appointment.getStart_date()).thenReturn(SDF.parse("10/02/2010 02:02:02"));
		
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		Schedule test = new Schedule(title);
		test.appointments = appointments;
		
		
		//Appointment appointment = Mockito.mock(Appointment.class);
		//Mockito.when(test.getTitle()).thenReturn(title);
		//doReturn(test.title).when(test.getTitle());
		
		

	}

}
