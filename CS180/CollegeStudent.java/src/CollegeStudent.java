/**
 * This program is a superclass that will be used to create two an object named CollegeStudent.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/18/2019
 *
 */

public class CollegeStudent {

    private double dormCost;
    private double tuition;
    private Residency residency;
    private boolean livesOffCampus;

    public CollegeStudent(String residency) throws IllegalArgumentException {
        if (residency.equalsIgnoreCase(Residency.IN_STATE.toString().replaceAll("_", " "))) {
            tuition = 23000.00;
            this.residency = Residency.IN_STATE;
        } else if (residency.equalsIgnoreCase(Residency.OUT_OF_STATE.toString().replaceAll("_", " "))) {
            tuition = 42000.00;
            this.residency = Residency.OUT_OF_STATE;
        } else if (residency.equalsIgnoreCase(Residency.INTERNATIONAL.toString().replaceAll("_", " "))) {
            tuition = 44500.00;
            this.residency = Residency.INTERNATIONAL;
        } else {
            throw new IllegalArgumentException("Student residency must be one of the three specified statuses");
        }
        livesOffCampus = false;
        dormCost = 800.00;
    }


    public CollegeStudent(String residency, boolean livesOffCampus) throws IllegalArgumentException {
        if (residency.equalsIgnoreCase(Residency.IN_STATE.toString().replaceAll("_", " "))) {
            tuition = 23000.00;
            this.residency = Residency.IN_STATE;
        } else if (residency.equalsIgnoreCase(Residency.OUT_OF_STATE.toString().replaceAll("_", " "))) {
            tuition = 42000.00;
            this.residency = Residency.OUT_OF_STATE;
        } else if (residency.equalsIgnoreCase(Residency.INTERNATIONAL.toString().replaceAll("_", " "))) {
            tuition = 44500.00;
            this.residency = Residency.INTERNATIONAL;
        } else {
            throw new IllegalArgumentException("Student residency must be one of the three specified statuses");
        }

        if (livesOffCampus) {
            dormCost = 500.00;
        } else {
            dormCost = 800.00;
        }

        this.livesOffCampus = livesOffCampus;
    }

    public double getDormCost() {
        return dormCost;
    }

    public double getTuition() {
        return tuition;
    }

    public Residency getResidency() {
        return residency;
    }

    public boolean isLivingOffCampus() {
        return livesOffCampus;
    }

    public void setDormCost(double dormCost) {
        this.dormCost = dormCost;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public double calculateYearlyCost() {
        return (dormCost * 12.00) + tuition;
    }

    public String toString() {
        String temp = "";
        if (residency == Residency.IN_STATE) {
            temp = "In State";
        } else if (residency == Residency.OUT_OF_STATE) {
            temp = "Out of State";
        } else {
            temp = "International";
        }

        String one = "The total expenses are " + calculateYearlyCost();
        String two = "Here is the breakdown: ";
        String three = "This student is " + temp;
        String four = "Yearly tuition: " + tuition;
        String five = "Dorm costs: " + (dormCost * 12.00);
        return one + "\n" + two + "\n" + three + "\n" + four + "\n" + five;
    }

    public static void main(String[] args) {
        System.out.printf("%d", 3 << 1);
    }

}
