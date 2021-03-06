package cz.kozenym.graph;

import cz.kozenym.graph.Edge;
import cz.kozenym.graph.Graph;
import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;


public class GraphExecutor extends Graph {

	public GraphExecutor(int n, int maxGrade) {
		super(n, maxGrade);
	}
	/*
	 * Squander implementation
	 */
	@Ensures ( {
		"return[int] in this.edges.elts", /*return array is a subset of set of edges*/
		"return[int].(src + dest) = this.nodes.elts", /*return array has to cover all nodes*/
		"return.length = #this.nodes.elts - 1 ", /*number of edges in return array is number of nodes - 1*/
		"all i: int | i >= 0 && i < return.length - 1 => return[i].dest = return[i+1].src" /*in return array has to be every destination node
		 of actual edge source node of following one*/
		})
	@Modifies ({ "return.length" , "return.elems" })/*we allow to modify elements of returned array and its length*/
	@FreshObjects ( cls = Edge[].class , num = 1 )/*return value is sequence of edges representing Hamiltonian path*/
	public Edge [] solveHamiltonianPath()
	{
		return Squander.exe(this);
	}
}
