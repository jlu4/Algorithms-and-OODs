package robot;

import java.util.List;

public class Order {
	private Customer customer;
	private List<Item> items;
	
	public Order(Customer customer){
		this.customer = customer;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	
	public void cancelItem(Item item){
		int index = items.indexOf(item);
		items.remove(index);
	}
	
	public void prepare(){	
		for(int i = 0; i < items.size(); i++){
			Item item = items.get(i);			
			item.prepare();			
		}		
	}
	
	public double getTotal(){
	
		double total = 0.0;	
		for(int i = 0; i < items.size(); i++){
			Item item = items.get(i);			
			total += item.getTotal();			
		}
		
		return total;
	}
}
