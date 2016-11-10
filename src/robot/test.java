package robot;

public class test {
	public static void main(String[] args){
		
		Robot robot = new Robot(89757);		// make a robot
	
		Customer customerA = new Customer(1, 25.0);		// comes in a customer
	
		Order order = new Order(customerA);		// make a order		
		Sandwich sandwich = new Sandwich(2);		
		order.addItem(sandwich);
		
		robot.takeOrder(order);		// take the order
		
		Customer customerB = new Customer(2, 12.0);		// comes in a customer
	
		order = new Order(customerB);		// make a order		
		Sandwich sandwich = new Sandwich(5);	
		order.addItem(sandwich);
		
		robot.takeOrder(order);		// take the order
		
		robot.prepare();		// prepare the food
		
	}
}
