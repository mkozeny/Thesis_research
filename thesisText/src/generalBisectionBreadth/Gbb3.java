@Requires("a <= this.n")
@Ensures ({"resultA.elts in this.nodes.elts" ,
			     "#resultA.elts == a" ,
			     "resultN.elts in this.nodes.elts" ,
			     "#resultN.elts == (this.n - a)",
			     "all a: resultA.elts | no n: resultN.elts |" +
				   "a.value == n.value" ,
				   "commonEdges.elts in this.edges.elts" ,
				   "#commonEdges.elts <= treshold" ,
				   "all e: this.edges.elts | " +
				   "(((e.src in resultA.elts) && (e.dest in resultN.elts) || " +
				   "(e.dest in resultA.elts) && (e.src in resultN.elts))?"+
				   "(e in commonEdges.elts):(e !in commonEdges.elts))"})
@Modifies ({ "resultA.elts" , "resultN.elts", "commonEdges.elts",
				     "commonEdges.length" })
public void solveGeneralBisectionBreadthProblem(Set<Node> resultA, 
				Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
{
	Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
}