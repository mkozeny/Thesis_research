package graph;

public class Edge {
    private Node src;
	private Node dest;
    public Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
        
    }
	public Node getSrc() {
		return src;
	}
	public void setSrc(Node src) {
		this.src = src;
	}
	public Node getDest() {
		return dest;
	}
	public void setDest(Node dest) {
		this.dest = dest;
	} 
	@Override
	public boolean equals(Object obj) {
		if(obj == null || (!(obj instanceof Edge))) return false;
		Edge e = (Edge)obj;
		if(e.getSrc() == null || this.getSrc()==null || e.getDest()==null || this.getDest()==null) return false;
		return (e.getSrc().equals(this.getSrc()) && e.getDest().equals(this.getDest()));
	}
    
  }

