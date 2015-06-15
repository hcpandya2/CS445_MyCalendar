import java.util.Calendar;


public class Yearly_occurance extends ReoccuranceRule {

	public Yearly_occurance(Calendar firstAppointment) {
		super(firstAppointment);
		
	}

	@Override
	public boolean CheckDate(int month, int date, int year) {
		Calendar testcal = Calendar.getInstance();
		testcal.set(year, month - 1, date);
		return (firstAppointment.get(Calendar.DATE)) == date && (firstAppointment.get(Calendar.MONTH) == month - 1) && withinDateRange(testcal);
	}

	@Override
	public String getName() {
		
		return "Yearly";
	}

	public String Get_eventfreq(){
		return "YEARLY";
	}
}
