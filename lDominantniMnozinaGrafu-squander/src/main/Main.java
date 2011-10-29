package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.ExtendedNode;
import graph.GraphExtension;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphExtension ge = new GraphExtension(80,2,10);
		ge.generateGraph();
		//GraphExecutor grex = new GraphExecutor(2,ge.getExtendedNodes());
		ExtendedNode [] result = ge.solveLDominantniMnozinaGrafuProblem();
		//System.out.println("Size: "+result.length);
		Set<ExtendedNode> coveredNodes = new HashSet<ExtendedNode>();
		System.out.println("---CHOOSED NODES("+result.length+")---");
		for(ExtendedNode node:result)
		{
			System.out.println("Node: "+node.getValue());
			coveredNodes.addAll(node.getNeighbourhood());
		}
		System.out.println("---COVERED NODES("+coveredNodes.size()+")---");
		List<ExtendedNode> outputNodes = new ArrayList<ExtendedNode>();
		outputNodes.addAll(coveredNodes);
		Collections.sort(outputNodes);
		for(ExtendedNode node:outputNodes)
		{
			System.out.print(node.getValue()+", ");
		}
		System.out.println();
	}

}
