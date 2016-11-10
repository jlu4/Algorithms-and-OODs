package GroupInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import GroupInterview.Optimization.scheduleWrapper;

public class TestClass {
	static Optimization optimizationUsedForTesting = new Optimization();
	static Schedule scheduleForTesting = new Schedule();
	static Selection selectionForTesting = new Selection();
	static List<scheduleRequest> test = new ArrayList<>();
	static List<scheduleRequest> test1 = new ArrayList<>();
	static HashMap<Integer, Integer> locationValueMap = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> contentScoreMap = new HashMap<Integer, Integer>();

	public static void generateSchedule() {
		/*int id, int location, int starttime, int endtime*/
		test.add(new scheduleRequest(1, 6, 0, 7));
		test.add(new scheduleRequest(2, 3, 5, 15));   //相同长度不同区域
		test.add(new scheduleRequest(3, 2, 3, 8));
		test.add(new scheduleRequest(4, 6, 0, 6));
		test.add(new scheduleRequest(5, 1, 7, 11));
		test.add(new scheduleRequest(6, 1, 25, 35));
		test.add(new scheduleRequest(7, 1, 10, 20));
		test.add(new scheduleRequest(8, 4, 25, 65));// location 0的list
		test.add(new scheduleRequest(9, 2, 0, 10));
		test.add(new scheduleRequest(1, 2, 10, 20));
		test.add(new scheduleRequest(2, 2, 20, 30));
		test.add(new scheduleRequest(3, 1, 30, 40));
		test.add(new scheduleRequest(4, 6, 40, 50));// location 1的list
		test.add(new scheduleRequest(5, 3, 0, 25));
		test.add(new scheduleRequest(6, 2, 0, 45));
		test.add(new scheduleRequest(7, 3, 0, 40));// location 2的list
		test.add(new scheduleRequest(8, 2, 0, 10));
		test.add(new scheduleRequest(9, 1, 10, 20));
		test.add(new scheduleRequest(1, 5, 20, 30));
		test.add(new scheduleRequest(2, 5, 20, 40));
		test.add(new scheduleRequest(3, 6, 30, 50));
		test.add(new scheduleRequest(4, 3, 0, 10));
		test.add(new scheduleRequest(5, 1, 10, 20));
		test.add(new scheduleRequest(6, 4, 20, 30));
		test.add(new scheduleRequest(7, 4, 20, 50));
		test.add(new scheduleRequest(8, 6, 10, 30));
		test.add(new scheduleRequest(9, 5, 0, 10));
		test.add(new scheduleRequest(1, 5, 10, 20));
		test.add(new scheduleRequest(2, 3, 20, 30));
		test.add(new scheduleRequest(3, 4, 20, 50));
		test.add(new scheduleRequest(4, 4, 10, 30));
		test1.add(new scheduleRequest(5, 1, 0, 10));
		test1.add(new scheduleRequest(9, 1, 0, 10));
		test1.add(new scheduleRequest(7, 1, 0, 10));
		test1.add(new scheduleRequest(6, 1, 15, 35));
		test1.add(new scheduleRequest(3, 1, 15, 35));
		test1.add(new scheduleRequest(4, 1, 15, 35));
		test1.add(new scheduleRequest(9, 2, 0, 10));
		test1.add(new scheduleRequest(8, 2, 0, 10));
		test1.add(new scheduleRequest(1, 2, 10, 20));
		test1.add(new scheduleRequest(2, 2, 20, 30));
		test1.add(new scheduleRequest(6, 2, 0, 45));
		test1.add(new scheduleRequest(4, 3, 0, 10));
		test1.add(new scheduleRequest(5, 3, 0, 25));
		test1.add(new scheduleRequest(2, 3, 20, 30));
		test1.add(new scheduleRequest(7, 3, 0, 40));
		test1.add(new scheduleRequest(7, 4, 20, 50));
		test1.add(new scheduleRequest(3, 4, 20, 50));
		test1.add(new scheduleRequest(8, 4, 25, 65));
		test1.add(new scheduleRequest(9, 5, 0, 10));
		test1.add(new scheduleRequest(1, 5, 10, 20));
		test1.add(new scheduleRequest(1, 5, 20, 30));
		test1.add(new scheduleRequest(2, 5, 20, 40));
		test1.add(new scheduleRequest(4, 6, 0, 6));
		test1.add(new scheduleRequest(1, 6, 0, 7));
		test1.add(new scheduleRequest(8, 6, 10, 30));
		test1.add(new scheduleRequest(4, 6, 40, 50));
		test1.add(new scheduleRequest(3, 6, 30, 50));
		
		
	}// this function is used for generating the list for the tests

