import java.util.Calendar;


public class Monthly_occurance extends ReoccuranceRule {

	public Monthly_occurance(Calendar firstAppointment) {
		super(firstAppointment);
		
	}

	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(month, date, year);
		return firstAppointment.get(Calendar.DATE) == date && withinDateRange(testcal);
		
	}

	@Override
	public String getName() {
		
		return "Montly";
	}

}
