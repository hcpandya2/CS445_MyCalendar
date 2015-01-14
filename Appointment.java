import java.time.Duration;
import java.util.Date;
import java.util.Scanner;




public class Appointment {

	Duration time;
	Date start_date;
	Date end_date;
	String title;
	String description;
	boolean reoccure;
	
	public Appointment(String title, Duration time, Date startdate, Date enddate, boolean reoccur){
		Scanner scan = new Scanner(System.in);
		this.title = title;
		this.time = time;
		this.start_date = startdate;
		this.end_date = enddate;
		this.reoccure = reoccur;
		
		
		System.out.print("Please enter a description for the appointment:");
		this.description = scan.nextLine();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isReoccure() {
		return reoccure;
	}

	public void setReoccure(boolean reoccure) {
		this.reoccure = reoccure;
	}

	
	
	
	
	
}
