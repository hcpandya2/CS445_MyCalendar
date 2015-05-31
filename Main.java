import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ValidationException;

import java.util.Scanner;

public class Main {

	public static void main(String [] args) throws IOException, ParserException, ParseException, ValidationException{
		
		Scanner scan = new Scanner(System.in);
		
		while(true){
			System.out.println("The Calendar starts here");
			System.out.println("Choose from the options below");
			System.out.println("To Add new Appointment -> A/a");
			System.out.println("To Find an Appointment -> B/b");
			System.out.println("To Delete an Appointment -> C/c");
			System.out.println("To Edit an Appointment -> D/d");
			System.out.println("To list all the Appointments -> E/e");
			System.out.println("To load appointments from the file -> F/f");
			System.out.println("To print all the appointments from the file -> G/g");
			System.out.println("To write the list of appointments to the file -> I/i");
			
			System.out.println("To print the list of appointments on a given day -> J/j");
			System.out.println("To print the list of appointments on a given week -> K/k");
			System.out.println("To print the list of appointments on a given month -> L/l");
			System.out.println("To print the list of appointments on a given year -> M/m");
			System.out.print("To exit from the program -> quit \n");
			
			while(scan.hasNext()){
				String input = scan.nextLine();
				if(input.equalsIgnoreCase("a")){
					User_commands.Add_New_Appointment();
					System.out.println(" ");
					break;
				} 
				else if(input.equalsIgnoreCase("b")){
					User_commands.Find_Appointment();
					System.out.println(" ");
					break;
			    }
				else if(input.equalsIgnoreCase("c")){
					User_commands.Delete_Appointment();
					System.out.println(" ");
					break;
				}
				else if(input.equalsIgnoreCase("d")){
					User_commands.Edit_Appointment();
					System.out.println(" ");
					break;
				}
				else if (input.equalsIgnoreCase("e")){
					User_commands.List_Appointments();
					System.out.println(" ");
					break;
				}
				else if(input.equalsIgnoreCase("i")){
					User_commands.read_file();
					System.out.println(" ");
					break;
				}
				else if (input.equalsIgnoreCase("f")){
					ArrayList<Appointment> result = new ArrayList<Appointment>();
					result = User_commands.Import_Appointment();
					
					Schedule.appointments = result;
					
					System.out.println("Printing appointments from the file... \n");
					System.out.println(result);
					System.out.println(" ");
					System.out.println("printing appointments from list_of_appointments");
					System.out.println(Schedule.appointments);
	
					break;
				}
				else if (input.equalsIgnoreCase("g")){
					User_commands.print_appointments_from_file();
					System.out.println("  ");
					break;
				}
	
				else if (input.equalsIgnoreCase("j")){
					System.out.println("Enter the date(MM-dd-yyyy): ");
					String string_date = scan.nextLine();					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					date.setTime(SDF.parse(string_date));
					
					User_commands.PrintDatesAppointments(date);
					
					System.out.println("ENd of writing......");
					System.out.println("  ");
					break;
				}
				
				else if (input.equalsIgnoreCase("k")){
					System.out.println("Enter the date(MM-dd-yyyy): ");
					String string_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					date.setTime(SDF.parse(string_date));
					
					User_commands.PrintWeeksAppointments(date);
					System.out.println("ENd of writing.........");
					System.out.println("  ");
					break;
				}
				
				else if (input.equalsIgnoreCase("l")){
					System.out.println("Enter the date(MM-dd-yyyy):");
					String string_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					date.setTime(SDF.parse(string_date));
					
					User_commands.PrintMonthsAppointments(date);
					System.out.println("ENd of writing......");
					System.out.println("  ");
					break;
				}
				
				else if (input.equalsIgnoreCase("m")){
					System.out.println("Enter the date(MM-dd-yyyy): ");
					String string_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					date.setTime(SDF.parse(string_date));
					
					User_commands.PrintYearsAppointments(date);
					System.out.println("ENd of writing......");
					System.out.println("  ");
					break;
				}
				else if(input.equalsIgnoreCase("quit"))
					return;
				else 
					System.out.print("Make an appropriate selection: \n");
				continue;
			}
			continue;
		}
	}
}
