import java.util.*;
/*
	Robin Waiting time
	time complexity; O(n)
	space complexity: O(n)
*/
/*[size=11.000000pt]int[] arrival = {0, 1, 3, 9};int[] run = {2, 1, 7, 5};[size=11.000000pt]int q = 2;[size=11.000000pt]��������������Average Wait Time��4/4 = 1s��������澭�ĳ��������1.25s����֪��������˻��ǳ�����Сbug?*/
/*int[] arrival1 = {0, 1, 4};
int[] run1 = {5, 2, 3};. 牛人云集,丢�亩三分地
int q1 = 3;. From 1point 3acres bbs
���case�ǵõ�2.3333ô������amazon����case*/
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



/*��һ��int[] arrival time, int[] Execution time, int q. ���ӣ� ��0��1��4�� ��5��2��3�� q=3. �������average wait time 2.3333333*/
/*input:[0, 1, 3, 9], [2, 1, 7, 5], 2  output:1.0*/