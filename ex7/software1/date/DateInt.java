package Exercises.ex7.software1.date;

public class DateInt implements Date {

	private int date;
	private int day;
	private int month;
	private  int year;

	public DateInt(int date) {
		this.date =date;
		setDate();
	}

	private void setDate(){
		int days = date%365;
		int currMonth= 1;
		for (int i = 1; 0 < days ; i++) {
			if (days <= Date.getDaysInMonth(i)) {
				break;
			}
			days -= Date.getDaysInMonth(i);
			currMonth++;
		}
		day = 1 + days;
		month = currMonth;
		year = 1 + (date - date%365)/365;
	}


	@Override
	public String toString() {
		return String.format("%s/%s/%s",day,month,year);
	}
	
	@Override
	public int getDay() {

		return day;
	}

	@Override
	public int getMonth() {
		return month;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public void addDays(int days) {
		date += days;
		setDate();
		if (year < 1|| day < 1 || month < 1){
			day = 1;
			month = 1;
			year = 1;
		}
	}

}
