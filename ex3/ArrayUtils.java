package Exercises.ex3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayUtils {

    public static int[][] transposeMatrix(int[][] m) {
        int numOfRows = Array.getLength(m);
        int numOfColumns = Array.getLength(m[0]);
        int[][] mainArr = new int[numOfColumns][numOfRows];
        for (int indexC = 0; indexC < numOfColumns; indexC++) {
            int[] arr = new int[numOfRows];
            for (int indexR = 0; indexR < numOfRows; indexR++) {
                arr[indexR] = m[indexR][indexC];
            }
            mainArr[indexC] = arr;
        }
        return mainArr;
    }

    public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
        int[] newArr = new int[Array.getLength(array)];
        int newIndex;
        int currentIndex;

        if (direction == 'R' && move > 0) {
            for (int index = 0; index < Array.getLength(array); index++) {
                currentIndex = index;
                newIndex = ((currentIndex + move) % (Array.getLength(array)));
                newArr[newIndex] = array[currentIndex];
            }
            array = newArr;
            return array;
        }
        if (direction == 'L' && move > 0) {
            for (int index = 0; index < Array.getLength(array); index++) {
                currentIndex = index;
                newIndex = ((currentIndex - move) % (Array.getLength(array)));
                if (newIndex < 0) {
                    newIndex = Array.getLength(array) + newIndex;
                }
                newArr[newIndex] = array[currentIndex];
            }
            array = newArr;
            return array;
        } else {
            return array;
        }
    }

    public static int alternateSum(int[] array) {
        int maxSum = 0;
        for (int startIndex = 0; startIndex < Array.getLength(array); startIndex++) {
            int sum = 0;
            int countIndex = 0;
            for (int index = startIndex; index < Array.getLength(array); index++) {
                if (countIndex % 2 == 0) {
                    countIndex++;
                    sum = sum + array[index];
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                } else {
                    countIndex++;
                    sum = sum - array[index];
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }

            }

        }
        return maxSum;
    }

    public static int findPath(int[][] m, int i, int j) {
        int initial = i;
        int prev = i;
        if (m[i][j] ==1) {
            return 1;
        }
        int counter = 0;
        for (int index =0;index < Array.getLength(m[0]); index++) {
            if (index != initial && m[initial][index] == 1 && index != prev) {
                prev = initial;
                initial = index;
                index = 0;
                if (initial == j) {
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
//        int[][] m = {{1,2,3}};
//        System.out.println(Arrays.deepToString(transposeMatrix(m)));

//        int[] array = {0,8,9,5,6};
//        System.out.println(Arrays.toString(shiftArrayCyclic(array,6,'L')));

//        int[] array = {1, 2, -3, 4, 5};
//        System.out.println(alternateSum(array));

        int[][] twoDimensionArray = {{1,1,0},{0,1,1},{1,1,0}};
        System.out.println(findPath(twoDimensionArray,0,0));


    }

}
