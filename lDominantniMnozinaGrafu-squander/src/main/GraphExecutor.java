package main;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;
import edu.mit.csail.sdg.squander.annotations.Options;
import graph.ExtendedNode;


public class GraphExecutor {

	
	
	public GraphExecutor(int treshold, Set<ExtendedNode> nodes) {
		super();
		this.treshold = treshold;
		this.nodes = nodes;
	}

	private int treshold;
	
	private Set<ExtendedNode> nodes;
	
	@Ensures ( {
		"return[int] in this.nodes.elts" ,
		"return.length <= this.treshold" ,
		"{n: return.elts | (n + n.neighbourhood)} == this.nodes.elts"
		//"all i: int | i >= 0 && i < return.length - 1 => return[i].dest = return[i+1].src"
		})
	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = ExtendedNode[].class , num = 1 )
	@Options(ensureAllInts = true)
	public ExtendedNode [] solveLDominantniMnozinaGrafuProblem()
	{
		return Squander.exe(this);
	}

	public int getTreshold() {
		return treshold;
	}

	public void setTreshold(int treshold) {
		this.treshold = treshold;
	}

	public Set<ExtendedNode> getNodes() {
		return nodes;
	}

	public void setNodes(Set<ExtendedNode> nodes) {
		this.nodes = nodes;
	}
	
	
}