package robot;

public class Customer {
	private int customerId;
	private double balance;
	
	public Customer(int customerId, double initBalance){
		this.customerId = customerId;
		this.balance = initBalance;
	}
	
	public boolean charge(double amount){
		if(balance >= amount){
			balance -= amount;
			return true;
		}
		else{
			return false;
		}
	}
	
	public void refund(double amount){
		balance += amount;
	}
}
