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
	public abstract String Get_eventfreq();
	
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
	
	public boolean withinDateRange(Calendar testcal){
		Calendar firstAptTimeless = Calendar.getInstance();
		firstAptTimeless.set(firstAppointment.get(Calendar.YEAR), firstAppointment.get(Calendar.MONTH), firstAppointment.get(Calendar.DATE), 0, 0, 0);
		Calendar lastAptTimeless = null;
		if(lastAppointment!= null){
			lastAptTimeless = Calendar.getInstance();
			lastAptTimeless.set(lastAppointment.get(Calendar.YEAR), lastAppointment.get(Calendar.MONTH), lastAppointment.get(Calendar.DATE), 23, 59, 59);
		}
		return (testcal.compareTo(firstAptTimeless) >= 0) && (lastAptTimeless == null || testcal.compareTo(lastAptTimeless) <= 0);
	}	
}																																			
