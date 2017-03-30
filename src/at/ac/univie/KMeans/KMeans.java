package at.ac.univie.KMeans;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

import at.ac.univie.Plot.Plot2D;

public class KMeans {

	private static Scanner scanner;

	public static void main(String[] args) {
		int dimension = 2;
		int numbClusters = 2;
		int numbPoints = 10;
		float standardDeviation = 5;
		float minDistance = 2;
		int minValue = 0;
		int maxValue = 20;
		GaussianClusterGenerator generator = new GaussianClusterGenerator(standardDeviation, minDistance, minValue,
				maxValue);
		//ArrayList<Point> points = generator.randomPoints(numbClusters, numbPoints, dimension);
		
		//FOR TESTS ONLY
		ArrayList<Point> points = new ArrayList<>();
		Point point1 = new Point();
		point1.addNewCoordinate(1);
		point1.addNewCoordinate(2);
		point1.setClusterNumb(0);
		
		Point point2 = new Point();
		point2.addNewCoordinate(3);
		point2.addNewCoordinate(4);
		point2.setClusterNumb(0);
		
		Point point5 = new Point();
		point5.addNewCoordinate((float)3.5);
		point5.addNewCoordinate((float)4.5);
		point5.setClusterNumb(1);
		
		
		Point point3 = new Point();
		point3.addNewCoordinate(5);
		point3.addNewCoordinate(6);
		point3.setClusterNumb(1);
		
		Point point4 = new Point();
		point4.addNewCoordinate(7);
		point4.addNewCoordinate(8);
		point4.setClusterNumb(1);
		
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		//TESTS END
		
		Plot2D scatterplotdemo4 = new Plot2D("K-Means", points, numbClusters);
		scatterplotdemo4.pack();
		RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
		scatterplotdemo4.setVisible(true);

		KMeans.lloyd(points, numbClusters, dimension);
		
		Plot2D scatterplotdemo42 = new Plot2D("K-Means", points, numbClusters);
		scatterplotdemo42.pack();
		RefineryUtilities.centerFrameOnScreen(scatterplotdemo42);
		scatterplotdemo42.setVisible(true);
	}

	public static ArrayList<Point> lloyd(ArrayList<Point> points, int k, int d) {
		ArrayList<Point> centroids = getCentroidOfCluster(points, d, k);
		System.out.println("\nCENTROIDS\n");
		for (Point point : centroids) {
			System.out.println(point.toString() + "\n");
			System.out.println("L2-norm: " + point.getL2Norm() + "\n");
		}
		System.out.println("Before change\n");
		for (Point point : points) {
			System.out.println(point.toString() + "\n");
			
		}
		for(Point point:points){
			distAndChangeCluster(point, centroids);
		}
		System.out.println("After change\n");
		for (Point point : points) {
			System.out.println(point.toString() + "\n");
			
		}
		return null;
	}

	/**
	 * Initial strategy a)
	 * 
	 * @param point
	 * @return
	 */
	public static ArrayList<Point> getCentroidOfCluster(ArrayList<Point> points, int d, int k) {
		ArrayList<Point> centroid = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			centroid.add(new Point());
			centroid.get(i).setClusterNumb(i);
		}
		float[] zaehler = new float[k];
		int counter = 0;
		do {
			for (Point point : points) {
				zaehler[point.getClusterNumb()] += point.getCoordinates().get(counter);
			}
			int numbPointsInCluster = 0;

			for (int i = 0; i < k; i++) {
				numbPointsInCluster = CountNumberPointsOfCluster(points, i);
				centroid.get(i).addNewCoordinate(zaehler[i] / numbPointsInCluster);
			}
			for (int i = 0; i < k; i++) {
				zaehler[i] = 0;
			}
			counter++;
		} while (counter < d);
		return centroid;
	}

	public static int CountNumberPointsOfCluster(ArrayList<Point> points, int clusterNumb) {
		int counter = 0;
		for (Point point : points) {
			if (point.getClusterNumb() == clusterNumb) {
				counter++;
			}
		}
		return counter;
	}
	
	public static void distAndChangeCluster(Point point,ArrayList<Point> centroids){
		float normPoint = point.getL2Norm();
		float tmp=Math.abs(normPoint-getCentroidPoint(centroids, point.getClusterNumb()).getL2Norm());
		for(Point centroid:centroids){
			if(Math.abs(normPoint-centroid.getL2Norm())<tmp){
				tmp=Math.abs(normPoint-centroid.getL2Norm());
				point.setClusterNumb(centroid.getClusterNumb());
			}
		}
	}
	
	public static Point getCentroidPoint(ArrayList<Point> centroids, int clusterNumb){
		for(Point centroid:centroids){
			if(centroid.getClusterNumb()==clusterNumb){
				return centroid;
			}
		}
		return null;
	}
	
}
