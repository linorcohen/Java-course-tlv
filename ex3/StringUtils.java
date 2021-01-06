package Exercises.ex3;

import java.lang.reflect.Array;
import java.util.Arrays;


public class StringUtils {

    public static String findSortedSequence(String str) {
        String[] strArray = str.split(" ");
        String strCheck = "";
        String longest = "";

        for (int index = 0; index < Array.getLength(strArray); index++) {
            String first = strArray[index];
            strCheck = first;
            for (int indexNext = index + 1; indexNext < Array.getLength(strArray); indexNext++) {
                String next = strArray[indexNext];
                if (first.compareTo(next) <= 0) {
                    strCheck = String.join(" ", strCheck, next);
                    first = next;
                } else {
                    break;
                }
            }
            if (strCheck.length() >= longest.length()) {
                longest = strCheck;
            }
        }
        return longest;
    }

    public static String parityXorStrings(String a, String b) {
        String str = "";
        for (int index = 0; index < a.length(); index++) {
            int countFirst = 0;
            int countSec = 0;
            char letter = a.charAt(index);
            for (int indexSec = 0; indexSec < b.length(); indexSec++) {
                if (letter == b.charAt(indexSec)) {
                    countSec++;
                }
            }
            for (int indexThird = 0; indexThird < a.length(); indexThird++) {
                if (letter == a.charAt(indexThird)) {
                    countFirst++;
                }
            }
            if (countFirst % 2 != 0 && countSec % 2 == 0) {
                str = String.join("", str, String.valueOf(a.charAt(index)));
            }
        }
        return str;
    }

    public static boolean isAnagram(String a, String b) {
        String[] aArr = (a.replace(" ", "")).split("");
        String[] bArr = (b.replace(" ", "")).split("");
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        if (Arrays.equals(aArr, bArr)) {
            return true;
        } else {
            return false;

        }
    }

    public static void main(String[] args) {
//        String str = "life is not not not fair";
//        System.out.println(findSortedSequence(str));

//        System.out.println(parityXorStrings("fireman", "maniac"));

//        System.out.println(isAnagram("mothEr in law","hitlEr woman"));
//        System.out.println(isAnagram("listeN","Silent"));
//        System.out.println(isAnagram("software","jeans"));
//        System.out.println(isAnagram("Funeral","real Fun"));
//        System.out.println(isAnagram("Aa","aA"));
//        System.out.println(isAnagram("","    "));



    }
}
