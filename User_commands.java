import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class User_commands {
	
	Scanner scan;
	InputStreamReader input;
	BufferedReader reader;
	
	public User_commands(){
		scan = new Scanner(System.in);
		input = new InputStreamReader(System.in);
		reader = new BufferedReader(input);
	}
	
	public static Calendar getATime(String string_date){
		SimpleDateFormat SDF  = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(SDF.parse(string_date));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date; 
	}
	
	public void Add_New_Appointment(){
		System.out.print("Enter the information for the appointment.\nTitle: ");
		String Title		  = scan.nextLine();
		System.out.print("Summary: ");
		String summary        = scan.nextLine();
		System.out.print("Description: ");
		String Description	  = scan.nextLine();
		System.out.print("Start Date(dd-M-yyyy hh:mm:ss): ");
		String string_date 	  = scan.nextLine();
		Calendar startdate = getATime(string_date);
       
		System.out.print("Recurence Rule (Once/Daily/Weekly/Monthly/Yearly): ");
		String Recurrence 	  = scan.nextLine();
		Calendar lastdate = null;
		if(!Recurrence.equalsIgnoreCase("once")){
			System.out.println("Enter the date of last occurance of this event(dd-M-yyyy hh:mm:ss) or enter forever: ");
			String lastoccurance = scan.nextLine();
			if (!lastoccurance.equalsIgnoreCase("forever")){
				lastdate = getATime(lastoccurance);
			}
		}
		
		System.out.print("Duration in minutes: ");
		int Duration		  = scan.nextInt(); 
		System.out.println("\nAdding the appointment to the list_of_appointments...");
		Schedule.add_new(Title,summary, Description, startdate, lastdate, Duration, Recurrence);
		System.out.println("\nNew list of appointments:");
		List_Appointments();
	}
	
	public ArrayList<Appointment> Find_Appointment(){
		System.out.println("Enter the search word to find appointment: ");
		String search_word = scan.nextLine();
		return Schedule.find_app(search_word);
	}
	 
	public void Delete_Appointment(){
		
		System.out.println("Enter the title of the appointment to delete: ");
		while(scan.hasNext()){
			String title = scan.nextLine();
			
			if(!title.equalsIgnoreCase("quit")){
				ArrayList<Appointment> result = Schedule.find_app(title);
				
				if(result.size() == 0){
					System.out.println("No such appointment exists.\n" +
						               "Enter the title of the appointment to continue the search" +
									   " or enter quit to exit from the delete option");
					continue;
				}
				else if (result.size() != 1){
					System.out.println("Enter the title of one appointment among the following:\n" + result.toString());
					continue;
				}
				else{
					Schedule.delete(result.get(0));
					System.out.println("Appointment Deleted!!\n" +
						               "New list of appointments:");
					List_Appointments();
					return;
				}
			}
			else {
				return;
			}
		}
	}
	
	public void List_Appointments(){
		System.out.println("List of Current Appointments: ");
		for(Appointment ap: Schedule.appointments){
			System.out.println(ap);
		}
	}
	
	public void Edit_Appointment(){
		System.out.print("Enter the Appointment title to find the appointment: ");
		String title = scan.nextLine();
		Appointment result = null;
		for(Appointment a : Schedule.appointments){
			if(title.equalsIgnoreCase(a.title))
				result = a;
		}
		
		if(result == null){
			System.out.println("No such appointment exists");
		}		
		else{
			
			while(true){
				System.out.println("Select the property to change or quit to exit\n" +
								"Title, Description, Recurrence_Rule, Start_Date, End_Date, Duration");
				
				String property = scan.nextLine();
				
				if(property.equalsIgnoreCase("title")){
					System.out.print("Enter the New Title:");
					String new_title = scan.nextLine();
					result.setTitle(new_title);
					System.out.println("The new Title is: " + result.getTitle());
				}
				else if(property.equalsIgnoreCase("description")){
					System.out.print("Enter the New Description:");
					String new_description = scan.nextLine();
					result.setDescription(new_description);
					System.out.println("The new Description is: " + result.getDescription());
				}
				else if(property.equalsIgnoreCase("recurrence_rule")){
					System.out.println("Enter the New Recurrence_Rule:");
					System.out.print("Once/Daily/Weekly/Montly/Yearly");
					String recurrence_rule = scan.nextLine();
					result.UpdateReoccurancetype(recurrence_rule);
					System.out.println("The new Recurrence_Rule is: " + result.r);
				}
				else if(property.equalsIgnoreCase("start_date")){
					System.out.println("Enter the new start_date (MM/DD/YYYY HH:mm:ss ");
					String start_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
					Calendar startdate = Calendar.getInstance();
					try {
						startdate.setTime(SDF.parse(start_date));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					result.setStart_date(startdate);
					
					System.out.println("The new Start_date is: "+ 
					startdate.get(Calendar.MONTH) +"/" + startdate.get(Calendar.DATE) + "/"+ startdate.get(Calendar.YEAR)+ " " +
					startdate.get(Calendar.HOUR) + ":" + startdate.get(Calendar.MINUTE) + ":" + startdate.get(Calendar.SECOND) );
				}
				else if(property.equalsIgnoreCase("end_date")){
					System.out.println("Enter the new start_date (MM/DD/YYYY HH:mm:ss ");
					String end_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
					Calendar enddate = Calendar.getInstance();
					try {
						enddate.setTime(SDF.parse(end_date));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					result.setEnd_date(enddate);
					System.out.println("The new End_date is: "+ 
					enddate.get(Calendar.MONTH) +"/" + enddate.get(Calendar.DATE) + "/"+ enddate.get(Calendar.YEAR)+ " " +
					enddate.get(Calendar.HOUR) + ":" + enddate.get(Calendar.MINUTE) + ":" + enddate.get(Calendar.SECOND) );
				}
				else if(property.equalsIgnoreCase("duration")){
					System.out.println("Enter the new duration ");
					int duration = scan.nextInt();
					result.setDuration(duration);
					System.out.println("The new duration is: " + result.getDescription());
				}
				else if(property.equalsIgnoreCase("quit")){
					System.out.println("The changed appointment is as follows:\n" +
									   result.toString() + "\n" +
									   "End of editing the appointment");
					return;
				}
				else{
					System.out.println("Select the proper option to perform changes:");
					continue;
				}
				break;
			}
		}
	}

	public ArrayList<Appointment> GetDatesAppointments(Calendar date){
		return Schedule.getDatesAppointments(date);
	}
	
	public ArrayList<Appointment> GetWeeksAppointments(Calendar date){
		return Schedule.getWeeksAppointments(date);	
	}
	
	public ArrayList<Appointment> GetMonthsAppointments(Calendar date){
		return Schedule.getMonthsAppointments(date);
	}
	
	public ArrayList<Appointment> GetYearsAppointments(Calendar date){
		return Schedule.getYearsAppointments(date);
	}
	
	public void PrintDatesAppointments(Calendar date){
		printArrayListOfAppointments(
				//print appointments from this date
				GetDatesAppointments(date)
				);
	}
	
	public void PrintWeeksAppointments(Calendar date){
		printArrayListOfAppointments(
				//print appointments from this week
				GetWeeksAppointments(date)
				);
	}
	
	public void PrintMonthsAppointments(Calendar date){
		printArrayListOfAppointments(
				//print appointments from this month
				GetMonthsAppointments(date)
				);
	}
	
	public void PrintYearsAppointments(Calendar date){
		printArrayListOfAppointments(
				//print appointments from this week
				GetYearsAppointments(date)
				);
	}
	
	public ArrayList<Appointment> Import_Appointment(){
		System.out.print("Enter the name of the file:");
		try {
			String filename = reader.readLine();
			return File_Reader.Read_File(filename);
		} catch (Exception e) {}
		return new ArrayList<Appointment>();
	}
	
	public void printArrayListOfAppointments(ArrayList<Appointment> appointments){
		for(Appointment ap : appointments){
			if(ap instanceof header_date_occur){
				System.out.println(((header_date_occur)ap).toStringTestFriendly());
			}
			else{
				System.out.println(ap.toStringTestFriendly());
			}
		}
	}
	
	public void read_file() {
		System.out.println("Enter the file name:");
		//String filename = scan.next();
		String filename = "";
		try {
			filename = reader.readLine();
			File_Reader.write_appointments(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("done writing\n");
	}
}
