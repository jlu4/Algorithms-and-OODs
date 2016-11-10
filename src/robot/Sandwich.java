package robot;

public class Sandwich extends Item{
	private static final int ID = 10082;
	private static final String NAME = "sandwich";
	private static final double PRICE = 2.99;
	
	public Sandwich(int qty){
		itemId = ID;
		itemName = NAME;
		price = PRICE;
		quantity = qty;
	}
		
	public void prepare(){
		processBread();
		processMeat();
		packSandwich();
	}
	
	private void processBread(){}
	
	private void processMeat(){}
	
	private void packSandwich(){}
}
