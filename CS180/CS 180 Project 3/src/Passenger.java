/**
 *
 * This program creates the booking status for each of the passengers
 *
 * @author Jack Bauer, bauer88
 *
 * @version 3/22/2019
 *
 */

public class Passenger {
    public static final String CANCELED = "Canceled";
    public static final String WAITLIST = "Waitlist";
    public static final String CONFIRMED = "Confirmed";

    private String passengerName;
    private String bookingStatus;
    private Route route;

    public Passenger(String name, Route route) {
        this.passengerName = name;
        this.route = route;
        this.bookingStatus = WAITLIST;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Route getRoute() {
        return route;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void confirm() {
        if (bookingStatus.equals(WAITLIST)) {
            this.bookingStatus = CONFIRMED;
        }
    }

    public void cancel() {
        if (bookingStatus.equals(WAITLIST)) {
            this.bookingStatus = CANCELED;
        }
    }
}
