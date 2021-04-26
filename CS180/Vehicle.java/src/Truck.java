/**
 * This program will implement an interface to give statistics about a certain truck.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/04/2019
 *
 */

public class Truck implements Vehicle {
    private String make;
    private String model;

    private int year;
    private int numWheels;
    private int numSeats;
    private int numMiles;

    private double maxCargoLoad;
    private double maxCargoSpace;
    private double currentCargoLoad;
    private double currentAvailableCargoSpace;

    public Truck(String make, String model, int year, int numWheels, int numSeats, double maxCargoLoad,
                 double maxCargoSpace) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.numWheels = numWheels;
        this.numSeats = numSeats;
        this.maxCargoLoad = maxCargoLoad;
        this.maxCargoSpace = maxCargoSpace;
        this.currentAvailableCargoSpace = maxCargoSpace;
        this.numMiles = 0;
        this.currentCargoLoad = 0;
    }

    public double getMaxCargoLoad() {
        return maxCargoLoad;
    }

    public double getMaxCargoSpace() {
        return maxCargoSpace;
    }

    public double getCurrentCargoLoad() {
        return currentCargoLoad;
    }

    public double getCurrentAvailableCargoSpace() {
        return currentAvailableCargoSpace;
    }

    void drive(int miles) {
        numMiles += miles;
    }

    boolean addCargo(double weight, double volume) {
        if (((currentCargoLoad + weight) <= maxCargoLoad) && ((currentAvailableCargoSpace - volume) >= 0)) {
            currentCargoLoad += weight;
            currentAvailableCargoSpace -= volume;
            return true;
        } else {
            return false;
        }
    }

    void removeCargo(double weight, double volume) {
        if ((currentCargoLoad - weight) < 0) {
            currentCargoLoad = 0;
        } else {
            currentCargoLoad -= weight;
        }
        if ((currentAvailableCargoSpace + volume) > maxCargoSpace) {
            currentAvailableCargoSpace = maxCargoSpace;
        } else {
            currentAvailableCargoSpace += volume;
        }
    }

    void addTrailer(double volume) {
        maxCargoSpace += volume;
    }

    void removeTrailer(double volume) {
        maxCargoSpace -= volume;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public int getNumMiles() {
        return numMiles;
    }

    public void printInfo() {
        System.out.println("Year: " + year);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Odometer: " + numMiles);
        System.out.println("Cargo Load: " + currentCargoLoad + "/" + maxCargoLoad + " lbs.");
        System.out.println("Cargo Space Available: " + currentAvailableCargoSpace + "/" + maxCargoSpace + " ft.^3");
    }
}
