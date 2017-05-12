package mealPalRouteOptimization;

import java.util.List;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;




public class GraphVisualization {
	
	String graphStyleSheet =
            "node { fill-color: black; text-color: white; size: 50px; text-size: 20px;} "+
            "node#0 { shape: box; fill-color: grey;  } "+
            "edge.marked {fill-color: black;}"+        
            "edge { fill-color: red; arrow-shape: arrow; arrow-size: 5px, 5px; }";
	
	public void makeGraphVisualization(List<Double> bestSolution){
		
	
		
		
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		
		Graph myGraph = new MultiGraph("Trip Graph");
		
		myGraph.addAttribute("ui.stylesheet", graphStyleSheet);
		
		myGraph.setAutoCreate(true);
		myGraph.setStrict(false);
		
		myGraph.display(); // displays a empty graph
		
		
		// add all the edges to the to the graph
	    for(int e=0; e<bestSolution.size()-1;e++){                
            myGraph.addEdge(Integer.toString(e), bestSolution.get(e).toString(), bestSolution.get(e+1).toString(), true);
        }
	    
	    for (Node node : myGraph)
            node.addAttribute("ui.label", node.getId());
        
        
	    
	    //
	    
	    for(int i=0; i< myGraph.getEdgeCount(); i++){
            myGraph.getEdge(i).addAttribute("ui.class", "marked");
            //sleepTimer();
        }
	    
	    
	    
		
		
	}
	
	private void sleepTimer() {
        try { Thread.sleep(5000); } catch (Exception e) {}
    }
	
	
	
	
	
	
	
	
	

}
