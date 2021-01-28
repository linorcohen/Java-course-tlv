package Exercises.ex10.enumRiddles;

enum Day {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int num;

    Day(int num){
        this.num = num;
    }

    private Day getDay(int num){
        for (Day day : Day.values()){
            if (day.getDayNumber() == num){
                return day;
            }
        }
        return null;
    }

    public Day next() {
        if (num+1 > Day.values().length){
            return MONDAY;
        }
        return getDay(num+1);
    }

    int getDayNumber() {
        return num;
    }
}

public class DayTest {
    public static void main(String[] args) {
        for (Day day : Day.values()) {
            System.out.printf("%s (%d), next is %s\n", day, day.getDayNumber(), day.next());
        }
    }
}
