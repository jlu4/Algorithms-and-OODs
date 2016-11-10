package transportation;

import java.text.NumberFormat;
import java.util.*;


public class aMyTest {
	private static Map<Integer, Map<Region, Inventory>> productToInventoryMap = new HashMap<Integer, Map<Region, Inventory>>();
	private static Map<Region, Map<Region, List<ShoppingCost>>> warehouseToDestinationMap = new HashMap<Region, Map<Region,List<ShoppingCost>>>();
	
	public aMyTest(Inventory[] inventoryArray, ShoppingCost[] shoppingCostArray) {	
		for (Inventory inventory : inventoryArray) {
			if (productToInventoryMap.containsKey(inventory.productId)) {
				Map<Region, Inventory> inventoryMap = productToInventoryMap.get(inventory.productId);
				if(!inventoryMap.containsKey(inventory.shipFrom)) {
					inventoryMap.put(inventory.shipFrom, inventory);
					 productToInventoryMap.put(inventory.productId, inventoryMap);
				}
			} else {
				Map<Region, Inventory> inventoryMap = new HashMap<>();
				inventoryMap.put(inventory.shipFrom, inventory);
				productToInventoryMap.put(inventory.productId, inventoryMap);
			}
		}
		
		for (ShoppingCost cost : shoppingCostArray) {
			if (warehouseToDestinationMap.containsKey(cost.shipFrom)) {
				Map<Region, List<ShoppingCost>> shipToCostMap = new HashMap<>();
			}
		}
	}
	
	public static List<SomeClass> Problem1(int productId, Region destination) {
		List<SomeClass> result = new LinkedList<SomeClass>();
		
		if (productToInventoryMap.containsKey(productId)) {
			Map<Region, Inventory> warehouseMap = productToInventoryMap.get(productId);
			
			for (Region warehouse : warehouseMap.keySet()) {
				Inventory currentIvt = warehouseMap.get(warehouse);
				
				if (warehouseToDestinationMap.containsKey(currentIvt.shipFrom) && warehouseToDestinationMap.get(currentIvt.shipFrom).containsKey(destination)) {
					List<ShoppingCost> currentCostList = warehouseToDestinationMap.get(currentIvt.shipFrom).get(destination);
					result.add(new SomeClass(currentIvt, currentCostList));
				}	
			}
		}
		
		return result;
	}
	
	
	
	
	
	public static List<ShippingSolution> Problem2(List<Order> OrderEntryList) {
		List<ShippingSolution> shippingList = new LinkedList<ShippingSolution>();
		
		if (OrderEntryList == null || OrderEntryList.size() == 0) {
			return shippingList;
		}

		int orderFinishNumber = 0;
		
		Queue<ProcessedOrder> maxHeap = new PriorityQueue<ProcessedOrder>(OrderEntryList.size(), new Comparator<ProcessedOrder>(){
			public int compare(ProcessedOrder p1, ProcessedOrder p2) {				
				return p2.quantityLeft - p1.quantityLeft;
			}
		});
		Map<Region, Integer> connect = new HashMap<>();
		for (Order currentOrder : OrderEntryList) {
			List<SomeClass> someClassList = Problem1(currentOrder.productId, currentOrder.destination);
			int totalAvailQuantity = 0;
			Set<Region> availWarehouseSet = new HashSet<Region>();
			
			for (SomeClass sc : someClassList) {
				if (!availWarehouseSet.contains(sc.inventory.shipFrom)) {
					totalAvailQuantity += sc.inventory.quantity;
					availWarehouseSet.add(sc.inventory.shipFrom);
				}				
			}
			//Kill all the orders that impossible to complete, and keep the orders can be completed
			if (totalAvailQuantity >= currentOrder.needQuantity) {
				ProcessedOrder newProcessedOrderNode = new ProcessedOrder(currentOrder, totalAvailQuantity, totalAvailQuantity - currentOrder.needQuantity);
				//this set is depend on every product
				newProcessedOrderNode.availWarehouseSet = availWarehouseSet;
				maxHeap.offer(newProcessedOrderNode);
			}
		}
				
		while (!maxHeap.isEmpty()) {
			ProcessedOrder po = maxHeap.poll();	

			if (po.quantityLeft < 0) {
				break;
				//skip the order which has been finished or empty order
			} else if (po.order.needQuantity <= 0) {
				continue;
			}

			int productId = po.order.productId;
			int quantityNeeded = po.order.needQuantity;
			//for output
			ShippingSolution solution = new ShippingSolution(po.order);
			Map<Region, Inventory> warehouseReversionMap = productToInventoryMap.get(productId);
			int currentTotalAvailQuantity = 0;
			
			for (Region warehouse : po.availWarehouseSet) {
				currentTotalAvailQuantity += productToInventoryMap.get(productId).get(warehouse).quantity;
			}
			//skip the order which is no more can be finished
			if (currentTotalAvailQuantity < quantityNeeded) {
				continue;
			}
			
			for (Region warehouse : po.availWarehouseSet) {
				Inventory currentIvt = warehouseReversionMap.get(warehouse);
				int reversionQuantity = currentIvt.quantity;

				if (reversionQuantity == 0) {
					continue;
				}
				//for output
				solution.warehouseList.add(warehouse);
				solution.quantityList.add(Math.min(reversionQuantity, quantityNeeded));

				if (reversionQuantity >= quantityNeeded) {
					reversionQuantity -= quantityNeeded;
					quantityNeeded = 0;
				} else {
					quantityNeeded -= reversionQuantity;
					reversionQuantity = 0;
				}
                //update map
				warehouseReversionMap.put(warehouse, new Inventory(currentIvt.shipFrom, currentIvt.productId, reversionQuantity));

				if (quantityNeeded == 0) {
					break;
				}
			}	

			productToInventoryMap.put(productId, warehouseReversionMap);
			orderFinishNumber++;
			//Output
			shippingList.add(solution);	
		}
		
		double finishRate = (orderFinishNumber * 1.0) / OrderEntryList.size();
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(2);
		System.out.println("total orders are " + OrderEntryList.size() + ", filled orders are " + orderFinishNumber + ", filled rate is " + nt.format(finishRate));
		System.out.println();
		return shippingList;
	}
	
//	public static List<Integer> Problem3(List<ShippingSolution> shippingList) {
//		
//	}
	
