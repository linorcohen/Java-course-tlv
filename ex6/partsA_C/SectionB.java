package Exercises.ex6.partsA_C;

import java.util.Arrays;
import java.util.Random;

public class SectionB {

    /*
     * @post $ret == true iff exists i such that array[i] == value
     */
    public static boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    /*
     * @pre array != null
     * @pre array.length > 2
     * @pre Arrays.equals(array, Arrays.sort(array))
     */
    public static int guess(int[] array) {
        Random rand = new Random();
        int randomGuess = rand.nextInt(array.length);
        return array[randomGuess];
    }

    /*
     * @pre Arrays.equals(array, Arrays.sort(array))
     * @pre array.length >= 1
     * @post for all i array[i] >= $ret
     */
    public static int min(int[] array) {
        return array[0];
    }

    /*
     * @pre array.length >=1
     * @post for all i array[i] >= $ret
     * @post Arrays.equals(array, prev(array))
     */
    public static int min2(int[] array) {
        Arrays.sort(array);
        return array[0];
    }

    /*
     * @pre word.length() >=1
     * @post for all i : $ret.charAt(i) == word.charAt(a.length() - i - 1)

     */
    public static String reverse(String word) {
        char[] reversedWordArr = word.toCharArray();
        for( int index = 0; index < word.length();index++){
            reversedWordArr[index] = word.charAt(word.length()-index -1);
        }
        return String.valueOf(reversedWordArr);
    }

    public static void main(String[] args) {
        int[] array = {2,3,1,4};
        System.out.println(contains(array, 5));
        System.out.println(min(array));
        int[] sorted = {1,2,3,4};
        System.out.println(Arrays.equals(array, sorted ));
        System.out.println(min2(array));
        System.out.println(reverse("love"));
        System.out.println(guess(sorted));
    }

}
