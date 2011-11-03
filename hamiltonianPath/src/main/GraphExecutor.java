package main;

import graph.Edge;
import graph.Graph;

public class GraphExecutor extends Graph {

	public GraphExecutor(int n) {
		super(n);
	}
	public void solveHamiltonianPathRecursively()
	{
		for(int i=0; i< n; i++)
		{
			for(int j=0; j< n; j++)
			{
				//if((matrixOfFollowers[j][i]==1 && result[0]==null) ||(matrixOfFollowers[j][i]==1 && pointer > 0 && result[pointer-1].getDest().equals(new Node(j)) && doesResultNotContainNode(new Node(i))))
				if(((matrixOfFollowers[j][i] && result[0]==null) 
						||(matrixOfFollowers[j][i] && pointer > 0 
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
	}
	
	private boolean doesResultNotContainNode(int value)
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
	}
}
