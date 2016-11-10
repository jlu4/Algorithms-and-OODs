import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/*最后那个题除了要注意输出edge顺序首先要按node1排序，node1相同按node2(这个上面那个小伙伴的帖子有说，但题目没说)，

题目还要求所有点要连成一个没环的连通块，我有一个case没过应该是给出的edge里面不能找到连接所有点的一条路径，对于这

样的case，题目说直接返回空表就好了*/

class Edge {
	String src;
	String dest;
	int weight;
	public Edge(String src, String dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Edge: " + src + " - " + dest + " | " + "  Weight: " + weight;
	}
}
public class Kruskal {
	
	private static void KruskalMST(List<Edge> mEdgeList) {
		int[] pre = new int[75];
		List<Edge> mResultantEdgeList = new ArrayList<Edge>();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(mEdgeList.size(), new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
			    return e1.weight - e2.weight;  	  //remember
			}	
		 });
		
		for(int i = 0; i < pre.length; i++){
			pre[i] = i;
		}
		for(Edge e : mEdgeList){
			pq.offer(e);
		}
		Set<String> set = new HashSet<String>();
		while (!pq.isEmpty()) {   
			Edge edge = pq.poll();
			if(!set.contains(edge.src)) set.add(edge.src);
			if(!set.contains(edge.dest)) set.add(edge.dest);
			int e1 = edge.src.charAt(0) -'0';
			int e2 = edge.dest.charAt(0) -'0';
			int v1 = Find(e1, pre); 
			int v2 = Find(e2, pre); 	
			if (v1 != v2) {  
				mResultantEdgeList.add(edge);
				Union(v1, v2, pre);
			}
		}	
		System.out.println(set.size());
		System.out.println(mResultantEdgeList.size());
		if(set.size() - mResultantEdgeList.size() != 1) {
			System.out.println("NULL");
			return;
		}
		Comparator<Edge> cmp = new Comparator<Edge>() {
	        public int compare(Edge e1, Edge e2) {
	        	if(!e1.src.equals(e2.src)) {
					return e1.src.compareTo(e2.src);
				} else {
					return e1.dest.compareTo(e2.dest);
				}
	        }
        };
       // mResultantEdgeList.sort(cmp);
		
		for (Edge edge : mResultantEdgeList) {
			System.out.println(edge);
		}
	}
	
	public static int Find(int vertex, int[] pre) {
		while(vertex != pre[vertex]){
			vertex = pre[vertex];
		}
		return vertex;
	}
	public static void Union(int v1, int v2, int pre[]) { 
		int i = Find(v1, pre);
		int j = Find(v2, pre);
		if (i == j)
			return;
		pre[i] = j;   //quick union     remember
		/*for (int k = 0; k < pre.length; k++)
			if(pre[k] == i) pre[k] = j;*/
			/*for (int i = 0; i < id.length; i++)  
	            if (id[i] == pID) id[i] = qID; */ 
	}
	
	public static void main(String[] args) {
		helper();
	}
	public static void helper() {
		List<Edge> mEdgeList = new ArrayList<Edge>();
		mEdgeList.add(new Edge("zCity", "aCity", 4));
		mEdgeList.add(new Edge("zCity", "gCity", 8));
		mEdgeList.add(new Edge("aCity", "bCity", 8));
		mEdgeList.add(new Edge("aCity", "gCity", 11));
		mEdgeList.add(new Edge("bCity", "fCity", 1));
		mEdgeList.add(new Edge("bCity", "cCity", 7));
		mEdgeList.add(new Edge("bCity", "hCity", 2));
		mEdgeList.add(new Edge("bCity", "eCity", 4));
		mEdgeList.add(new Edge("cCity", "dCity", 9));
		mEdgeList.add(new Edge("cCity", "eCity", 14));
		mEdgeList.add(new Edge("dCity", "eCity", 10));
		/*mEdgeList.add(new Edge("zCity", "gCity", 8));
		mEdgeList.add(new Edge("aCity", "bCity", 8));*/
		KruskalMST(mEdgeList);
		
	}
	/*
	 * graph.addEdge(graph, 0, 1, 4); graph.addEdge(graph, 0, 7, 8);
	 * graph.addEdge(graph, 1, 2, 8); graph.addEdge(graph, 1, 7, 11);
	 * graph.addEdge(graph, 2, 3, 7); graph.addEdge(graph, 2, 8, 2);
	 * graph.addEdge(graph, 2, 5, 4); graph.addEdge(graph,3, 4, 9);
	 * graph.addEdge(graph, 3, 5, 14); graph.addEdge(graph, 4, 5, 10);
	 * graph.addEdge(graph, 5, 6, 2); graph.addEdge(graph, 6, 7, 1);
	 * graph.addEdge(graph, 6, 8, 6); graph.addEdge(graph, 7, 8, 7);
	 */
}
