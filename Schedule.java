import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Schedule {

	//String name;
	static ArrayList<Appointment> appointments;   // list of all the existing or upcoming appointments
	
	// this is for when the user wants to actually delete an appointment, not just stop it from reoccurring
	public static void delete (Appointment ap){
		appointments.remove(ap);
	}
	
	//make appointment no longer occur after endDate
	public static void end (Appointment ap, Calendar endDate){
		ap.setEnd_date(endDate);
	}
	
	// adding a new appointment to the list
	public static void add_new (String title,String summary, String description, Calendar startdate, Calendar lastdate, int duration, String reoccuranceType){
		
		Appointment appointment = new Appointment(title,description,startdate,duration,reoccuranceType);
		appointment.setSummary(summary);
		appointment.r.setlastAppointment(lastdate);
		if(appointments == null){
			appointments = new ArrayList<Appointment>();
		}
		
		(Schedule.appointments).add(appointment);
	}
	
	public static ArrayList<Appointment> getDatesAppointments(Calendar date){
		ArrayList<Appointment> datesAppointments = new ArrayList<Appointment>();
		
		for(Appointment ap : appointments){
			if(ap.IsOndate(date))
				datesAppointments.add(ap);
		}
		
		Collections.sort(datesAppointments);
		Calendar copy = Calendar.getInstance();
		copy.setTime(date.getTime());
		datesAppointments.add(0, new header_date_occur(copy));
		return datesAppointments;
	}
	
	public static ArrayList<Appointment> getAppointmentsInRange(Calendar startDate, Calendar endDate){
		ArrayList<Appointment> appointmentsInRange = new ArrayList<Appointment>();
		
		while (startDate.compareTo(endDate) <= 0){
			appointmentsInRange.addAll(getDatesAppointments(startDate));
			startDate.add(Calendar.DATE, 1);
		}
		
		return appointmentsInRange;
	}
	
	public static ArrayList<Appointment> getWeeksAppointments(Calendar date){
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		Calendar startDate = Calendar.getInstance();
		startDate.set(date.get(Calendar.YEAR),
					  date.get(Calendar.MONTH),
					  date.get(Calendar.DATE) - dayOfWeek + 1,
					  0, 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		endDate.add(Calendar.DATE, 6);
		return getAppointmentsInRange(startDate, endDate);
	}
	
	public static ArrayList<Appointment> getMonthsAppointments(Calendar date){
		Calendar startDate = Calendar.getInstance();
		startDate.set(date.get(Calendar.YEAR),
					  date.get(Calendar.MONTH),
					  /*date=*/1,
					  0, 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		int lastDateInMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		endDate.set(Calendar.DATE, lastDateInMonth);
		return getAppointmentsInRange(startDate, endDate);
	}

	public static ArrayList<Appointment> getYearsAppointments(Calendar date){
		Calendar startDate = Calendar.getInstance();
		startDate.set(date.get(Calendar.YEAR),
					  /*month=*/0,
					  /*date=*/1,
					  0, 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		endDate.set(Calendar.DATE, 31);
		endDate.set(Calendar.MONTH, 11); //month is 0 to 11, right???
		return getAppointmentsInRange(startDate, endDate);
	}
	
	// this method returns an appointment with matching title keyword or it returns a null 
	public static ArrayList<Appointment> find_app(String search_word){
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		for(Appointment a : appointments){
			if(a.title != null && a.title.trim().equalsIgnoreCase(search_word.trim())){
				result.add(a);
			}
		}
		
		return result;
	}
	
		
}
