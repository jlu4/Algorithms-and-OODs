package transportation;

import java.util.*;
import java.util.Map.Entry;

public class First {
	// hashmap<productid, arraylist<pair(region, quantity)>> or cost?
	// hashmap<shipTo, pair<shipFrom,cost>>
	// 同一ID里的INVENTORY的SHIPFROM和QUANTITY是一一对应
	// 一个ID可以有多个INVENTORY
	// 一个INVENTORY可以有多个ID
	// SHIPFROM和SHIPTO也是一一对应 但是反过来可能是另一种一一对应
	// 一个PRODUCT可以有很多INVENTORY, 所以有不同的SHIPTO，
	// 一个INVENTROY可以有很多到这个DESTINATION和COST组合
	HashMap<Integer, List<Map.Entry<Region, Integer>>> InventoryMap;
	HashMap<Region, List<Map.Entry<Region, Integer>>> ShippingAndCost;
	HashMap<Integer, List<Inventory>> InventoryMap1;

	public First() {
		InventoryMap = new HashMap<>();
		ShippingAndCost = new HashMap<>();
	}

	public List<SomeClass> deliverPossible(int productId, Region destination) {
		Inventory[] inventory = testClass.inventoryList;
		// new AbstractMap.SimpleEntry<Region, Integer>(region1, 15);
		/*for (Inventory i : inventory) {
			if (!InventoryMap.containsKey(i.productId)) {
				List<Map.Entry<Region, Integer>> tempList = new ArrayList<>();
				for (int j = 0; j < 30; j++) {
					if (i.productId == inventory[j].productId) {
						Map.Entry<Region, Integer> temp = new AbstractMap.SimpleEntry<Region, Integer>(
								inventory[j].shipFrom, inventory[j].quantity);
						tempList.add(temp);
					}
				}
				InventoryMap.put((i.productId), tempList);
			}
		}*/
		ShoppingCost[] shippingCost = testClass.shippingCostList;
		/*
		 * for (ShoppintCost shopping : shippingCost) { List<Map.Entry<Region,
		 * Integer>> tempList = new ArrayList<>(); for (int j = 0; j <
		 * shippingCost.length; j++) { if (shippingCost[j].shipTo ==
		 * shopping.shipTo) { Map.Entry<Region, Integer> temp = new
		 * AbstractMap.SimpleEntry<Region, Integer>(
		 * shippingCost[j].inventory.shipFrom, shippingCost[j].cost);
		 * tempList.add(temp); } } ShippingAndCost.put(shopping.shipTo,
		 * tempList); } List<SomeClass> result = new ArrayList<SomeClass>();
		 * List<ShoppintCost> CostList = new ArrayList<ShoppintCost>();
		 * ShoppintCost resultShopping = null; for (ShoppintCost shopping :
		 * shippingCost) { if (shopping.shipTo.equals(destination) &&
		 * shopping.inventory.productId == productId) { resultShopping =
		 * shopping; CostList.add(shopping); } } SomeClass someClass = new
		 * SomeClass(resultShopping.inventory, CostList); result.add(someClass);
		 */
		List<SomeClass> result = new ArrayList<SomeClass>();
		List<Inventory> totalInvent = new ArrayList<>();
		for (Inventory rightInvent : inventory) {
			if (rightInvent.productId == productId) {
				totalInvent.add(rightInvent);
			}
		}

		SomeClass someClass = null;
		Set<Integer> CostSet = new HashSet<>();
		for (Inventory right : totalInvent) {
			List<ShoppingCost> cost = new ArrayList<>();
			for (ShoppingCost shopping : shippingCost) {
				if (shopping.shipTo.equals(destination) && shopping.shipFrom == right.shipFrom) {
					if (!CostSet.contains(shopping.cost)) {
						cost.add(shopping);
						CostSet.add(shopping.cost);
					}
				}
			}
			if (cost != null && cost.size() != 0){
				someClass = new SomeClass(right, cost);
			    result.add(someClass);
			}
		}
		return result;
	}

	// sc.inventory.shipFrom sc.inventory.quantity sc.inventory.shipFrom
	// cost.cost cost.shipFrom ost.shippingDays cost.shipTo
	
	public void secondPart(List<Order> OrderEntryList) {
		PriorityQueue<Inventory> pq = new PriorityQueue<Inventory>(OrderEntryList.size(), new Comparator<Inventory>() {
			@Override
			public int compare(Inventory i1, Inventory i2) {
				return i1.quantity - i2.quantity;
			}
		});
		for (Order order : OrderEntryList) {
			List<SomeClass> somelist =  deliverPossible(order.productId, order.destination);
			
			for (SomeClass some : somelist) {
				pq.offer(some.inventory);
			}
			while(!pq.isEmpty()) {
				
			}
		}

	}

}
