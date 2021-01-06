package Exercises.ex2;

public class Assigment02Q01Sec02 {

    public static void sumStringCharacters(String[] strings) {
        for (String str : strings) {
            if (str == null || str.length() == 0) {
                continue;
            }
            int index;
            int stringSum = 0;
            for (index = 0; index < str.length(); index++) {
                char strChar = str.charAt(index);
                stringSum += strChar;
            }
            System.out.println(stringSum);

        }
    }

    public static void main(String[] args) {
        String[] strings = {"aaa", "hello world", "bb"};
        sumStringCharacters(strings);
    }


}