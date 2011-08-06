package main;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;
import edu.mit.csail.sdg.squander.annotations.Options;

public class Graph {
	  
	  
	  
	  
	  private Set<Node> nodes = new LinkedHashSet<Node>();
	  private Set<Edge> edges = new LinkedHashSet<Edge>();
	  
	  
	    
	  public Set<Node> getNodes() {
		return nodes;
	}



	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}



	public Set<Edge> getEdges() {
		return edges;
	}



	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}



	@Ensures({
	      "return.keys = this.nodes.elts", 
	      "all c : return.vals | c > 0 && c <= k",
	      "all e : this.edges.elts | return.elts[e.src] != return.elts[e.dest]"
	  })
	  @Modifies("return.elts")
	  @Options(ensureAllInts=true)
	  @FreshObjects(cls = Map.class, typeParams={Node.class, Integer.class}, num = 1)
	  public Map<Node, Integer> color(int k) {
		  
	      return Squander.exe(this, k);
	  }
	}
