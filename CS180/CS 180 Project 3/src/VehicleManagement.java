/**
 *
 * This program is the root class of all the other classes and controls what happens to each vehicle
 *
 * @author Jack Bauer, bauer88
 *
 * @version 3/22/2019
 *
 */

public class VehicleManagement {

    private Vehicle[] vehicles;
    private int count;
    private int bus;
    private int plane;

    public VehicleManagement(int bus, int plane) {
        Vehicle[] things = new Vehicle[0];
        this.vehicles = things;
        count = 0;
        this.bus = bus;
        this.plane = plane;
    }

    public boolean createRoute(Route route, int capacity) {
        Bus bus1 = new Bus(route, capacity);
        Vehicle[] car = new Vehicle[count + 1];
        if (bus == 0) {
            return false;
        }
        if (lookupVehicle(route) != -1) {
            return false;
        }
        for (int x = 0; x < count; x++) {
            car[x] = vehicles[x];
        }

        car[count] = bus1;
        vehicles = car;
        count++;
        bus--;
        return true;
    }

    public boolean addPassengerToVehicle(Passenger person) {
        if (lookupVehicle(person.getRoute()) == -1) {
            person.cancel();
            return false;
        }
        int number = lookupVehicle(person.getRoute());
        if (this.plane > 0) {
            if (vehicles[number].addPassenger(person)) {
                if (vehicles[number] instanceof Bus && vehicles[number].getCount() >= 2 * vehicles[number].getCapacity()
                ) {
                    vehicles[number] = vehicles[number].upgrade(3 * vehicles[number].getCapacity());
                    this.plane--;
                    this.bus++;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return vehicles[number].addPassenger(person, false);
        }
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public int getCount() {
        return count;
    }

    public int lookupVehicle(Route route) {
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getRoute().equals(route)) {
                return i;
            }
        }
        return -1;
    }

    public int getAvailableBus() {
        return bus;
    }

    public int getAvailablePlane() {
        return plane;
    }
}
