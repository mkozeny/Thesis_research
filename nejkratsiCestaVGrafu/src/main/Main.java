package main;

import java.util.Random;

import graph.Edge;
import graph.GraphExtension;
import graph.Node;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphExtension ge = new GraphExtension(10,30,0);
		ge.generateGraph();
		Node [] nodes = new Node [ge.getNodes().size()];
		ge.getNodes().toArray(nodes);
		Random nodeChooser = new Random();
		ge.setStartNode(nodes[nodeChooser.nextInt(ge.getNodes().size()-1)]);
		//nodeChooser = new Random();
		ge.setEndNode(nodes[nodeChooser.nextInt(ge.getNodes().size()-1)]);
		//GraphExecutor grex = new GraphExecutor(2,ge.getExtendedNodes());
		Edge [] result = ge.solveShortestPath(0, ge.getStartNode(),ge.getEndNode());
		//Edge [] result = ge.solveShortestPath();
		System.out.println("Size: "+result.length);
		int cost = 0;
		System.out.println("STARTED NODE: "+ge.getStartNode().getValue());
		System.out.println("END NODE: "+ge.getEndNode().getValue());
		for(Edge e:result)
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+", dest: "+e.getDest().getValue()+", cost: "+e.getCost());
			cost += e.getCost();
		}
		System.out.println("COST: "+cost);

	}

}
