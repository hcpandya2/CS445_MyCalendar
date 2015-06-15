import java.util.Calendar;


public class Monthly_occurance extends ReoccuranceRule {

	public Monthly_occurance(Calendar firstAppointment) {
		super(firstAppointment);
		
	}

	
	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(year, month - 1, date);
		return firstAppointment.get(Calendar.DATE) == date && withinDateRange(testcal);
	}
	

	@Override
	public String getName() {
		
		return "monthly";
	}
	
	public String Get_eventfreq(){
		return "MONTHLY";
	}


}
