import java.util.Scanner;

/**
 * This program will help users find the total cost of their customized computer.
 *
 * @author Jack Bauer bauer88@purdue.edu
 *
 * @version 02/04/2019
 *
 */

public class ComputerCustomizer {
    public static void main(String [] args) {
        Scanner a = new Scanner(System.in);
        System.out.println("CS18000's Computer Customizer");
        System.out.println();
        System.out.println("Starting price: $4999.00");
        System.out.println();
        double startingPrice = 4999.00;
        double price1 = 0.00;
        double price2 = 0.00;
        double price3 = 0.00;
        double price4 = 0.00;
        double price5 = 0.00;
        double totalPrice;

        //Processor
        System.out.println("Processor choices");
        System.out.println("(1) 8-core Intel Xeon W (+ $0.00)");
        System.out.println("(2) 10-core Intel Xeon W (+ $800.00)");
        System.out.println("(3) 14-core Intel Xeon W (+ $1600.00)");
        System.out.println("(4) 18-core Intel Xeon W (+ $2400.00)");
        System.out.print("Your choice: ");
        int processor = a.nextInt();
        if (processor < 1 || processor > 4) {
            System.out.println("Invalid Choice!");
            return;
        } else System.out.println();
        switch (processor) {
            case 1: price1 = 0.00;
            break;
            case 2: price1 = 800.00;
            break;
            case 3: price1 = 1600.00;
            break;
            case 4: price1 = 2400.00;
            break;
        }

        //Memory
        System.out.println("Memory choices");
        System.out.println("(1) 32GB DDR4 RAM (+ $0.00)");
        System.out.println("(2) 64GB DDR4 RAM (+ $800.00)");
        System.out.println("(3) 128GB DDR4 RAM (+ $2400.00)");
        System.out.print("Your choice: ");
        int memory = a.nextInt();
        if (memory < 1 || memory > 3) {
            System.out.println("Invalid Choice!");
            return;
        } else System.out.println();
        switch (memory) {
            case 1: price2 = 0.00;
            break;
            case 2: price2 = 800.00;
            break;
            case 3: price2 = 2400.00;
            break;
        }

        //Storage
        System.out.println("Storage choices: ");
        System.out.println("(1) 1TB SSD (+ $0.00)");
        System.out.println("(2) 2TB SSD (+ $800.00)");
        System.out.println("(3) 4TB SSD (+ $2000.00)");
        System.out.print("Your choice: ");
        int storage = a.nextInt();
        if (storage < 1 || storage > 3) {
            System.out.println("Invalid Choice!");
            return;
        } else System.out.println();
        switch (storage) {
            case 1: price3 = 0.00;
            break;
            case 2: price3 = 800.00;
            break;
            case 3: price3 = 2800.00;
            break;
        }

        //Graphics
        System.out.println("Graphics choices");
        System.out.println("(1) Radeon Pro Vega 56 (+ $0.00)");
        System.out.println("(2) Radeon Pro Vega 64 (+ $600.00");
        System.out.print("Your choice: ");
        int graphics = a.nextInt();
        if (graphics < 1 || graphics > 2) {
            System.out.println("Invalid Choice!");
            return;
        } else System.out.println();
        switch (graphics) {
            case 1: price4 = 0.00;
            break;
            case 2: price4 = 600.00;
            break;
        }

        //Mouse and/or Trackpad
        System.out.println("Mouse or Trackpad");
        System.out.println("(1) Mouse (+ $0.00)");
        System.out.println("(2) Trackpad (+ $50.00)");
        System.out.println("(3) Both (+ $149.00)");
        System.out.print("Your choice: ");
        int mort = a.nextInt();
        if (mort < 1 || mort > 3) {
            System.out.println("Invalid Choice!");
            return;
        } else System.out.println();
        switch (mort) {
            case 1: price5 = 0.00;
            break;
            case 2: price5 = 50.00;
            break;
            case 3: price5 = 149.00;
            break;
        }

        //Total price
        totalPrice = startingPrice + price1 + price2 + price3 + price4 + price5;
        System.out.printf("%s" + "%.2f", "Total price: $", totalPrice);
    }
}
