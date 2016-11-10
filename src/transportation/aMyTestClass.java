package transportation;

import java.util.ArrayList;
import java.util.List;


public class aMyTestClass {
    static List<Order> orderList = new ArrayList<Order>();
    static Inventory[] inventoryList = new Inventory[2];
    static ShoppingCost[] shippingCostList = new ShoppingCost[3];
    static Region region1 = Region.EAST;
    static Region region2 = Region.WEST;
    static Region region3 = Region.SOUTH;
    static Region region4 = Region.NORTH;
    
    public aMyTestClass() {
    	Inventory inventory1 = new Inventory(region1, 1, 25);
    	Inventory inventory2 = new Inventory(region2, 1, 20);
    	inventoryList[0] = inventory1;
    	inventoryList[1] = inventory2;
    	
    	ShoppingCost shopping1 = new ShoppingCost(150, 8, Region.SOUTH, Region.EAST);
		ShoppingCost shopping2 = new ShoppingCost(251, 4, Region.NORTH, Region.EAST);
		ShoppingCost shopping3 = new ShoppingCost(352, 5, Region.NORTH, Region.WEST);
		shippingCostList[0] = shopping1;
		shippingCostList[1] = shopping2;
		shippingCostList[2] = shopping3;
		
		Order order1 = new Order(1, Region.SOUTH, 10, 20);
		Order order2 = new Order(1, Region.NORTH, 10, 15);
		orderList.add(order1);
		orderList.add(order2);
    }
    
    public static List<Order> getOrderList() {
		return orderList;
	}

	public static void setOrderList(List<Order> orderList) {
		aMyTestClass.orderList = orderList;
	}

	public static Inventory[] getInventoryList() {
		return inventoryList;
	}

	public static void setInventoryList(Inventory[] inventoryList) {
		aMyTestClass.inventoryList = inventoryList;
	}

	public static ShoppingCost[] getShippingCostList() {
		return shippingCostList;
	}

	public static void setShippingCostList(ShoppingCost[] shippingCostList) {
		aMyTestClass.shippingCostList = shippingCostList;
	}
}
