@Requires("a <= this.n")
@Modifies ({ "resultA.elts" , "resultN.elts", "commonEdges.elts",
				     "commonEdges.length" })
public void solveGeneralBisectionBreadthProblem(Set<Node> resultA, 
				Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
{
	Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
}