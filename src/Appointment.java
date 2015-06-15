import java.util.Calendar;
import java.util.Date;

public class Appointment implements Comparable<Appointment> {

	String title;               // appointment name
	String description;
	String summary;
	ReoccuranceRule r;
	
	public Appointment(String title, String description, Calendar startdate, int duration, String reoccuranceType){
		this.title = title;
		this.description = description;
		
		if(reoccuranceType.equalsIgnoreCase("Once"))
			r = new Once_occurance(startdate);
		else if(reoccuranceType.equalsIgnoreCase("Daily"))
			r = new Daily_occurance(startdate);
		else if(reoccuranceType.equalsIgnoreCase("Weekly"))
			r = new Weekly_occurance(startdate);
		else if(reoccuranceType.equalsIgnoreCase("Monthly"))
			r = new Monthly_occurance(startdate);
		else if(reoccuranceType.equalsIgnoreCase("Yearly"))
			r = new Yearly_occurance(startdate);
		else
			System.out.println("Error - BRRRRrrrr!");
		
		r.setFirstAppointment(startdate);
		r.setDuration(duration);
	} 

	public String getTitle() {
		return title;
	}
	
	public void setSummary(String summary){
		this.summary = summary;
	}
	
	public String getSummary(){
		return summary;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	//StartDate also contain's the appointments start time of appointment
	public Calendar getStart_date() {
		return r.getFirstAppointment();
	}

	//StartDate also contain's the appointments start time of appointment
	public void setStart_date(Calendar start_date) {
		r.setFirstAppointment(start_date);
	}

	public Calendar getEnd_date() {
		return r.getLastAppintment();
	}

	public void setEnd_date(Calendar end_date) {
		r.setlastAppointment(end_date);
	}
	
	public int getDuration(){
		return r.getDuration();
	}
	
	public void setDuration(int duration){
		r.setDuration(duration);
	}
	
	public boolean IsOndate(Calendar testDate){
		return r.CheckDate(testDate.get(Calendar.MONTH) + 1, testDate.get(Calendar.DATE), testDate.get(Calendar.YEAR));
	}

	public void setReoccuranceRule(ReoccuranceRule  Re){
		r = Re;
	}
	public void UpdateReoccurancetype(String reoccuranceType){
		
		ReoccuranceRule r_new;
		if(reoccuranceType.equals("Once"))
			r_new = new Once_occurance(getStart_date());
		else if(reoccuranceType.equals("Daily"))
			r_new  = new Daily_occurance(getStart_date());
		else if(reoccuranceType.equals("Weekly"))
			r_new  = new Weekly_occurance(getStart_date());
		else if(reoccuranceType.equals("Monthly"))
			r_new  = new Monthly_occurance(getStart_date());
		else if(reoccuranceType.equals("Yearly"))
			r_new  = new Yearly_occurance(getStart_date());
		else{
			System.out.println("Error - Invalid Reoccurance Type. No changes made.");
			return;
		}
		
		r_new.setDuration(getDuration());
		r_new.setlastAppointment(getEnd_date());
		
		r = r_new;
	}
	
	@Override
	public int compareTo(Appointment ap) {
		//we need to sort only based on time of day
		Calendar thisTimeOfDay = Calendar.getInstance();
		thisTimeOfDay.setTime(getStart_date().getTime());
		thisTimeOfDay.set(Calendar.DATE, 1);
		thisTimeOfDay.set(Calendar.MONTH, 1);
		thisTimeOfDay.set(Calendar.YEAR, 1);
		Calendar apTimeOfDay = Calendar.getInstance();
		apTimeOfDay.setTime(ap.getStart_date().getTime());
		apTimeOfDay.set(Calendar.DATE, 1);
		apTimeOfDay.set(Calendar.MONTH, 1);
		apTimeOfDay.set(Calendar.YEAR, 1);
		return thisTimeOfDay.compareTo(apTimeOfDay);
	}
	
	public String toStringTestFriendly(){
		return toString();
	}
	
	@Override
	public String toString() {
		//if a normal appointment, print time and duration
		return "[ description=" + description
				+ ", (" + r.getName() + "), Title=" + getTitle()
				+ ", Description=" + getDescription()
				+ ", Start_date=" +
				(getStart_date().get(Calendar.MONTH) + 1) +"/" +
				getStart_date().get(Calendar.DATE) + "/"+
				getStart_date().get(Calendar.YEAR)+ " " +
				getStart_date().get(Calendar.HOUR) + ":" +
				getStart_date().get(Calendar.MINUTE) + ":" +
				getStart_date().get(Calendar.SECOND) + 
				", Duration=" + getDuration() + "]";
	}
}