	public static void testInitial() {
		//optimizationUsedForTesting.initializeSchedule(test);
		scheduleForTesting.initializeSchedule(test);
		optimizationUsedForTesting.initializeSchedule(test1);
		scheduleForTesting.schedule(1);
		scheduleForTesting.schedule(2);
		scheduleForTesting.schedule(3);
		scheduleForTesting.schedule(4);
		scheduleForTesting.schedule(5);
		scheduleForTesting.schedule(6);
		System.out.println("Before Schedule:");
		for(TreeMap<Integer, List<GroupInterview.Schedule.scheduleWrapper>> tm : scheduleForTesting.listOfSchedule) {
			for(Map.Entry<Integer, List<GroupInterview.Schedule.scheduleWrapper>> entry : tm.entrySet()) {
		    	//System.out.println(entry.getKey());
		    	for(GroupInterview.Schedule.scheduleWrapper sw : entry.getValue()) {
		    		System.out.print(sw.startEnd + " ");
		    		System.out.print("id =" + sw.originalObject.id + " ");
		    		System.out.print("starttime =" + sw.originalObject.starttime  + " ");
		    		System.out.print("endtime =" + sw.originalObject.endtime  + " ");
		    		System.out.print("location =" + sw.originalObject.location  + " ");
		    		System.out.println("");
		    	}
		    }
		}
		System.out.println("After Schedule:");
		for(scheduleRequest scheduleResult : scheduleForTesting.result) {
			System.out.print("id =" + scheduleResult.id + " ");
    		System.out.print("starttime =" + scheduleResult.starttime  + " ");
    		System.out.print("endtime =" + scheduleResult.endtime  + " ");
    		System.out.print("location =" + scheduleResult.location  + " ");
    		System.out.println("");
		}
		selectionForTesting.select(scheduleForTesting.result, 0);
		//System.out.println("testInitial result:" + optimizationUsedForTesting.listOfSchedule.toString());
		
		/*for(TreeMap<Integer, List<scheduleWrapper>> tm : optimizationUsedForTesting.listOfSchedule) {
			    for(Map.Entry<Integer, List<scheduleWrapper>> entry : tm.entrySet()) {
			    	System.out.println(entry.getKey());
			    	for(scheduleWrapper sw : entry.getValue()) {
			    		System.out.print("id =" + sw.originalObject.id + " ");
			    		System.out.print("starttime =" + sw.originalObject.starttime  + " ");
			    		System.out.print("endtime =" + sw.originalObject.endtime  + " ");
			    		System.out.print("location =" + sw.originalObject.location  + " ");
			    	}
			    }
				
			}*/
		
	}

	public static void testGnerateGapList() {
		optimizationUsedForTesting.generateGapList();
		//System.out.println(optimizationUsedForTesting.listofGaps.toString());
	}

	static List<Integer> insertTimes = new ArrayList<>();
	static List<Integer> endTimes = new ArrayList<>();
	static scheduleinsertionObject sI = new scheduleinsertionObject(10,4);

	public static void testMaxValue() {
		scheduleRequest result = optimizationUsedForTesting.maxValue(insertTimes, endTimes, sI);
		System.out.println(result.endtime);
	}

	public static void testOptimization() {
		 scheduleRequest result=optimizationUsedForTesting.optimize2(sI);
		//scheduleRequest result = optimizationUsedForTesting.optimize2(sI);
		System.out.println("id: "+sI.id+"  startTime: "+result.starttime+" endtime: "+result.endtime+"  location "+result.location);
	}

	public static void main(String args[]) {
		generateSchedule();
		testInitial();
	    testGnerateGapList();
		//testMaxValue();
		testOptimization();
	}
}