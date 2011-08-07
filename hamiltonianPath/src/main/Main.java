package main;

import graph.Edge;
import graph.Graph;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphExecutor graph = new GraphExecutor(10);
		graph.generateGraph();
		graph.solveHamiltonianPathRecursively();
		for(Edge e:graph.getResult())
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+", dest: "+e.getDest().getValue());
		}
	}

}
