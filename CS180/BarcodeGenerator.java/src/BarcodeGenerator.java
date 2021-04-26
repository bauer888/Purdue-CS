
    import java.util.Scanner;

    /**
     * CS180 Lab02
     *
     * This program will contain information about Purdue's shipments, such as which building the shipment will go to and the specific room number. The program will then take that information and encode that input into a barcode number.
     *
     * @author Jack Bauer, bauer88@purdue.edu, L11
     *
     * @version 01/16/2019
     *
     */

    public class BarcodeGenerator {
        public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter building name: ");
            String name = s.nextLine();
            int barcode = (byte)name.charAt(0);
            barcode += (byte)name.charAt(1) <<8;
            barcode += (byte)name.charAt(2) <<16;
            barcode += (byte)name.charAt(3) <<24;

            System.out.println("Enter room number: ");
            String name2 = s.nextLine();

            System.out.println(name);
            System.out.println(name2);
            System.out.println(barcode +" "+ name2);


        }
    }

