import java.io.File;
import java.io.IOException;
import java.util.*;

public class DijGraph {
    static int MAXWEIGHT = 10000000;//The weight of edge will not exceed this number
    private Node[] nodeArr;//The vertices set in the graph
    private int nodeCount;//number of total vertices
    private int edgeCount;//number of total edges
    public static int heapSize = 0;

    //Two option for the DijGraph constructor
    //Option 0 is used to build graph with for part 1: implementation for Dijkstra
    //Option 1 is used to build graph with for part 2: simple application of Dijkstra
    public DijGraph(String graph_file, int option)throws IOException{
        if (option == 0){
            File file = new File(graph_file);
            Scanner sc = new Scanner(file);
            nodeCount = sc.nextInt();
            edgeCount = sc.nextInt();
            nodeArr = new Node[nodeCount + 1];
            for(int i =0; i < nodeCount + 1; i ++){
                nodeArr[i]= new Node(i);
            }
            for(int i = 0;i < edgeCount; i ++){
                int begin = sc.nextInt();
                int end = sc.nextInt();
                int weight = sc.nextInt();
                nodeArr[begin].addEdge(end, weight);
                nodeArr[end].addEdge(begin,weight);
            }
        }
        else if (option == 1){
            File file = new File(graph_file);
            Scanner sc = new Scanner(file);
            nodeCount = sc.nextInt();
            edgeCount = sc.nextInt();
            nodeArr = new Node[nodeCount + 1];
            for(int i =0; i < nodeCount + 1; i ++){
                if(i != 0){
                    nodeArr[i]= new Node(i, sc.next());
                }
            }
            for(int i = 0;i < edgeCount; i ++){
                String begin = sc.next();
                String end = sc.next();
                int weight = sc.nextInt();
                Node beginNode = findByName(begin);
                Node endNode = findByName(end);
                beginNode.addEdge(endNode.getNodeNumber(), weight);
                endNode.addEdge(beginNode.getNodeNumber(),weight);
            }
        }

    }

    //Finding the single source shortest distances by implementing dijkstra.
    //Using min heap to find the next smallest target
    //
    public  Dist[] dijkstra(int source) {
        Dist[] result = new Dist[nodeCount + 1];
        //minheap array add everything else that isnt source to array
        Dist[] minHeap = new Dist[nodeCount];
        //result[source] = new Dist(source, 0);
        //insert(result, new Dist(source, 0), source);
        heapSize = nodeCount;
        //heapSize = 1;
        for (int i = 1; i < result.length; i++) {
            if (i == source) {
                result[source] = new Dist(source, 0);
                insert(minHeap, new Dist(source, 0), source - 1);
            } else {
                Dist dist = new Dist(i, MAXWEIGHT);
                result[i] = dist;
                //insert(result, new Dist(i, MAXWEIGHT), i);
                insert(minHeap, dist, i - 1);
            }
           // heapSize++;
        }
        //iterate through hashmap and get keys: vertex, distance from source
        while (heapSize > 0) {
            //System.out.println();
            Dist u = extractMin(minHeap, heapSize);
            int nodeNumber = u.getNodeNumber();
            //int distance = result[nodeNum].getDist();
            //TA helped me with the below code
            HashMap<Integer, Integer> map = nodeArr[nodeNumber].getEdges();
            for (int i = 0; i < heapSize; i++) {
                int s = minHeap[i].getNodeNumber();
                if (map.containsKey(s)) {
                    int alt = u.getDist() + map.get(s);
                    if (alt < result[s].getDist()) {
                        minHeap[i].updateDist(alt);
                        result[s].updateDist(alt);
                        insert(minHeap, minHeap[i], i);
                    }
                }
            }
        }
        return result;
    }

    //Find the vertex by the location name
    public Node findByName(String name){
        for (int x =1; x < nodeCount + 1; x++){
            if(nodeArr[x].getLocation().equals(name)){
                return nodeArr[x];
            }
        }
        return null;
    }

    //Implement insertion in min heap
    //first insert the element to the end of the heap
    //then swim up the element if necessary
    //Set it as static as always
    public static void insert(Dist [] arr, Dist value, int index) {
        if (index >= arr.length) {
            return;
        }
        arr[index] = value;
        //int i = 0;
        while (value.compareTo(arr[(index - 1) / 2]) == -1) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
            //i++;
        }
        //heapSize++;

        //TODO
    }
    //to reference parent node: index / 2

    public static void swap(Dist []arr, int index1, int index2){
        Dist temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    //Extract the minimum element in the min heap
    //replace the last element with the root
    //then do minheapify
    //Set it as static as always
    public static Dist extractMin (Dist[] arr, int size) {
        heapSize = size - 1;
        Dist result = arr[0];
        arr[0] = arr[heapSize];
        arr[heapSize] = null;
        //size--;
        minHeapify(arr, 0, heapSize);
        //TODO
        return result;
    }
    //check if element at index is bigger than right or left child.
    //check if right is smaller than left and swap right and recursive call right
    //else swap left and recursive call left
    public static void minHeapify(Dist[] arr, int index, int size) {
        int first = index;
        if ((2 * index + 1) < size && (arr[first].compareTo(arr[2 * index + 1]) > 0)) {
            first = (2 * index + 1);
        }
        if ((2 * index + 2) < size && (arr[first].compareTo(arr[2 * index + 2]) > 0)) {
            first = (2 * index + 2);
        }
        if (first != index) {
            swap(arr, index, first);
            minHeapify(arr, first, size);
        }
//        if (!(index <= heapSize && index >= (heapSize / 2))) {
//            if (arr[index].compareTo(arr[(index * 2) + 1]) == 1 || arr[index].compareTo(arr[index * 2]) == 1) {
//                if (arr[(index * 2) + 1].compareTo(arr[index * 2]) == -1) {
//                    swap(arr, index, (index * 2) + 1);
//                    minHeapify(arr, (index * 2) + 1);
//                } else if (arr[(index * 2) + 1].compareTo(arr[index * 2]) == 1) {
//                    swap(arr, index, index * 2);
//                    minHeapify(arr, index * 2);
//                } else {
//
//                }
//            }
//        }
    }
    //to reference right child: (index * 2) + 1
    //to reference left child: index * 2

    //This will print the shortest distance result
    //The output format will be what we expect to pass the test cases
    public static void printResult(Dist[] result, int source){
        for(int x = 1;  x < result.length; x++){
            if(x != source){
                System.out.println(result[x].getNodeNumber() + " " +result[x].getDist());
            }
        }
    }

    public static void main(String[] args)throws IOException {
        DijGraph graph = new DijGraph(args[0], 0);
        Dist[] result  = graph.dijkstra(7);
        printResult(result, 7);
    }
}
