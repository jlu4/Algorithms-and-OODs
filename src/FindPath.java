import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindPath {
        public static void main (String[] args) {
                Connection[] citys = new Connection[10];
                citys[0] = new Connection("A","B",6);
                citys[1] = new Connection("A","D",1);
                citys[2] = new Connection("A","E",5); 
                citys[3] = new Connection("B","C",3);
                citys[4] = new Connection("B","D",5);
                citys[5] = new Connection("C","D",6);
                citys[6] = new Connection("C","F",6);
                citys[7] = new Connection("D","F",4);
                citys[8] = new Connection("D","E",5);
                citys[9] = new Connection("E","F",2);
                ArrayList<Connection> list = new ArrayList<Connection> ();
                for (Connection temp : citys) {
                        list.add(temp);
                }
                
                for (Connection temp : findPath(list)){
                        temp.printConnection();
                }
                
        }
        
        public static List<Connection> findPath (List<Connection> list) {
                ArrayList<Connection> result = new ArrayList<Connection> ();
                ArrayList<String> cityTree = new ArrayList<String> ();        //to Maintain the cities have been traversed.

                while (!list.isEmpty()) {
                        //find the minimum cost to the city from cityTree among the result.
                        Connection temp = findMin(list, cityTree);
                        if (temp == null) {// we finished
                                break;
                        }
                        list.remove(temp);        //remove the current minimum cost from grand set
                        cityTree.add(temp.city1);
                        cityTree.add(temp.city2);
                        result.add(temp);
                }
                //override compare make it alphabet order
                Comparator<Connection> cmp = new Comparator<Connection>(){
                        public int compare(Connection c1, Connection c2) {
                                if (c1.city1.equals(c2.city1)) {
                                        return c1.city2.compareTo(c2.city2);
                                }
                                return c1.city1.compareTo(c2.city1);
                        }};
                result.sort(cmp);
                
                return result;
        }

        //find the minimum cost between the city we traversed and we did not traverse.
        public static Connection findMin(List<Connection> list, ArrayList<String> cityTree) {
                Connection result = null;
                int minCost = Integer.MAX_VALUE;        //minimum cost
                
                for (Connection con : list) {
                        if (con.cost <= minCost) {
                                //if none of the city we traversed
                                //or the connection is connected to either city from cityTree.
                                if ((cityTree.contains(con.city1) && !cityTree.contains(con.city2)) ||
                                                cityTree.contains(con.city2) && !cityTree.contains(con.city1)) {
                                        minCost = con.cost;
                                        result = con;
                                }
                                if (cityTree.isEmpty()) {
                                        minCost = con.cost;
                                        result = con;
                                }
                        }
                }
                
                return result;
        }


}
