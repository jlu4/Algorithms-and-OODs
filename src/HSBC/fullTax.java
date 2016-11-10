package HSBC;

public class fullTax {
    private double tax;
    public fullTax(Items item) {
 	 setTax(item.getPrice() * 0.15);
	     System.out.println("This is baseSalesTax");
    }
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	} 

}
