import java.util.Scanner;

public class MatrixCalculator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the size of the first matrix (format row x col)");
        System.out.println();
        System.out.println("Enter rows");
        int rows1 = s.nextInt();
        System.out.println("Enter columns");
        int cols1 = s.nextInt();
        System.out.println();
        int[][] matrix1 = new int[rows1][cols1];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                System.out.println("Enter the number for row " + i + " and column " + j);
                int num = s.nextInt();
                matrix1[i][j] = num;
            }
        }
        System.out.println();
        System.out.println("Matrix 1 is: ");
        for (int i = 0; i < rows1; i++) {
            System.out.print("\n");
            for (int j = 0; j < cols1; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();

        System.out.println("Enter the size of the second matrix (format row x col)");
        System.out.println();
        System.out.println("Enter rows");
        int rows2 = s.nextInt();
        System.out.println("Enter columns");
        int cols2 = s.nextInt();
        System.out.println();


        int[][] matrix2 = new int[rows2][cols2];
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                System.out.println("Enter the number for row " + i + " and column " + j);
                int num = s.nextInt();
                matrix2[i][j] = num;
            }
        }

        System.out.println();
        System.out.println("Matrix 2 is: ");
        for (int i = 0; i < rows2; i++) {
            System.out.print("\n");
            for (int j = 0; j < cols2; j++) {
                System.out.print(matrix2[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();

        int[][] addMatrix = new int[rows2][cols2];
        if (rows1 != rows2 || cols1 != cols2) {
            System.out.println("Matrices size are not the same!");
        } else {
            for (int i = 0; i < rows2; i++) {
                for (int j = 0; j < cols2; j++) {
                    addMatrix[i][j] = (matrix1[i][j] + matrix2[i][j]);
                }
            }
            System.out.println("Result after addition");
            for (int i = 0; i < rows2; i++) {
                System.out.print("\n");
                for (int j = 0; j < cols2; j++) {
                    System.out.print(addMatrix[i][j] + " ");
                }
            }
        }
        System.out.println();
        System.out.println();



        int[][] multMatrix = new int[rows1][cols2];
        int sum = 0;
        if (cols1 != rows2) {
            System.out.println("Matrix size doesn't fit requirement!");
        } else {
            for (int i = 0; i < rows1; i++) {
                for (int j = 0; j < cols2; j++) {
                    for (int k = 0; k < rows2; k++) {
                        sum += (matrix1[i][k] * matrix2[k][j]);
                    }
                    multMatrix[i][j] = sum;
                    sum = 0;
                }
            }
            System.out.println("Result after multiplication");
            for (int i = 0; i < rows1; i++) {
                System.out.print("\n");
                for (int j = 0; j < cols2; j++) {
                    System.out.print(multMatrix[i][j] + " ");
                }
            }
        }
    }
}
