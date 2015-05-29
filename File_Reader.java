import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Calendar;



import java.io.PrintWriter;
import java.net.SocketException;
import java.nio.file.Files;
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
					
				    Date start = SDF.parse(component.getProperty("DTSTART").getValue());
			        Date end = SDF.parse(component.getProperty("DTEND").getValue());
			        
			        java.util.Calendar startdate = java.util.Calendar.getInstance();
			        java.util.Calendar enddate = java.util.Calendar.getInstance();
			        int startday = start.getDay();
			        int endday = end.getDay();
			        
			        startdate.set(start.getYear(),start.getMonth(),start.getDate(),start.getHours(),start.getMinutes(), start.getSeconds());
			        enddate.set(end.getYear(),end.getMonth(),end.getDate(),end.getHours(),end.getMinutes(), end.getSeconds());
			        String title;
			        if(component.getProperty("NAME") != null){
			        	title = component.getProperty("NAME").getValue();
			        } 
			        else 
			        	title = null;
			        String summary = component.getProperty("SUMMARY").getValue();
				    Date time = SDF.parse(component.getProperty("DTSTAMP").getValue());
				   
				    if(component.getProperty("LOCATION") != null){
				    	String location = component.getProperty("LOCATION").getValue();
				    }
				    
				    int duration;
				    if(component.getProperty("DURATION") != null){
				    	duration = Integer.parseInt(component.getProperty("DURATION").getValue());
				    }
				    else 
				    	duration = (int) Math.round((end.getTime() - start.getTime())/3600);
				    	
				    String recurrence;
				    if(component.getProperty("FREQ") != null){
				    	recurrence = component.getProperty("FREQ").getValue();
				    	
				    } 
				    else
				    	recurrence = "Once";
				    
				    String description;
				    if(component.getProperty("DESCRIPTION") != null){
				    	description = component.getProperty("DESCRIPTION").getValue();
				    } 
				    else 
				    	description = null;
			        
				    //setting up the appointment object with the given date.... 
				    Appointment appointment = new Appointment(title,description,startdate,duration,recurrence);
				    appointment.setEnd_date(enddate);
				    appointment.setDescription(description);
				    list_of_appointments.add(appointment);
				   // System.out.println(appointment);
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
	public static void Write_to_File (String filename) throws IOException, ValidationException, ParseException{

		
		FileOutputStream fout = new FileOutputStream(filename);
		
		GregorianCalendar start = new GregorianCalendar();
		start.set(2013, 2,5,9, 0);
		
		GregorianCalendar end=new GregorianCalendar();
		end.set(2013, 2,5,17,0);
		
		DateTime startTime=new DateTime(start.getTime());
		DateTime endTime=new DateTime(end.getTime());
		
		//Create event
		VEvent eightHourEvent = new VEvent(startTime,endTime,"Test Event");
		
		net.fortuna.ical4j.model.Calendar cal = new net.fortuna.ical4j.model.Calendar();
		//add product Id
		cal.getProperties().add(new ProdId("-//Mozilla.org/NONSGML Mozilla Calendar V1.1//EN"));
		cal.getProperties().add(Version.VERSION_2_0);
		cal.getProperties().add(CalScale.GREGORIAN);
		
		//generate unique identifier
		UidGenerator ug = new UidGenerator("uidGen");
		Uid uid = ug.generateUid();
		
		eightHourEvent.getProperties().add(uid);

		//add event in ical4j calendar
		cal.getComponents().add(eightHourEvent);
		//System.out.println(cal.toString());
		
		
		//save event in test.ics file
				String eventName = "Testing appointment";
				 java.util.GregorianCalendar startDate = new GregorianCalendar();
				 startDate.set(2014, 2,5,9, 0);
				 
				 java.util.Calendar endDate = new GregorianCalendar();
				 endDate.set(2014,2,5,12,0);
				
				DateTime start_date = new DateTime(startDate.getTime());
				DateTime end_date = new DateTime(endDate.getTime());
				
				String rrule = "FREQ=WEEKLY;INTERVAL=1;COUNT=10";
				
				
				//VEvent meeting = new VEvent(start_date, end_date, eventName);
				// deleted end_date for purpose of checking
				
				VEvent meeting = new VEvent(start_date,eventName);
				ug = new UidGenerator("uidGen");
				Uid u_id = ug.generateUid();
				
				meeting.getProperties().add(u_id);
				meeting.getProperties().add(new RRule(rrule));
				cal.getComponents().add(meeting);
				
				
				//System.out.println(cal.toString());
				//System.out.println(meeting);
			
				 CalendarOutputter out=new CalendarOutputter();
				 out.output(cal, fout);	 	
			
		
	}
	
	@SuppressWarnings("unused")
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
			java.util.GregorianCalendar endDate = null;
			
			if(a.getEnd_date() != null){
				endDate = (GregorianCalendar) a.getEnd_date();
			}
			String eventname = a.getTitle();
			String summary = a.getTitle();
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
			
			
			if(end_date != null){
				event = new VEvent(start_date,end_date,eventname);
			}
			else {
				 event = new VEvent(start_date,eventname);
			}
			
			
			event.getProperties().add(u_id);
			event.getProperties().add(new Description(description));
			
			if(!frequency.equalsIgnoreCase("")){
				rrule = "FREQ="+frequency;
				event.getProperties().add(new RRule(rrule));
				System.out.println(rrule);
			}
			cal.getComponents().add(event);
		}
		
		CalendarOutputter out=new CalendarOutputter();
		out.output(cal, fout);
	}
	
	
	
}
