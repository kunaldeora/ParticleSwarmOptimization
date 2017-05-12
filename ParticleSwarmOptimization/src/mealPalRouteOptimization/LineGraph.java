package mealPalRouteOptimization;

import org.jfree.chart.ChartPanel;

import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineGraph extends ApplicationFrame{
	
	
	
	public LineGraph(String title1 , String title2,HashMap<Integer,Integer> myHash){
		
		super(title1 + " v/s " + title2);
		JFreeChart lineChart = ChartFactory.createLineChart(
		         title1 + " v/s " + title2,
		         title1 , title2,
		         createDataset(myHash, title2),
		         PlotOrientation.VERTICAL,
		         true,true,false);
		         
		      ChartPanel chartPanel = new ChartPanel(lineChart);
		      chartPanel.setPreferredSize(new java.awt.Dimension(1200,900));
		      setContentPane(chartPanel);
		
		
	}
	
	private DefaultCategoryDataset createDataset(HashMap<Integer,Integer> myHash, String title2) {
		
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      for(Integer key :myHash.keySet()){
	    	  dataset.addValue( myHash.get(key), "# " + title2 , key );
	      }
	      
	      
	      return dataset;
	   }
	
	
	
	
	
	

}
