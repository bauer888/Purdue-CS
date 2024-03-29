import javax.swing.table.TableModel;
import java.io.*;
import java.util.*;
import java.util.Scanner;

/**
 *
 * This program will manipulate a set of data based on different requirements.
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version 03/01/22019
 *
 */


public class Preprocessing {

    /** YOU ARE NOT REQUIRED TO CHANGE THIS LINE. ENSURE THE FILE survey_data.csv IS LOCATED AT THE ROOT OF YOUR PROJECT
     *  FOR EXAMPLE, IF YOU ARE USING INTELLIJ AND YOUR PROJECT IS CALLED Project2, PLACE IT IN THE FOLDER Project2
     * (NOT THE src FOLDER)
     */
    private static String fileName = "survey_data.csv";
    // DO NOT REMOVE THESE LINES. THEY ARE USED FOR TESTING YOUR PROJECT.
    private static Double[][] cleanedData;
    private static Double[][] transformedData;
    private static Double[][] reducedData;
    private static int[][] preprocessedData;

    // THE NEXT TWO DECLARATIONS ARE A 2D ARRAY CONTAINING EVERY ROW AND COLUMN (EXCEPT THE FIRST COLUMN - DEGREE -
    // HELD WITHIN THE 1D ARRAY degreeColumn.
    private static Double[][] dataSet;
    private static String[] degreeColumn;


    public static void main(String[] args) {
        // DO NOT CHANGE THIS CALL OR THE DATA SET WILL NOT BE LOADED!
        loadData();

        // Call printData() whenever you want the data set in its current state to be printed.
        printData();

        /* TASK 1 - DATA CLEANING
         * PUT YOUR CODE FOR TASK 1 BELOW. */
        double[] colSum = new double[5];
        double[] denom = new double[5];
        for (int x = 0; x < dataSet.length; x++) {
            for (int y = 0; y < dataSet[x].length; y++) {
                if (dataSet[x][y] != null) {
                    denom[y] += 1;
                    colSum[y] += dataSet[x][y];
                }
            }
        }
        for (int x = 0; x < dataSet.length; x++) {
            for (int y = 0; y < dataSet[x].length; y++) {
                if (dataSet[x][y] == null) {
                    dataSet[x][y] = colSum[y] / denom[y];
                }
            }
        }
        printData();





        /* END OF CODE FOR TASK 1 */

        // DO NOT REMOVE THIS METHOD CALL! IT IS USED FOR TESTING YOUR RESULTS FROM TASK 1.
        storeCleanedData(dataSet);

        /* TASK 2 - DATA TRANSFORMATION
         * PUT YOUR CODE FOR TASK 2 BELOW. */
        for (int i = 0; i < dataSet[0].length; i++) {
            double max = dataSet[0][i];
            double min = dataSet[0][i];
            for (int j = 0; j < dataSet.length; j++) {
                double v = dataSet[j][i];
                if (dataSet[j][i] > max) {
                    max = v;
                }
                if (dataSet[j][i] < min) {
                    min = v;
                }
            }
            for (int j = 0; j < dataSet.length; j++) {
                double v = dataSet[j][i];
                dataSet[j][i] = ((v - min) / (max - min));
            }
        }
        printData();





        /* END OF CODE FOR TASK 2 */

        // DO NOT REMOVE THIS METHOD CALL! IT IS USED FOR TESTING YOUR RESULTS FROM TASK 2.
        storeTransformedData(dataSet);

        /* TASK 3 - DATA REDUCTION
         * PUT YOUR CODE FOR TASK 3 BELOW. */
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of bins: ");
        double bins = s.nextDouble();
        double b = 1.0 / bins;
        for (int i = 0; i < dataSet.length; i++) {
            for (int j = 0; j < dataSet[i].length; j++) {
                for (double k = 1.0; k <= bins; k++) {
                    if (dataSet[i][j] <= b) {
                        dataSet[i][j] = 0.0;
                        break;
                    } else if (dataSet[i][j] > (b * k) && dataSet[i][j] <= (b * (k + 1.0))) {
                        dataSet[i][j] = (k);
                        break;
                    }
                }
            }
        }
        printData();



        /* END OF CODE FOR TASK 3 */

        // DO NOT REMOVE THIS METHOD CALL! IT IS USED FOR TESTING YOUR RESULTS FROM TASK 3.
        storeReducedData(dataSet);

        /* TASK 4 - LABEL ENCODING
         * PUT YOUR CODE FOR TASK 4 BELOW. */
        int max2 = 0;
        int[] colNum = new int[degreeColumn.length];
        for (int x = 0; x < degreeColumn.length; x++) {
            degreeColumn[x] = degreeColumn[x].toLowerCase();
            max2 = degreeColumn[x].length();
            int ascii = 0;
            for (int y = 0; y < max2; y++) {
                int c = (int) degreeColumn[x].charAt(y);
                ascii += c;
                colNum[x] = ascii;
            }
        }


        /* END OF CODE FOR TASK 4 */

        /* TASK 5 - PUTTING IT ALL TOGETHER
         * PUT YOUR CODE FOR TASK 5 BELOW. */
        int[][] intArray = new int[dataSet.length][6];
        for (int x = 0; x < dataSet.length; x++) {
            for (int y = 0; y < dataSet[x].length; y++) {
                intArray[x][y + 1] = dataSet[x][y].intValue();
                intArray[x][0] = colNum[x];
            }
        }
        doMachineLearning(intArray);





        /* END OF CODE FOR TASK 5 */

    }

