package robot;

import java.util.ArrayList;
import java.util.List;

public class Robot {
	private int robotId;
	private List<Order> orders;
	
	public Robot(int robotId){
		this.robotId = robotId;
		orders = new ArrayList<Order>();
	}
	
	public boolean takeOrder(Order order){
	
		Customer customer = order.getCustomer();		
		boolean isSuccess = customer.charge(order.getTotal());
		
		if(isSuccess){
			orders.add(order);
		}
		
		return isSuccess;
	}
	
	public void cancelOrder(Order order){
		int index = orders.indexOf(order);
		orders.remove(index);
		
		Customer customer = order.getCustomer();
		customer.refund(order.getTotal());
	}
	
	public void prepare(){
		for(int i = 0; i < orders.size(); i++){
			Order order = order.get(i);
			order.prepare();
		}
	}	
}
