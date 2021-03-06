import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Kruskal1 {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
  
        double graph[][]={{0,1,4,4,5},  
                          {1,0,3,7,5},  
                          {4,3,0,6,Double.MAX_VALUE},  
                          {4,7,6,0,2},  
                          {5,5,Double.MAX_VALUE,2,0}};  
        double tree[][]=minimumSpanningTree(graph);  
        if(tree==null)  
        {  
            System.out.println("no spanning tree");  
            System.exit(0);  
        }  
        PriorityQueue<Edge1> edgeList=generateEdgeList(tree);  
        for(Edge1 e:edgeList)  
        {  
            System.out.println(e);  
        }  
          
    }  
  
    /** 
     * 给定一个带权值的邻接矩阵（其中若i=j，权值为0，若i和j不可达权值为Double.max），返回一个最小生成树， 
     * @param graph 
     * @return 
     */  
    public static double[][] minimumSpanningTree(double[][] graph) {  
  
        int rlength=graph.length;  
        int clength=graph[0].length;  
        PriorityQueue<Edge1> edgeList;  
        edgeList=generateEdgeList(graph);  
        double tree[][]=new double[rlength][clength];  
        /** 
         * 初始化tree 
         */  
        for(int i=0;i<rlength;i++)  
        {  
            for(int j=0;j<clength;j++)  
            {  
                if(i==j)  
                    tree[i][j]=0;  
                else  
                    tree[i][j]=Double.MAX_VALUE;  
            }  
        }  
          
        /** 
         * map用于标识边的某个顶点属于哪个集合，认为顶点刚开始属于不同的集合，当选择一条边时，就合并两个集合，如果选择的边在同一个集合内，就代表有环路出现了 
         */  
        Map<Integer,Set<Integer>> map=new HashMap<Integer,Set<Integer>>();  
        int edgeCount=0;  
        while(edgeCount<rlength-1&&!edgeList.isEmpty())  
        {  
            Edge1 e=edgeList.poll();  
              
              
            Set<Integer> setU=map.get(e.u);  
            Set<Integer> setV=map.get(e.v);  
            //System.out.println(e);  
            //边的两个顶点都未出现在其他集合中  
            if(setU==null&&setV==null)  
            {  
                Set<Integer> set=new HashSet<Integer>();  
                set.add(e.u);  
                set.add(e.v);  
                map.put(e.u, set);  
                map.put(e.v, set);  
            }//有一个顶点在其他集合中，一个不在，将不在的那个顶点集合合并进去  
            else if(setU==null&&setV!=null)  
            {  
                map.put(e.u, setV);  
                setV.add(e.u);  
            }  
            else if(setU!=null&&setV==null)  
            {  
                map.put(e.v, setU);  
                setU.add(e.v);  
            }//分别在不同的集合中，合并两个集合  
            else if(setU!=setV)  
            {  
                for(int v:setV)  
                {  
                    map.put(v, setU);  
                      
                }  
                  
                setU.addAll(setV);  
            }//两个顶点在同一个结合中，会出现环路，舍弃  
            else  
            {  
                continue;  
            }  
            tree[e.u][e.v]=e.weight;  
            tree[e.v][e.u]=e.weight;  
            edgeCount++;  
              
        }  
          
          
        if(edgeCount==rlength-1)  
            return tree;  
        else  
            return null;  
    }  
  
    /** 
     * 生成边的排序好的队列 
     * @param graph 
     * @return 
     */  
    private static PriorityQueue<Edge1> generateEdgeList(double[][] graph) {  
        PriorityQueue<Edge1> edgeList=new PriorityQueue<Edge1>();  
        int rlength=graph.length;  
        int clength=graph[0].length;  
        for(int i=0;i<rlength;i++)  
        {  
            for(int j=i+1;j<clength;j++ )  
            {  
                if(graph[i][j]>0&graph[i][j]<Double.MAX_VALUE)  
                {  
                    Edge1 e=new Edge1(i,j,graph[i][j]);  
                    edgeList.add(e);  
                }  
            }  
        }  
        return edgeList;  
    }  
  
      
  
}  
  
class Edge1 implements Comparable<Edge1>  
{  
    int u;  
    int v;  
    double weight;  
      
      
      
    public Edge1(int u, int v, double weight) {  
        super();  
        this.u = u;  
        this.v = v;  
        this.weight = weight;  
    }  
  
  
  
    @Override  
    public int compareTo(Edge1 e) {  
        if(e.weight==weight)  
        return 0;  
        else if(weight<e.weight)  
            return -1;  
        else  
            return 1;  
              
    }  
      
    public String toString()  
    {  
        return u+"--"+v+":"+weight;  
    }  
      
}  