import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class Recommender{

    /********************************
     * Do not change below code
     ********************************/

    int swaps, compares;
    int[] inversionCounts;
    String[] products;

    public Recommender(){
        swaps = 0;
        compares = 0;
    }

    public int getComapares() {
        return compares;
    }

    public int getSwaps() {
        return swaps;
    }
    /**************
     * This function is for the quick sort.
     **************/
    private boolean compare(int a ,int b){
        compares++;
        return a <= b;

    }
    /***************
     * This functions is for the quick sort.
     * By using this function, swap the similarity and the products at the same time.
     *****************/
    private void swap(int[] arr, int index1, int index2){
        swaps++;
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;

        String tempS = products[index1];
        products[index1] = products[index2];
        products[index2] = tempS;

    }

    /********************************
     * Do not change above code
     ********************************/

    /**
     * This function is for the calculate inversion counts of each option's.
     * @param dataset is file name of all data for hash table
     * @param options is the list of product name which we want to getting the inversion counts
     * @return it is integer array of each option's inversion counts. The order of return should be matched with options.
     */
    public int[] inversionCounts(String dataset, String[] options) {
        HashTable hashTable = new HashTable();
        inversionCounts = new int[options.length];
        try {
            hashTable.load(dataset);
            HashTable.Pair pair;
            int[] departmentRating;
            for (int i = 0; i < options.length; i++) {
                pair = hashTable.get(options[i]);
                //departmentRating = hashTable.get(options[i]).value.depRating;
                departmentRating = pair.value.depRating;
                inversionCounts[i] = mergeSort(departmentRating, 0, departmentRating.length - 1);
                //System.out.println(inversionCounts[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO

        return inversionCounts;
    }

    public int merge(int[] deptRating, int leftIndex, int rightIndex, int middleIndex) {
        int[] left = new int[middleIndex - leftIndex + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = deptRating[leftIndex + i];
        }
        int[] right = new int[rightIndex - middleIndex];
        for (int i = 0; i < right.length; i++) {
            right[i] = deptRating[middleIndex + 1 + i];
        }
        int firstIndex = 0;
        int secondIndex = 0;
        int finalIndex = leftIndex;
        int result = 0;
        while (firstIndex < left.length && secondIndex < right.length) {
            if (left[firstIndex] <= right[secondIndex]) {
                deptRating[finalIndex] = left[firstIndex];
                finalIndex++;
                firstIndex++;
            } else {
                deptRating[finalIndex] = right[secondIndex];
                finalIndex++;
                secondIndex++;
                result += ((middleIndex + 1) - (leftIndex + firstIndex));
            }
        }
        while (firstIndex < left.length) {
            deptRating[finalIndex] = left[firstIndex];
            finalIndex++;
            firstIndex++;
        }
        while (secondIndex < right.length) {
            deptRating[finalIndex] = right[secondIndex];
            finalIndex++;
            secondIndex++;
        }

        return result;
    }

    public int mergeSort(int[] deptRating, int leftIndex, int rightIndex) {
        int result = 0;
        if (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            result += mergeSort(deptRating, leftIndex, middleIndex);
            result += mergeSort(deptRating, middleIndex + 1, rightIndex);
            result += merge(deptRating, leftIndex, rightIndex, middleIndex);
        }
        return result;
    }

    /**
     * Get the sequence of recommendation from the dataset by sorting the inverse count.
     * Compare the similarity of depRating between RecentPurchase's and each option's.
     * Use inverse count to get the similarity of two array.
     * */
    public String[] recommend(String dataset, String recentPurchase, String[] options) {
        products = options.clone();
        String[] recent = {recentPurchase};
        int[] intRecent = inversionCounts(dataset, recent);
        int[] intOptions = inversionCounts(dataset, options);
        int[] difference = new int[options.length];
        for (int i = 0; i < options.length; i++) {
            difference[i] = Math.abs(intRecent[0] - intOptions[i]);
        }
        quickSort(difference, 0, difference.length - 1);
        // TODO

        return products;
    }

    public int pivotFunction(int[] inversionDiffs, int firstIndex, int lastIndex) {
        int pivot = inversionDiffs[lastIndex];
        int smallElement = (firstIndex - 1);
        for (int i = firstIndex; i < lastIndex; i++) {
            if (compare(inversionDiffs[i], pivot)) {
                smallElement++;
                swap(inversionDiffs, smallElement, i);
            }
        }
        swap(inversionDiffs, smallElement + 1, lastIndex);
        return smallElement + 1;
    }

    public void quickSort(int[] inversionDiffs, int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {
            int pivot = pivotFunction(inversionDiffs, firstIndex, lastIndex);
            quickSort(inversionDiffs, firstIndex, pivot - 1);
            quickSort(inversionDiffs, pivot + 1, lastIndex);
        }
    }
//    public static void main(String[] args) {
//        Recommender r = new Recommender();
//        String[] options = {"pPhone1", "kPhone2", "tPhone3", "dPhone3", "bPhone2", "ePhone3", "gPhone1", "nPhone2"};
//        r.inversionCounts("test.txt", options);
//    }
}
