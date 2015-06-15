
import java.util.Calendar;

public class Daily_occurance extends ReoccuranceRule {

	
	public Daily_occurance(Calendar firstAppointment){
		super(firstAppointment);
	}
	

	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(year, month - 1, date);
		return withinDateRange(testcal);
	}

	@Override
	public String getName() {
		return "Daily";
	}
	
	public String Get_eventfreq(){
		return "DAILY";
	}

	
}
