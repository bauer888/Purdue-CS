/**
 *
 * This program creates the route for each of the passengers
 *
 * @author Jack Bauer, bauer88
 *
 * @version 3/22/2019
 *
 */

public class Route {
    private String from; // The start
    private String to; // The destination

    public Route(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Route) {
            Route newRoute = (Route)obj;
            return (this.from.equals(newRoute.from) && (this.to.equals(newRoute.to)));

        } else {
            return false;
        }
    }

    public String toString() {
        return String.format("Route: From %s to %s\n", this.from, this.to);
    }
}
