package mealPalRouteOptimization;

import java.util.Random;

public class MatrixInit {
	
	
	double[][] adjacencyMatrix;
	
	public MatrixInit(int noOfCust){
		
		int cust = noOfCust +1;
		adjacencyMatrix = new double[cust][cust];
		
		for(int i=0;i<cust;i++){
			for(int j=i;j<cust;j++){
				if(i==j){
					adjacencyMatrix[i][j] = 0;	
				}
				else{
					double rand = randomInRange();
					adjacencyMatrix[i][j] = Math.round(rand);
					adjacencyMatrix[j][i] = Math.round(rand);
				}
			}
		}
		
		
	}
	
	
	
	public static double randomInRange() {
		  
		  double max = 63.0;
		  double min = 12.0; 
		  Random random = new Random();
		  double range = max - min;
		  double scaled = random.nextDouble() * range;
		  double shifted = scaled + min;
		  return shifted; // == (rand.nextDouble() * (max-min)) + min;
		  
		}
	
	
	
	
	

}
