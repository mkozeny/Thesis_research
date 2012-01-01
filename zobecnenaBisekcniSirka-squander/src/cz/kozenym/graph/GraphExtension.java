package cz.kozenym.graph;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;

public class GraphExtension extends Graph {

	private Edge [] edgesArray;
	
	
	public GraphExtension(int n, int maxGrade) {
		super(n, maxGrade);
	}
	/*number of nodes both node groups has to be 0 at the beginning*/
	@Requires ({ "#resultA.elts=0" , "#resultN.elts=0"})
	@Ensures ( {
		"resultA.elts in this.nodes.elts", /*result set A is subset of set of nodes*/
		"#resultA.elts == a", /*size of result set A has to be equal to a*/
		"resultN.elts in this.nodes.elts", /*result set N is subset of set of nodes*/
		"#resultN.elts == (this.n - a)", /*size of result set N has to be equal to n - a*/
		" all a: resultA.elts | no n: resultN.elts |" +
				" a.value == n.value", /*no node has to appear in both node groups*/
		/*number of edges connecting both groups has to be lower or equal to treshold*/
		" (sum i: int | (i>=0 && i<this.edgesArray.length &&" +
		"(((this.edgesArray[i].src in resultA.elts) && (this.edgesArray[i].dest in resultN.elts)) || " +
		"((this.edgesArray[i].dest in resultA.elts) && (this.edgesArray[i].src in resultN.elts))))?1:0) <= treshold"})
	/*we allow to modify elements and length of both node groups*/
	@Modifies ({ "resultA.elts" , "resultN.elts", "resultA.length" , "resultN.length"})
	public void solveGeneralBisectionBreadthProblem(Set<Node> resultA, Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
	{
		Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
	}
	public void generateGraph(boolean fromPredecessor)
	{
		if(fromPredecessor)
			super.generateGraph();
		this.edgesArray = new Edge [this.edges.size()];
		this.edges.toArray(this.edgesArray);
	}
}
