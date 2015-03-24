import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Calendar;



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
			    //System.out.println("Component [" + component.getName() + "]");
			    list_of_components.put(new Integer(counter),component);
			    System.out.println(counter + "\n" + list_of_components.get(counter));
			    counter++;
			}
			
			
			
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
