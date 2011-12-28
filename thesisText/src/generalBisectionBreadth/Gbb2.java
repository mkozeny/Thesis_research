@Requires("a <= this.n")
@Ensures ({"resultA.elts in this.nodes.elts" ,
			     "#resultA.elts == a" ,
			     "resultN.elts in this.nodes.elts" ,
			     "#resultN.elts == (this.n - a)"})
@Modifies ({ "resultA.elts" , "resultN.elts", "commonEdges.elts",
				     "commonEdges.length" })
public void solveGeneralBisectionBreadthProblem(Set<Node> resultA, 
				Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
{
	Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
}