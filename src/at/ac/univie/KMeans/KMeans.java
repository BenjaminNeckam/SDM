package at.ac.univie.KMeans;

import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import at.ac.univie.Plot.Plot2D;


public class KMeans {
	
	public static void main(String[] args) {
		int dimension = 2;
		int numbClusters = 3;
		int numbPoints = 100;
		float standardDeviation = 1;
		float minDistance = 2;
		int minValue = 0;
		int maxValue = 20;
		GaussianClusterGenerator generator = new GaussianClusterGenerator(standardDeviation, minDistance, minValue, maxValue);
		//Initial Strategie b)
		ArrayList<Point> points = generator.randomPoints(numbClusters, numbPoints, dimension);
		//Initial Strategie a)
		//Potentialy not right
		//ArrayList<Point> points = generator.randomPartition(numbClusters, numbPoints, dimension);
		
		Plot2D scatterplotdemo4 = new Plot2D("K-Means",points,numbClusters);
        scatterplotdemo4.pack();
        RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
        scatterplotdemo4.setVisible(true);
        
        KMeans.lloyd(points,numbClusters);
	}
	
	public static ArrayList<Point> lloyd(ArrayList<Point> points, int k){
		//Mean of coordinates of points
		//TODO change that mean of x and y calculated individually to create new Point
		float[] mean = new float[3];
		for(int i=0;i<points.size();i++){
			mean[i%k]+=meanOfPoint(points.get(i));
		}
		for(int i=0;i<k;i++){
			System.out.println(mean[i]);
		}
		
		return null;
	}
	
	public static float meanOfPoint(Point point){
		int size = point.getCoordinates().size();
		float result=0;
		for(Float value:point.getCoordinates()){
			result+=value;
		}
		result=result/size;
		return result;
	}
}
