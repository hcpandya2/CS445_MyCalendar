import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;

import java.util.Scanner;

public class Main {

	public static void main(String [] args) throws IOException, ParserException, ParseException{
		
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
			System.out.print("To exit from the program -> quit \n");
			
			while(scan.hasNext()){
				String input = scan.next();
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
				else if (input.equalsIgnoreCase("f")){
					HashMap<Integer,Component> result = User_commands.Import_Appointment();
					
					for (Iterator i = result.values().iterator(); i.hasNext();) {
					    Appointment component = (Appointment) i.next();
					    (Schedule.appointments).add(component);
					}
					System.out.println(Schedule.appointments);
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
