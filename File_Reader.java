import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Calendar;



import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;



public class File_Reader {

	public static HashMap<Integer,Component> Read_File(String filename) throws IOException, ParserException{
		FileInputStream fin = null;
		HashMap<Integer,Component> list_of_components = new HashMap<Integer,Component>();
		HashMap <Component,ArrayList<Property>> list_of_property = new HashMap<Component,ArrayList<Property>>();
		
		try {
			fin = new FileInputStream(filename);
			CalendarBuilder builder = new CalendarBuilder();
			net.fortuna.ical4j.model.Calendar cal = builder.build(fin);
			
			
			for (Iterator i = cal.getComponents().iterator(); i.hasNext();) {
			    Component component = (Component) i.next();
			    
			    if(component.getName().equalsIgnoreCase("VEVENT")){
				    list_of_components.put(new Integer(list_of_components.size() + 1),component);
				   			    
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
		
		return list_of_components;

	
	}
	
	public static void Write_to_File (String filename, boolean value) throws IOException{

		BufferedWriter outStream= new BufferedWriter(new FileWriter(filename, value));
		
		for(Appointment a : Schedule.appointments){
			
			outStream.write("BEGIN:EVENT");
			outStream.write("NAME:"+ a.title);
			outStream.write("DTSTART:" + a.getStart_date().get(java.util.Calendar.MONTH) + a.getStart_date().get(java.util.Calendar.DATE) + a.getStart_date().get(java.util.Calendar.YEAR) + 
								   "T" + a.getStart_date().get(java.util.Calendar.HOUR)  + a.getStart_date().get(java.util.Calendar.MINUTE)+ a.getStart_date().get(java.util.Calendar.SECOND) +'Z');
			outStream.write("DTEND:" +  a.getEnd_date().get(java.util.Calendar.MONTH) + a.getEnd_date().get(java.util.Calendar.DATE) + a.getEnd_date().get(java.util.Calendar.YEAR) + 
					   			  "T" + a.getEnd_date().get(java.util.Calendar.HOUR)  + a.getEnd_date().get(java.util.Calendar.MINUTE)+ a.getEnd_date().get(java.util.Calendar.SECOND) +'Z');
			outStream.write("DESCRIPTION:" + a.getDescription());
			outStream.write("END EVENT");
			//outStream.write();
		}
		
	}
	
	
	
	
}
