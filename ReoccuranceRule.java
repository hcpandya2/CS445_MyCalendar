import java.util.Calendar;


public abstract class ReoccuranceRule {

	Calendar firstAppointment;
	Calendar lastAppointment; //will be populated if they terminate reoccurance
	int duration;
	
	public ReoccuranceRule(Calendar firstAppointment){
		this.firstAppointment = firstAppointment;
		lastAppointment = null;
	}
		
	public abstract boolean CheckDate(int month, int date, int year);
	public abstract String getName();
	
	public int getDuration(){
		return duration;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}

	public Calendar getFirstAppointment() {
		return firstAppointment;
	}

	public void setFirstAppointment(Calendar firstAppointment) {
		this.firstAppointment = firstAppointment;
	}
	
	public Calendar getLastAppintment(){
		return lastAppointment;
	}
	
	public void setlastAppointment(Calendar lastAppointment) {
		this.lastAppointment = lastAppointment;
	}
	
	boolean withinDateRange(Calendar testcal){
		return (testcal.compareTo(firstAppointment) >= 0) && (lastAppointment == null || testcal.compareTo(lastAppointment) <= 0);
		
	}
}																																			
