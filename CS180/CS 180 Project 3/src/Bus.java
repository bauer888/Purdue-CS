/**
 *
 * This program creates an object called Bus and implements the Vehicle interface.
 *
 * @author Jack Bauer, bauer88
 *
 * @version 3/22/2019
 *
 */

public class Bus implements Vehicle {

    private int capacity;
    private Passenger[] passengers;
    private int count;
    private Route route;

    public Bus(Route route) {
        this.route = route;
        capacity = 2;
        this.passengers = new Passenger[2 * capacity];
        this.count = 0;

    }

    public Bus(Route route, int capacity) {
        this.route = route;
        this.capacity = capacity;
        count = 0;
        Passenger[] dudes = new Passenger[2 * capacity];
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
        if (waitingList) {
            if (route.equals(person.getRoute()) && count < capacity) {
                passengers[count] = person;
                person.confirm();
                count++;
                return true;
            } else if (route.equals(person.getRoute()) && count >= capacity) {
                passengers[count] = person;
                count++;
                return true;
            } else {
                person.cancel();
                return false;
            }
        } else {
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
    }

    @Override
    public boolean addPassenger(Passenger person) {
        return addPassenger(person, true);
    }

    @Override
    public Vehicle upgrade(int newCapacity) {
        Airplane boi = new Airplane(this.route, newCapacity);
        for (int x = 0; x < count; x++) {
//            passengers[x].confirm();
            boi.addPassenger(passengers[x]);
        }
        return boi;
    }
}
