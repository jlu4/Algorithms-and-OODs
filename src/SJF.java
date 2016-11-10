import java.util.*;


public class SJF {
	public static float Solution(int[] req, int[] dur) {
		if(req == null || dur == null || req.length != dur.length || req.length <= 1 || dur.length <= 1) return (float)0.0;
		int len = req.length;
		int crtTime = req[0];  //current time
		int waitTime = 0;  //total wait time
		PriorityQueue<Process> processPQ = new PriorityQueue<Process>(new Comparator<Process>(){
			@Override
			public int compare(Process p1, Process p2){
				if(p1.exeTime == p2.exeTime) return p1.arrTime - p2.arrTime;  //if execute time equals, compare arrive time
				return p1.exeTime - p2.exeTime; 
			}
		});
		int index = 1;
		processPQ.offer(new Process(req[0], dur[0]));
		while(!processPQ.isEmpty()){
			Process crtProcess = processPQ.poll();
			waitTime += crtTime - crtProcess.arrTime; //total waitTime
			crtTime += crtProcess.exeTime;   //the current time after i process arrive
			for(; index < len && req[index] <= crtTime; ++index)       //let all process which arrived get into priority queue
				processPQ.offer(new Process(req[index], dur[index]));
		}
		return (float)waitTime / len;
	}
	public static void main(String[] args) {
		int[] nums1 = new int[]{0, 1, 3, 9};
		int[] nums2 = new int[]{2, 1, 7, 5};
		System.out.println(Solution(nums1, nums2));

	}
}






/*package AmazonOA;

import java.util.HashSet;

public class AverageWaiting {

        public AverageWaiting() {
            
        }
        
. From 1point 3acres bbs        public double findAverage(int []request, int[]duration){
                if(request==null||duration==null||request.length==0||duration.length==0){
                        return 0;
                }
                int n=request.length;
                int []end=new int[n];
                int curTime=0;
                for(int i=0;i<n;i++){
                        if(i==0){
                                curTime=duration[0];
                                end[0]=duration[0];
                        }else{
                                int minDuration=Integer.MAX_VALUE;
                                int curProcess=0;
                                for(int j=0;j<n;j++){
                                        if(end[j]!=0) continue;
                                        if(request[j]<=curTime){
                                                if(duration[j]<minDuration){
                                                        minDuration=duration[j];
                                                        curProcess=j;
                                                }
                                        }else{
                                                break;
                                        }
                                }
                                if(curProcess==0){
                                        curProcess=i;
                                        curTime=request[curProcess];
                                }
                                curTime=curTime+duration[curProcess];
                                end[curProcess]=curTime;
                        }
                }-google 1point3acres
                int waitSum=0;
                for(int i=0;i<n;i++){
                        waitSum+=end[i]-request[i]-duration[i];
                }
                return (double)waitSum/(double)n;
        }
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                AverageWaiting solution=new AverageWaiting();
                int[] request={0,1,4,9};//{0,2,4,5};
                int[] duration={2,1,7,5};//{7,4,1,4};
                double result=solution.findAverage(request, duration);
                System.out.println(result);
        }

}*/
