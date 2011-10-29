@Modifies ({ "return.length" , "return.elems" })
@FreshObjects ( cls = Integer[].class , num = 1 )
public Integer [] solveKnapSackProblem(int minCost)
{
	return Squander.exe(this, minCost);
}