@Ensures({ "return[int] in {0,1}",
					 "return.length = this.n ",
					 "(sum i: int | this.v[i]*return[i]) <= this.capacity",
					 "(sum i: int | this.c[i]*return[i]) > minCost"
})
@Modifies ({ "return.length" , "return.elems" })
@FreshObjects ( cls = Integer[].class , num = 1 )
public Integer [] solveKnapSackProblem(int minCost)
{
	return Squander.exe(this, minCost);
}