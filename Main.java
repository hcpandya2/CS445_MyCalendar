import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ValidationException;

import java.util.Scanner;

public class Main {

	public static void main(String [] args) throws IOException, ParserException, ParseException, ValidationException{
		
		Scanner scan = new Scanner(System.in);
		
		
		/*
		HashMap<Integer,Component> components = File_Reader.Read_File("basic.ics");
		System.out.println(components);
		*/
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
			System.out.println("To print all the appointments to the file -> H/h ");
			System.out.println("To write the list of appointments to the file -> I/i");
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
					
					/*for (Iterator i = result.values().iterator(); i.hasNext();) {
					  Appointment component = (Appointment) i.next();
					  (Schedule.appointments).add(component);
					}*/
					
					Schedule.appointments = result;
					
					//System.out.println(Schedule.appointments);
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
				
				else if (input.equalsIgnoreCase("h")){
					User_commands.Print_Appointments_to_file();
					System.out.println("ENd of writing to file......");
					System.out.println(" ");
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
