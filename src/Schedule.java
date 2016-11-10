import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Schedule {
	/**
	 * wrapper for a shceduleObject used to get the list of map
	 */
	class scheduleWrapper {
		boolean startEnd;
		public scheduleRequest originalObject;

		scheduleWrapper(scheduleRequest s, boolean se) {
			originalObject = s;
			startEnd = se;
		}
	}

	final int LocationNum = 20;
	Map<String, scheduleRequest> setOfContents;
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule;
	List<scheduleRequest> result;
	List<scheduleRequest> abandon;

	public Schedule() {
		setOfContents = new HashMap<>();
		listOfSchedule = new ArrayList<>();
		result = new LinkedList<>();
		abandon = new LinkedList<>();
	}

	/**
	 * This function is used for initializing the list of map the map is used to
	 * store the schedule of locations one element in this list stands for a
	 * location.
	 */
	public void initializeSchedule(List<scheduleRequest> scheduleList) {// ֻ����һ���ط��������룬��ʱ����Ҫ��һ�¡�
		for (int i = 0; i < LocationNum; i++) {
			listOfSchedule.add(new TreeMap<>());
		}
		for (scheduleRequest s : scheduleList) {
			if (listOfSchedule.get(s.location).containsKey(s.starttime)) {
				listOfSchedule.get(s.location).get(s.starttime).add(new scheduleWrapper(s, true));
			} else {
				List<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, true));
				listOfSchedule.get(s.location).put(s.starttime, newList);
			}
			if (listOfSchedule.get(s.location).containsKey(s.endtime)) {
				listOfSchedule.get(s.location).get(s.endtime).add(new scheduleWrapper(s, false));
			} else {
				List<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, false));
				listOfSchedule.get(s.location).put(s.endtime, newList);
			}
		}
	}

	/* ��ǰ�汾�ݲ����ǵݹ���� */
	public void schedule(int location) {
		for (Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(location).entrySet()) {
			for (scheduleWrapper sW : entry.getValue()) {
				if (!sW.startEnd && setOfContents.containsKey(sW.originalObject.id)) {
					// ����һ�����̵�ͷ����Ҫɾ�������
					result.add(setOfContents.get(sW.originalObject.id));// ֻ��ƽ�������ĲŻ������
					setOfContents.remove(sW.originalObject.id);// ��set��ȥ��
				}
			}
			/* �˴���Ϊ������ȳ���� */
			for (scheduleWrapper sW : entry.getValue()) {
				if (sW.startEnd) {// ����һ����ʼ������
					if (setOfContents.containsKey(sW.originalObject.id)) {// ���set���Ѿ���ͬ��������
						if (calculateDuration(setOfContents.get(sW.originalObject.id)) < calculateDuration(
								sW.originalObject)) {// �Ƚ����ߵ�distance
							abandon.add(setOfContents.get(sW.originalObject.id));// ��ԭ�е�value��С��ɾ��ԭ�в�������abandon��
							setOfContents.remove(sW.originalObject.id);
							setOfContents.put(sW.originalObject.id, sW.originalObject);// ���µķ���
						} else
							abandon.add(sW.originalObject);// �������µı�����abandon��
					} else if (setOfContents.size() >= 3) {// �����ǰset�ڵ�����������������
						String keyToRemove = findSmallest(setOfContents);// �ҳ�����duration��С��һ��
						if (calculateDuration(setOfContents.get(keyToRemove)) < calculateDuration(sW.originalObject)) {
							abandon.add(setOfContents.get(keyToRemove));// �����С���Ǹ�valueС�ڵ�ǰҪ����ģ�
							setOfContents.remove(keyToRemove);// ɾ����Сvalue��һ��
							setOfContents.put(sW.originalObject.id, sW.originalObject);// �������ļ���set
						} else {
							abandon.add(sW.originalObject);// ����ֱ�����������ġ�
						}
					} else {
						setOfContents.put(sW.originalObject.id, sW.originalObject);// ������������������������Ǽ��뼴��
					}
				}
			}
		}
	}

	/**
	 * this function is used to find the element with smallest value in a set of
	 * scheduleObjects the function is called in schedule function
	 */
	public String findSmallest(Map<String, scheduleRequest> set2) {
		int smallest = Integer.MAX_VALUE;
		String resultName = null;
		for (Map.Entry<String, scheduleRequest> entry : set2.entrySet()) {
			if (calculateDuration(entry.getValue()) < smallest) {
				smallest = calculateDuration(entry.getValue());
				resultName = entry.getValue().id;
			}
		}
		return resultName;
	}

	/**
	 * this function is used to calculate a duration of a scheduleObject
	 */
	public int calculateDuration(scheduleRequest a) {
		return a.endtime - a.starttime;
	}
}



// Ŀǰ�ó���Ψһ�����������в�����������Ҫ��һ��ʲô��ʽ��
