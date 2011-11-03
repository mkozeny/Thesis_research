package graph;

import java.util.Arrays;
import java.util.Collections;

public class State {

	private boolean [] coverage;
	
	private boolean [] configuration;
	
	private boolean [] coveredGraph;
	
	private int n;
	
	
	
	public State(int n) {
		super();
		this.n = n;
		this.coverage = new boolean[n];
		this.configuration = new boolean[n];
		this.coveredGraph = new boolean[n];
		for(int i = 0; i< this.coveredGraph.length; i++)
		{
			coveredGraph[i] = true;
		}
	}



	public boolean isCoveringAllNodes()
	{
		return Arrays.equals(this.coverage, this.coveredGraph);
	}
	
	public int countOfNodes()
	{
		return Collections.frequency(Arrays.asList(this.configuration), true);
	}



	public boolean[] getCoverage() {
		return coverage;
	}



	public void setCoverage(boolean[] coverage) {
		this.coverage = coverage;
	}



	public boolean[] getConfiguration() {
		return configuration;
	}



	public void setConfiguration(boolean[] configuration) {
		this.configuration = configuration;
	}

	
	
}