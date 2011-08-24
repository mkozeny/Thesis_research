package main;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;
import graph.Node;

public class GraphExecutor {

	private int treshold;
	
	@Ensures ( {
		"return[int] in this.nodes.elts" ,
		"return.length <= this.treshold" ,
		"all n: return.elts | n + n.neighbourhood = this.nodes.elts" +
				"",
		//"all i: int | i >= 0 && i < return.length - 1 => return[i].dest = return[i+1].src"
		})
	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = Node[].class , num = 1 )
	public Node [] solveHamiltonianPath()
	{
		return Squander.exe(this);
	}
}
