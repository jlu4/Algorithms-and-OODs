package HSBC;

public class baseTax {
	 private double tax;
     public baseTax(Items item) {
    	 System.out.println("This is baseSalesTax");
    	 setTax(item.getPrice() * 0.1);
     }
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
}
