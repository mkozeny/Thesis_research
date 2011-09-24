package graph;

public class Edge {
    private Node src;
	private Node dest;
	private int id;
	private int cost;
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
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || (!(obj instanceof Edge))) return false;
		Edge e = (Edge)obj;
		
		return (e.getId()==this.getId());
	}
    
  }

