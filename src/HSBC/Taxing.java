package HSBC;

public class Taxing implements taxingFactory<Object> {

	@Override
	public double createTax(Items item) {
		// TODO Auto-generated method stub
		if (item.isSpecial() && item.isImport())
			return new fullTax(item).getTax();
		if (item.isSpecial()) 
		    return new baseTax(item).getTax();
		if (item.isImport())
		    return new importTax(item).getTax();
		return 0;
	}

}
