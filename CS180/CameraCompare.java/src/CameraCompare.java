import java.util.Scanner;

/**
 * This program will help users compare two cameras.
 *
 * @author Jack Bauer bauer88@purdue.edu
 *
 * @version 02/06/2019
 */
public class CameraCompare {

    public static void main(String[] args) {
        /**
         * DO NOT REMOVE ANY OF THESE VARIABLES!
         *
         * These should be used by your program to complete the outlined task.
         *
         * You may add variables as you see fit.
         */
        boolean hasWiFi1;
        boolean hasWiFi2;
        boolean isWaterResistant1;
        boolean isWaterResistant2;
        boolean hasGPS1;
        boolean hasGPS2;
        boolean hasZoom1;
        boolean hasZoom2;
        String condition1;
        String condition2;
        double price1;
        double price2;
        int userRating1;
        int userRating2;
        int cameraValue1 = 0;
        int cameraValue2 = 0;
        Scanner s = new Scanner(System.in);
        // HINT: Scanner


        /**
         * Part 1 -- Obtaining Camera Details
         */
        System.out.println("Enter attributes of Camera 1:"); // DO NOT REMOVE THIS LINE

        //Wifi
        System.out.println("Is it WiFi enabled? (true/false)");
        boolean inputWifi1 = Boolean.parseBoolean(s.nextLine());
        if (inputWifi1) {
            hasWiFi1 = true;
        } else {
            hasWiFi1 = false;
        }

        //Water Resistance
        System.out.println("Is it water resistant? (true/false)");
        boolean inputResis1 = Boolean.parseBoolean(s.nextLine());
        if (inputResis1) {
            isWaterResistant1 = true;
        } else {
            isWaterResistant1 = false;
        }

        //GPS
        System.out.println("Is it GPS enabled? (true/false)");
        boolean inputGPS1 = Boolean.parseBoolean(s.nextLine());
        if (inputGPS1) {
            hasGPS1 = true;
        } else {
            hasGPS1 = false;
        }

        //Zoom
        System.out.println("Is the lens able to zoom? (true/false)");
        boolean inputZoom1 = Boolean.parseBoolean(s.nextLine());
        if (inputZoom1) {
            hasZoom1 = true;
        } else {
            hasZoom1 = false;
        }

        //Condition
        System.out.println("What is the condition? (New/Refurbished/Used)");
        condition1 = s.nextLine();

        //Price
        System.out.println("Enter price from $0.00 to $1000.00");
        price1 = s.nextDouble();
        price1 = Math.round(price1 * 100) / 100.0d;

        //User Rating
        System.out.println("Enter user rating from 0 to 5");
        userRating1 = s.nextInt();





        /**
         * DO NOT REMOVE THESE LINES
         */
        System.out.println("======================");
        System.out.println("Enter attributes of Camera 2:");

        //Wifi
        System.out.println("Is it Wifi enabled? (true/false)");
        s.nextLine();
        boolean inputWifi2 = Boolean.parseBoolean(s.nextLine());
        if (inputWifi2) {
            hasWiFi2 = true;
        } else {
            hasWiFi2 = false;
        }

        //Water Resistance
        System.out.println("Is it water resistant? (true/false)");
        boolean inputResis2 = Boolean.parseBoolean(s.nextLine());
        if (inputResis2) {
            isWaterResistant2 = true;
        } else {
            isWaterResistant2 = false;
        }

        //GPS
        System.out.println("Is it GPS enabled? (true/false)");
        boolean inputGPS2 = Boolean.parseBoolean(s.nextLine());
        if (inputGPS2) {
            hasGPS2 = true;
        } else {
            hasGPS2 = false;
        }

        //Zoom
        System.out.println("Is the lens able to zoom? (true/false)");
        boolean inputZoom2 = Boolean.parseBoolean(s.nextLine());
        if (inputZoom2) {
            hasZoom2 = true;
        } else {
            hasZoom2 = false;
        }

        //Condition
        System.out.println("What is the condition? (New/Refurbished/Used)");
        condition2 = s.nextLine();

        //Price
        System.out.println("Enter price from $0.00 to $1000.00");
        price2 = s.nextDouble();
        price2 = Math.round(price2 * 100) / 100.0d;

        //User Rating
        System.out.println("Enter user rating from 0 to 5");
        userRating2 = s.nextInt();




        /**
         * Part 2 -- Computing Camera Values
         */

        //Camera Value 1
        if (hasWiFi1) {
            cameraValue1 = cameraValue1 + 1;
        } else cameraValue1 = cameraValue1;
        if (isWaterResistant1) {
            cameraValue1 = cameraValue1 + 1;
        } else cameraValue1 = cameraValue1;
        if (hasGPS1) {
            cameraValue1 = cameraValue1 + 1;
        } else cameraValue1 = cameraValue1;
        if (hasZoom1) {
            cameraValue1 = cameraValue1 + 1;
        } else cameraValue1 = cameraValue1;
        if (condition1.equals("New")) {
            cameraValue1 = cameraValue1 + 2;
        } else if (condition1.equals("Refurbished")) {
            cameraValue1 = cameraValue1 + 1;
        } else cameraValue1 = cameraValue1;

        //Camera Value 2
        if (hasWiFi2) {
            cameraValue2 = cameraValue2 + 1;
        } else cameraValue2 = cameraValue2;
        if (isWaterResistant2) {
            cameraValue2 = cameraValue2 + 1;
        } else cameraValue2 = cameraValue2;
        if (hasGPS2) {
            cameraValue2 = cameraValue2 + 1;
        } else cameraValue2 = cameraValue2;
        if (hasZoom2) {
            cameraValue2 = cameraValue2 + 1;
        } else cameraValue2 = cameraValue2;
        if (condition2.equals("New")) {
            cameraValue2 = cameraValue2 + 2;
        } else if (condition2.equals("Refurbished")) {
            cameraValue2 = cameraValue2 + 1;
        } else cameraValue2 = cameraValue2;
        // HINT: compute Camera 1's value, then Camera 2



        /**
         * Part 3 -- Camera Comparison
         */

        //Camera Comparison
        String cameraEqual = " Camera 1 and Camera 2 are equal.";
        String camera1 = " Camera 1 is better than Camera 2!";
        String camera2 = " Camera 2 is better than Camera 1!";
        String cameraFinal = "";
        double priceFinal = Double.compare(price1, price2);
        double ratio1 = (price1 / cameraValue1);
        double ratio2 = (price2 / cameraValue2);
        double ratioFinal = Double.compare(ratio1, ratio2);
        if ((cameraValue1 == cameraValue2) && (userRating1 == userRating2) && (priceFinal == 0.00)) {
            cameraFinal = cameraEqual;
        } else if ((cameraValue1 > cameraValue2) && (userRating1 == userRating2) && (priceFinal == 0.00)) {
            cameraFinal = camera1;
        } else if ((cameraValue1 == cameraValue2) && (userRating1 > userRating2) && (priceFinal == 0.00)) {
            cameraFinal = camera1;
        } else if ((cameraValue1 == cameraValue2) && (userRating1 == userRating2) && (priceFinal < 0.00)) {
            cameraFinal = camera1;
        } else if ((cameraValue1 != cameraValue2) && (userRating1 != userRating2) && (ratio1 != ratio2)) {
            if (ratio1 < ratio2) {
                cameraFinal = camera1; 
            } else cameraFinal = camera2;
        } else if ((cameraValue1 != cameraValue2) && (userRating1 != userRating2) && (ratio1 == ratio2)) {
            cameraFinal = cameraEqual;
        } else if ((cameraValue1 != cameraValue2) && (userRating1 == userRating2) && (ratio1 != ratio2)) {
            if (ratio1 < ratio2) {
                cameraFinal = camera1;
            } else cameraFinal = camera2;
        } else if ((cameraValue1 == cameraValue2) && ((userRating1 != userRating2)) && (ratio1 != ratio2)) {
            if (ratio1 < ratio2) {
                cameraFinal = camera1;
            } else cameraFinal = camera2;
        } else cameraFinal = camera2;


        /**
         * Part 4 -- Comparison Results
         *
         * DO NOT REMOVE THESE LINES
         */
        System.out.println("======================");
        System.out.println("Result of Comparison:" + cameraFinal);



        // DO NOT USE System.Exit!
    }

}
