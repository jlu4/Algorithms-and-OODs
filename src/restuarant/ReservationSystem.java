package restuarant;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationSystem {
	HashMap<Long, Reservation> map;
	// From 10am to 9pm, each hour have a list of tables**********
	ArrayList<Table>[] tables;
	// two hour between each reservation for one table
	int MAX_TIME = 2;

	public Reservation getResrvation(Custmer custmer) {
		return map.get(custmer);
	}

	public void makeReservation(Custmer custmer, int startTime, int size) {
		Table newTable = findTable(startTime, size);
		if (newTable != null) {
			Reservation newReservation = new Reservation(newTable, custmer, startTime);
			long custmerId = custmer.getId();
			map.put(custmerId, newReservation);
			removeTable(startTime, newTable);
		} else {
			System.out.println("Can not find table !!");
		}
	}

	public void cancelReservation(Custmer custmer) {
		Reservation newReservation = map.remove(custmer.getId());
		addTable(newReservation.getTime(), newReservation.getTable());
	}

	public Table findTable(int startTime, int size) {
		ArrayList<Table> tableList = tables[startTime - 10];
		for (Table t : tableList) {
			if (t.getSize() >= size) {
				return t;
			}
		}
		return null;
	}

	public void removeTable(int startTime, Table table) {
		int index = startTime - 10;
		int period = MAX_TIME;
		//tables.length is hour
		while (index + period - 1 < tables.length && period > 0) {
			tables[index + period].remove(table);
			period--;
		}
	}

	public void addTable(int startTime, Table table) {
		int index = startTime - 10;
		int period = MAX_TIME;
		while (index + period - 1 < tables.length && period > 0) {
			tables[index + period].add(table);
			period--;
		}
	}

	public Reservation getReservation(Long custerId) {
		return map.get(custerId);
	}
}
