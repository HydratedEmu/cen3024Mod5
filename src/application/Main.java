package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.scene.chart.*;
 
/* info created using different sources along 
 * wiht some help from the lab on using FX Charts:
 * https://docs.oracle.com/javafx/2/charts/line-chart.htm
 * https://www.programiz.com/java-programming/examples/fibonacci-series
 * https://www.youtube.com/watch?v=dsmBRUCzS7k&t=0s&ab_channel=BrandonioProductions
 * https://www.youtube.com/watch?v=k-7jJP7QFEM&ab_channel=CodingwithJohn
 * */

public class Main extends Application {
	
	/* Fibonacci Sequence using the Recursive Method 
	 * USE LONG NOT DOUBLE**
	 * if index is not 0 or 1 or 2 create new Fibonacci term
	 * then return the new Fibonacci term */		
	public static /*double*/long fibRecursiveMethod(int index){
		// if 0,1,2
		if(index == 0) {
			return 0;
		}
		if(index <= 2) {
			return 1;
		}
		// newFibTerm
		long newFibTerm = fibRecursiveMethod(index - 1) + fibRecursiveMethod(index - 2);
		return newFibTerm;
	} // end recursive Fibonacci Method
	
	
	// Fibonacci Sequence using the Iterative Method
	public static long fibIterativeMethod(int index) {
		
		long previousTerm = 0;
		long currentTerm = 1;
		
		if(index == 0) {
			return 0;
		}
		if(index == 1) {
			return 1;
		}
		
		for(int i = 0; i <= index; i++) {
			long nextTermInSequence = previousTerm + currentTerm;
			previousTerm = currentTerm;
			currentTerm = nextTermInSequence;
		}
		
		return currentTerm;
	}//end Iterative Method
	
	
	// Create Chart: 
	// you must define two axes, create the LineChart 
	// object by instantiating the LineChart class	
	
	@Override
	public void start(Stage stage) {
		// stage title
		stage.setTitle("Iterative vs Recursive Fibonacci Chart");
		
		// define X and Y axis
		final NumberAxis yAxis = new NumberAxis();
		final NumberAxis xAxis = new NumberAxis();
		
		// define X and Y axis labels
		yAxis.setLabel("Time: Nanoseconds");
		xAxis.setLabel("Sequence");
		
		// create chart
		final LineChart<Number, Number> lineChart = new
				LineChart<>(xAxis, yAxis);
		
		// set chart title
		lineChart.setTitle("Comparing The Methods");
		
		//define series
		//XYChart.Series<X, Y>
		XYChart.Series recursive = new XYChart.Series<>();
		XYChart.Series iterate = new XYChart.Series<>();
		iterate.setName("Iterative Fibonacci Method");
		recursive.setName("Recursive Fibonacci Method");
		
		//loop through methods for timing in nanoseconds
		for(int i = 0; i <= 50; i++) {
			//iterative
			long beginTime = System.nanoTime();
			long iterativeMethodResult = fibIterativeMethod(i);
			long endTime = System.nanoTime();
			//long iterativeMethodResult = fibIterativeMethod(i);
			long timePassed = endTime - beginTime;
			iterate.getData().add(new XYChart.Data(i, timePassed));
			
			//recursive
			beginTime = System.nanoTime();
			long recursiveMethodResult = fibRecursiveMethod(i);
			endTime = System.nanoTime();
			timePassed = endTime - beginTime;
			recursive.getData().add(new XYChart.Data(i, timePassed));			
		}// end for loop
		
		Scene scene = new Scene(lineChart, 800, 800);
		
		lineChart.getData().add(recursive);
		lineChart.getData().add(iterate);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
