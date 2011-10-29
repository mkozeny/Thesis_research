package main;

import java.util.HashSet;
import java.util.Set;

import graph.Edge;
import graph.GraphExtension;
import graph.Node;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphExtension ge = new GraphExtension(25);
		ge.generateGraph();
		Set<Node> resultA = new HashSet<Node>();
		Set<Node> resultN = new HashSet<Node>();
		Set<Edge> commonEdges = new HashSet<Edge>();
		ge.solveZobecnenaBisekcniSirkaGrafuProblem(resultA, resultN, commonEdges, 7, 7);
		System.out.println("---SET A---");
		for(Node n:resultA)
		{
			System.out.print("Node: "+n.getValue()+" ");
		}
		System.out.println();
		System.out.println("---SET N---");
		for(Node n:resultN)
		{
			System.out.print("Node: "+n.getValue()+" ");
		}
		System.out.println();
		System.out.println("---COMMON EDGES("+commonEdges.size()+")---");
		for(Edge e:commonEdges)
		{
			System.out.print("Src node: "+e.getSrc().getValue()+" ");
			System.out.print("Dest node: "+e.getDest().getValue()+", ");
		}
		System.out.println();
	}

}