	public static void main(String[] args) {
		int productId = 1;
		Region destination = Region.NORTH;
		int productQuantity = 3;
		
//		aMyTestClass testCase = new aMyTestClass();
//		Inventory[] inventoryArray = testCase.getInventoryList();
//		ShoppingCost[] shoppingCostArray = testCase.getShippingCostList();
//		List<Order> orderList = testCase.getOrderList();
		
		testClass.testData();
		Inventory[] inventoryArray = testClass.inventoryList;
		ShoppingCost[] shoppingCostArray = testClass.shippingCostList;
		List<Order> orderList = testClass.OrderList;
		
		aMyTest testFunc = new aMyTest(inventoryArray, shoppingCostArray);		
		List<SomeClass> costList = Problem1(productId, destination);
	
		System.out.println("******************** Problem1 ********************");
		for (SomeClass sc : costList) {
			System.out.println("warehouse [" + sc.inventory.shipFrom + "], productId [" + sc.inventory.productId + "]");
			
			for (ShoppingCost subSc : sc.CostList) {
				System.out.println("from [" + subSc.shipFrom + "] to [" + subSc.shipTo + "], cost = [" + subSc.cost + "], need days = [" + subSc.shippingDays + "]");
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("******************** Problem2 ********************");
		List<ShippingSolution> shippingList = Problem2(orderList);
		
		for (int i = 0; i < shippingList.size(); i++) {
			ShippingSolution ss = shippingList.get(i);
			
			System.out.println("order[" + (i+1) + "]: product is " + ss.order.productId + ", quantity needs " + ss.order.needQuantity);
			
			for (int j = 0; j < ss.warehouseList.size(); j++) {
				Region warehouse = ss.warehouseList.get(j);
				int quantity = ss.quantityList.get(j);
				System.out.println("from [" + warehouse + "] to [" + ss.order.destination + "], shipping quantity is " + quantity);				
			}
			System.out.println();
		}	
		System.out.println();
	}
}	

class ProcessedOrder {
	Order order;
	Set<Region> availWarehouseSet;
	int availQuantity;
	int quantityLeft;
	
	public ProcessedOrder(Order order, int availQuantity, int quantityLeft) {
		this.order = order;
		this.availQuantity = availQuantity;
		this.quantityLeft = quantityLeft;
		availWarehouseSet = null;
	}
}

class ShippingSolution {
	Order order;
	List<Region> warehouseList;
	List<Integer> quantityList;
	
	public ShippingSolution(Order order){
		this.order = order;
		warehouseList = new LinkedList<Region>();
		quantityList = new LinkedList<Integer>();
	}
}
