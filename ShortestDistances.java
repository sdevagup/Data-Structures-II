package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */

import algs32.BST;
import algs41.BreadthFirstPaths;
import algs41.Graph;
import stdlib.StdIn;
import stdlib.StdOut;

public class ShortestDistances {

	public static void main(String[] args) {
		
		StdIn.fromFile("data/fecities.txt");

		String[] cities = StdIn.readAllStrings();
		BST<String, Integer> citiesBst = new BST<>();

		for(int i=0;i<cities.length;i++) {
			citiesBst.put(cities[i], i);
		}
		
		StdIn.fromFile("data/femajorcities.txt");

		String[] majorCities = StdIn.readAllStrings();
		
		Graph graph = new Graph(cities.length);
		
		StdIn.fromFile("data/feconnections.txt");

		
		while(StdIn.hasNextLine()) {
			String line = StdIn.readLine();

			String[] connectionCities = line.split("\\s+");
			graph.addEdge(citiesBst.get(connectionCities[0]), citiesBst.get(connectionCities[1]));
			
		}
		
		StdOut.printf("%20s", "");
		for (String majorCity: majorCities) {
			StdOut.printf("%-12s", majorCity);
		}
		StdOut.println();
		
		for(String city : cities) {
			BreadthFirstPaths paths = new BreadthFirstPaths(graph, citiesBst.get(city));
			StdOut.printf("%-20s", city);
			for (String majorCity: majorCities) {
				StdOut.printf("    %3d     ", paths.distTo(citiesBst.get(majorCity)));
			}
			StdOut.println();
			
		}
	}
}
