package HSBC;

import java.util.ArrayList;
import java.util.List;

public class Program {
	public static List<Items> itemList() {
		List<Items> res = new ArrayList<>();
		return res;
	}
	public static void main(String[] args) {
		List<Items> items = itemList();
		double TaxSum = 0;
		double priceSum = 0;
		for (Items item : items) {
			Taxing t = new Taxing();
			TaxSum += t.createTax(item);
			priceSum += item.getPrice();
			
		}
		System.out.println(priceSum);
		System.out.println(TaxSum);
	}
}
