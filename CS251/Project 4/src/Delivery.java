import java.util.*;


public class Delivery {
    private DijGraph westLafayette;//The graph
    private Node restaurant;//The vertex that the driver start
    private Node[] customer;//The vertices that the driver need to pass through
    private double slope;//Tip percentage function slope
    private double intercept;//Tip percentage function intercept
    private double [] order;//The order amount from each customer
    public Delivery (DijGraph graph,Node restaurant, Node[] customer, double slope, double intercept, double[] order){
        this.westLafayette = graph;
        this.restaurant = restaurant;
        this.customer = customer;
        this.slope = slope;
        this.intercept  = intercept;
        this.order = order;
    }

    //Finding the best path that the driver can earn most tips
    //Each time the driver only picks up three orders
    //Picking up N orders and find the maximum tips will be NP-hard
    public double bestPath() {
        double max = 0.0;
        Dist[] start = westLafayette.dijkstra(restaurant.getNodeNumber());
        for (int i = 0; i < 3; i++) {
            Dist[] first = westLafayette.dijkstra(customer[i].getNodeNumber());
            double firstDistance = start[customer[i].getNodeNumber()].getDist();
            double tip1 = (slope * firstDistance + intercept) * 0.01 * order[i];
            for (int j = 0; j < 3; j++) {
                if (j != i) {
                    Dist[] second = westLafayette.dijkstra(customer[j].getNodeNumber());
                    double secondDistance = first[customer[j].getNodeNumber()].getDist() + firstDistance;
                    double tip2 = (slope * secondDistance + intercept) * 0.01 * order[j];
                    for (int k = 0; k < 3; k++) {
                        if (k != j && k != i) {
                            //Dist[] third = westLafayette.dijkstra(customer[j].getNodeNumber());
                            double thirdDistance = second[customer[k].getNodeNumber()].getDist() + secondDistance;
                            double tip3 = (slope * thirdDistance + intercept) * 0.01 * order[k];
                            if (tip1 + tip2 + tip3 > max) {
                                max = tip1 + tip2 + tip3;
                            }
                        }
                    }
                }

            }
        }
        return max;
    }
}
