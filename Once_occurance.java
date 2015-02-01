import java.util.Calendar;

public class Once_occurance extends ReoccuranceRule{

	public Once_occurance(Calendar firstAppointment){
		super(firstAppointment);
	}
	

	@Override
	public boolean CheckDate(int month, int date, int year) {
		
		return firstAppointment.get(Calendar.DATE) == date &&
			   firstAppointment.get(Calendar.MONTH) == month &&
			   firstAppointment.get(Calendar.YEAR) == year;
	}

	@Override
	public String getName() {
		return "Once";
	}


}
