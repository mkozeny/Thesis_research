package graph;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Invariant;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;
import edu.mit.csail.sdg.squander.annotations.Options;

public class GraphExtension extends Graph {

	private int s;
	
	private int treshold;
	
	private Node startNode;
	
	private Node endNode;
	
	public GraphExtension(int n, int s, int treshold) {
		super(n);
		this.s = s;
		this.treshold = treshold;
		
	}
	
	
	
	public Node getStartNode() {
		return startNode;
	}



	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}



	public Node getEndNode() {
		return endNode;
	}



	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}



	//@Requires("this.treshold <= this.s")
	//@Invariant({"(sum i: int| return[i].cost) < this.s" ,
	//	"(sum i: int| return[i].cost) > this.treshold"})
	/*@Invariant("this.treshold <= this.s")
	@Ensures ( {
		"(sum i: int| return[i].cost) < this.s" ,
		"(sum i: int| return[i].cost) > this.treshold",
		"return[int] in this.edges.elts" ,
		"return[int].(src + dest) = this.nodes.elts" ,
		//"all e1: return[int] | no e2: return[int] - e1 | (e1.src.value=e2.src.value && e1.dest.value=e2.dest.value)",
		"return[0].src = this.startNode || return[0].dest = this.startNode" ,
		"return[return.length - 1].dest = this.endNode || return[return.length - 1].src = this.endNode" ,
		"all i: int | i != 0 && i != (return.length - 1) " +
		"=> (return[i].src != this.startNode && return[i].dest != this.startNode && return[i].src != this.endNode && return[i].dest != this.endNode)",
		"all i: int | i >= 0 && i < ((return.length - 2)>=0?(return.length - 2):0) => " +
		"(return[i].dest = return[i+1].src || return[i].src = return[i+1].dest)",
		"all i: int | i != 0 => lone return[i].id "
		
		})
	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = Edge[].class , num = 1 )
	@Options(ensureAllInts = true)
	public Edge [] solveShortestPath()
	{
		return Squander.exe(this);
	}*/
	/*@Ensures( {
	    "return[int] in this.edges.elts",
	    "return[int].(src + dest) = this.nodes.elts",
	    "return.length = #this.nodes.elts",
	    "all i : int | i >= 0 && i < return.length - 1 => return[i].dest = return[i+1].src",
	    //"this.startNode = return[0].src && this.startNode = return[return.length - 1].dest", 
	    "(sum i : int | return[i].cost) <= 40"
	})

	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = Edge[].class , num = 1 )
	public Edge [] solveShortestPath()
	{
		return Squander.exe(this);
	}*/
	
	/*@Ensures( {
		"(sum i : int | return[i].cost) <= this.s",
		"return[int] in this.edges.elts",
		"return[int].src in this.nodes.elts",
		"return[int].dest in this.nodes.elts",
		"all e1: return[int] | all e2: (return[int] - e1) | (e1.id!=e2.id)",
		"all i: int | i >=0 && i<(return.length-1) => (return[i].dest = return[i+1].src || return[i].src = return[i+1].dest)",
		"this.startNode = return[0].src || this.startNode = return[0].dest",
		"this.endNode = return[return.length-1].src || this.endNode = return[return.length-1].dest"
	    
	})*/
	@Ensures( {
		"return[int] in this.edges.elts",
	    "return[int].(src + dest) in this.nodes.elts",
	    "return.length = 3",
	    "all e1: return[int] | all e2: (return[int] - e1) | (e1.id!=e2.id)",
	    "all i : int | i >= 0 && i < return.length - 1 => (return[i].dest = return[i+1].src || return[i].src = return[i+1].dest)",
	    "startNode = return[0].src || startNode = return[0].dest",
	    "endNode = return[return.length-1].src || endNode = return[return.length-1].dest",
	    "(sum i : int | return[i].cost) <= 0"
	})
	@Modifies ({ "return.length" , "return.elems" })
	@FreshObjects ( cls = Edge[].class , num = 1 )
	@Options(ensureAllInts=true)
	public Edge [] solveShortestPath(int s, Node startNode, Node endNode)
	{
		return Squander.exe(this, s, startNode, endNode);
	}
}
