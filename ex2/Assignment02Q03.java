package Exercises.ex2;

import java.util.Arrays;

public class Assignment02Q03 {

    public static void main(String[] args) {
        int num =20;
        countFibonacciNumbers(num);
    }

    public static void countFibonacciNumbers(int number) {
        int firstNum = 1;
        int secondNum = 1;
        int sum;
        int[] fibonacci = new int[number];
        int numIndex;
        for (numIndex = 0;numIndex<number;numIndex++) {
            fibonacci[numIndex] = firstNum;
            sum = firstNum + secondNum;
            firstNum = secondNum;
            secondNum = sum;


        }
        System.out.println(Arrays.toString(fibonacci));

    }
}
