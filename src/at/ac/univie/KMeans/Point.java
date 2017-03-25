package at.ac.univie.KMeans;

import java.util.ArrayList;

public class Point {
	ArrayList<Float> coordinates;

	public Point() {}

	public Point(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		String coord = "[ ";
		
		for(int i=0;i<coordinates.size();i++){
			if(i==0){
				coord+=coordinates.get(i) + " ; ";
			}else if(i!=coordinates.size()-1){
				coord+= coordinates.get(i) + " ; ";
			}else{
				coord+=coordinates.get(i);
			}
		}
		coord+= "]";
		return coord;
	}

}
