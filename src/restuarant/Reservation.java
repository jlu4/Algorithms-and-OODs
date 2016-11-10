package restuarant;

public class Reservation {
	protected long ReservationId;
	protected Table table;
	protected Custmer custmer;
	protected int startTime;

	public Reservation(Table table, Custmer custmer, int startTime) {
	this.table = table;
	this.custmer = custmer;
	this.startTime = startTime;
	}

	public Table getTable() { return table;}
	public int getTime() { return startTime;}
}
