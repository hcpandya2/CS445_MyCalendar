import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Calendar;



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

	public static void main(String [] args) throws IOException, ParserException{
		FileInputStream fin = null;
		HashMap<Integer,Component> list_of_components = new HashMap<Integer,Component>();
		HashMap <Component,ArrayList<Property>> list_of_property = new HashMap<Component,ArrayList<Property>>();
		
		try {
			fin = new FileInputStream("basic.ics");
			CalendarBuilder builder = new CalendarBuilder();
			net.fortuna.ical4j.model.Calendar cal = builder.build(fin);
			
			int counter = 1;
			for (Iterator i = cal.getComponents().iterator(); i.hasNext();) {
			    Component component = (Component) i.next();
			    
			    if(component.getName().equalsIgnoreCase("VEVENT")){
				    list_of_components.put(new Integer(counter),component);
				    /*System.out.println(counter + "\n" + list_of_components.get(counter)); */
				    
				    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
					
				    Date start = SDF.parse(component.getProperty("DTSTART").getValue());
			        Date end = SDF.parse(component.getProperty("DTEND").getValue());
			        
			        java.util.Calendar startdate = java.util.Calendar.getInstance();
			        java.util.Calendar enddate = java.util.Calendar.getInstance();
			        int startday = start.getDay();
			        int endday = end.getDay();
			        
			        startdate.set(start.getYear(),start.getMonth(),start.getDate(),start.getHours(),start.getMinutes(), start.getSeconds());
			        enddate.set(end.getYear(),end.getMonth(),end.getDate(),end.getHours(),end.getMinutes(), end.getSeconds());
			        
			        String title = component.getProperty("NAME").getValue();
			        String summary = component.getProperty("SUMMARY").getValue();
				    Date time = SDF.parse(component.getProperty("DTSTAMP").getValue());
				   
				   /* if(component.)
				    String location = component.getProperty("LOCATION").getValue();
				    */
				    int duration = Integer.parseInt(component.getProperty("DURATION").getValue());
				    
				    String recurrence = component.getProperty("FREQ").getValue();
			        
				    String description = component.getProperty("DESCRIPTION").getValue();
				    
			        //setting up the appointment object with the given date.... 
				    Appointment appointment = new Appointment(title,description,startdate,duration,recurrence);
				    appointment.setEnd_date(enddate);
				    appointment.setDescription(description);
				    appointment.setDuration(duration);
			    
			    }
			    counter++;
			    
			}  // end of for loop iterator over the file ... 
			
			

			
		}// end of try block
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("file doesn't exist");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

				
	/*	// creating a new calendar 
		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);

		// Add events, etc..
		
		java.util.Calendar call = java.util.Calendar.getInstance();
		call.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
		call.set(java.util.Calendar.DAY_OF_MONTH, 25);

		//VEvent christmas = new VEvent(new Date(call.getTime()), "Christmas Day");
		// initialize as an all-day event..
		//
		//christmas.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);
		
		// end of adding event 
		*/
	}
}
