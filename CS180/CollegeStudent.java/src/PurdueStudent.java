/**
 * This program is a subclass that will be used to create two an object named PurdueStudent.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/18/2019
 *
 */

public class PurdueStudent extends CollegeStudent {

    private double bookFees;
    private double financialAid;
    private String major;

    public PurdueStudent(String residency, String major, boolean livesOffCampus, double gpa) throws
            IllegalArgumentException {
        super(residency, livesOffCampus);
        if (residency.equalsIgnoreCase(Residency.IN_STATE.toString().replaceAll("_", " "))) {
            setTuition(9992.00);
        } else if (residency.equalsIgnoreCase(Residency.OUT_OF_STATE.toString().replaceAll("_", " "))) {
            setTuition(28794.00);
        } else if (residency.equalsIgnoreCase(Residency.INTERNATIONAL.toString().replaceAll("_", " "))) {
            setTuition(30954.00);
        } else {
            throw new IllegalArgumentException("Student residency must be one of the three specified statuses");
        }

        if (major.equalsIgnoreCase("Computer Science")) {
            bookFees = 200.00;
            this.major = major;
        } else if (major.equalsIgnoreCase("Engineering")) {
            bookFees = 500.00;
            this.major = major;
        } else if (major.equalsIgnoreCase("Liberal Arts")) {
            bookFees = 750.00;
            this.major = major;
        } else {
            bookFees = 100.00;
            this.major = major;
        }

        if (gpa >= 3.75) {
            financialAid = 5000.00;
        } else if (gpa >= 3.50 && gpa < 3.75) {
            financialAid = 4500.00;
        } else if (gpa >= 3.00 && gpa < 3.50) {
            financialAid = 3000.00;
        } else if (gpa >= 2.50 && gpa < 3.00) {
            financialAid = 2500.00;
        } else if (gpa < 2.00) {
            throw new IllegalArgumentException("GPA needs to be above or equal to 2.00");
        }

    }

    public double getBookFees() {
        return bookFees;
    }

    public double getFinancialAid() {
        return financialAid;
    }

    public String getMajor() {
        return major;
    }

    public double getDormCost() {
        return super.getDormCost();
    }

    public double getTuition() {
        return super.getTuition();
    }

    public boolean isLivingOffCampus() {
        return super.isLivingOffCampus();
    }

    public void setBookFees(double bookFees) {
        this.bookFees = bookFees;
    }

    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double calculateYearlyCost() {
        return (getTuition() + bookFees + (getDormCost() * 12.00)) - financialAid;
    }

    public String toString() {
        String temp = "";
        if (getResidency() == Residency.IN_STATE) {
            temp = "In State";
        } else if (getResidency() == Residency.OUT_OF_STATE) {
            temp = "Out of State";
        } else {
            temp = "International";
        }
        String one = "The total expenses are " + calculateYearlyCost();
        String two = "Here is the breakdown: ";
        String three = "This student is " + getResidency().toString().replaceAll("_", " ");
        String four = "Yearly Tuition: " + getTuition();
        String five = "Dorm Costs: " + (getDormCost() * 12.00);
        String six = "Book Fees: " + bookFees;
        String seven = "Financial Aid: " + financialAid;
        return one + "\n" + two + "\n" + three + "\n" + four + "\n" + five + "\n" + six + "\n" + seven;
    }
}
