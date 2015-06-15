import java.util.Calendar;

public class header_date_occur extends Appointment {
	private Calendar dateFor;
	public header_date_occur(Calendar dateFor){
		super("header", "headerDesc", null, 0, "ONCE");
		this.dateFor = dateFor;
	}
	
	public Calendar getDateFor(){
		return dateFor;
	}
	
	public String toStringTestFriendly(){
		return toString();
	}
	
	@Override
	public String toString(){
		return "Appointments for: " +
				(dateFor.get(Calendar.MONTH) + 1) +"/" +
				dateFor.get(Calendar.DATE) +"/" +
				dateFor.get(Calendar.YEAR);
	}
}
