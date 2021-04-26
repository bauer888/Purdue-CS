/**
 *
 * This program provides an interface that is implemented by two other classes
 *
 * @author Jack Bauer, bauer88
 *
 * @version 3/22/2019
 *
 */

public interface Vehicle {

    public int getCapacity();

    public Route getRoute();

    public int getCount();

    public Passenger[] getPassengers();

    public boolean addPassenger(Passenger person, boolean waitingList);

    public boolean addPassenger(Passenger person);

    public Vehicle upgrade(int newCapacity);
}
