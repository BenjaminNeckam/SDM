package at.ac.univie.KMeans;

import at.ac.univie.interfaces.InitInterface;

public class KMeans implements InitInterface {
	
	public static void main(String[] args) {
		GaussianClusterGenerator generator = new GaussianClusterGenerator(1,1, 2, 0, 10);
		generator.generateCluster(2, 10, 2);
	}
}
