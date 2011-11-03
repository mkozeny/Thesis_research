package main;

import graph.GraphExtension;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphExtension ge = new GraphExtension(20,7);
		ge.generateGraph();
		ge.solveZobecnenaBisekcniSirkaGrafuProblem();
		boolean [] bestConfiguration = ge.getBestConfiguration().getConfiguration();
		System.out.println("---BEST CONFIGURATION---");
		for(int i = 0; i<bestConfiguration.length;i++)
		{
			if(bestConfiguration[i])
				System.out.println("Node: "+i);
		}
		System.out.println();
		System.out.println("---COUNT OF COMMON EDGES---");
		System.out.println(ge.getBestConfiguration().getCountOfCommonEdges());
	}

}
