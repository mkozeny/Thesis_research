package graph;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Graph {
	
	protected int n;
	
	protected boolean [] [] matrixOfFollowers;
	
	protected boolean [] [] matrixOfIncidency;
	
	protected Edge [] result;
	
	protected int pointer;
	
	public Graph(int n) {
		super();
		this.n = n;
		this.nodes = new HashSet<Node>();
		this.edges = new HashSet<Edge>();
		this.matrixOfFollowers = new boolean [n][n];
		this.matrixOfIncidency = new boolean [n][n];
		this.result = new Edge [n-1];
		this.pointer = 0;
	}

	protected Set<Node> nodes;
	
	protected Set<Edge> edges;
	
	public void generateGraph()
	{
		Node src = new Node(0);
		Node first = src;
		this.nodes.add(src);
		for(int i=1; i < n; i++)
		{
			Node dest = new Node(i);
			this.nodes.add(dest);
			Edge edge = new Edge(src, dest);
			this.edges.add(edge);
			src = dest;
		}
		Edge edge = new Edge(src, first);
		this.edges.add(edge);
		final int maxNumberOfNodes = this.n*(this.n+1);
		Random generator = new Random();
		int countOfExtraEdges = generator.nextInt((int)(0.6*maxNumberOfNodes));
		countOfExtraEdges += (int)0.3*maxNumberOfNodes;
		Random nodeChooser = new Random();
		Node [] arrayOfNodes =  new Node[this.nodes.size()];
		nodes.toArray(arrayOfNodes);
		int choosedNode;
		for(int i=1; i < countOfExtraEdges; i++)
		{
			choosedNode = nodeChooser.nextInt(this.nodes.size()-1);
			Node firstNode = arrayOfNodes[choosedNode];
			if(getGradeOfNode(firstNode)>=3)
				continue;
			choosedNode = nodeChooser.nextInt(this.nodes.size()-1);
			Node secondNode = arrayOfNodes[choosedNode];
			if(getGradeOfNode(secondNode)>=3)
				continue;
			Edge e = new Edge(firstNode, secondNode);
			this.edges.add(e);
		}
		choosedNode = nodeChooser.nextInt(this.nodes.size()-1);
		Node selectedNode = arrayOfNodes[choosedNode];
		Set<Edge> edgesToRemove = new HashSet<Edge>();
		for(Edge e:this.edges)
		{
			if(e.getDest().equals(selectedNode))
				edgesToRemove.add(e);
		}
		this.edges.removeAll(edgesToRemove);
		int id = 1;
		Random edgeCost = new Random();
		int tmp = 0;
		for (Edge e : this.edges) {
			matrixOfFollowers[e.getSrc().getValue()][e.getDest().getValue()] = true;
			matrixOfIncidency[e.getSrc().getValue()][e.getDest().getValue()] = true;
			matrixOfIncidency[e.getDest().getValue()][e.getSrc().getValue()] = true;
			e.setCost(tmp = edgeCost.nextInt(10));
			e.setCost(tmp == 0?1:tmp);
			e.setId(id);
			id++;
			/*System.out.println(k+". Edge: src: " + ((e.getSrc()!=null)?e.getSrc().getValue():"") + ", dest: "
					+ ((e.getDest()!=null)?e.getDest().getValue():""));*/
			//k++;
		}
		for(Edge e:this.edges)
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+", dest: "+e.getDest().getValue()+", cost: "+e.getCost());
		}
		System.out.println();
		System.out.println("---MATRIX OF INCIDENCY---");
		for(int i=0; i < n; i++)
		{
			for(int j=0; j < n; j++)
			{
				System.out.print((matrixOfIncidency[i][j]?1:0)+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*public void solveHamiltonianPathRecursively()
	{
		for(int i=0; i< n; i++)
		{
			for(int j=0; j< n; j++)
			{
				//if((matrixOfFollowers[j][i]==1 && result[0]==null) ||(matrixOfFollowers[j][i]==1 && pointer > 0 && result[pointer-1].getDest().equals(new Node(j)) && doesResultNotContainNode(new Node(i))))
				if(((matrixOfFollowers[j][i]==1 && result[0]==null) 
						||(matrixOfFollowers[j][i]==1 && pointer > 0 
						&& result[pointer-1].getDest().getValue()==j && doesResultNotContainNode(i))) 
						&& i!=j)
				{
					//result[pointer]=new Edge(new Node(j), new Node(i));
					result[pointer]=findEdge(j, i);
					if(pointer == n - 2)
						return;
					++pointer;
					
					solveHamiltonianPathRecursively();
					if (pointer < n - 2 || result[pointer] == null)
						result[--pointer]=null;
				}
			}
		}
	}*/
	public Edge [] getResult()
	{
		return result;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}
	
	
	
	/*private boolean doesResultNotContainNode(int value)
	{
		for(int i=0; i< this.n-1; i++)
		{
			if(this.result[i]!=null)
			{
				if(result[i].getSrc().getValue() == value || result[i].getDest().getValue()==value)
					return false;
			}
			else
				break;
		}
		return true;
	}
	
	private Edge findEdge(int srcValue,int destValue)
	{
		for(Edge e:this.edges)
		{
			if(e.getSrc().getValue() == srcValue && e.getDest().getValue()==destValue)
				return e;
			
		}
		return null;
	}*/
	protected int getGradeOfNode(Node node)
	{
		int grade = 0;
		for(Edge e:this.edges)
		{
			if(e.getSrc().equals(node)||e.getDest().equals(node))
				grade++;
		}
		return grade;
	}
}
