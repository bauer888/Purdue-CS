import java.util.ArrayList;
import java.util.Scanner;

public class Mean {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean restart = true;
        double first;
        System.out.println("Welcome to the Mean and Range Calculator!");
        ArrayList<Double> numbers = new ArrayList<Double>();
        do {
            boolean restart2 = true;
            while (restart2) {
                System.out.print("Please enter your number: ");
                String response = s.nextLine();
                char[] chars = response.toCharArray();
                boolean digit = true;
                for (int i = 0; i < chars.length; i++) {
                    if (!Character.isDigit(chars[i]) && !(chars[i] == '.')) {
                        System.out.println("Numbers only!");
                        digit = false;
                        break;
                    } else {
                        digit = true;
                    }
                }
                while (digit) {
                    first = Double.parseDouble(response);
                    numbers.add(first);
                    digit = false;
                    restart2 = false;
                }

//                if (s.hasNextInt()) {
//                    first = s.nextInt();
//                    numbers.add(first);
//                    restart2 = false;
//                } else {
//                    System.out.println("Numbers Only!");
//                    restart2 = true;
//                }
            }
            boolean repeat = true;
            while (repeat) {
                System.out.print("Do you wish to enter another number?(y/n): ");
                String next = s.nextLine();
                if (next.equalsIgnoreCase("y")) {
                    repeat = false;
                    restart = true;
                } else if (next.equalsIgnoreCase("n")) {
                    repeat = false;
                    restart = false;
                } else {
                    System.out.println("Invalid Option.");
                    repeat = true;
                }
            }

        } while (restart);
        double sum = 0;
        double num = 0;
        double mean = 0;
        double range = 0;
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
            if (numbers.get(i) > max) {
                max = numbers.get(i);
            }
            if (numbers.get(i) < min) {
                min = numbers.get(i);
            }
        }
        num = numbers.size();
        mean = sum / num;
        range = max - min;
        System.out.println("############################################");
        System.out.printf("Sum: %.2f\n", sum);
        System.out.printf("Num: %.0f\n", num);
        System.out.printf("Mean: %.2f\n", mean);
        System.out.printf("Range: %.2f\n", range);
        System.out.println("Thank you for using the Mean and Range Calculator!");

    }
}
