/**
 *
 * This program implements an interface to write complex numbers.
 *
 * @author Jack Bauer
 *
 * @version 3/25/2019
 *
 */

public class ComplexNumber extends Object implements Comparable<ComplexNumber> {

    private double re;
    private double im;


    public ComplexNumber() {
        this.re = 0.0;
        this.im = 0.0;
    }

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber(ComplexNumber complexNumber) {
        if (complexNumber == null) {
            throw new IllegalArgumentException("Complex number is null");
        }
        this.re = complexNumber.re;
        this.im = complexNumber.im;
    }

    public synchronized double getRe() {
        return this.re;
    }

    public synchronized double getIm() {
        return this.im;
    }

    public synchronized void setRe(double re) {
        this.re = re;
    }

    public synchronized void setIm(double im) {
        this.im = im;
    }

    public synchronized ComplexNumber conjugate() {
        ComplexNumber conj = new ComplexNumber(getRe(), -1 * getIm());
        return conj;
    }

    public synchronized ComplexNumber reciprocal() {
        ComplexNumber recip = new ComplexNumber((getRe()) / (Math.pow(getRe(), 2) + Math.pow(getIm(), 2)),
                -1 * ((getIm()) / (Math.pow(getRe(), 2) + Math.pow(getIm(), 2))));
        return recip;
    }

    public synchronized ComplexNumber add(ComplexNumber complexNumber) throws IllegalArgumentException {
        if (complexNumber == null) {
            throw new IllegalArgumentException("Complex number is null");
        }
        complexNumber = new ComplexNumber(complexNumber.getRe(), complexNumber.getIm());
        ComplexNumber addFinal = new ComplexNumber(getRe() + complexNumber.getRe(),
                getIm() + complexNumber.getIm());
        return addFinal;

    }

    public synchronized ComplexNumber subtract(ComplexNumber complexNumber) throws IllegalArgumentException {
        if (complexNumber == null) {
            throw new IllegalArgumentException("Complex number is null");
        }
        complexNumber = new ComplexNumber(complexNumber.getRe(), complexNumber.getIm());
        ComplexNumber subtractFinal = new ComplexNumber(getRe() - complexNumber.getRe(),
                getIm() - complexNumber.getIm());
        return subtractFinal;
    }

    public synchronized ComplexNumber multiply(ComplexNumber complexNumber) throws IllegalArgumentException {
        if (complexNumber == null) {
            throw new IllegalArgumentException("Complex number is null");
        }
        complexNumber = new ComplexNumber(complexNumber.getRe(), complexNumber.getIm());
        ComplexNumber multiplyFinal = new ComplexNumber((complexNumber.getRe() * getRe()) - (complexNumber.getIm() *
                getIm()), (complexNumber.getIm() * getRe()) + (complexNumber.getRe() * getIm()));
        return multiplyFinal;
    }

    public synchronized ComplexNumber divide(ComplexNumber complexNumber) throws IllegalArgumentException {
        if (complexNumber == null) {
            throw new IllegalArgumentException("Complex number is null");
        }
        complexNumber = new ComplexNumber(complexNumber.getRe(), complexNumber.getIm());
        ComplexNumber divideFinal = new ComplexNumber(((complexNumber.getRe() * getRe()) + (complexNumber.getIm() *
                getIm())) / (Math.pow(complexNumber.getRe(), 2) + Math.pow(complexNumber.getIm(), 2)),
                -1 * ((complexNumber.getIm() * getRe()) - (complexNumber.getRe() * getIm())) /
                        (Math.pow(complexNumber.getRe(), 2) + Math.pow(complexNumber.getIm(), 2)));
        return divideFinal;
    }

    public synchronized int compareTo(ComplexNumber complexNumber) {
        ComplexNumber compare = new ComplexNumber(getRe(), getIm());
        if (complexNumber.equals(compare)) {
            return 0;
        } else if ((complexNumber.getRe() == getRe()) && getIm() < complexNumber.getIm()) {
            return (int)((getRe() - complexNumber.getRe()) + (complexNumber.getIm() - getRe()));
        } else if ((getRe() < complexNumber.getRe()) && getIm() == complexNumber.getIm()) {
            return (int)((complexNumber.getRe() - getRe()) + (getIm() - complexNumber.getIm()));
        } else {
            return (int)((getRe() - complexNumber.getRe()) + (getIm() - complexNumber.getIm()));
        }
    }

    public synchronized boolean equals(Object object) {
        return object instanceof ComplexNumber && ((ComplexNumber) object).getRe() == getRe() &&
                ((ComplexNumber) object).getIm() == getIm();
    }

    public synchronized String toString() {
        if (getIm() < 0) {
            String negative = String.format("%.6f - %.6fi", getRe(), -1 * getIm());
            return negative;
        } else {
            String positive = String.format("%.6f + %.6fi", getRe(), getIm());
            return positive;
        }
    }

    public static void main(String[] args) {
        ComplexNumber me = new ComplexNumber(4, 5);
        ComplexNumber you = new ComplexNumber(2, 6);
        System.out.println(me.compareTo(you));
    }

}
