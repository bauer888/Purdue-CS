import java.util.Scanner;

public class Run {

    /**
     * Gets the passenger details and creates a passenger object from the
     * details.
     *
     * @return - Returns the passenger object.
     */
    @SuppressWarnings("resource")
    public static Passenger createPassenger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the FROM: ");
        String from = scanner.next();
        System.out.println("Enter the TO: ");
        String to = scanner.next();

        Route route = new Route(from, to);
        Passenger obj = new Passenger(name, route);

        return obj;
    }

    public static void passengers(Passenger[] passengers, int count) {
        System.out.println("**************************************************************");
        System.out.println("                      PASSENGER MANIFEST                      ");
        System.out.println("**************************************************************");
        System.out.printf("\t%s %16s %40s\n", "S.N", "Name", "Booking Status");

        for (int i = 0; i < count; i++) {
            System.out.printf("\t%-14d %-35s %s\n", i, passengers[i].getPassengerName(),
                    passengers[i].getBookingStatus());
        }
        System.out.println("\n\n");
    }

    public static void vehicles(Vehicle[] vehicles, int count) {
        System.out.println("**************************************************************");
        System.out.println("                      FLEET INFORMATION                       ");
        System.out.println("**************************************************************");

        if (count == 0)
            System.out.println("\tNo Vehicle assigned");

        for (int i = 0; i < count; i++) {
            System.out.println("----------- VEHICLE " + i + " -----------");
            if (vehicles[i] instanceof Bus)
                System.out.println("\tVehicle TYPE: " + "Bus");
            else
                System.out.println("\tVehicle TYPE: " + "Airplane");
            System.out.println("\tRoute: " + vehicles[i].getRoute());
            System.out.println("\tVehicle Capacity: " + vehicles[i].getCapacity());
            System.out.println("\tTotal Bookings: " + vehicles[i].getCount());
        }
    }

    public static void menu() {
        System.out.println("************************************************");
        System.out.println("              VEHICLE MANAGEMENT                ");
        System.out.println("************************************************");
        System.out.println("Choose from the following: ");
        System.out.println("\t1. Add a Passenger");
        System.out.println("\t2. Add a route");
        System.out.println("\t3. Display all Passengers on a vehicle");
        System.out.println("\t4. Display all Vehicles in the system");
        System.out.println("\t5. Exit");
        System.out.println("Enter your choice:");
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        VehicleManagement obj = new VehicleManagement(10, 5);
        int choice;
        String from, to;
        do {
            menu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Passenger tmp = createPassenger();
                    if (!obj.addPassengerToVehicle(tmp))
                        System.out.println("\nRoute does not exist or can not be booked anymore\n");
                    else
                        System.out.println("\nSuccessfully Added.\n");
                    break;
                case 2:
                    System.out.println("Enter the from destination: ");
                    from = scanner.next();
                    System.out.println("Enter the to destination: ");
                    to = scanner.next();
                    System.out.println("Enter the capacity: ");
                    int capacity = scanner.nextInt();
                    if (obj.createRoute(new Route(from, to), capacity))
                        System.out.println("\nSuccessfully Added.\n");
                    else
                        System.out.println("\nRoute already exists.\n");
                    break;
                case 3:
                    System.out.println("Enter the from destination: ");
                    from = scanner.next();
                    System.out.println("Enter the to destination: ");
                    to = scanner.next();

                    int vehicleid = obj.lookupVehicle(new Route(from, to));
                    if (vehicleid != -1)
                        passengers(obj.getVehicles()[vehicleid].getPassengers(),
                                obj.getVehicles()[vehicleid].getCount());
                    break;
                case 4:
                    vehicles(obj.getVehicles(), obj.getCount());
                    break;
            }

        } while (choice != 5);
    }
}