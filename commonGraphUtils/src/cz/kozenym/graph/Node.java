package cz.kozenym.graph;

import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable{
    protected final int value;
    public Node(int value) { this.value = value; }
	@Override
	public int compareTo(Node o) {
		if(this.value > o.value)
			return 1;
		else if(this.value < o.value)
			return -1;
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || (!(obj instanceof Node))) return false;
		Node n = (Node)obj;
		return n.getValue() == this.getValue();
	}
	public int getValue() {
		return value;
	}
	
	
  }
