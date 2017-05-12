package mealPalRouteOptimization;

public class Customer {
	
	String custName;
	int location;
	int demand;
	
	
	public Customer(int demand){
		
		this.demand = demand;
	}
	
	@Override
	public String toString() {
		return "Customer [demand=" + demand + "]";
	}
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	
	

}
