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

package graph;

import graph.ExtendedNode.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphExtension extends Graph {

	private int l;
	
	public GraphExtension(int n, int l) {
		super(n);
		this.l = l;
	}
	@Override
	public void generateGraph()
	{
		super.generateGraph();
		for(Edge e:this.edges)
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+",  dest: "+e.getDest().getValue());
			
			
		}
		List<ExtendedNode> actualNodes = new ArrayList<ExtendedNode>();
		for(Node n:this.nodes)
		{
			actualNodes.add(new ExtendedNode(n.getValue()));
		}
		for(ExtendedNode n:actualNodes)
		{
			createNeighbourhood(n, actualNodes);
			//break;
		}
		
	}

	private void createNeighbourhood(ExtendedNode node, List<ExtendedNode> actualNodes)
	{
		System.out.println("---STARTING SEARCHING---");
		List<ExtendedNode> searchedNodes = new ArrayList<ExtendedNode>();
		searchedNodes.addAll(actualNodes);
		searchedNodes.remove(node);
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
			if(tmp.getDistance()>l)
				break;
			System.out.println("Node: "+tmp.getValue());
			List<ExtendedNode> directNeighbours = getNodesDirectNeighbours(tmp,searchedNodes);
			for(ExtendedNode neighbour:directNeighbours)
			{
				if(neighbour.getState() == State.FRESH)
				{
					System.out.print("Visiting neighbour: "+neighbour.getValue()+", state: "+neighbour.getState()+";");
					neighbour.setState(State.OPENED);
					neighbour.setDistance(tmp.getDistance()+1);
					queue.add(neighbour);
					node.getNeighbourhood().add(neighbour);
				}
			}
			System.out.println();
			tmp.setState(State.CLOSED);
		}
		//actualNodes.add((ExtendedNode) actual);
	}
	
	
	private List<ExtendedNode> getNodesDirectNeighbours(ExtendedNode tmp, List<ExtendedNode> actualNodes)
	{
		List<ExtendedNode> directNeghbours = new ArrayList<ExtendedNode>();
		for(ExtendedNode n:actualNodes)
		{
			if(this.matrixOfIncidency[tmp.getValue()][n.getValue()]==1)
				directNeghbours.add(n);
			
		}
		return directNeghbours;
	}
}


