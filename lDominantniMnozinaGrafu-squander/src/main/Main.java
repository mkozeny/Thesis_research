package main;

import graph.ExtendedNode;
import graph.GraphExtension;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphExtension ge = new GraphExtension(10,1,3);
		ge.generateGraph();
		//GraphExecutor grex = new GraphExecutor(2,ge.getExtendedNodes());
		ExtendedNode [] result = ge.solveLDominantniMnozinaGrafuProblem();
		System.out.println("Size: "+result.length);
		for(ExtendedNode node:result)
		{
			System.out.println("Node: "+node.getValue());
		}
	}

}