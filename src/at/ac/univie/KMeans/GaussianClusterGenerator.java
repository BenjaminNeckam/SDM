package at.ac.univie.KMeans;

import java.util.ArrayList;
import java.util.Random;

public class GaussianClusterGenerator {
	ArrayList<Point> points;
	float deviation;
	float minPointDistance;
	float rangeMin;
	float rangeMax;

	/**
	 * Constructor for Gaussian distribution generator
	 * @param deviation
	 * @param distance
	 * @param rangeMin
	 * @param rangeMax
	 */
	public GaussianClusterGenerator(float deviation, float distance,float rangeMin, float rangeMax){
		this.deviation = deviation;
		this.minPointDistance = distance;
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
	}

	/**
	 * Generates the dataset
	 * @param k
	 * @param n
	 * @param d
	 * @return
	 */
	public ArrayList<Point> randomPoints(int k, int n, int d){
		ArrayList<Point> mainPoints = generateMainPoints(k,d);
		mainPoints.addAll(generateGaussianDistPoints(k, n, d, mainPoints));
		//ArrayList<Point> points = generateGaussianDistPoints(k, n, d, mainPoints);
//		System.out.println("Other points:\n");
//		for(Point point:points){
//			System.out.println(point.toString());
//		}
			return mainPoints;
	}
	

	/**
	 * Generates specific coordinates
	 * @param d
	 * @return
	 */
	public ArrayList<Float> generateCoordinates(int d){
		ArrayList<Float> coordinates = new ArrayList<>();
		Random random = new Random();
		float temp;
		for(int i=0; i<d;i++) {
			temp = rangeMin + (rangeMax-rangeMin)*random.nextFloat();
			coordinates.add(temp);
		}
		return coordinates;
	}
	
	/**
	 * Generates coordinates which are gaussian distributed
	 * @param d
	 * @param point
	 * @return
	 */
	public ArrayList<Float> generateGaussianCoordinates(int d, Point point){
		ArrayList<Float> coordinates = new ArrayList<>();
		Random random = new Random();
		float temp;
		for(int i=0; i<d;i++) {
			temp = (float) (point.getCoordinates().get(i)+random.nextGaussian()*deviation);
			coordinates.add(temp);
		}
		return coordinates;
	}
	

	/**
	 * Checks if the distance between two points are higher or equal than the minimum distance
	 * @param point1
	 * @param point2
	 * @param d
	 * @param minPointDistance
	 * @return
	 */
	public boolean isDistanceGood(Point point1, Point point2, int d, float minPointDistance) {
		ArrayList<Float> coordinates1 = point1.getCoordinates();
		ArrayList<Float> coordinates2 = point2.getCoordinates();
		for(int i=0;i<d;i++) {
			if(Math.abs(coordinates1.get(i)-coordinates2.get(i))>=minPointDistance) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Generates the main points
	 * @param k
	 * @param d
	 * @return
	 */
	public ArrayList<Point> generateMainPoints(int k,int d){
		ArrayList<Point> mainPoints = new ArrayList<>();
		boolean distanceGood = false;
		for(int i=0; i<k;i++) {
			if(i==0) {
				mainPoints.add(new Point(generateCoordinates(d),i));
			}else {
				do {
					Point tempPoint = new Point(generateCoordinates(d),i);
					distanceGood = isDistanceGood(mainPoints.get(i-1), tempPoint, d, minPointDistance);
					if(distanceGood) {
						mainPoints.add(tempPoint);
					}
				}while(!distanceGood);
			}
		}
		return mainPoints;
	}
	
	/**
	 * Generates gaussian distributed points around the main points
	 * @param k
	 * @param n
	 * @param d
	 * @param mainPoints
	 * @return
	 */
	public ArrayList<Point> generateGaussianDistPoints(int k,int n,int d,ArrayList<Point> mainPoints){
		ArrayList<Point> points = new ArrayList<>();
		for(int i=0;i<(n-k);i++){
			Point tmp = new Point(generateGaussianCoordinates(d, mainPoints.get(i%k)),i%k);
			points.add(tmp);
		}
		return points;
	}
	
}
