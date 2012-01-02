@Requires({"#resultA.elts=0" , "#resultN.elts=0"})
@Ensures({
		"resultA.elts in this.nodes.elts" ,
		"#resultA.elts == a" ,
		"resultN.elts in this.nodes.elts" ,
		"#resultN.elts == (this.n - a)" ,
		"all a: resultA.elts | no n: resultN.elts |" +
				" a.value == n.value",
		"(sum i: int | (i>=0 && i<this.edgesArray.length && " +
		"(((this.edgesArray[i].src in resultA.elts) && " + 
		"(this.edgesArray[i].dest in resultN.elts)) || " +
		"((this.edgesArray[i].dest in resultA.elts) && " +
		"(this.edgesArray[i].src in resultN.elts))))?1:0) = treshold"})
@Modifies ({"resultA.elts" , "resultN.elts", "resultA.length" , 
			"resultN.length"})
public void solveGeneralBisectionBreadthProblem(Set<Node> resultA, 
				Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
{
	Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
}