package main;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;


public class KnapSack {

	public KnapSack() {
		super();
	}
	
	@Ensures({"(sum e: result.elts | (e.choosed?e.v:0)) <= capacity",
		"(sum e: result.elts | (e.choosed?e.c:0)) = minCost"
		})
	@Modifies ({
		"Thing.choosed"
		})
	public void solveKnapSackProblem(Set<Thing> result, int n, int capacity, int minCost)
	{
		Squander.exe(this, result, n, capacity , minCost);
	}
	
	/*@Ensures({"(sum i:int | (result[i].choosed?result[i].v:0)) <= capacity",
		"(sum i:int | (result[i].choosed?result[i].c:0)) > minCost"
		})
	@Modifies ({
		"Thing.choosed"
		})
	public void solveKnapSackProblem(Thing [] result, int n, int capacity, int minCost)
	{
		Squander.exe(this, result, n, capacity , minCost);
	}*/
	/*@Ensures({"(sum e: result[int] | (e.choosed?e.v:0)) <= capacity",
		"(sum e: result[int] | (e.choosed?e.c:0)) > minCost"
		})
	@Modifies ({
		"Thing.choosed"
		})
	public void solveKnapSackProblem(Thing [] result, int n, int capacity, int minCost)
	{
		Squander.exe(this, result, n, capacity , minCost);
	}*/
}
