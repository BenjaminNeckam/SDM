package at.ac.univie.KMeans;

import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import at.ac.univie.Plot.Plot2D;
import at.ac.univie.interfaces.InitInterface;

public class KMeans implements InitInterface {
	
	public static void main(String[] args) {
		int dimension = 2;
		int numbClusters = 5;
		int numbPoints = 100;
		float standardDeviation = 2;
		float minDistance = 2;
		int minValue = 0;
		int maxValue = 50;
		GaussianClusterGenerator generator = new GaussianClusterGenerator(standardDeviation, minDistance, minValue, maxValue);
		ArrayList<Point> points = generator.generateCluster(numbClusters, numbPoints, dimension);
		
		Plot2D scatterplotdemo4 = new Plot2D("K-Means",points,numbClusters);
        scatterplotdemo4.pack();
        RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
        scatterplotdemo4.setVisible(true);
	}
}
