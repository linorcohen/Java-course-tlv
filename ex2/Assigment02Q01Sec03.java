package Exercises.ex2;


import java.util.Arrays;

public class Assigment02Q01Sec03 {

    public static void main(String[] args) {
        String[] strings = {"aa", "Hello world", "cft"};
        convertStringToInt(strings);
    }

    public static void convertStringToInt(String[] strings) {
        for (String str : strings) {
            if (str == null || str.length() ==0) {
                continue;
            }
            int index;
            int[] stringAsInt = new int[str.length()];
            for (index = 0; index < str.length(); index++) {
                int strChar = str.charAt(index);
                stringAsInt[index] = strChar;
            }
            int sumEven = 0;
            for (int num : stringAsInt) {
                int divided = num % 4;
                if (divided % 2 == 0) {
                    sumEven += 1;

                }

            }
            System.out.println(str + " = " + Arrays.toString(stringAsInt) + " = " + sumEven);
        }
    }
}
