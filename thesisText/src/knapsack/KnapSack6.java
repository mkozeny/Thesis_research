@Ensures({"return.length = this.n ",
		"(sum i: int | (return[i]?this.v[i]:0)) <= this.capacity",
		"(sum i: int | (return[i]?this.c[i]:0)) > minCost"
		})
@Modifies ({"return.length",
		"return.elems" })
@FreshObjects ( cls = Boolean[].class , num = 1 )
public Boolean [] solveKnapSackProblem(int minCost)
{
	return Squander.exe(this, minCost);
}