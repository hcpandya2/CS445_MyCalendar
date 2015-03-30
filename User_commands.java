import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;


public class User_commands {
	
	static Scanner scan = new Scanner(System.in);
	/* add new appointment
	 * edit app.
	 * delete app
	 * search app. 
	 * sort app. based on day, week, month.... 
	 * load apps.
	 * export apps.
	 *   
	 */
	
	public static void Add_New_Appointment() throws ParseException{
		System.out.println("Enter the information for the appointment.");
		
		System.out.print("Title: ");
		String Title		  = scan.nextLine();
		System.out.print("Description: ");
		String Description	  = scan.nextLine();
		
		System.out.print("Start Date(MM/DD/YYYY HH:MM:ss): ");
		String string_date 	  = scan.nextLine();
		SimpleDateFormat SDF  = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
		Calendar startdate = Calendar.getInstance();
		startdate.setTime(SDF.parse(string_date));
       
		System.out.print("Recurence Rule (Once/Daily/Weekly/Monthly/Yearly): ");
		String Recurrence 	  = scan.nextLine();
		
		System.out.print("Duration in hours");
		int Duration		  = scan.nextInt();
		
		Schedule.add_new(Title, Description, startdate, Duration, Recurrence);
		System.out.println("New list of appointments:");
		System.out.println(Schedule.appointments);
		
	}
	
	public static ArrayList<Appointment> Find_Appointment(){
		
		System.out.println("Enter the search word to find appointment: ");
		String search_word = scan.nextLine();
		return Schedule.find_app(search_word);
		
	}
	 
	public static void Delete_Appointment(){
		
		System.out.println("Enter the title of the appointment to delete: ");
		while(scan.hasNext()){
			String title = scan.nextLine();
			
			if(!title.equalsIgnoreCase("quit")){
				ArrayList<Appointment> result = Schedule.find_app(title);
				
				if(result == null){
					System.out.println("No such appointment exists.");
					System.out.println("Enter the title of the appointment to continue the search"
							+ " or enter quit to exit from the delete option");
					continue;
				}
				else if (result.size() != 1){
					System.out.println("Enter the title of one appointment among the following:");
					System.out.println(result);
					continue;
				}
				else{
					Schedule.delete(result.get(0));
					System.out.println("Appointment Deleted!!");
					System.out.println("The new list of appointments");
					System.out.println(Schedule.appointments);
					return;
				}
			}
			else 
				return;
		}
		
	}
	public static void List_Appointments(){
		System.out.println("List of Current Appointments: ");
		System.out.println(Schedule.appointments);
	}
	
	public static void Edit_Appointment() throws ParseException{
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
				System.out.println("Select the property to change or quit to exit ");
				System.out.print("Title, Description, Recurrence_Rule, Start_Date, End_Date, Duration");
				
				String property = scan.nextLine();
				
				if(property.equalsIgnoreCase("title")){
					System.out.print("Enter the New Title:");
					String new_title = scan.nextLine();
					result.setTitle(new_title);
					System.out.println("The new Title is: " + result.getTitle());
					break;
				}
				else if(property.equalsIgnoreCase("description")){
					System.out.print("Enter the New Description:");
					String new_description = scan.nextLine();
					result.setDescription(new_description);
					System.out.println("The new Description is: " + result.getDescription());
					break;
				}
				else if(property.equalsIgnoreCase("recurrence_rule")){
					System.out.println("Enter the New Recurrence_Rule:");
					System.out.print("Once/Daily/Weekly/Montly/Yearly");
					String recurrence_rule = scan.nextLine();
					result.UpdateReoccurancetype(recurrence_rule);
					System.out.println("The new Recurrence_Rule is: " + result.r);
					break;
				}
				else if(property.equalsIgnoreCase("start_date")){
					System.out.println("Enter the new start_date (MM/DD/YYYY HH:mm:ss ");
					String start_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
					Calendar startdate = Calendar.getInstance();
					startdate.setTime(SDF.parse(start_date));
					
					result.setStart_date(startdate);
					
					System.out.println("The new Start_date is: "+ 
					startdate.get(Calendar.MONTH) +"/" + startdate.get(Calendar.DATE) + "/"+ startdate.get(Calendar.YEAR)+ " " +
					startdate.get(Calendar.HOUR) + ":" + startdate.get(Calendar.MINUTE) + ":" + startdate.get(Calendar.SECOND) );
					break;
				}
				else if(property.equalsIgnoreCase("end_date")){
					System.out.println("Enter the new start_date (MM/DD/YYYY HH:mm:ss ");
					String end_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
					Calendar enddate = Calendar.getInstance();
					enddate.setTime(SDF.parse(end_date));
					
					result.setEnd_date(enddate);
					System.out.println("The new End_date is: "+ 
					enddate.get(Calendar.MONTH) +"/" + enddate.get(Calendar.DATE) + "/"+ enddate.get(Calendar.YEAR)+ " " +
					enddate.get(Calendar.HOUR) + ":" + enddate.get(Calendar.MINUTE) + ":" + enddate.get(Calendar.SECOND) );
					break;

				}
				else if(property.equalsIgnoreCase("duration")){
					System.out.println("Enter the new duration ");
					int duration = scan.nextInt();
					result.setDuration(duration);
					System.out.println("The new duration is: " + result.getDescription());
					break;
				}
				else if(property.equalsIgnoreCase("quit")){
					System.out.println("The changed appointment is as follows: ");
					System.out.println(result);
					System.out.println("End of editing the appointment");
					return;
				}
				else{
					System.out.println("Select the proper option to perform changes:");
				}
				
			}
		}
		
	}
	
	public static void Sort_Appointments(){
		
	}
	
	public static  HashMap<Integer,Component> Import_Appointment() throws IOException, ParserException{
		System.out.print("Enter the name of the file:");
		String filename = scan.nextLine();
		return File_Reader.Read_File(filename);
		
	}
	
	public static void Export_Appointment(){
		
	}
	
	

}
