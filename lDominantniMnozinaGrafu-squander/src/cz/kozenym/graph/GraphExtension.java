/*package graph;

import graph.ExtendedNode.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphExtension extends Graph {

	public GraphExtension(int n) {
		super(n);
		
	}
	@Override
	public void generateGraph()
	{
		super.generateGraph();
		for(Edge e:this.edges)
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+",  dest: "+e.getDest().getValue());
			Node n;
			this.nodes.remove(e.getSrc());
			e.setSrc(n=new ExtendedNode(e.getSrc().getValue()));
			this.nodes.add(n);
			this.nodes.remove(e.getDest());
			e.setDest(n=new ExtendedNode(e.getDest().getValue()));
			this.nodes.add(n);
		}
		for(Node n:this.nodes)
		{
			
			createNeighbourhood((ExtendedNode) n);
		}
		
	}

	private void createNeighbourhood(ExtendedNode node)
	{
		System.out.println("---STARTING SEARCHING---");
		List<ExtendedNode> actualNodes = new ArrayList<ExtendedNode>();
		for(Node n1:this.nodes)
			actualNodes.add((ExtendedNode)n1);
		actualNodes.remove(node);
		for(ExtendedNode n:actualNodes)
		{
			n.setState(State.FRESH);n.setDistance(Integer.MAX_VALUE);
		}
		node.setState(State.OPENED);node.setDistance(0);
		Queue<ExtendedNode> queue = new LinkedList<ExtendedNode>();
		queue.add(node);
		while(!queue.isEmpty())
		{
			ExtendedNode tmp = queue.poll();
			System.out.println("Node: "+tmp.getValue());
			List<ExtendedNode> directNeighbours = getNodesDirectNeighbours(tmp);
			for(ExtendedNode neighbour:directNeighbours)
			{
				if(neighbour.getState() == State.FRESH)
				{
					System.out.print("Visiting neighbour: "+neighbour.getValue()+";");
					neighbour.setState(State.OPENED);
					neighbour.setDistance(tmp.getDistance()+1);
					queue.add(neighbour);
				}
			}
			System.out.println();
			tmp.setState(State.CLOSED);
		}
	}
	
	private List<ExtendedNode> getNodesDirectNeighbours(ExtendedNode tmp)
	{
		List<ExtendedNode> directNeghbours = new ArrayList<ExtendedNode>();
		for(Edge e:this.edges)
		{
			if(e.getSrc().getValue() == tmp.getValue() && (!directNeghbours.contains(e.getDest())))
				directNeghbours.add((ExtendedNode) e.getDest());
			else if(e.getDest().getValue() == tmp.getValue() && (!directNeghbours.contains(e.getDest())))
				directNeghbours.add((ExtendedNode) e.getSrc());
		}
		return directNeghbours;
	}
}
 */

package cz.kozenym.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cz.kozenym.graph.ExtendedNode.State;
import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;

public class GraphExtension extends Graph {

	private int l;

	private int treshold;

	private Set<ExtendedNode> extendedNodes;

	public GraphExtension(int n, int l, int treshold, int maxGrade) {
		super(n, maxGrade);
		this.l = l;
		this.treshold = treshold;
		this.extendedNodes = new HashSet<ExtendedNode>();
	}

	public void generateGraph(boolean fromPredecessor) {
		if (fromPredecessor)
			super.generateGraph();
		System.out.println("---EDGES---");
		for (Edge e : this.edges) {
			System.out.println("Edge src: " + e.getSrc().getValue()
					+ ",  dest: " + e.getDest().getValue());

		}
		// List<ExtendedNode> actualNodes = new ArrayList<ExtendedNode>();
		for (Node n : this.nodes) {
			extendedNodes.add(new ExtendedNode(n.getValue()));
		}
		for (ExtendedNode n : extendedNodes) {
			createNeighborhood(n);
			// break;
		}
		for (ExtendedNode n : extendedNodes) {
			System.out.println("ACTUAL NODE: " + n.getValue());
			for (ExtendedNode neighbour : n.getNeighbourhood())
				System.out.println("	NEIGHBOUR NODE: " + neighbour.getValue());
		}
		System.out.println("---END OF GRAPH GENERATION---");
	}

	private void createNeighborhood(ExtendedNode node) {
		// System.out.println("---STARTING SEARCHING---");
		Set<ExtendedNode> searchedNodes = new HashSet<ExtendedNode>();
		searchedNodes.addAll(extendedNodes);
		searchedNodes.remove(node);
		for (ExtendedNode n : extendedNodes) {
			n.setState(State.FRESH);
			n.setDistance(Integer.MAX_VALUE);
		}
		node.setState(State.OPENED);
		node.setDistance(0);
		Queue<ExtendedNode> queue = new LinkedList<ExtendedNode>();
		queue.add(node);
		while (!queue.isEmpty()) {
			ExtendedNode tmp = queue.poll();
			if (tmp.getDistance() == this.l)
				break;
			// System.out.println("Node: "+tmp.getValue());
			Set<ExtendedNode> directNeighbours = getNodesDirectNeighbours(tmp,
					searchedNodes);
			for (ExtendedNode neighbour : directNeighbours) {
				if (neighbour.getState() == State.FRESH) {
					// System.out.print("Visiting neighbour: "+neighbour.getValue()+", state: "+neighbour.getState()+";");
					neighbour.setState(State.OPENED);
					neighbour.setDistance(tmp.getDistance() + 1);
					queue.add(neighbour);
					node.getNeighbourhood().add(neighbour);
				}
			}
			// System.out.println();
			tmp.setState(State.CLOSED);
		}
		// actualNodes.add((ExtendedNode) actual);
	}

	private Set<ExtendedNode> getNodesDirectNeighbours(ExtendedNode tmp,
			Set<ExtendedNode> actualNodes) {
		Set<ExtendedNode> directNeghbours = new HashSet<ExtendedNode>();
		for (ExtendedNode n : actualNodes) {
			if (this.matrixOfIncidency[tmp.getValue()][n.getValue()])
				directNeghbours.add(n);

		}
		return directNeghbours;
	}

	public Set<ExtendedNode> getExtendedNodes() {
		return extendedNodes;
	}

	@Ensures({
			"result.elts in this.extendedNodes.elts", /*result set is a subset of all extended nodes*/
			"result.length <= this.treshold", /*length of result set has to be lower or equal than treshold*/
			"result.elts.neighbourhood.elts == this.extendedNodes.elts", /*neighborhoods of all nodes in result set has to cover whole graph*/
			" all q1: result.elts | all q2: (result.elts - q1) |"
					+ " (all n: q2.neighbourhood.elts | n.value!=q1.value)" /*there is no node appearing in result set more than once*/})
	@Modifies({ "result.elts", "result.length" })/*we allow to modify elements of result set and its length*/
	public void solveLDominantSetOfGraphProblem(Set<ExtendedNode> result) {
		Squander.exe(this, result);
	}
}
