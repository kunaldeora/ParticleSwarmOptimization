package mealPalRouteOptimization;

import java.util.Arrays;
import java.util.Random;

public class MealPal {
	
	
	Customer[] customers;
	MatrixInit matrix;
	

	final int MAX_CUST_DEMAND = 9;
	final int MAX_VEHICLE_CAPACITY = 15;
	
	public MealPal(int noOfCust){
		matrix = new MatrixInit(noOfCust);
		customers = new Customer[noOfCust];
		Random rand = new Random();
		
		for(int i=0;i<customers.length;i++){
			customers[i] = new Customer(rand.nextInt(MAX_CUST_DEMAND-3+1) + 1);
		}
	}
	
	public void updateDemands(){
		Random rand = new Random();
		for(int i=0;i<customers.length;i++){
			customers[i] = new Customer(rand.nextInt(MAX_CUST_DEMAND-3+1) + 2);
		}
		
		
	}

	
	
	public void printObjects(){
	   System.out.println("The number of customer: " + customers.length);
	   System.out.println("Maximum customer demand : " + MAX_CUST_DEMAND);        
       System.out.println("Maximum vehicle capacity : " + MAX_VEHICLE_CAPACITY);
       
        for(Customer c: customers)
            System.out.println(c);
        System.out.println();
        
        System.out.println("-----------The adjacency matrix to calculate the fitness value-------------");
		
		for(int i=0;i<matrix.adjacencyMatrix.length;i++){
			for(int j=0;j<matrix.adjacencyMatrix.length;j++){
				
				System.out.print(matrix.adjacencyMatrix[i][j]+ "\t");
			}
			
			System.out.println();
		}	
		
		System.out.println();
	}
	

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	public MatrixInit getMatrix() {
		return matrix;
	}

	public void setMatrix(MatrixInit matrix) {
		this.matrix = matrix;
	}

	public int getMAX_CUST_DEMAND() {
		return MAX_CUST_DEMAND;
	}

	public int getMAX_VEHICLE_CAPACITY() {
		return MAX_VEHICLE_CAPACITY;
	}
	
	
	
	
	

}
