import java.util.LinkedList;
import java.util.Queue;

/*
	find path in a 2D matrix maze
	time complexity: O(m*n)
	space complexity: O(m*n) probably min(m, n) + 1 (diagonal length + 1)
*/
/*给的题设是robot andy想找desired shelf。这题需要自己import queue*/
/*i = x / n, j = x % n，where i, j是2d坐标值，x是1d坐标值，i是row index，j是column index，n是number of elements in a row。vice versa哈*/
public class Maze_Path{
	public static int Solution(int[][] maze){
		if(maze == null || maze.length == 0 || maze[0].length == 0) return 0;
		if(maze[0][0] == 9) return 1;
		int rowNum = maze.length;
		int colNum = maze[0].length;
		// four direction around current point, (down, right, up, left)
		int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		Queue<MPoint> myQ = new LinkedList<MPoint>();    //need to defind a class
		myQ.offer(new MPoint(0, 0));
		while(!myQ.isEmpty()){
			MPoint crt = myQ.poll();
			for(int i = 0; i < 4; ++i){           
				int tmpRow = crt.row + direction[i][0];//as x + x1, y + y1, not going to use crt itself, remember crt + direction = tmp
				int tmpCol = crt.col + direction[i][1]; //remember not i,0 0,i but i,0 i,1 it is a matrix
				if(tmpRow >= 0 && tmpCol >= 0 && tmpRow < rowNum && tmpCol < colNum && maze[tmpRow][tmpCol] > 0){ //full check
					if(maze[tmpRow][tmpCol] == 9) return 1;
					myQ.offer(new MPoint(tmpRow, tmpCol));
					maze[tmpRow][tmpCol] = -1;   
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) {
	    int[][] maze = {{1,0,0,0,0},{1,1,1,1,1},{1,0,0,0,1},{0,0,9,1,1}};  
	    int[][] maze1 = {{1,0,0,0,0},{1,1,1,1,1},{1,0,0,0,1},{0,0,9,1,1}};         
	    int[][] maze2 = {{1,1,1,1},{1,0,0,0},{1,9,0,0}};  
	    int[][] maze3 = {{9}};  
	    int[][] maze4 ={{1,1,1,1,1,1},{1,1,1,1,0,0},{0,0,1,0,0,0},{1,1,1,1,1,1},{1,0,0,0,1,0},{1,1,1,0,9,0}};
	    System.out.println(Solution(maze));
	}
}