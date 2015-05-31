import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Name;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;


public class File_Reader {
	
	public static String reoccurance_rule ="";

	public static ArrayList<Appointment> Read_File(String filename) throws IOException, ParserException{
		FileInputStream fin = null;
		ArrayList<Appointment> list_of_appointments = new ArrayList<Appointment>();
		HashMap <Component,ArrayList<Property>> list_of_property = new HashMap<Component,ArrayList<Property>>();
		
		try {
			fin = new FileInputStream(filename);
			
			System.out.println("Total file size to read (in bytes) : "
					+ fin.available());
			
			CalendarBuilder builder = new CalendarBuilder();
			net.fortuna.ical4j.model.Calendar cal = builder.build(fin);
			
			
			for (Iterator i = cal.getComponents().iterator(); i.hasNext();) {
			    Component component = (Component) i.next();
			    
			    if(component.getName().equalsIgnoreCase("VEVENT")){
				    //list_of_components.put(new Integer(list_of_components.size() + 1),component);
				   			    
				    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
				    SimpleDateFormat SDFalt = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
				    
				    Date start;
				    try{
				    	start = SDF.parse(component.getProperty("DTSTART").getValue());
				    }
				    catch(ParseException ex){
				    	start = SDFalt.parse(component.getProperty("DTSTART").getValue());
				    }
				    
				    Date end;
				    try{
				    	end = SDF.parse(component.getProperty("DTEND").getValue());
				    }
				    catch(ParseException ex){
				    	end = SDFalt.parse(component.getProperty("DTEND").getValue());
				    }
				
			        java.util.Calendar startdate = java.util.Calendar.getInstance();
			        java.util.Calendar endtime = java.util.Calendar.getInstance();
			        startdate.setTime(start);
			        endtime.setTime(end);
			        String title;
			        if(component.getProperty("NAME") != null){
			        	title = component.getProperty("NAME").getValue();
			        } 
			        else 
			        	title = null;
			        
			        String summary = "";
			        if(component.getProperty("SUMMARY") != null){
			        	summary = component.getProperty("SUMMARY").getValue();
			        }
				    //Date time = SDF.parse(component.getProperty("DTSTAMP").getValue());
				   
				    /*
			        if(component.getProperty("LOCATION") != null){
				    	String location = component.getProperty("LOCATION").getValue();
				    }
				    */
				    
				    int duration = (int) Math.round((end.getTime() - start.getTime())/60000);  	
				    	
				    String recurrence;
				    if(component.getProperty("RRULE") != null){
				    	recurrence = component.getProperty("RRULE").getValue().split("=")[1].split(";")[0];	
				    } 
				    else
				    	recurrence = "Once";
				    int testhere = 1;
				    if(testhere > 0){}
				    
				    java.util.Calendar lastdate = null;
				    if(component.getProperty("RRULE") != null && component.getProperty("RRULE").getValue().contains("UNTIL")){
				    	 Date last = SDF.parse(component.getProperty("RRULE").getValue().split("=")[2]);
					         lastdate = java.util.Calendar.getInstance();
					         lastdate.setTime(last); //this hasn't been tested yet!
				    }
				    
				    String description;
				    if(component.getProperty("DESCRIPTION") != null){
				    	description = component.getProperty("DESCRIPTION").getValue();
				    } 
				    else 
				    	description = null;
			        
				    //setting up the appointment object with the given date.... 
				    Appointment appointment = new Appointment(title,description,startdate,duration,recurrence);
				    appointment.setEnd_date(lastdate);
				    appointment.setDescription(description);
				    appointment.setSummary(summary);
				    list_of_appointments.add(appointment);
				    System.out.println(appointment);
			    }
			    
			}  // end of for loop iterator over the file ... 
		
		}// end of try block
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("file doesn't exist");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list_of_appointments;
	}
	
	public static void write_appointments(String filename) throws ParseException, IOException, ValidationException{
		
		FileOutputStream fout = new FileOutputStream(filename);
		net.fortuna.ical4j.model.Calendar cal = new net.fortuna.ical4j.model.Calendar();
		//add product Id
		cal.getProperties().add(new ProdId("-//Mozilla.org/NONSGML Mozilla Calendar V1.1//EN"));
		cal.getProperties().add(Version.VERSION_2_0);
		cal.getProperties().add(CalScale.GREGORIAN);
		
		UidGenerator ug = new UidGenerator("uidGen");
		
		for(Appointment a : Schedule.appointments){
			
			java.util.GregorianCalendar startDate = (GregorianCalendar) a.getStart_date();
			java.util.GregorianCalendar endDate = (GregorianCalendar)java.util.Calendar.getInstance();
			endDate.setTime(startDate.getTime());
			endDate.add(java.util.Calendar.MINUTE, a.r.duration);
			
			String eventname = a.getTitle();
			String summary = a.getSummary();
			String frequency = a.r.Get_eventfreq();
			String description = a.getDescription();
			
			DateTime start_date = new DateTime(startDate.getTime());
			DateTime end_date = null;
			
			if(endDate != null){
				end_date = new DateTime(endDate.getTime());
			}
			
			Uid u_id = ug.generateUid();
			String rrule ="";
			VEvent event;

			event = new VEvent(start_date,end_date,summary);
			
			event.getProperties().add(u_id);
			event.getProperties().add(new Name(eventname));
			event.getProperties().add(new Description(description));
			
			if(!frequency.equalsIgnoreCase("")){
				rrule = "FREQ="+frequency;
				if(a.r.lastAppointment != null){
					java.util.Calendar la = a.r.lastAppointment;
					String addThisUntil = ";UNTIL=" +
										  la.get(java.util.Calendar.YEAR) +
										  "" + String.format("%02d", la.get(java.util.Calendar.MONTH) + 1) +
										  "" + String.format("%02d", la.get(java.util.Calendar.DATE)) +
										  "T" + String.format("%02d", la.get(java.util.Calendar.HOUR)) +
										  "" + String.format("%02d", la.get(java.util.Calendar.MINUTE)) +
										  "" + String.format("%02d", la.get(java.util.Calendar.SECOND)) +
										  "Z";
					rrule += addThisUntil;
				}
				
				event.getProperties().add(new RRule(rrule));
				System.out.println(rrule);
			}
			cal.getComponents().add(event);
		}
		
		CalendarOutputter out=new CalendarOutputter();
		out.output(cal, fout);
	}
}
