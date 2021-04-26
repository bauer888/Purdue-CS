/**
 * This program will use an interface that will be implemented into two different classes.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/04/2019
 *
 */

public interface Vehicle {
    String getMake();
    String getModel();

    int getYear();
    int getNumWheels();
    int getNumSeats();
    int getNumMiles();

    default void printInfo() {
        System.out.println("Year: " + getYear());
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Odometer: " + getNumMiles());
    }
}