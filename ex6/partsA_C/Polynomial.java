package Exercises.ex6.partsA_C;

import java.util.Arrays;

public class Polynomial {

    double[] polyArray;

    /*
     * Creates the zero-polynomial with p(x) = 0 for all x.
     */
    public Polynomial() {
        polyArray = new double[1];
    }

    /*
     * Creates a new polynomial with the given coefficients.
     */
    public Polynomial(double[] coefficients) {
        polyArray = coefficients;
    }


    /*
     * Adds this polynomial to the given one
     *  and returns the sum as a new polynomial.
     */
    public Polynomial adds(Polynomial polynomial) {
        double[] addedArray = Arrays.copyOf(polyArray, polyArray.length);
        if (polynomial.polyArray.length <= polyArray.length) {
            addValuesOfArrayToArray(polynomial.polyArray, addedArray);
        }
        if (polynomial.polyArray.length > polyArray.length) {
            addedArray = Arrays.copyOf(polynomial.polyArray, polynomial.polyArray.length);
            addValuesOfArrayToArray(polyArray, addedArray);
        }
        return new Polynomial(addedArray);
    }


    private void addValuesOfArrayToArray(double[] arrayFrom, double[] arrayTo) {
        for (int index = 0; index < arrayFrom.length; index++) {
            arrayTo[index] += arrayFrom[index];
        }
    }


    /*
     * Multiplies a to this polynomial and returns
     * the result as a new polynomial.
     */
    public Polynomial multiply(double a) {
        double[] multiArr = Arrays.copyOf(polyArray, polyArray.length);
        for (int index = 0; index < polyArray.length; index++) {
            multiArr[index] = polyArray[index] * a;
        }
        return new Polynomial(multiArr);

    }

    /*
     * Returns the degree (the largest exponent) of this polynomial.
     */
    public int getDegree() {
        for (int index = polyArray.length - 1; index > 0; index--) {
            if (polyArray[index] != 0) {
                return index;
            }
        }
        return 0;
    }

    /*
     * Returns the coefficient of the variable x
     * with degree n in this polynomial.
     */
    public double getCoefficient(int n) {
        if (polyArray.length <= n) {
            return 0.0;
        }
        return polyArray[n];
    }

    /*
     * set the coefficient of the variable x
     * with degree n to c in this polynomial.
     * If the degree of this polynomial < n, it means that that the coefficient of the variable x
     * with degree n was 0, and now it will change to c.
     */
    public void setCoefficient(int n, double c) {
        if (polyArray.length <= n) {
            polyArray = Arrays.copyOf(polyArray, n + 1);
        }
        polyArray[n] = c;
    }

    /*
     * Returns the first derivation of this polynomial.
     *  The first derivation of a polynomial a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
     */
    public Polynomial getFirstDerivation() {
        double[] firstDerivationArr = Arrays.copyOfRange(polyArray, 1, polyArray.length);
        for (int index = 0; index < firstDerivationArr.length; index++) {
            firstDerivationArr[index] = firstDerivationArr[index] * index;
        }
        return new Polynomial(firstDerivationArr);
    }

    /*
     * given an assignment for the variable x,
     * compute the polynomial value
     */
    public double computePolynomial(double x) {
        double sum = 0.0;
        for (int index = 0; index < polyArray.length; index++) {
            sum += polyArray[index] * Math.pow(x, index);
        }
        return sum;
    }

    /*
     * given an assignment for the variable x,
     * return true iff x is a root of this polynomial
     */
    public boolean isARoot(double x) {
        return computePolynomial(x) == 0;
    }

    public void getPolynomial(){
        System.out.println(Arrays.toString(polyArray));
    }
}
