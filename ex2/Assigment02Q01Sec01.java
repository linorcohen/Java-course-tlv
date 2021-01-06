package Exercises.ex2;

public class Assigment02Q01Sec01 {

    private static void checkOddFirstCharacter(String[] strings) {
        for (String str : strings) {
            if (str == null || str.length() == 0) {
                continue;
            }
            char firstChar = str.charAt(0);
            if (firstChar % 2 != 0) {
                System.out.println(firstChar);
            }
        }
    }

    public static void main(String[] args) {
        String[] strings = {"After", "", null, "C", "f"};
        checkOddFirstCharacter(strings);
    }

}
