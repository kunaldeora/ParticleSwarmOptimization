package mealPalRouteOptimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Swarm {
	
	double gBestValue;
	List<Double> gBestArray;
	Particle[] listOfParticle;
	double[] initialArray = {1,2,3,4,5,6,7};
	List<Double> bestSolution = new ArrayList<>();
	
	
	
	

	public Swarm(int numOfParticle, int numOfCust, double[][] adjacencyMatrix){
		
		listOfParticle = new Particle[numOfParticle];
		for(int i=0;i<numOfParticle;i++){
			listOfParticle[i] = new Particle(shuffleArray(initialArray),numOfCust);
			listOfParticle[i].fitnessValue = calcFitnessValue(listOfParticle[i].x, adjacencyMatrix);		
			listOfParticle[i].pBestValue = calcFitnessValue(listOfParticle[i].pBest, adjacencyMatrix);		
			
		}
		
		
		
		gBestArray = new ArrayList<>();
		gBestValue = Double.MAX_VALUE;
		
		updateGBestValue(listOfParticle);
		
	}
	
	public void printEveryIteration(int iteration){
		
		System.out.print(iteration + "\t\t");
		
		for(Particle e : listOfParticle){
			System.out.print(e.fitnessValue +"\t" + e.pBestValue+"\t\t");
		}
		
		System.out.println(gBestValue);
		
	}
	
	
	public void printValue(){
		
		
		System.out.println("The number of particles in the swarm are :" + listOfParticle.length);
		
		for(Particle p: listOfParticle){
			System.out.println(p);
		}
				
		System.out.println("GbestArray="+gBestArray+"   GBestValue = "+gBestValue+"   BestSolutionArray = "+bestSolution+" ");
		System.out.println();
		
	}
	
	public static double[] shuffleArray(double[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
                int change = i + random.nextInt(n - i);
                swap(a, i, change);
        }
        
        return a;
	}
	
	private static void swap(double[] a, int i, int change) {
        double helper = a[i];
        a[i] = a[change];
        a[change] = helper;
	}
	
	public double calcFitnessValue(List<Double> xSolution,double[][] adjacencyMatrix){
		double sum =0;
		double flag = 0;
		for(int i=0;i<xSolution.size();i++){
			double k = xSolution.get(i);
			sum+=adjacencyMatrix[(int) flag][(int) k];
			flag = k;
		}
		sum+=adjacencyMatrix[(int) flag][0];
		return sum;
	}
	
	
	public void generateOptimizedSolution(double[][] adjacencyMatrix){
	
		// calculate new velocity
		calculateNewVelocity(listOfParticle);
		
		// calculate new solution
		calculateNewSolutionForTheParticle(listOfParticle);

		
		for(int i=0;i<listOfParticle.length;i++){
			
			Particle currParticle = listOfParticle[i];
			
			currParticle.fitnessValue = calcFitnessValue(currParticle.x, adjacencyMatrix);		
			
			if(currParticle.fitnessValue < currParticle.pBestValue ){				
				currParticle.pBest = currParticle.x;
				currParticle.pBestValue = calcFitnessValue(currParticle.pBest, adjacencyMatrix);				
			}
			
		}
		
		updateGBestValue(listOfParticle);
		
				
	}
	
	public void updateGBestValue(Particle[] listOfParticle){

		double min = Double.MAX_VALUE;
		List<Double> test = new ArrayList<>();
		
		
		for(int i=0;i<listOfParticle.length;i++){
			double fitness = listOfParticle[i].pBestValue;
			
			if(fitness<min){
				min = fitness;
				test = listOfParticle[i].pBest;
				
			}

		}
		
		gBestValue = min;
		gBestArray = test;
		
	}
	
	public void calculateNewVelocity(Particle[] listOfParticle){
		final double w = 0.6;
		final double theta1 = 0.2;
		final double theta2 = 0.2;
		final double beta1 = 0.3;
		final double beta2 = 0.5;

		for(int i=0;i<listOfParticle.length;i++){
			
			List<Double> newVelocity = new ArrayList<>();
			
			Particle testParticle = listOfParticle[i];
			
			List<Double> oldVelocity = testParticle.velocity;
			
			for(int k=0;k<oldVelocity.size();k++){
				
				double calcVelocity = w * oldVelocity.get(k) + 
						theta1*beta1*(testParticle.pBest.get(k)-testParticle.x.get(k)) +
						theta2*beta2*(gBestArray.get(k)-testParticle.x.get(k));
				newVelocity.add(calcVelocity);
				
				
			}
			
			listOfParticle[i].velocity = newVelocity;
		}
		
	}
	
	public void calculateNewSolutionForTheParticle(Particle[] listOfParticle){
		
		
		for(int i=0;i<listOfParticle.length;i++){
			Particle testParticle = listOfParticle[i];
			List<Double> newSolution = new ArrayList<Double>();
			List<Double> oldSolution = testParticle.x;
			
			for(int k=0;k<oldSolution.size();k++){
				double newVal = (oldSolution.get(k)+ testParticle.velocity.get(k) > oldSolution.size() ? oldSolution.size() : oldSolution.get(k)+ testParticle.velocity.get(k)); 
				newSolution.add(newVal);
			}
			
			testParticle.x = newSolution;
		}
		
		

	}
	
	public List<Double> getBestSolutionOfSwarm(){
		
		HashMap<Double, List<Double>> myHashMap = new HashMap<Double,List<Double>>();

		
		for(int i=0;i<gBestArray.size();i++){

			if(myHashMap.get(gBestArray.get(i)) == null){
				myHashMap.put(gBestArray.get(i), new ArrayList<Double>());
			}
			
			myHashMap.get(gBestArray.get(i)).add((double) i);
	
		}
		
		
		Collections.sort(gBestArray);
		
		
		for(int k=0;k<gBestArray.size();k++){
			
			double val = gBestArray.get(k);
			
			if(myHashMap.get(val).size()>1){
				
				for(int j=0;j< myHashMap.get(val).size();j++){
					
					double valToAdd = myHashMap.get(val).get(j);
					bestSolution.add(valToAdd +1);
					k++;
				}
				
				
				
			}else{
				bestSolution.add(myHashMap.get(val).get(0) +1);	
			}
		}
		
		return bestSolution;
		
	}
	
	public List<Double> analysisOfTheDelivery(List<Double> bestSolution,MealPal mp){
		
		List<Double> tripDelivery = new ArrayList<>();
		tripDelivery.add(0.0);
		int vehicleCapacity = mp.MAX_VEHICLE_CAPACITY;
		 
		for(int i=0;i<bestSolution.size();i++){
			
			double custNumber = bestSolution.get(i);
			int custDemand = mp.customers[(int) custNumber-1].demand;
			
			  if(custDemand <= vehicleCapacity){
				  tripDelivery.add(custNumber);
				  vehicleCapacity = vehicleCapacity - custDemand;
			   }else{
				  tripDelivery.add(0.0);
				  vehicleCapacity = mp.MAX_VEHICLE_CAPACITY;
				  tripDelivery.add(custNumber);
			   }
		}
		
		tripDelivery.add(0.0);
		
		return tripDelivery;
	}
	
	public int countNumberOfTrips(List<Double> trip){
		
		int count =0;
		for(int k=0;k<trip.size();k++){
			
			if(trip.get(k)==0){
				count++;
			}
			
		}
		count--;
		return count;
	}
	
	
	
	
	
	
	
	public double getgBestValue() {
		return gBestValue;
	}

	public void setgBestValue(double gBestValue) {
		this.gBestValue = gBestValue;
	}



	public List<Double> getgBestArray() {
		return gBestArray;
	}

	public void setgBestArray(List<Double> gBestArray) {
		this.gBestArray = gBestArray;
	}

	public double[] getInitialArray() {
		return initialArray;
	}

	public void setInitialArray(double[] initialArray) {
		this.initialArray = initialArray;
	}

	public Particle[] getListOfParticle() {
		return listOfParticle;
	}
	public void setListOfParticle(Particle[] listOfParticle) {
		this.listOfParticle = listOfParticle;
	}
	
	
	@Override
	public String toString() {
		return "Swarm [gBestValue=" + gBestValue + ", gBestArray=" + gBestArray + ", listOfParticle="
				+ Arrays.toString(listOfParticle) + ", bestSolution=" + bestSolution + "]";
	}
	
	
	
	
}
