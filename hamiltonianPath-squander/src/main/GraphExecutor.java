package main;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;
import graph.Edge;
import graph.Graph;

public class GraphExecutor extends Graph {

	public GraphExecutor(int n) {
		super(n);
	}
	@Ensures ( {
		"return[int] in this.edges.elts" ,
		"return[int].(src + dest) = this.nodes.elts" ,
		"return.length = #this.nodes.elts - 1 " ,
		"all i: int | i >= 0 && i < return.length - 1 => return[i].dest = return[i+1].src"
		})
	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = Edge[].class , num = 1 )
	public Edge [] solveHamiltonianPath()
	{
		return Squander.exe(this);
	}
}
