import java.util.Calendar;

public class Weekly_occurance extends ReoccuranceRule {

	public Weekly_occurance(Calendar firstAppointment) {
		super(firstAppointment);
		
	}

	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(year, month, date);
		return testcal.get(Calendar.DAY_OF_WEEK) == firstAppointment.get(Calendar.DAY_OF_WEEK) && withinDateRange(testcal);
	}

	@Override
	public String getName() {
		
		return "Weekly";
	}

	

	
}
