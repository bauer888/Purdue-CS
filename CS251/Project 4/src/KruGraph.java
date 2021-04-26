import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KruGraph {
    private Vertex[] vertexArr;
    private ArrayList<MyEdge> edgeArr;
    private int vertexCount;
    private int edgeCount;

    //Implement the constructor for KruGraph
    //The format of the input file is the same as the format of the input file in Dijkstra
    public KruGraph(String graph_file)throws IOException{
        File file = new File(graph_file);
        Scanner sc = new Scanner(file);
        vertexCount = sc.nextInt();
        edgeCount = sc.nextInt();
        vertexArr = new Vertex[vertexCount + 1];
        for(int i = 1; i < vertexCount + 1; i ++){
            vertexArr[i] = new Vertex(i);
        }
        edgeArr = new ArrayList<>(edgeCount);
        for(int i = 0;i < edgeCount; i ++) {
            int begin = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            addEgde(begin, end, weight);
        }
        //TODO
    }

    //Could be a helper function
    private void addEgde(int from, int to, int weight) {
        MyEdge homie = new MyEdge(from, to, weight);
        edgeArr.add(homie);
        //TODO

    }


    //Implement Kruskal with weighted union find algorithm
    //look at pseudo code from slides
    public PriorityQueue<MyEdge> kruskalMST() {
        PriorityQueue<MyEdge> cyu = new PriorityQueue<>(edgeCount);
        PriorityQueue<MyEdge> trie = new PriorityQueue<>(vertexCount - 1);
        int i = 0;
        while (i < edgeCount) {
            cyu.add(edgeArr.get(i));
            i++;
        }
        while ((cyu.size() > 0) && trie.size() < (vertexCount - 1)) {
            MyEdge edge = cyu.poll();
            if (find(vertexArr[edge.getS()]) != find(vertexArr[edge.getD()])) {
                union(vertexArr[edge.getS()], vertexArr[edge.getD()]);
                trie.add(edge);
            }
        }
        //TODO
        return trie;
    }

    //Implement the recursion trick for the leaves to update the parent efficiently
    //Set it as static as always
    //recursive
    public static Vertex find(Vertex x){
        if (x.getParent() != x) {
            x.updateParent(find(x.getParent()));
        }
        //TODO
        return x.getParent();
    }


    //This function should union two vertices when an edge is added to the MST
    //Return true when the edge can be picked in the MST
    //Otherwise return false
    //Set it as static as always
    public static boolean union(Vertex x, Vertex y){
        Vertex xp = find(x);
        Vertex yp = find(y);
        if (xp == yp) {
            return false;
        } else if (xp.getSize() <= yp.getSize()) {
            xp.updateParent(yp);
            yp.updateSize(xp.getSize() + yp.getSize());
        } else {
            yp.updateParent(xp);
            xp.updateSize(xp.getSize() + yp.getSize());
        }
        //TODO
        return true;
    }

    //This is what we expect for the output format
    //The test cases will follow this format
    public static void printGraph(PriorityQueue<MyEdge> edgeList){
        int turn = edgeList.size();
        for (int i = 0; i < turn; i++) {
            MyEdge edge = edgeList.poll();
            int source = edge.getS();
            int dest = edge.getD();
            if(source > dest){
                int temp = source;
                source = dest;
                dest = temp;
            }
            System.out.println("from: " + source + " to: " + dest + " weight: " + edge.getWeight());
        }
    }
    public static int[] newStuff(int[] array, int x) {
        int[] result = {-1, -1};
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == x && counter == 0) {
                result[0] = i;
                result[1] = i;
                counter++;
            } else if (array[i] == x) {
                result[1] = i;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        int[] A = {-2 , 1, 1, 4, 4, 4, 6, 7, 9, 10};
        int x = 4;
        int[] s = newStuff(A, x);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
        }
    }

}
