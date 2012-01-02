package cz.kozenym.graph;

import java.util.Set;

import cz.kozenym.graph.Edge;
import cz.kozenym.graph.Graph;
import cz.kozenym.graph.Node;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.Options;

public class GraphExtension extends Graph {

	private Edge [] edgesArray;
	
	
	public GraphExtension(int n, int maxGrade) {
		super(n, maxGrade);
	}
	@Requires ({ "#resultA.elts=0" , "#resultN.elts=0"})
	@Ensures ( {
		"resultA.elts in this.nodes.elts" ,
		"#resultA.elts == a" ,
		"resultN.elts in this.nodes.elts" ,
		"#resultN.elts == (this.n - a)" ,
		//"commonEdges.elts in this.edges.elts" ,
		//"#commonEdges.elts = treshold" ,
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
		/*" all e: this.edges.elts | " +
		"(((e.src in resultA.elts) && (e.dest in resultN.elts) || " +
		"(e.dest in resultA.elts) && (e.src in resultN.elts))?(e in commonEdges.elts):(e !in commonEdges.elts))"*/
		" (sum i: int | (i>=0 && i<this.edgesArray.length &&" +
		"(((this.edgesArray[i].src in resultA.elts) && (this.edgesArray[i].dest in resultN.elts)) || " +
		"((this.edgesArray[i].dest in resultA.elts) && (this.edgesArray[i].src in resultN.elts))))?1:0) = treshold"})
	@Modifies ({ "resultA.elts" , "resultN.elts", "resultA.length" , "resultN.length"
		//, "commonEdges.elts", "commonEdges.length" 
		})
	//@Options(ensureAllInts = true)
	public void solveZobecnenaBisekcniSirkaGrafuProblem(Set<Node> resultA, Set<Node> resultN, Set<Edge> commonEdges, int a, int treshold)
	{
		Squander.exe(this, resultA, resultN, commonEdges, a, treshold);
	}
	public void generateGraph(boolean fromPredecessor)
	{
		if(fromPredecessor)
			super.generateGraph();
		this.edgesArray = new Edge [this.edges.size()];
		/*int i=0;
		for(Edge e:this.edges)
		{
			this.edgesArray[i] = e;
			i++;
		}*/
		this.edges.toArray(this.edgesArray);
	}
}
