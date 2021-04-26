/**
 *
 * This program creates an object called Airplane and implements the Vehicle interface
 *
 * @author Jack Bauer, bauer88
 *
 * @version 3/22/2019
 *
 */

public class Airplane implements Vehicle {

    private int capacity;
    private Passenger[] passengers;
    private int count;
    private Route route;

    public Airplane(Route route, int capacity) {
        this.route = route;
        this.capacity = capacity;
        count = 0;
        Passenger[] dudes = new Passenger[capacity];
        this.passengers = dudes;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public Route getRoute() {
        return route;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Passenger[] getPassengers() {
        Passenger[] counter = new Passenger[count];
        for (int i = 0; i < count; i++) {
            if (passengers[i] != null) {
                counter[i] = passengers[i];
            }
        }
        return counter;
    }

    @Override
    public boolean addPassenger(Passenger person, boolean waitingList) {
        if (route.equals(person.getRoute()) && count < capacity) {
            passengers[count] = person;
            person.confirm();
            count++;
            return true;
        } else {
            person.cancel();
            return false;
        }
    }

    @Override
    public boolean addPassenger(Passenger person) {
        return addPassenger(person, false);
    }

    @Override
    public Vehicle upgrade(int newCapacity) {
        return null;
    }
}
