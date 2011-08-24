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

	public KnapSack(int capacity, int n) {
		super();
		this.capacity = capacity;
		this.n = n;
	}
	@Requires({//"all i: int | i>=0 && i<this.n => return[i]=0",
		"return[int] in {0}"
	})
	@Invariant({"this.v", "this.c", "this.capacity", "this.n"})
	@Ensures({ "return[int] in {0,1}",
		//"return[int] ==1 || return[int] == 0",
		//"all i: int | i>=0 && i<this.n => return[i]=0 || return[i]=1",
		//"all i: int | i>=0 && i<this.n => return[i]=0 ||  return[i]=1",
		"return.length = this.n ",
		"(sum i: int | this.v[i]*return[i]) <= this.capacity",
		"(sum i: int | this.c[i]*return[i]) > minCost"
		//"(sum i: int | this.c[i]*return[i]) > this.minCost",
		
		})
	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = Integer[].class , num = 1 )
	@Options(//ensureAllInts = true,
		bitwidth = 9)
	public Integer [] solveKnapSackProblem(int minCost)
	{
		return Squander.exe(this, minCost);
	}
	/*@Ensures({ "(sum i: int | return[i]*this.c[i]) > minCost",
		"(sum i: int | return[i]*this.v[i]) <= this.capacity"})
	@Modifies ({ "return.length" , "return.elems" })
	public void solveKnapSackProblem(int [] result, int minCost)
	{
		Squander.exe(this, result, minCost);
	}*/
	
	public void setC(int[] c) {
		this.c = c;
	}
	public void setV(int[] v) {
		this.v = v;
	}
	
	
}

