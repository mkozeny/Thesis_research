package cz.kozenym.solver;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;


public class KnapSack {

	public KnapSack() {
		super();
	}
	
	@Ensures({"(sum e: result.elts | (e.choosed?e.v:0)) <= capacity", /*weight of configuration of things in knapsack has to be lower than its capacity*/
		"(sum e: result.elts | (e.choosed?e.c:0)) > minCost" /*cost of configuration of things in knapsack has to be grater than minimal cost*/
		})
	@Modifies ({
		"Thing.choosed" /*we allow to modify only boolean property choosed of class Thing*/
		})
	public void solveKnapSackProblem(Set<Thing> result, int n, int capacity, int minCost)
	{
		Squander.exe(this, result, n, capacity , minCost);
	}
}
