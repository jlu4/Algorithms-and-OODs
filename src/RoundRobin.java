import java.util.*;
/*
	Robin Waiting time
	time complexity; O(n)
	space complexity: O(n)
*/
/*[size=11.000000pt]int[] arrival = {0, 1, 3, 9};int[] run = {2, 1, 7, 5};[size=11.000000pt]int q = 2;[size=11.000000pt]的情况，我手算的Average Wait Time是4/4 = 1s，地里的面经的程序算的是1.25s，不知是我算错了还是程序有小bug?*/
/*int[] arrival1 = {0, 1, 4};
int[] run1 = {5, 2, 3};. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
int q1 = 3;. From 1point 3acres bbs
这个case是得到2.3333么，就是amazon给的case*/
public class RoundRobin {
	public static float Solution(int[] Atime, int[] Etime, int q) {
		//length = 1 no need to wait
		if(Atime == null || Etime == null || Atime.length <= 1 || Etime.length <= 1 || Atime.length != Etime.length) return 0;
		int len = Atime.length;
		Queue<Process> processList = new LinkedList<Process>();  
		int crtTime = Atime[0], waitTime = 0;    
		processList.add(new Process(Atime[0], Etime[0]));
		int index = 1;   
		while(!processList.isEmpty()) {
			Process crtProcess = processList.poll();
			waitTime += crtTime - crtProcess.arrTime;    
			crtTime += crtProcess.exeTime < q ? crtProcess.exeTime : q;      
			//smaller or equal
			for(; index < len && Atime[index] <= crtTime; ++index) //index will not back to zero. Should be global one; begin with 1    
				processList.add(new Process(Atime[index], Etime[index])); 
			if(crtProcess.exeTime > q)     
				processList.add(new Process(crtTime, crtProcess.exeTime - q));  //important! It is crtProcess not i
		}
		return (float)waitTime / len;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{0, 1, 4};
		int[] nums1 = new int[]{5, 2, 3};
		System.out.println(Solution(nums, nums1, 3));

	}
}



/*给一个int[] arrival time, int[] Execution time, int q. 例子： 【0，1，4】 【5，2，3】 q=3. 输出的是average wait time 2.3333333*/
/*input:[0, 1, 3, 9], [2, 1, 7, 5], 2  output:1.0*/