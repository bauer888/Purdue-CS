import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DataAnalyzer {
    public static void main(String[] args) {
        boolean result = true;
        while (result) {
            boolean obs = true;
            String s = "";
            while (obs) {
                s = JOptionPane.showInputDialog(null, "Enter the number of observations: ",
                        "Observations", JOptionPane.PLAIN_MESSAGE);
                if (Integer.parseInt(s) <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    obs = true;
                } else {
                    obs = false;
                }
            }

            ArrayList<Double> list = new ArrayList<>(0);
            for (int i = 1; i <= Integer.parseInt(s); i++) {
                String num = JOptionPane.showInputDialog(null, "Enter Observation " + i,
                        "Observation " + i, JOptionPane.QUESTION_MESSAGE);
                list.add(Double.parseDouble(num));
            }

            boolean set = true;
            while (set) {
                String[] operations = {"Sum", "Mean", "Median", "Max", "Min"};
                int n = JOptionPane.showOptionDialog(null, "Choose the type of Analysis", "Data Analyzer",
                        JOptionPane.YES_NO_CANCEL_OPTION, 0, null, operations, operations[0]);
                switch (n) {
                    case 0:
                        double sum = 0;
                        for (int i = 0; i < list.size(); i++) {
                            sum += list.get(i);
                        }
                        JOptionPane.showMessageDialog(null, "The sum is: " + sum, "Sum",
                                JOptionPane.PLAIN_MESSAGE);
                        break;
                    case 1:
                        double sum2 = 0;
                        double mean = 0;
                        for (int i = 0; i < list.size(); i++) {
                            sum2 += list.get(i);
                        }
                        mean = sum2 / list.size();
                        JOptionPane.showMessageDialog(null, "The mean is: " + mean, "Mean",
                                JOptionPane.PLAIN_MESSAGE);
                        break;
                    case 2:
                        double median = 0;
                        Collections.sort(list);
                        if (list.size() == 1) {
                            JOptionPane.showMessageDialog(null, "The median is: " + list.get(0),
                                    "Median", JOptionPane.PLAIN_MESSAGE);
                            break;
                        } else if (list.size() % 2 == 0) {
                            median = (list.size() + 1) / list.get(1);
                        } else if (list.size() % 2 != 0) {
                            median = (((list.size() / list.get(1)) + (list.size() / list.get(2))) / 2);
                        }
                        JOptionPane.showMessageDialog(null, "The median is: " + median, "Median",
                                JOptionPane.PLAIN_MESSAGE);
                        break;
                    case 3:
                        double max = Double.MIN_VALUE;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i) >= max) {
                                max = list.get(i);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "The max is: " + max, "Max",
                                JOptionPane.PLAIN_MESSAGE);
                        break;
                    case 4:
                        double min = Double.MAX_VALUE;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i) <= min) {
                                min = list.get(i);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "The min is: " + min, "Min",
                                JOptionPane.PLAIN_MESSAGE);
                        break;
                }
                int option = JOptionPane.showConfirmDialog(null, "Would you like to " +
                        "analyze this set of data again?", "Data", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == 0) {
                    set = true;
                } else {
                    set = false;
                }
            }
            int end = JOptionPane.showConfirmDialog(null, "Would you like to analyze " +
                    "another set of data?", "Repeat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (end == 0) {
                result = true;
            } else {
                JOptionPane.showMessageDialog(null, "Thank you for using the Data Analyzer!",
                        "End", JOptionPane.PLAIN_MESSAGE);
                result = false;
            }
        }
    }
}
