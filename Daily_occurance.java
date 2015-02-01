
import java.util.Calendar;

public class Daily_occurance extends ReoccuranceRule {

	public Daily_occurance(Calendar firstAppointment){
		super(firstAppointment);
	}
	

	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(month, date, year);
		return withinDateRange(testcal);
	}

	@Override
	public String getName() {
		return "Daily";
	}

	
}
