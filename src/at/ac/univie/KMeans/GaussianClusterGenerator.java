package at.ac.univie.KMeans;

import java.util.ArrayList;
import java.util.Random;

public class GaussianClusterGenerator {
	ArrayList<Point> points;
	int deviation;
	double density;
	double minPointDistance;
	double rangeMin;
	double rangeMax;

	public GaussianClusterGenerator(int deviation, double density, double distance,double rangeMin, double rangeMax){
		this.deviation = deviation;
		this.density = density;
		this.minPointDistance = distance;
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
	}

	public ArrayList<Point> generateCluster(int k, int n, int d){
		ArrayList<Point> mainPoints = new ArrayList<>();
		boolean distanceGood = false;
			for(int i=0; i<k;i++) {
				if(i==0) {
					mainPoints.add(new Point(generateCoordinates(d)));
				}else {
					do {
						Point tempPoint = new Point(generateCoordinates(d));
						distanceGood = isDistanceGood(mainPoints.get(i-1), tempPoint, d, minPointDistance);
						if(distanceGood) {
							mainPoints.add(tempPoint);
						}
					}while(!distanceGood);
				}
			}

			for()
	}

	public ArrayList<Double> generateCoordinates(int d){
		ArrayList<Double> coordinates = new ArrayList<>();
		Random random = new Random();
		double temp;
		for(int i=0; i<d;i++) {
			temp = rangeMin + (rangeMax-rangeMin)*random.nextDouble();
			coordinates.add(temp);
		}
		return coordinates;
	}

	public boolean isDistanceGood(Point point1, Point point2, int d, double minPointDistance) {
		ArrayList<Double> coordinates1 = point1.getCoordinates();
		ArrayList<Double> coordinates2 = point2.getCoordinates();
		for(int i=0;i<d;i++) {
			if(coordinates1.get(i)-coordinates2.get(i)<minPointDistance) {
				return false;
			}
		}
		return true;
	}

}
