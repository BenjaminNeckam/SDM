package at.ac.univie.KMeans;

import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import at.ac.univie.Plot.Plot2D;

public class KMeans {

	public static void main(String[] args) {
		int dimension = 2; //Dimension
		int numbClusters = 3; //Number of clusters
		int numbPoints = 100; //Total number of points
		float standardDeviation = 5; //Standarddeviation for generation of points with Gaussian distribution
		float minDistance = 2; //Minimum distance between the main points for good distribution
		int minValue = 0; // *For data generation* Minimum value for main point
		int maxValue = 20; // *For data generation* Maximu value for main point
		boolean lloydA=false; //Parameter for initial strategy a) (lloydA = true) and strategy b) (lloydA = false)
		
		//Lloyd
		GaussianClusterGenerator generator = new GaussianClusterGenerator(standardDeviation, minDistance, minValue,
				maxValue);
		ArrayList<Point> points = generator.randomPoints(numbClusters, numbPoints, dimension);
		
		Plot2D scatterplotdemo4 = new Plot2D("K-Means Start", points, numbClusters);
		scatterplotdemo4.pack();
		RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
		scatterplotdemo4.setVisible(true);

		//Lloyd algorithm call
		KMeans.lloyd(points, numbClusters, dimension,lloydA);
		
		Plot2D scatterplotdemo42 = new Plot2D("K-Means Clustered", points, numbClusters);
		scatterplotdemo42.pack();
		RefineryUtilities.centerFrameOnScreen(scatterplotdemo42);
		scatterplotdemo42.setVisible(true);
		//Lloyd finish
	}
	
	/**
	 * Llyod algorithm
	 * @param points
	 * @param k
	 * @param d
	 */
	public static void lloyd(ArrayList<Point> points, int k, int d,boolean lloydA) {
		ArrayList<Point> centroids;
		ArrayList<Integer> control = new ArrayList<>();
		Random random = new Random();
		int numb;
		if(lloydA==true){
			centroids = getCentroidOfCluster(points, d, k);
		}else{
			centroids= new ArrayList<>();
			for(int i=0;i<k;i++){
				do{
					numb = random.nextInt(points.size());
				}while(control.contains(numb));
				control.add(numb);
				centroids.add(points.get(numb));
				centroids.get(i).setClusterNumb(i);
			}
			
			
		}
		
		ArrayList<Point> centroidsTmp = new ArrayList<>();
		int counter=0;
		
		do{
			counter++;
			centroidsTmp.clear();
			centroidsTmp.addAll(centroids);
			
			for(Point point:points){
				distAndChangeCluster(point, centroids);
			}
			
			centroids.clear();
			ArrayList<Point> tmp = new ArrayList<>(getCentroidOfCluster(points, d, k));
			
			for(int i=0;i<tmp.size();i++){
				centroids.add(tmp.get(i));
			}
//			
//			System.out.println("Centroids: \n");
//			for(Point point:centroids){
//				System.out.println(point.toString());
//			}
//			System.out.println("CentroidsTmp: \n");
//			for(Point point:centroidsTmp){
//				System.out.println(point.toString());
//			}
//			System.out.println("Equality: " + equals(centroids, centroidsTmp));
		}while(!equalsList(centroids, centroidsTmp));
		System.out.println("Iterations until convergence: " + counter + "\n");
	}

	/**
	 * Computes the centroids of all clusters 
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
				//System.out.println("Cluster " + i + " points: " + numbPointsInCluster + "\n");
				centroid.get(i).addNewCoordinate(zaehler[i] / numbPointsInCluster);
			}
			for (int i = 0; i < k; i++) {
				zaehler[i] = 0;
			}
			counter++;
		} while (counter < d);
		
		return centroid;
	}

	/**
	 * Counts the number of points within a cluster
	 * @param points
	 * @param clusterNumb
	 * @return
	 */
	public static int CountNumberPointsOfCluster(ArrayList<Point> points, int clusterNumb) {
		int counter = 0;
		for (Point point : points) {
			if (point.getClusterNumb() == clusterNumb) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Computes the distance between a specific point and all centroids
	 * Changes the clusternumber of that point if a distance to another centroid is shorter
	 * @param point
	 * @param centroids
	 */
	public static void distAndChangeCluster(Point point,ArrayList<Point> centroids){
		//float tmp=Math.abs(getCentroidPoint(centroids, point.getClusterNumb()).getL2Norm()-normPoint);
		float tmp = dist(point,centroids.get(point.getClusterNumb()));
		float tmpDist=0;
		for(Point centroid:centroids){
			tmpDist=dist(point,centroid);
			if(tmpDist<tmp){
				tmp=tmpDist;
				point.setClusterNumb(centroid.getClusterNumb());
			}
		}
	}
	
	/**
	 * Get centroid by clusternumber
	 * @param centroids
	 * @param clusterNumb
	 * @return
	 */
	public static Point getCentroidPoint(ArrayList<Point> centroids, int clusterNumb){
		for(Point centroid:centroids){
			if(centroid.getClusterNumb()==clusterNumb){
				return centroid;
			}
		}
		return null;
	}
	
	/**
	 * Computes the distance between two points
	 * @param point1
	 * @param point2
	 * @return
	 */
	public static float dist(Point point1, Point point2){
		float sum=0;
		for(int i=0;i<point1.getCoordinates().size();i++){
			sum+=Math.pow(point1.getCoordinates().get(i)-point2.getCoordinates().get(i), 2);
		}
		float norm=(float)Math.sqrt(sum);
		return norm;
	}
	
	/**
	 * Checks if two lists are completely (size and elements) equal 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean equalsList(ArrayList<Point> list1, ArrayList<Point> list2){
		int counter=0;
		float epsilon=(float) 0.0000001;
		if(list1.size()==list2.size()){
			for(Point point:list1){
				for(int i=0;i<point.getCoordinates().size();i++){
					//System.out.println("Comparison: " + point.getCoordinates().get(i) + " : " + list2.get(counter).getCoordinates().get(i) + "\n");
					if(Math.abs((point.getCoordinates().get(i)-list2.get(counter).getCoordinates().get(i)))>=epsilon){
						return false;
					}
				}
				counter++;
			}
			return true;
		}else{
			return false;
		}
	}
	
}
