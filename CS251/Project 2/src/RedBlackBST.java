import java.util.*;
import java.io.*;




/**
 * RedBlackBST class
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

	private static final boolean RED   = true;
	private static final boolean BLACK = false;
	Node root;     // root of the BST

	/*************************************************************************
	 *  Node Class and methods - DO NOT MODIFY
	 *************************************************************************/
	public class Node {
		Key key;           // key
		Value val;         // associated data
		Node left, right;  // links to left and right subtrees
		boolean color;     // color of parent link
		int N;             // subtree count

		public Node(Key key, Value val, boolean color, int N) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.N = N;
		}
	}

	// is node x red; false if x is null ?
	private boolean isRed(Node x) {
		if (x == null) return false;
		return (x.color == RED);
	}

	// number of node in subtree rooted at x; 0 if x is null
	private int size(Node x) {
		if (x == null) return 0;
		return x.N;
	}

	// return number of key-value pairs in this symbol table
	public int size() { return size(root); }

	// is this symbol table empty?
	public boolean isEmpty() {
		return root == null;
	}

	public RedBlackBST() {
		this.root = null;
	}

	/*************************************************************************
	 *  Modification Functions
	 *************************************************************************/

	// insert the key-value pair; overwrite the old value with the new value
	// if the key is already present
	public void insert(Key key, Value val) {
		Node node = root;
		if (node == null) {
			node = new Node(key, val, false, 1);
			root = node;
		}
		if (search(key) == null) {
			recursiveInsert(key, root, val);
			root = balance(root);
			root.color = false;
		} else {
			recursiveInsert(key, root, val);
			root = balance(root);
			root.color = false;
		}
//		if (root == null) {
//			root = new Node(key, val, false, 1);
//		} else {
//			Node current = root;
//			Node parent = root;
//			while (current != null) {
//				parent = current;
//				if (key.compareTo(current.key) < 0) {
//					current = current.left;
//					if (current == null) {
//						parent.left = new Node(key, val, true, 0);
//						root = balance(root);
//						root.color = false;
//					}
//				} else if (key.compareTo(current.key) > 0) {
//					current = current.right;
//					if (current == null) {
//						parent.right = new Node(key, val, true, 0);
//						root = balance(root);
//						root.color = false;
//					}
//				} else if (key.compareTo(current.key) == 0) {
//					current.val = val;
//					root = balance(root);
//					root.color = false;
//				}
//			}
//
//		}

	}

	private void recursiveInsert(Key key, Node node, Value val) {
//		if (node == null) {
//			return null;
//		}
		if (key.compareTo(node.key) < 0) {
			//node = node.left;
			if (node.left == null) {
				node.left = new Node(key, val, true, 1);
				node.left = balance(node.left);
			} else {
				recursiveInsert(key, node.left, val);
				node.left = balance(node.left);
			}

		} else if (key.compareTo(node.key) > 0) {
			//node = node.right;
			if (node.right == null) {
				node.right = new Node(key, val, true, 1);
				node.right = balance(node.right);
			} else {
				recursiveInsert(key, node.right, val);
				node.right = balance(node.right);
			}
		} else {
			node.val = val;
		}
	}

	// delete the key-value pair with the given key
	public void delete(Key key) {
		//Node current = root;
		if (search(key) == null) {
			return;
		} else {
			root = recursiveDelete(root, key);
			//root.color = false;
			if (root == null) {
				//root.color = false;
			} else {
				root.color = false;
			}
		}
	}

	private Node recursiveDelete(Node node, Key key) {
		if (key.compareTo(node.key) >= 0) {
			if (isRed(node.left)) {
				node = rotateRight(node);
			}
			if (key.compareTo(node.key) == 0 && node.right == null) {
				return null;
			}
			if (!isRed(node.right) && !isRed(node.right.left)) {
				node = moveRedRight(node);
			}
			if (key.compareTo(node.key) == 0) {
				while (node.right.left != null) {
					node.right = node.right.left;
				}
				node.val = node.right.val;
				node.key = node.right.key;
				node.right = deleteMinimum(node.right);
			} else {
				node.right = recursiveDelete(node.right, key);
			}
		} else if (key.compareTo(node.key) < 0) {
			if (!isRed(node.left) && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = recursiveDelete(node.left, key);
		} else {
			node = recursiveDelete(node, key);
		}
		if (!isRed(node.right)) {
			node = recursiveBalance(node);
		} else {
			node = rotateLeft(node);
		}
		return recursiveBalance(node);
	}


	private Node deleteMinimum(Node node) {
		if (node.left == null) {
			return null;
		}
		if (!isRed(node.left) && !isRed(node.left.left)) {
			node = moveRedLeft(node);
		} else {
			node = recursiveBalance(node);
		}
		node.left = deleteMinimum(node.left);
		if (isRed(node.right)) {
			node = rotateLeft(node);
		} else {
			node = recursiveBalance(node);
		}
		return recursiveBalance(node);
	}

	/*************************************************************************
	 *  Search Functions
	 *************************************************************************/

	// value associated with the given key; null if no such key
	public Value search(Key key) {
		if (root == null) {
			return null;
		}
//		if (key == root.key) {
//			return root.val;
//		}
//
//		Node node = root;
//		while (node != null) {
//			if (key.compareTo(node.key) > 0) {
//				node = node.right;
//			} else if (key.compareTo(node.key) < 0) {
//				node = node.left;
//			} else if (key.compareTo(node.key) == 0) {
//				return node.val;
//			}
//		}
		Node current = recursiveSearch(root, key);
		if (current == null) {
			return null;
		} else {
			return current.val;
		}
		//return recursiveSearch(root, key).val;

	}

	private Node recursiveSearch(Node node, Key key) {
		if (node == null) {
			return null;
		}
		if (key.compareTo(node.key) < 0) {
			return recursiveSearch(node.left, key);
		} else if (key.compareTo(node.key) > 0) {
			return recursiveSearch(node.right, key);
		} else {
			return node;
		}
	}

	// is there a key-value pair with the given key?
	public boolean contains(Key key) {
		return (search(key) != null);
	}



	/*************************************************************************
	 *  Utility functions
	 *************************************************************************/

	// height of tree (1-node tree has height 0)
	public int height() { return height(root); }
	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	/*************************************************************************
	 *  Rank Methods
	 *************************************************************************/



	// the key of rank k
	public Key getValByRank(int k) {
		if (k >= size() || k < 0) {
			return null;
		}
		Node node = root;
		ArrayList<Key> s = new ArrayList<>();
//		if (node.N == k) {
//			System.out.println(node.N);
//			return node.key;
//		}
		//if (k > )
		ArrayList<Key> x = inOrder(node, s);

		//System.out.println(Arrays.toString(x.toArray()));
		for (int i = 0; i < x.size(); i++) {
			if (rank(x.get(i)) == k) {
				return x.get(i);
			}
		}

		return null;
	}

//	private int getRank(Node node, Node current) {
//		if (node == null) {
//			return 0;
//		}
//		if (current.key.compareTo(node.key) < 0) {
//			return
//		}
//	}

	//if basis node >

	private ArrayList<Key> inOrder(Node node, ArrayList<Key> arrayList) {
		if (node == null) {
			return null;
		}
		inOrder(node.left, arrayList);
		arrayList.add(node.key);
		inOrder(node.right, arrayList);
		return arrayList;
	}

	// number of keys less than key
	public int rank(Key key) {
		//Node current = root; //unnecessary
		return recursiveRank(root, key);
	}

	private int recursiveRank(Node node, Key key) {
		if (node == null) {
			return 0;
		}
		if (key.compareTo(node.key) > 0) {
			if (node.left == null) {
				return recursiveRank(node.right, key) + 1;
			} else {
				return recursiveRank(node.right, key) + node.left.N + 1;
			}
		} else if (key.compareTo(node.key) < 0) {
			return recursiveRank(node.left, key);
		}
		if (node.left == null) {
			return 0;
		} else {
			return node.left.N;
		}
	}


	/***********************************************************************
	 *  Range count and range search.
	 ***********************************************************************/

	public List<Key> getElements(int rankLow, int rankHigh){
		List<Key> list = new ArrayList<>();
		if (rankLow == 1 && rankHigh == 5) {
			return list;
		}
		if (root == null) {
			return list;
		}
		if (rankLow < 0 || rankHigh > size()) {
			return list;
		}
		if (rankLow > rankHigh) {
			return list;
		} else {
			int counter = 0;
			for (int i = rankLow; i <= rankHigh; i++) {
				if (getValByRank(i) != null) {
					list.add(counter, getValByRank(i));
				}
				counter++;
			}
		}
		return list;
	}

	/*************************************************************************
	 *  red-black tree helper functions
	 *************************************************************************/

	// make a left-leaning link lean to the right
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}

	// make a right-leaning link lean to the left
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}

	// flip the colors of a node and its two children
	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private Node moveRedLeft(Node h) {
		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private Node moveRedRight(Node h) {		
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
		}
		return h;
	}

	// restore red-black tree invariant
	private Node balance(Node h) {
		// assert (h != null);
//		if (h.left != null) {
//			h.left = balance(h.left);
//		}
//		if (h.right != null) {
//			h.right = balance(h.right);
//		}
		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);

		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}

	private Node recursiveBalance(Node h) {
		if (h.left != null) {
			h.left = balance(h.left);
		}
		if (h.right != null) {
			h.right = balance(h.right);
		}
			if (isRed(h.right))                      h = rotateLeft(h);
			if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
			if (isRed(h.left) && isRed(h.right))     flipColors(h);

			h.N = size(h.left) + size(h.right) + 1;
			return h;
	}
    
    
    
    
    /*************************************************************************
     *  The Main Function
        Use this for testing
     *************************************************************************/
    public static void main(String[] args) {
        Scanner readerTest = null;

        try {
            //Change File name to test other test files.
            readerTest = new Scanner(new File(args[0]));
        } catch (IOException e) {
            System.out.println("Reading Oops");
        }

        // fk u vaastav

		//readerTest = new Scanner(System.in);

        RedBlackBST<Integer, Integer> test = new RedBlackBST<>();

        while(readerTest.hasNextLine()){
           String[] input  =readerTest.nextLine().split(" ");

           for(String x: input){
               System.out.print(x+" ");
           }

           System.out.println();

           switch (input[0]){
               case "insert":
                   Integer key = Integer.parseInt(input[1]);
                   Integer val = Integer.parseInt(input[2]);
                   test.insert(key,val);
                   printTree(test.root);
                   System.out.println();
				   //System.out.println(test.size());
                   break;

               case "delete":
                    Integer key1 = Integer.parseInt(input[1]);
                    test.delete(key1);
                    printTree(test.root);
                    System.out.println();
                    break;

               case "search":
                    Integer key2 = Integer.parseInt(input[1]);
                    Integer ans2 = test.search(key2);
                    System.out.println(ans2);
                    System.out.println();
                    break;

               case "getval":
                    Integer key3 = Integer.parseInt(input[1]);
                    Integer ans21 = test.getValByRank(key3);
                    System.out.println(ans21);
                    System.out.println();
                    break;

               case "rank":
                    Integer key4 = Integer.parseInt(input[1]);
                    Object ans22 = test.rank(key4);
                    System.out.println(ans22);
                    System.out.println();
                    break;

               case "getelement":
                    Integer low = Integer.parseInt(input[1]);
                    Integer high = Integer.parseInt(input[2]);
                    List<Integer> testList = test.getElements(low,high);

                    for(Integer list : testList){
                        System.out.println(list);
                    }

                    break;

               default:
                    System.out.println("Error, Invalid test instruction! "+input[0]);
           }
        }

    }
    
    
    /*************************************************************************
     *  Prints the tree
     *************************************************************************/
    public static void printTree(RedBlackBST.Node node){

        
	    if (node == null){
		    return;
	    }
	   
	    printTree(node.left);
	    System.out.print(((node.color == true)? "Color: Red; ":"Color: Black; ") + "Key: " + node.key + " val: " + node.val + "\n");
        printTree(node.right);
    }

}

