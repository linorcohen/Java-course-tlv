package Exercises.ex2;

public class Assignment02Q02 {

    public static void main(String[] args) {
        int num = 100;
        givenPiAccordingToNum(num);

    }


    public static void givenPiAccordingToNum(int num) {
        int numIndex;
        double sum = 1.0;
        double odd = 1.0;
        for (numIndex = 1; numIndex < num; numIndex++) {

            if (numIndex % 2 != 0) {
                odd += 2;
                sum -= 1.0 / odd;
            }
            if (numIndex % 2 == 0) {
                odd += 2;
                sum += 1.0 / odd;
            }

        }
        double sumPi = 4 * sum;
        System.out.println(sumPi + "\t" + Math.PI);

    }
}