import java.util.Calendar;


public class Yearly_occurance extends ReoccuranceRule {

	public Yearly_occurance(Calendar firstAppointment) {
		super(firstAppointment);
		
	}

	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(month, date, year);
		return (firstAppointment.get(Calendar.DATE)) == date && (firstAppointment.get(Calendar.MONTH) == month) && withinDateRange(testcal);
	}

	@Override
	public String getName() {
		
		return "Yearly";
	}

}
