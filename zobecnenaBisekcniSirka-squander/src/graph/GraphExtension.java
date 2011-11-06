package graph;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.Options;

public class GraphExtension extends Graph {


	
	public GraphExtension(int n, int maxGrade) {
		super(n, maxGrade);
	}
	@Ensures ( {
		"resultA.elts in this.nodes.elts" ,
		"#resultA.elts == a" ,
		"resultN.elts in this.nodes.elts" ,
		"#resultN.elts == (this.n - a)" ,
		"commonEdges.elts in this.edges.elts" ,
		"#commonEdges.elts <= treshold" ,
		" all a: resultA.elts | no n: resultN.elts |" +
				" a.value == n.value"/*,
		" all e: commonEdges.elts | " +
		"((some a: resultA.elts | e.src.value == a.value) && (some n: resultN.elts | e.dest.value == n.value)) || " +
		"((some a: resultA.elts | e.dest.value == a.value) && (some n: resultN.elts | e.src.value == n.value))"*/
		/*,
		" all e: this.edges.elts | " +
		"(all a: resultA.elts | (all n: resultN.elts | ((e.src.value == a.value) && (e.dest.value == n.value)) || " +
		"(( e.dest.value == a.value) && ( e.src.value == n.value))?(e in commonEdges.elts):(e !in commonEdges.elts)))"*/
		,
		" all e: this.edges.elts | " +
		"(((e.src in resultA.elts) && (e.dest in resultN.elts) || " +
		"(e.dest in resultA.elts) && (e.src in resultN.elts))?(e in commonEdges.elts):(e !in commonEdges.elts))"})
	@Modifies ({ "resultA.elts" , "resultN.elts", "commonEdges.elts", "commonEdges.length" })
	//@Options(ensureAllInts = true)
	public void solveZobecnenaBisekcniSirkaGrafuProblem(Set<Node> resultA, Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
	{
		Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
	}
}
