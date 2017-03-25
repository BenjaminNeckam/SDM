package at.ac.univie.KMeans;

import java.util.ArrayList;

public class Point {
	ArrayList<Double> coordinates;

	public Point() {}

	public Point(ArrayList<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public ArrayList<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Double> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		//TODO
		return null;
	}

}
