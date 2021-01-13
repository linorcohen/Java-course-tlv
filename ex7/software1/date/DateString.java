package Exercises.ex7.software1.date;


public class DateString implements Date {

    private int day;
    private int month;
    private int year;

    public DateString(String date) {
        String[] dateArray = date.split("/");
        int[] innerDate = new int[3];
        for (int i = 0; i < dateArray.length; i++) {
            innerDate[i] = Integer.parseInt(dateArray[i]);
        }
        this.day = innerDate[0];
        this.month = innerDate[1];
        this.year = innerDate[2];
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", day, month, year);
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
        if (days > 0) {
            addingDays(days);
        } else {
            subDays(days);
        }

        if (year < 1 || day < 1 || month < 1) {
            day = 1;
            month = 1;
            year = 1;
        }
    }

    private void subDays(int days) {
        int innerDays = days;
        if (days <= -365) {
            int yearsToSub = days / 365;
            year += yearsToSub;
            innerDays = days % 365;
        }
        while (innerDays < 0) {
            int daysToSub = Math.min(-innerDays, day);
            innerDays += daysToSub;
            if (day - daysToSub == 0) {
                month--;
                day = Date.getDaysInMonth(month);
                if (month == 0) {
                    month = 12;
                    year--;
                }
            } else {
                day -= daysToSub;
            }
        }
    }

    private void addingDays(int days) {
        int innerDays = days;
        if (days >= 365) {
            int yearsToAdd = days / 365;
            year += yearsToAdd;
            innerDays = days % 365;
        }
        while (innerDays > 0) {
            int daysToAdd = Math.min(innerDays, Date.getDaysInMonth(month) - day);
            innerDays -= daysToAdd;
            if (day + daysToAdd == Date.getDaysInMonth(month)) {
                day = 0;
                month++;
                if (month == 13) {
                    month = 1;
                    year++;
                }
            } else {
                day += daysToAdd;
            }
        }
    }


}


