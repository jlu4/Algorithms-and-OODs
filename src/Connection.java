public class Connection {
        String city1;
        String city2;
        int cost;
        Connection (String city1, String city2, int cost) {
                this.city1 = city1;
                this.city2 = city2;
                this.cost = cost;
        }
        
        public void printConnection () {
                System.out.println("{ " + this.city1 + " , " + this.city2 + "} " + this.cost);
        }
}