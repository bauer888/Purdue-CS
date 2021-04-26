import java.util.Scanner;

/**
 * This program will help the user perform physics calculations.
 *
 * @author Jack Bauer bauer88@purdue.edu
 *
 * @version 02/01/2019
 *
 */

public class Physics {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("***Physics Formula Calculator***");
        System.out.println(" ");
        System.out.println("======================");
        System.out.println("(0) Newton's Second Law");
        System.out.println("(1) Momentum");
        System.out.println("(2) Work");
        System.out.println("(3) Kinetic Energy");
        System.out.println("(4) Power");
        System.out.println("(5) Exit");
        System.out.println("======================");
        System.out.print("Enter your choice: ");
        int equationNumber = s.nextInt();
        if (equationNumber < 0 || equationNumber > 5) {
            System.out.println("Invalid menu choice!");
        } else System.out.println();

        double m1;
        double a1;
        double F1;
        double m2;
        double v1;
        double p1;
        double F2;
        double d1;
        double W1;
        double m3;
        double v2;
        double KE;
        double F3;
        double v3;
        double P1;

        switch (equationNumber) {
            //Newton's Second Law
            case 0: System.out.println("F = m * a");
            System.out.println(" ");
            System.out.print("Enter the mass (m): ");
            m1 = s.nextDouble();
            System.out.print("Enter the acceleration (a): ");
            a1 = s.nextDouble();
            F1 = m1 * a1;
            System.out.printf("%s" + "%.6f" + "%s", "F = ", F1, " N");
            System.out.println();
            System.out.println("***Goodbye!***");
            break;

            //Momentum
            case 1: System.out.println("p = m * v");
            System.out.println(" ");
            System.out.print("Enter the mass (m): ");
            m2 = s.nextDouble();
            System.out.print("Enter the velocity (v): ");
            v1 = s.nextDouble();
            p1 = m2 * v1;
            System.out.printf("%s" + "%.6f" + "%s", "p = ", p1, " kg * m/s");
            System.out.println();
            System.out.println("***Goodbye!***");
            break;

            //Work
            case 2: System.out.println("W = F * d");
            System.out.println(" ");
            System.out.print("Enter the force (F): ");
            F2 = s.nextDouble();
            System.out.print("Enter the displacement (d): ");
            d1 = s.nextDouble();
            W1 = F2 * d1;
            System.out.printf("%s" + "%.6f" + "%s", "W = ", W1, " J");
            System.out.println();
            System.out.println("***Goodbye!***");
            break;

            //Kinetic Energy
            case 3: System.out.println("KE = 0.5 * m * v^2");
            System.out.println();
            System.out.print("Enter the mass (m): ");
            m3 = s.nextDouble();
            System.out.print("Enter the velocity (v): ");
            v2 = s.nextDouble();
            KE = 0.5 * m3 * Math.pow(v2, 2);
            System.out.printf("%s" + "%.6f" + "%s", "KE = ", KE, " J");
            System.out.println();
            System.out.println("***Goodbye!***");
            break;

            //Power
            case 4: System.out.println("P = F * v");
            System.out.println();
            System.out.print("Enter the force (F): ");
            F3 = s.nextDouble();
            System.out.print("Enter the velocity (v): ");
            v3 = s.nextDouble();
            P1 = F3 * v3;
            System.out.printf("%s" + "%.6f" + "%s", "P = ", P1, " W");
            System.out.println();
            System.out.println("***Goodbye!***");
            break;

            case 5: System.out.println("***Goodbye!***");
            break;
        }
    }
}
