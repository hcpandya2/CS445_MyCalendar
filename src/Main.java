//main final version
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

	public static void main(String [] args) {
		User_commands uc = new User_commands();
		runProgram(uc);
	}
	
	public static void printMenu(){
		System.out.println("The Calendar starts here\n" +
						   "Choose from the options below\n" +
						   "To Add new Appointment -> A/a\n" +
						   "To Find an Appointment -> B/b\n" +
						   "To Delete an Appointment -> C/c\n" +
						   "To Edit an Appointment -> D/d\n" +
						   "To list all the Appointments -> E/e\n" +
						   "To load appointments from the file -> F/f\n" +
						   "To write the list of appointments to the file -> I/i\n" +
						   "To print the list of appointments on a given day -> J/j\n" +
						   "To print the list of appointments on a given week -> K/k\n" +
						   "To print the list of appointments on a given month -> L/l\n" +
						   "To print the list of appointments on a given year -> M/m\n" +
						   "To exit from the program -> quit");
	}
	
	public static void runProgram(User_commands uc){
		Scanner scan = new Scanner(System.in);
		
		while(true){
			printMenu();
			while(scan.hasNext()){
				String input = scan.nextLine();
				if(input.equalsIgnoreCase("a")){
					try {
						uc.Add_New_Appointment();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} 
				else if(input.equalsIgnoreCase("b")){
					System.out.println(uc.Find_Appointment());
			    }
				else if(input.equalsIgnoreCase("c")){
					uc.Delete_Appointment();
				}
				else if(input.equalsIgnoreCase("d")){
					uc.Edit_Appointment();
				}
				else if (input.equalsIgnoreCase("e")){
					uc.List_Appointments();
				}
				else if(input.equalsIgnoreCase("i")){
					uc.read_file(); //this really writes...
				} 
				else if (input.equalsIgnoreCase("f")){
					Schedule.appointments = uc.Import_Appointment();
					
					System.out.println("File loaded - Printing appointments: \n");
					for(Appointment ap : Schedule.appointments){
						System.out.println(ap);
					}
				}
				//else if (input.equalsIgnoreCase("g")){
				//	uc.print_appointments_from_file();
				//}
				else if (input.equalsIgnoreCase("j")){
					System.out.println("Enter the date(MM-dd-yyyy): ");
					String string_date = scan.nextLine();					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(SDF.parse(string_date));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					uc.PrintDatesAppointments(date);
					System.out.println("End of writing......");
				}
				else if (input.equalsIgnoreCase("k")){
					System.out.println("Enter the date(MM-dd-yyyy): ");
					String string_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(SDF.parse(string_date));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					uc.PrintWeeksAppointments(date);
					System.out.println("End of writing.........");
				}
				else if (input.equalsIgnoreCase("l")){
					System.out.println("Enter the date(MM-dd-yyyy):");
					String string_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(SDF.parse(string_date));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					uc.PrintMonthsAppointments(date);
					System.out.println("End of writing......");
				}
				else if (input.equalsIgnoreCase("m")){
					System.out.println("Enter the date(MM-dd-yyyy): ");
					String string_date = scan.nextLine();
					
					SimpleDateFormat SDF  = new SimpleDateFormat("MM-dd-yyyy");
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(SDF.parse(string_date));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					uc.PrintYearsAppointments(date);
					System.out.println("End of writing......");
				}
				else if(input.equalsIgnoreCase("quit")){
					scan.close();
					return;
				}
				else{ 
					System.out.print("Invalid command entered - make an appropriate selection: \n");
				}
				System.out.println("");
				break;
			}
		}
	}
}
