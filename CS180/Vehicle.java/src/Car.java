/**
 * This program will implement an interface to give statistics about a certain car.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/04/2019
 *
 */

public class Car implements Vehicle {
    private String make;
    private String model;

    private int year;
    private int numWheels;
    private int numSeats;
    private int numMiles;

    public Car(String make, String model, int year, int numWheels, int numSeats) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.numWheels = numWheels;
        this.numSeats = numSeats;
        this.numMiles = 0;
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
    }

    void drive(int miles) {
        numMiles += miles;
    }
}
