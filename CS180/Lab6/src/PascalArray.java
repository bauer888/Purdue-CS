import java.util.Scanner;

    /**
     * This program will create Pascal's Triangle up to a given number of rows.
     *
     * @author Jack Bauer, bauer88@purdue.edu
     *
     * @version 02/13/2019
     *
     */

    public class PascalArray {
        public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            int rows;
            while (true) {
                System.out.println("How many rows do you want?");
                rows = s.nextInt();
                if (rows < 1) {
                    System.out.println("Please enter an integer greater than or equal to 1. How many rows do you want?");
                    rows = s.nextInt();
                }
                int[][] triangle = new int[rows + 1][];
                triangle[1] = new int[1 + 2];
                triangle[1][1] = 1;
                for (int x = 2; x <= rows; x++) {
                    triangle[x] = new int[x + 2];
                    for (int y = 1; y < triangle[x].length - 1; y++) {
                        triangle[x][y] = triangle[x - 1][y - 1] + triangle[x - 1][y];
                    }
                }
                for (int x = 1; x <= rows; x++) {
                    for (int y = 1; y < triangle[x].length - 1; y++) {
                        System.out.print(triangle[x][y] + " ");
                    }
                    System.out.println();
                }
                System.out.println("Do you want to generate another Pascal's Triangle?");
                String str = s.next();
                if (str.equals("n")) {
                    break;
                } else {
                    while(!str.equals("y") && !str.equals("n")) {
                        System.out.println("ERROR: input only 'y' or 'n'. Do you want to generate another Pascal's " +
                                "Triangle? (y/n)");
                    }
                }
            }
        }
    }
