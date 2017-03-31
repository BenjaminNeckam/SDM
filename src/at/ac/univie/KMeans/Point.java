package at.ac.univie.KMeans;

import java.util.ArrayList;

public class Point {
	ArrayList<Float> coordinates;
	int clusterNumb;

	public Point() {}

	public Point(ArrayList<Float> coordinates, int clusterNumb) {
		this.coordinates = coordinates;
		this.clusterNumb = clusterNumb;
	}

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}

	
	public int getClusterNumb() {
		return clusterNumb;
	}

	public void setClusterNumb(int clusterNumb) {
		this.clusterNumb = clusterNumb;
	}

	public void addNewCoordinate(float value){
		if(this.coordinates==null){
			this.coordinates = new ArrayList<>();
			this.coordinates.add(value);
		}else{
			this.coordinates.add(value);
		}
	}
	
	/**
	 * Overwrite an exitisting coordinate
	 * @param index
	 * @param value
	 */
	public void overwriteCoordinate(int index,float value){
		if(this.coordinates==null){
			this.coordinates = new ArrayList<>();
			this.coordinates.add(value);
		}else{
			this.coordinates.remove(index);
			this.coordinates.add(value);
		}
	}
	
	@Override
	public String toString() {
		String coord = "Cluster: " + getClusterNumb() + " -> [ ";
		
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
