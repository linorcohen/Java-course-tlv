package Exercises.ex7.software1.date;

public interface Date {

    public static int getDaysInMonth(int month) {
        if (month == 4 | month == 6 | month == 9 | month == 11) {
            return 30;
        }
        if (month == 2) {
            return 28;
        } else {
            return 31;
        }
    }

    public String toString();

    public int getDay();

    public int getMonth();

    public int getYear();

    public void addDays(int days);

    public default int differenceInDays(Date other) {
        int sum1 = other.getDay() - this.getDay();
        int sum2 = countDaysByMonth(other.getMonth()) - countDaysByMonth(this.getMonth());
        int sum3 = other.getYear()*365 - this.getYear()*365;
        return sum1+sum2+sum3;
    }

    private int countDaysByMonth(int month) {
        int sum =0;
        for (int i = 1; i < month; i++){
            sum += getDaysInMonth(i);
        }
        return sum;
    }

    public default boolean isBetweenDates(Date date1, Date date2) {
        return differenceInDays(date1) > 0 && differenceInDays(date2) < 0 || differenceInDays(date1) < 0 && differenceInDays(date2) > 0;
    }
}
