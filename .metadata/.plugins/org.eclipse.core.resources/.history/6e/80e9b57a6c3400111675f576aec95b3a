package cz.kozenym.graph;

import java.util.HashSet;
import java.util.Set;

import cz.kozenym.graph.Node;

public class ExtendedNode extends Node {


	public static enum State
	{
		FRESH,
		OPENED,
		CLOSED
	}
	
	public ExtendedNode(int value) {
		super(value);
		this.neighbourhood = new HashSet<ExtendedNode>();
		this.neighbourhood.add(this);
	}
	
	private State state;
	
	private int distance;

	private Set<ExtendedNode> neighbourhood;

	public Set<ExtendedNode> getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(Set<ExtendedNode> neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
