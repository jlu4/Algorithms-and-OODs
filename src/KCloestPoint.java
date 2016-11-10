import java.util.Comparator;
	import java.util.PriorityQueue;
public class KCloestPoint {

		public static double getDistance(Point p1, Point p2) {
			return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
		}
		public static Point[] Solution(Point[] array, Point origin, int k) {
	      if(array == null || array.length <= k) return array;   //limitation remember
			Point[] result = new Point[k];
			PriorityQueue<Point> myPQ = new PriorityQueue<Point>(k, new Comparator<Point> () {  //kuohao remember, length == k?
				@Override
				public int compare(Point p1, Point p2) {
					double i = getDistance(p2, origin) - getDistance(p1, origin);   //from small to big
					if (i > 0) return 1;
					else if (i < 0) return -1;
					return 0;
				}
			});
			for(int i = 0; i < array.length; ++i){
				myPQ.offer(array[i]);
				if(i >= k)
					myPQ.poll();
			}
			int index = k;
			while(!myPQ.isEmpty())
				result[--index] = myPQ.poll();
			return result;
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Point p1 = new Point();
			p1.x = 1.12;
			p1.y = 2.21;
			Point p2 = new Point();
			p2.x = 1.23;
			p2.y = 3.23;
			Point p3 = new Point();
			p3.x = -1.10;
			p3.y = 1.23;
			Point p4 = new Point();
			p4.x = -1.12;
			p4.y = 3.32;
			Point p5 = new Point();
			p5.x = 0;
			p5.y = -1.23;
			Point p6 = new Point();
			p6.x = 3.12;
			p6.y = -1.32;
			Point p7 = new Point();
			p7.x = 0;
			p7.y = 0;
			Point p8 = new Point();
			p8.x = -1.23;
			p8.y = 3.32;
			Point p9 = new Point();
			p9.x = 2;
			p9.y = 2;
			Point origin = new Point(0, 0);
			Point[] p = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8, p9 };
	        Point[] res = Solution(p, origin, 3);
	        for(Point n : res) {
	        	System.out.println(n.x +" " + n.y);
	        }
		}
}

