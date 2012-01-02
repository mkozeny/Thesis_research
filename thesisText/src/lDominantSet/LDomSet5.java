@Ensures({
		"result.elts in this.extendedNodes.elts" ,
		"result.length = this.treshold" ,
		"result.elts.neighbourhood.elts == this.extendedNodes.elts",
		"all q1: result.elts | all q2: (result.elts - q1) |" +
		"(all n: q2.neighbourhood.elts | n.value!=q1.value)"
		})
@Modifies({"result.elts","result.length"})
public void solveLDominantSetOfGraphProblem(Set<ExtendedNode> result)
{
	Squander.exe(this, result);
}