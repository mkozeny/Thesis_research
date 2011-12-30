@Ensures({"(sum e: result.elts | (e.choosed?e.v:0)) <= capacity",
	    		"(sum e: result.elts | (e.choosed?e.c:0)) > minCost"
     		})
@Modifies ("Thing.choosed")
public void solveKnapSackProblem(Set<Thing> result, int n, int capacity, int minCost)
{
	Squander.exe(this, result, n, capacity , minCost);
}