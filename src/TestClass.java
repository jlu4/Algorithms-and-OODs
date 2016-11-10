import java.util.ArrayList;
import java.util.List;
public class TestClass {
	static Optimization optimizationUsedForTesting = new Optimization();
	static List<scheduleRequest> test = new ArrayList<>();

	public static void generateSchedule() {
		test.add(new scheduleRequest(5, 0, 0, "1"));
		test.add(new scheduleRequest(15, 0, 7, "1"));
		test.add(new scheduleRequest(8, 0, 3, "2"));
		test.add(new scheduleRequest(6, 0, 0, "3"));
		test.add(new scheduleRequest(10, 0, 7, "3"));
		test.add(new scheduleRequest(35, 0, 25, "1"));
		test.add(new scheduleRequest(30, 0, 20, "2"));
		//test.add(new scheduleRequest("3", 0, 50, 65));// location 0µÄlist
		test.add(new scheduleRequest(10, 1, 0, "1"));
		test.add(new scheduleRequest(20, 1, 10, "2"));
		test.add(new scheduleRequest(30, 1, 20, "3"));
		test.add(new scheduleRequest(40, 1, 30, "2"));
		test.add(new scheduleRequest(50, 1, 40, "1"));// location 1µÄlist
		test.add(new scheduleRequest(25, 2, 0, "1"));
		//test.add(new scheduleRequest("2", 2, 0, 45));
		//test.add(new scheduleRequest("3", 2, 0, 40));// location 2µÄlist
	}// this function is used for generating the list for the tests

	public static void testInitial() {
		optimizationUsedForTesting.initializeSchedule(test);
		System.out.println("testInitial result:" + optimizationUsedForTesting.listOfSchedule.toString());
	}

	public static void testGnerateGapList() {
		optimizationUsedForTesting.generateGapList();
		System.out.println(optimizationUsedForTesting.listofGaps.toString());
	}

	static List<Integer> insertTimes = new ArrayList<>();
	static List<Integer> endTimes = new ArrayList<>();
	static scheduleinsertionObject sI = new scheduleinsertionObject(1,4);

//	public static void testMaxValue() {
//		scheduleRequest result = optimizationUsedForTesting.maxValue(insertTimes, endTimes, sI);
//		System.out.println(result);
//	}

	public static void testOptimization() {
		 scheduleRequest result=optimizationUsedForTesting.optimize1(sI);
		//scheduleRequest result = optimizationUsedForTesting.optimize2(sI);
		System.out.println("name:"+sI.id+",startTime:"+result.starttime+",endtime:"+result.endtime+",location"+result.location);
	}

	public static void main(String args[]) {
		generateSchedule();
		testInitial();
		testGnerateGapList();
		//testMaxValue();
		testOptimization();
	}
}
