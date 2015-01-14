import java.util.ArrayList;


public class Calendar {

	String name;
	static ArrayList<Appointment> appointments;           		// list of all the existing or upcoming appointments
	static ArrayList<Appointment> past_appointments;	 	// list of all the past appointments
	
	
	//initializing a calendar with a name and a list of appointment
	public Calendar(String name){
		
		this.name = name;
		appointments = new ArrayList<Appointment>();
		past_appointments = new ArrayList<Appointment>();
		
	}
	
	// removing a appointment from the current list of appointments and adding it to the past_appointments
	public static void delete (Appointment ap){
		for(Appointment a : appointments){
			if(a.equals(ap))
				past_appointments.add(ap);
				appointments.remove(ap);
		}
	
	}  // end of delete
	
	// adding a new appointment to the list
	public static void add_new (Appointment ap){
		appointments.add(ap);	
		
	}
	
	
	// this method returns an appointment with matching title keyword or it returns a null 
	public static Appointment find_app(String title){
		for(Appointment a : appointments){
			if((a.title).equalsIgnoreCase(title))
				return a;
			}
		
		System.out.println("No Such Appointment Exists with tile  " + title);
		return null;
	}
	
	
	
	
	
	
}
