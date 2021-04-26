import java.util.Scanner;

/**
 *
 * This program will keep a running count of the dealer's hand and the user's hand.
 *
 * @author Jack Bauer bauer88@purdue.edu
 *
 * @version 02/11/2019
 *
 */
public class CardCounter {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int runningCount = 0;

        for (int z = 0; z < 50; z++) {
            if (z < 1) {
                System.out.println("Welcome to the Card Counter Program!");
                System.out.println();
            }



            System.out.println("Enter Your Hand: ");
            if (z > 0) {
                s.nextLine();
            }
            String user = s.nextLine();
            for (int x = 0; x < user.length(); x++) {
                if (user.charAt(x) == '2') {
                    runningCount = runningCount + 1;
                } else if (user.charAt(x) == '3') {
                    runningCount = runningCount + 1;
                } else if (user.charAt(x) == '4') {
                    runningCount = runningCount + 1;
                } else if (user.charAt(x) == '5') {
                    runningCount = runningCount + 1;
                } else if (user.charAt(x) == '6') {
                    runningCount = runningCount + 1;
                } else if (user.charAt(x) == '7') {
                    runningCount = runningCount;
                } else if (user.charAt(x) == '8') {
                    runningCount = runningCount;
                } else if (user.charAt(x) == '9') {
                    runningCount = runningCount;
                } else if (user.charAt(x) == '1') {
                    runningCount = runningCount - 1;
                } else if (user.charAt(x) == '0') {
                    runningCount = runningCount;
                } else if (user.charAt(x) == 'J') {
                    runningCount = runningCount - 1;
                } else if (user.charAt(x) == 'Q') {
                    runningCount = runningCount - 1;
                } else if (user.charAt(x) == 'K') {
                    runningCount = runningCount - 1;
                } else if (user.charAt(x) == 'A') {
                    runningCount = runningCount - 1;
                } else if (user.charAt(x) == ',') {
                    runningCount = runningCount;
                } else runningCount = runningCount;
            }




            System.out.println("Enter Dealers Hand: ");
            String dealer = s.nextLine();
            for (int y = 0; y < dealer.length(); y++) {
                if (dealer.charAt(y) == '2') {
                    runningCount = runningCount + 1;
                } else if (dealer.charAt(y) == '3') {
                    runningCount = runningCount + 1;
                } else if (dealer.charAt(y) == '4') {
                    runningCount = runningCount + 1;
                } else if (dealer.charAt(y) == '5') {
                    runningCount = runningCount + 1;
                } else if (dealer.charAt(y) == '6') {
                    runningCount = runningCount + 1;
                } else if (dealer.charAt(y) == '7') {
                    runningCount = runningCount;
                } else if (dealer.charAt(y) == '8') {
                    runningCount = runningCount;
                } else if (dealer.charAt(y) == '9') {
                    runningCount = runningCount;
                } else if (dealer.charAt(y) == '1') {
                    runningCount = runningCount - 1;
                } else if (dealer.charAt(y) == '0') {
                    runningCount = runningCount;
                } else if (dealer.charAt(y) == 'J') {
                    runningCount = runningCount - 1;
                } else if (dealer.charAt(y) == 'Q') {
                    runningCount = runningCount - 1;
                } else if (dealer.charAt(y) == 'K') {
                    runningCount = runningCount - 1;
                } else if (dealer.charAt(y) == 'A') {
                    runningCount = runningCount - 1;
                } else if (dealer.charAt(y) == ',') {
                    runningCount = runningCount;
                }
            }



            System.out.println("Running Count = " + runningCount);
            System.out.println();

            System.out.println("Continue? (Y/N): ");
            String continue1 = s.next();
            if (continue1.equals("Y")) {
                continue;
            } else System.exit(0);
        }
    }
}
