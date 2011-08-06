package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Edge;
import main.Node;

public class Test {

	public static void main(String[] args) {
		Graph g = new Graph();
		//example1
		/*Set<Node> nodes = new LinkedHashSet<Node>();
		Node node1 = new Node(1);
		nodes.add(node1);
		Node node2 = new Node(2);
		nodes.add(node2);
		Node node3 = new Node(3);
		nodes.add(node3);
		Node node4 = new Node(4);
		nodes.add(node4);

		Set<Edge> edges = new LinkedHashSet<Edge>();
		Edge edge1 = new Edge(node1, node3, 1);
		edges.add(edge1);
		Edge edge2 = new Edge(node1, node4, 1);
		edges.add(edge2);
		Edge edge3 = new Edge(node2, node3, 1);
		edges.add(edge3);
		Edge edge4 = new Edge(node2, node4, 1);
		edges.add(edge4);
		g.setNodes(nodes);
		g.setEdges(edges);
		Map<Node, Integer> mapping = g.color(2);
		List<Node> result = new ArrayList<Node>();
		result.addAll(mapping.keySet());
		Collections.sort(result);*/
		//end of example1
		
		//example2
		Set<Node> nodes = new LinkedHashSet<Node>();
		Node node1 = new Node(1);
		nodes.add(node1);
		Node node2 = new Node(2);
		nodes.add(node2);
		Node node3 = new Node(3);
		nodes.add(node3);
		Node node4 = new Node(4);
		nodes.add(node4);
		Node node5 = new Node(5);
		nodes.add(node5);
		Node node6 = new Node(6);
		nodes.add(node6);
		Node node7 = new Node(7);
		nodes.add(node7);
		Node node8 = new Node(8);
		nodes.add(node8);
		Node node9 = new Node(9);
		nodes.add(node9);
		Node node10 = new Node(10);
		nodes.add(node10);
		
		
		Set<Edge> edges = new LinkedHashSet<Edge>();
		Edge edge1 = new Edge(node1, node2, 1);
		edges.add(edge1);
		Edge edge2 = new Edge(node2, node3, 1);
		edges.add(edge2);
		Edge edge3 = new Edge(node3, node4, 1);
		edges.add(edge3);
		Edge edge4 = new Edge(node4, node5, 1);
		edges.add(edge4);
		
		
		Edge edge5 = new Edge(node6, node8, 1);
		edges.add(edge5);
		Edge edge6 = new Edge(node6, node9, 1);
		edges.add(edge6);
		Edge edge7 = new Edge(node6, node1, 1);
		edges.add(edge7);
		
		
		Edge edge8 = new Edge(node7, node9, 1);
		edges.add(edge8);
		Edge edge9 = new Edge(node7, node10, 1);
		edges.add(edge9);
		Edge edge10 = new Edge(node7, node2, 1);
		edges.add(edge10);
		
		Edge edge11 = new Edge(node8, node10, 1);
		edges.add(edge11);
		Edge edge12 = new Edge(node8, node1, 1);
		edges.add(edge12);
		Edge edge13 = new Edge(node8, node3, 1);
		edges.add(edge13);
		
		Edge edge14 = new Edge(node9, node6, 1);
		edges.add(edge14);
		Edge edge15 = new Edge(node9, node7, 1);
		edges.add(edge15);
		Edge edge16 = new Edge(node9, node4, 1);
		edges.add(edge16);
		
		Edge edge17 = new Edge(node10, node7, 1);
		edges.add(edge17);
		Edge edge18 = new Edge(node10, node8, 1);
		edges.add(edge18);
		Edge edge19 = new Edge(node10, node5, 1);
		edges.add(edge19);
		
		g.setNodes(nodes);
		g.setEdges(edges);
		Map<Node, Integer> mapping = g.color(3);
		List<Node> result = new ArrayList<Node>();
		result.addAll(mapping.keySet());
		Collections.sort(result);
		//end of example2
		
		for (Node n : result) {
			System.out.println("Node: " + n.label + ", color: "
					+ mapping.get(n));
		}
	}
}