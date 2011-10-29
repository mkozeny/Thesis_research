package main;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Invariant;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;
import edu.mit.csail.sdg.squander.annotations.Options;

public class KnapSack {

	private int capacity;
	
	private int n;
	
	private int [] v;
	
	private int [] c;

	public KnapSack(int capacity, int n, int [] c, int [] v) {
		super();
		this.capacity = capacity;
		this.n = n;
		this.c = c;
		this.v = v;
	}
	@Ensures({"return.length = this.n ",
		"(sum i: int | (return[i]?this.v[i]:0)) <= this.capacity",
		"(sum i: int | (return[i]?this.c[i]:0)) = minCost"
		})
	@Modifies ({"return.length",
		"return.elems" })
	@FreshObjects ( cls = Boolean[].class , num = 1 )
	public Boolean [] solveKnapSackProblem(int minCost)
	{
		return Squander.exe(this, minCost);
	}
	
	public void setC(int[] c) {
		this.c = c;
	}
	public void setV(int[] v) {
		this.v = v;
	}
	
	
}

