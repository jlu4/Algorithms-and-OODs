package robot;
/*Design a robot that will take your order and make sandwiches for you. 

Once I was done with this, I was supposed to extend it to have multiple robots doing this job like an assembly line handling multiple sandwiches and other edible items 

Once I handled that, he asked me to create a web service for this that will handle online ordering. He also wanted me to implement fulfillment centers
*/

/*
1. Create Abstract class Item(it will have price and abstract prepare() method) 
2. Create Sawdwich class,It will be extending Item class 
3. Create Order class,It will have List<item> ,Customer Info 
4. Robat as client,for accepting order,it will have list of orders,And will prepare items calling item's prepare method.
*/public abstract class Item {
	protected int itemId;	
	protected String itemName;	
	protected double price;
	protected int quantity;
	
	public int getItemId(){
		return itemId;
	}
	
	public String getItemName(){
		return itemName;
	}
	
	public double getTotal(){
		return quantity * price;
	}
	
	public abstract void prepare();
}
