package cz.kozenym.graph;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.Options;

public class GraphExtension extends Graph {

	public GraphExtension(int n, int maxGrade) {
		super(n, maxGrade);
	}

	/*number of nodes both node groups has to be 0 at the beginning*/
	@Requires ({ "#resultA.elts=0" , "#resultN.elts=0"})
	@Ensures({
			"resultA.elts in this.nodes.elts", /*result set A is subset of set of nodes*/
			"#resultA.elts == a", /*size of result set A has to be equal to a*/
			"resultN.elts in this.nodes.elts", /*result set N is subset of set of nodes*/
			"#resultN.elts == (this.n - a)", /*size of result set N has to be equal to n - a*/
			"commonEdges.elts in this.edges.elts", /*set of common edges is subset of set of edges*/
			"#commonEdges.elts <= treshold", /*number of common edges has to be lower or equal to treshold*/
			/*if there is an edge with started node in result set A and destination node in result set N
			 * or in the opposite way, then put this node into set of common edges, otherwise do not that*/
			" all a: resultA.elts | no n: resultN.elts |"
					+ " a.value == n.value",
					" all e: this.edges.elts | "
					+ "(((e.src in resultA.elts) && (e.dest in resultN.elts) || "
					+ "(e.dest in resultA.elts) && (e.src in resultN.elts))?(e in commonEdges.elts):(e !in commonEdges.elts))" })
	/*we allow to modify elements of both node groups and set of common edges and length of that set*/
	@Modifies({ "resultA.elts", "resultN.elts", "commonEdges.elts",
			"commonEdges.length" })
	@Options(ensureAllInts = true)
	public void solveGeneralBisectionBreadthProblem(Set<Node> resultA,
			Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold) {
		Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
	}
}