package mealPalRouteOptimization;

import java.util.List;
import java.util.Map;

import org.jfree.ui.RefineryUtilities;

import java.util.HashMap;

public class PSO {
	
	static List<Double> bestSolution;
	static List<Double> tripDelivery;
	static int numOfTrips;
    static HashMap<Integer,Integer> particleGbest = new HashMap<Integer,Integer>();
	static HashMap<Integer,Integer> particleTrips = new HashMap<Integer,Integer>();

	public static void main(String[] args) {
		
		
		int numOfCustomer = 7;
		int numberOfParticle = 3;		
		int noOfIteration = 10;
		
		System.out.println("---------------------MealPal Details-------------------------");
				
		MealPal mp = new MealPal(numOfCustomer);		
		mp.printObjects();
		
				
		for(int j=0;j<5;j++){
			
			
			
			System.out.println("---------------------Swarm Details----------------------------");
			
			Swarm swarm = new Swarm(numberOfParticle,numOfCustomer,mp.matrix.adjacencyMatrix);		
			swarm.printValue();
			
			
			System.out.println("---------------------Iteration Details-------------------------");
			
			System.out.print("Iteration\t");		
			for(int i=0;i<numberOfParticle;i++){
				System.out.print("f(x)\tf(pBest)\t");
			}
			
			System.out.println("GbestValue");
			
			
			for(int k=0;k<noOfIteration;k++){
				swarm.generateOptimizedSolution(mp.matrix.adjacencyMatrix);
				swarm.printEveryIteration(k);	
			}
			
			System.out.println("--------------------------------------------------------------");
			
			bestSolution = swarm.getBestSolutionOfSwarm();
			
			System.out.println("The best solution obtained after 10 iteration is :-");
			System.out.println("");
			System.out.println("BesSolution = "+bestSolution);
			
			System.out.println("-------------------Analysis of the delivery--------------------");
			
			tripDelivery=swarm.analysisOfTheDelivery(bestSolution,mp);
			
			numOfTrips = swarm.countNumberOfTrips(tripDelivery);
			
			System.out.print("Trip of the delivery:--" +tripDelivery+ "\t\t" + "Number of trips required:-" + numOfTrips + "\t\t");
			
			GraphVisualization graph = new GraphVisualization();
			graph.makeGraphVisualization(tripDelivery);
					
			System.out.println("---------------------------------------------------------------");
			
			
			particleGbest.put(numberOfParticle,(int) swarm.gBestValue);
			particleTrips.put(numberOfParticle, numOfTrips);
		    //noOfIteration = noOfIteration+5;
			numberOfParticle = numberOfParticle+3;
			mp.updateDemands();
		} // end of my first loop
		
		LineGraph ll = new LineGraph("Particle", "Gbest", particleGbest);
		ll.pack( );
	    RefineryUtilities.centerFrameOnScreen( ll );
	    ll.setVisible( true );
	    
	    LineGraph ll1 = new LineGraph("Particle", "Trips", particleTrips);
	    ll1.pack( );
	    RefineryUtilities.centerFrameOnScreen( ll1 );
	    ll1.setVisible( true );
		
		System.out.println("------------End of PSO------------------");

	}

}
