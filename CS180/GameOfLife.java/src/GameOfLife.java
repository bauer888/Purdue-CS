import java.util.Scanner;

/**
 *
 * This program will simulate the Game of Life.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 02/20/2019
 *
 */
public class GameOfLife {
    public static void main(String[] args) {
        int generation = 0;
        boolean yes = true;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of rows/columns:");
        int rows = s.nextInt();
        System.out.println();
        int[][] matrix = new int[rows + 2][rows + 2];
        int[][] compMatrix = new int[rows + 2][rows + 2];
        int count = 0;

        for (int i = 1; i < rows + 1; i++) {
            String nextRow = s.next();
            int col = 1;
            for (int j = 0; j < nextRow.length(); j += 2) {
                matrix[i][col] = Integer.parseInt(nextRow.substring(j, j + 1));
                col++;
            }
        }

        System.out.println();
        System.out.println("Generation 0: ");


        do  {

            compMatrix = new int[rows + 2][rows + 2];
            generation++;

            for (int x = 1; x < rows + 1; x++) {
                System.out.println();
                for (int y = 1; y < rows + 1; y++) {
                    count = matrix[x - 1][y - 1] + matrix[x - 1][y] + matrix[x][y - 1] + matrix[x][y + 1] +
                            matrix[x + 1][y] + matrix[x + 1][y + 1] + matrix[x + 1][y - 1] + matrix[x - 1][y + 1];
                    compMatrix[x][y] = count;
                    System.out.print(matrix[x][y] + " ");

                }
            }
            for (int h = 1; h < rows + 1; h++) {
                for (int k = 1; k < rows + 1; k++) {
                    if (matrix[h][k] == 1) {
                        if (compMatrix[h][k] > 3 || compMatrix[h][k] < 2) {
                            matrix[h][k] = 0;
                        } else if (compMatrix[h][k] == 2 || compMatrix[h][k] == 3) {
                            matrix[h][k] = 1;
                        } else {
                            matrix[h][k] = 1;
                        }
                    }
                    if (matrix[h][k] == 0) {
                        if (compMatrix[h][k] == 3) {
                            matrix[h][k] = 1;
                        } else if (compMatrix[h][k] != 3) {
                            matrix[h][k] = 0;
                        }
                    }
                }
            }
            System.out.println();
            System.out.println();
            String response = s.next();
            if (response.equals("Q") || response.equals("q")) {
                yes = false;
            } else {
                System.out.println();
                yes = true;
                System.out.printf("Generation %d: \n", generation);
            }
        }
        while (yes);

        System.out.println("Program Terminated");
    }
}
