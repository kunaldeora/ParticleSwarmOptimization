package mealPalRouteOptimization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Particle {
	
	List<Double> x;
	double fitnessValue;
	List<Double> pBest;
	double pBestValue;
	List<Double> velocity;
	
	
	
	
	public Particle(double[] initializeArr, int noOfCustomer){
		
		x = new ArrayList<Double>();
		pBest= new ArrayList<Double>();
		velocity = new ArrayList<Double>();
			
		for(int i=0;i<initializeArr.length;i++){
			this.x.add(initializeArr[i]);
		}
		
		this.pBest = x; // pBest will be same as X in the first iteration 
		
		//random double numbers generated from 0-7
		for(int i=0;i<initializeArr.length;i++){
			double randNumb = randomInRange(noOfCustomer);
			//System.out.println("The random number is :" + randNumb);
			velocity.add(randNumb);
		}
		
		fitnessValue = Double.MAX_VALUE;
		pBestValue = Double.MAX_VALUE;
	}

	

	@Override
	public String toString() {
		return "Particle [x=" + x + ", fitnessValue=" + fitnessValue + ", pBest=" + pBest + ", pBestValue=" + pBestValue
				+ ", velocity=" + velocity + "]";
	}



	public List<Double> getpBest() {
		return pBest;
	}
	public void setpBest(List<Double> pBest) {
		this.pBest = pBest;
	}
	public List<Double> getVelocity() {
		return velocity;
	}
	public void setVelocity(List<Double> velocity) {
		this.velocity = velocity;
	}
	public List<Double> getX() {
		return x;
	}
	public void setX(List<Double> x) {
		this.x = x;
	}
	public double getFitnessValue() {
		return fitnessValue;
	}
	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
	
	
	public static double randomInRange(int max) {
		
		  double min = 0.0; 
		  Random random = new Random();
		  double range = max - min;
		  double scaled = random.nextDouble() * range;
		  double shifted = scaled + min;
		  return shifted; // == (rand.nextDouble() * (max-min)) + min;
		  
		}

}
