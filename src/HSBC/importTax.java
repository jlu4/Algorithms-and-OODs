package HSBC;


public class importTax {
       private double tax;
       public importTax(Items item) {
    	 setTax(item.getPrice() * 0.05);
   	     System.out.println("This is baseSalesTax");
       }
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	} 

}
