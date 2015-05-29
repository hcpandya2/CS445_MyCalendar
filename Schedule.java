import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Schedule {

	String name;
	static ArrayList<Appointment> appointments;   // list of all the existing or upcoming appointments
	
	//initializing a calendar with a name and a list of appointment
	
	
	
	public Schedule(String name){
		this.name = name;
		appointments = new ArrayList<Appointment>();
		//past_appointments = new ArrayList<Appointment>();
	}
	
	// this is for when the user wants to actually delete an appointment, not just stop it from reoccurring
	public static void delete (Appointment ap){
		appointments.remove(ap);
	}
	
	//make appointment no longer occur after endDate
	public static void end (Appointment ap, Calendar endDate){
		ap.setEnd_date(endDate);
	}
	
	// adding a new appointment to the list
	public static void add_new (String title, String description, Calendar startdate, int duration, String reoccuranceType){
		
		Appointment appointment = new Appointment(title,description,startdate,duration,reoccuranceType);
		if(appointments == null){
			appointments = new ArrayList<Appointment>();
		}
		
		if(appointments != null && !(Schedule.appointments).contains(appointment)){
			(Schedule.appointments).add(appointment);
		}
		else System.out.println("This appointment already exists");
		
	}
	
	public static ArrayList<Appointment> getDatesAppointments(Calendar date){
		ArrayList<Appointment> datesAppointments = new ArrayList<Appointment>();
		
		for(Appointment ap : appointments){
			if(ap.IsOndate(date))
				datesAppointments.add(ap);
		}
		
		Collections.sort(datesAppointments);
		
		return datesAppointments;
	}
	
	// this method returns an appointment with matching title keyword or it returns a null 
	public static ArrayList<Appointment> find_app(String search_word){
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		for(Appointment a : appointments){
			if(a.title.equalsIgnoreCase(search_word)){
				result.add(a);
			}
		}
		
		return result;
	}
	
		
}
