package Exercises.ex1;


public class Assigment1 {

    public static void main(String[] args) {
        CheckRightTriangle(2, 3, 1);
    }

    public static void CheckRightTriangle(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0) {
            System.out.println("invalid input!");
        }
        int yPow = (int) Math.pow(y, 2);
        int xPow = (int) Math.pow(x, 2);
        int zPow = (int) Math.pow(z, 2);
        if ((x + y > z) && (x + z > y) && (y + z > x)) {
            if ((yPow + xPow == zPow) || (zPow + xPow == yPow) || (yPow + zPow == xPow)) {
                System.out.println("the input (" + x + "," + y + "," + z + ") defines a valid right triangle!");
            } else {
                System.out.println("the input (" + x + "," + y + "," + z + ") defines a valid triangle!");
            }
        } else {
            System.out.println("the input (" + x + "," + y + "," + z + ") doesn't defines a valid triangle!");
        }
    }
}
