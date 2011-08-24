package main;

import java.util.Set;

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
	
	public KnapSack() {
		super();
	}
	
	public KnapSack(int capacity, int n) {
		super();
		this.capacity = capacity;
		this.n = n;
	}
	
	@Requires ("result.length == n" )

	@Invariant({"all e: result.elts | e.v && e.c", "n", "capacity","minCost"})
	@Ensures({// "return[int] in {0,1}",
		//"return[int] ==1 || return[int] == 0",
		"all e: result.elts | e.choosed==0  || e.choosed==1",
		//"all i: int | i>=0 && i<this.n => return[i]=0 ||  return[i]=1",
		//"return.length = this.n ",
		"(sum e: result.elts | e.v*e.choosed) <= capacity",
		"(sum e: result.elts | e.c*e.choosed) > minCost"
		//"(sum i: int | this.c[i]*return[i]) > this.minCost",
		
		})
	@Modifies ({
		"Thing.choosed [][]"
		})

	//@FreshObjects ( cls = Integer[].class , num = 1 )
	//@Options(ensureAllInts = true,
	//	bitwidth = 9)
	public void solveKnapSackProblem(Set<Thing> result, int n, int capacity, int minCost)
	{
		Squander.exe(this, result, n, capacity , minCost);
	}
}
