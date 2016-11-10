import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class AdjListNode {
	int dest;
	int weight;
	AdjListNode next;
	AdjListNode(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
		this.next = null;
	}
	public String toString() {
		return "" + dest + " - " + weight;
	}
}
public class Prim {  //Prims MST graph
	int numVertices;  // Number of vertices in the graph
	public static final int INF = 9999999;
	Map<Integer, LinkedList<AdjListNode>> adjList;  // Adjacecny list of vertices
	TreeMap<Integer, Integer> heap = new TreeMap<Integer, Integer>();  // Heap , containis all those node which have not been included
	public Prim(int numVertices) {
		this.numVertices = numVertices;
		this.adjList = new TreeMap<Integer, LinkedList<AdjListNode>>();
	}
	private void addEdge(Prim graph, int src, int dest, int weight) {  //Edge Being added to the graph
		AdjListNode node = new AdjListNode(dest, weight);
		LinkedList<AdjListNode> list = null;
		list = adjList.get(src);  // Adding adjacent node for source
		if (list == null)
			list = new LinkedList<AdjListNode>();
		list.addFirst(node);
		adjList.put(src, list);
		node = new AdjListNode(src, weight);  // ---- Again adjacent node for dest as , graph is undirected ----
		list = null;
		list = adjList.get(dest);
		if (list == null)
			list = new LinkedList<AdjListNode>();
		list.addFirst(node);
		adjList.put(dest, list);
	}
	public void PrimeMST(Prim graph) { //Method to find Prims MST
		Map<Integer, Integer> MSTholder = new TreeMap<Integer, Integer>();// List to Store MST formed
		Set<Integer> set = adjList.keySet(); // initialize heap Except root
		for (Integer i : set) {  // Heap Creation for all keys in adjacency list
			createHeap(i, INF);
		}
		while (heap.size() != 0) {  // Main Algo starts here , all initialisation over
			int minEdgeVertex = findMin(); // get the vertex with minimum edge in the heap
			heap.remove(minEdgeVertex); // node removed from heap and its moves to the Set s.
			LinkedList<AdjListNode> list = adjList.get(minEdgeVertex);
			Iterator<AdjListNode> it = list.iterator();
			while (it.hasNext()) {
				AdjListNode node = it.next();
				int vertex = node.dest;
				if ((heap.containsKey(vertex)) && (node.weight < INF)) {
					heap.put(vertex, node.weight); // decreasing node value
					MSTholder.put(vertex, minEdgeVertex);
				}
			}
		}
		long cost = MSTCost(MSTholder);  //TODO : uncomment if u wish to print Node of MST
		System.out.println("Cost is :" + cost);
	}
	private long MSTCost(Map<Integer, Integer> MSTholder) {  //cost of the spanning tree
		Set<Map.Entry<Integer, Integer>> set = MSTholder.entrySet();
		long sum = 0;
		for (Map.Entry<Integer, Integer> entry : set) {
			int key = entry.getKey();
			int value = entry.getValue();
			List<AdjListNode> list = adjList.get(value);
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					AdjListNode node = list.get(j);
					if ((node.dest) == key) {
						sum += node.weight;
					}
				}
			}
		}
		return sum;
	}
	private void printMST(Map<Integer, Integer> MSTholder) {  //Print the Prim;s MST at the end
		System.out.println(" MST is : ");
		Set<Map.Entry<Integer, Integer>> set = MSTholder.entrySet();
		for (Map.Entry<Integer, Integer> entry : set) {
			System.out.println(entry.getKey() + " -- " + entry.getValue());
		}
	}
	public void createHeap(int vertex, Integer weight) {  //Heap Creation of all nodes in the graph
		if (heap == null)
			heap = new TreeMap<Integer, Integer>();

		heap.put(vertex, weight);
	}
	private int findMin() {
		Set<Map.Entry<Integer, Integer>> list = heap.entrySet(); //the minimum key for a given value here key is "node" and value is
		int minKey = heap.firstKey();   //"weight" of all edges in the heap
		int minValue = INF;
		if (list != null) {
			for (Map.Entry<Integer, Integer> entry : list) {
				if (minValue > entry.getValue()) {
					minValue = entry.getValue();
					minKey = entry.getKey();
				}
			}
		}
		return minKey;
	}
	public static void main(String[] args) throws Exception {
		helper();
	}
	private static void helper() throws Exception {
		int numvertices = 9;
		Prim graph = new Prim(numvertices);
		/*
		 * graph.addEdge(graph, 0, 1, 4); graph.addEdge(graph, 0, 7, 8);
		 * graph.addEdge(graph, 1, 2, 8); graph.addEdge(graph, 1, 7, 11);
		 * graph.addEdge(graph, 2, 3, 7); graph.addEdge(graph, 2, 8, 2);
		 * graph.addEdge(graph, 2, 5, 4); graph.addEdge(graph,3, 4, 9);
		 * graph.addEdge(graph, 3, 5, 14); graph.addEdge(graph, 4, 5, 10);
		 * graph.addEdge(graph, 5, 6, 2); graph.addEdge(graph, 6, 7, 1);
		 * graph.addEdge(graph, 6, 8, 6); graph.addEdge(graph, 7, 8, 7);
		 */
		graph.addEdge(graph, 0, 1, 2);
		graph.addEdge(graph, 0, 5, 3);
		graph.addEdge(graph, 1, 2, 11);
		graph.addEdge(graph, 1, 6, 12);
		graph.addEdge(graph, 2, 6, 1);
		graph.addEdge(graph, 2, 3, 9);
		graph.addEdge(graph, 3, 6, 4);
		graph.addEdge(graph, 3, 4, 6);
		graph.addEdge(graph, 4, 6, 13);
		graph.addEdge(graph, 4, 5, 5);
		graph.addEdge(graph, 5, 6, 7);
		graph.PrimeMST(graph);

	}
}