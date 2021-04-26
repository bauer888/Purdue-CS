/**
 * This program is a subclass that will be used to create two an object named IUStudent.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/18/2019
 *
 */

public class IUStudent extends CollegeStudent {

    private double bookFees = 1034.00;
    private double transportationCost;
    private double financialAid;

    public IUStudent(String residency, boolean livesOffCampus, double gpa) throws IllegalArgumentException {
        super(residency, livesOffCampus);

        if (residency.equalsIgnoreCase(Residency.IN_STATE.toString().replaceAll("_", " "))) {
            setTuition(10534.00);
        } else if (residency.equalsIgnoreCase(Residency.OUT_OF_STATE.toString().replaceAll("_", " "))) {
            setTuition(34846.00);
        }

        if (livesOffCampus) {
            transportationCost = 500.00;
            setDormCost(400.00);
        } else {
            transportationCost = 100.00;
            setDormCost(800.00);
        }

        if (gpa >= 3.75) {
            financialAid = 4500.00;
        } else if (gpa >= 3.50 && gpa < 3.75) {
            financialAid = 3500.00;
        } else if (gpa >= 3.00 && gpa < 3.50) {
            financialAid = 2750.00;
        } else if (gpa >= 2.50 && gpa < 3.00) {
            financialAid = 2500.00;
        } else {
            financialAid = 0.00;
        }

        if (gpa < 0.00) {
            throw new IllegalArgumentException("GPA needs to be above or equal to 0");
        }

        if (residency.equalsIgnoreCase(Residency.INTERNATIONAL.toString().replaceAll("_", " "))) {
            throw new IllegalArgumentException("Student must be in state or out of state");
        }
    }

    public double getBookFees() {
        return bookFees;
    }

    public double getTransportationCost() {
        return transportationCost;
    }

    public double getFinancialAid() {
        return financialAid;
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

    public void setTransportationCost(double transportationCost) {
        this.transportationCost = transportationCost;
    }


    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }


    public double calculateYearlyCost() {
        return (getTuition() + bookFees + transportationCost + (getDormCost() * 12.00)) - financialAid;

    }

    public String toString() {
        String temp = "";
        if (getResidency() == Residency.IN_STATE) {
            temp = "In State";
        } else if (getResidency() == Residency.OUT_OF_STATE) {
            temp = "Out of State";
        }

        String one = "The total expenses are: " + calculateYearlyCost();
        String two = "Here is the breakdown: ";
        String three = "This student is " + temp;
        String four = "Yearly Tuition: " + getTuition();
        String five = "Dorm Costs: " + (getDormCost() * 12.00);
        String six = "Book Fees: " + bookFees;
        String seven = "Transportation Cost: " + transportationCost;
        String eight = "Financial Aid: " + financialAid;
        return one + "\n" + two + "\n" + three + "\n" + four + "\n" + five + "\n" + six + "\n" + seven + "\n" + eight;
    }
}