    /**
     * Method printData
     * Pretty-prints table made up of values from dataSet and degreeColumn.
     *
     * Usage: Simply call printData() in your code whenever you would like the table to be printed.
     *
     * Note: You do not need to edit this method, but if you wish to edit it to print your own values, your grade will
     * not be affected.
     */
    private static void printData() {
        String[] headers = {"Age", "CS180_Grade", "GPA", "Credit_Hours", "Months_Until_Employment"};
        System.out.printf("| %-40s |", "Degree");
        for (String header : headers) {
            System.out.printf(" %-23s |", header);
        }
        System.out.println();
        for (int i = 0; i < 174; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i < dataSet.length; i++) {
            System.out.printf("| %-40s |", degreeColumn[i]);
            for (int j = 0; j < dataSet[i].length; j++) {
                System.out.printf(" %-23s |", dataSet[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Method loadData
     * Loads the file fileName from disk
     */
    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            ArrayList<Double[]> data = new ArrayList<>(); // ArrayList used for data sets of different length.
            ArrayList<String> stringColumn = new ArrayList<>();

            String line = br.readLine(); // Ignore Headers
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",", -1);
                stringColumn.add(row[0]);
                Double[] restOfRow = new Double[row.length - 1];
                for (int i = 1; i < row.length; i++) {
                    try {
                        restOfRow[i - 1] = Double.parseDouble(row[i]);
                    } catch (NumberFormatException e) { // Missing Value
                        restOfRow[i - 1] = null;
                    }
                }
                data.add(restOfRow);
            }
            dataSet = new Double[data.size()][data.get(0).length];
            for (int i = 0; i < data.size(); i++) {
                dataSet[i] = data.get(i);
            }
            degreeColumn = new String[stringColumn.size()];
            for (int i = 0; i < stringColumn.size(); i++) {
                degreeColumn[i] = stringColumn.get(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error Loading File. Ensure survey_data.csv " +
                    "is located within the same folder as this file.");
        } catch (IOException e) {
            System.out.println("Error While Parsing Data From CSV:");
            e.printStackTrace();
        }
    }

    /**
     * Method doMachineLearning
     * Does some very complicated machine learning with the data you have preprocessed!
     * WARNING: DO NOT TOUCH THIS CODE!
     * @param preprocessedDataSet A 2D array of preprocessed data.
     */
    private static void doMachineLearning(int[][] preprocessedDataSet) {
        System.out.println("The Machine is Learning...");
        System.out.println();
        String[] headers = {"Degree", "Age", "CS180_Grade", "GPA", "Credit_Hours", "Months_Until_Employment"};
        System.out.print("|");
        for (String header : headers) {
            System.out.printf(" %-23s |", header);
        }
        System.out.println();
        for (int i = 0; i < 157; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int[] row : preprocessedDataSet) {
            for (int value : row) {
                System.out.printf(" %-23s |", value);
            }
            System.out.println();
        }
        System.out.println("The Machine has Learned!");
        Preprocessing.preprocessedData = preprocessedDataSet;
    }

    /**
     * Method storeCleanedData
     * Stores the provided data set in the 2D array cleanedData.
     * Used for testing.
     * @param cleanedDataSet The data set to store.
     */
    private static void storeCleanedData(Double[][] cleanedDataSet) {
        cleanedData = new Double[cleanedDataSet.length][cleanedDataSet[0].length];
        for (int i = 0; i < cleanedDataSet.length; i++) {
            if (cleanedDataSet[i].length >= 0) System.arraycopy(cleanedDataSet[i], 0, cleanedData[i], 0,
                    cleanedDataSet[i].length);
        }
    }

    /**
     * Method storeReducedData
     * Stores the provided data set in the 2D array reducedData.
     * Used for testing.
     * @param reducedDataSet The data set to store.
     */
    private static void storeReducedData(Double[][] reducedDataSet) {
        reducedData = new Double[reducedDataSet.length][reducedDataSet[0].length];
        for (int i = 0; i < reducedDataSet.length; i++) {
            if (reducedDataSet[i].length >= 0) System.arraycopy(reducedDataSet[i], 0, reducedData[i], 0,
                    reducedDataSet[i].length);
        }
    }

    /**
     * Method storeTransformedData
     * Stores the provided data set in the 2D array transformedData.
     * Used for testing.
     * @param transformedDataSet The data set to store.
     */
    private static void storeTransformedData(Double[][] transformedDataSet) {
        transformedData = new Double[transformedDataSet.length][transformedDataSet[0].length];
        for (int i = 0; i < transformedDataSet.length; i++) {
            if (transformedDataSet[i].length >= 0) System.arraycopy(transformedDataSet[i], 0, transformedData[i], 0,
                    transformedDataSet[i].length);
        }
    }
}

