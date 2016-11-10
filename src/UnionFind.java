class UFNode {
	int parent; // parent of Vertex at i in the nodeHolder
	int rank; // Number of object present in the tree/ Cluster
	UFNode(int parent, int rank) {
		this.parent = parent;
		this.rank = rank;
	}
}
public class UnionFind {
	private UFNode[] nodeHolder;  // Node Holder haveing UFNode
	private int count;  // number of node
	public UnionFind(int size) {
		if (size < 0)
			throw new IllegalArgumentException();
		count = size;
		nodeHolder = new UFNode[size];
		for (int i = 0; i < size; i++) {
			nodeHolder[i] = new UFNode(i, 1); // default values, node points to itself and rank is 1
		}
	}
	public int Find(int vertex) {  //Finds the parent of a given vertex, using recursion
		if (vertex < 0 || vertex >= nodeHolder.length)
			throw new IndexOutOfBoundsException();
		if (nodeHolder[vertex].parent != vertex)   //equals to itself, 
			nodeHolder[vertex].parent = Find(nodeHolder[vertex].parent); 
		return nodeHolder[vertex].parent;
	}
	public int getCount() {
		return count;
	}
	public boolean isConnected(int v1, int v2) {    //return true if both vertex have same parent
		return Find(v1) == Find(v2);
	}
	public void Union(int v1, int v2) {  //unions two cluster of two vertices
		int i = Find(v1);
		int j = Find(v2);
		if (i == j)
			return;
		if (nodeHolder[i].rank < nodeHolder[j].rank) {
			nodeHolder[i].parent = j;
			nodeHolder[j].rank = nodeHolder[j].rank + nodeHolder[i].rank;
		} else {
			nodeHolder[j].parent = i;
			nodeHolder[i].rank = nodeHolder[i].rank + nodeHolder[j].rank;
		}
		count--;
	}
}