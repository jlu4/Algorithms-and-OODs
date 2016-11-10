package HSBC;

abstract class Items {
	private String type;
	private boolean isImport;
	private boolean isSpecial;
	private double price;
    
    public Items() {
    }
    
	public boolean isSpecial() {
		return isSpecial;
	}
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
    public boolean isImport() {
		return isImport;
	}
	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
