package transportation;

public class ShoppingCost {
	int cost;
    int shippingDays;
    Region shipTo;
    Region shipFrom;

    public ShoppingCost(int cost, int shippingdays, Region shipTo, Region shipFrom) {
  	  this.cost = cost;
  	  this.shippingDays = shippingdays;
  	  this.shipTo = shipTo;
  	  this.shipFrom = shipFrom;
    }
    public  ShoppingCost() {
  	  
    }
}
