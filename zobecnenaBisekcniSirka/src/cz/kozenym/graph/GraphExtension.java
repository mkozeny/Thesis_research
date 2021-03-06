package cz.kozenym.graph;

import java.util.Arrays;
import java.util.Stack;

import cz.kozenym.graph.Graph;

public class GraphExtension extends Graph{

	private int a;
	
	private Stack<State> stateStack;
	
	private State bestConfiguration;
	
	public GraphExtension(int n, int a, int maxGrade) {
		super(n, maxGrade);
		this.a = a;
		State initState = new State(n/*, a*/,0);
		//initState.init();
		initState.setCountOfCommonEdges(countOfCommonEdges(initState,true));
		this.stateStack = new Stack<State>();
		this.stateStack.push(initState);
		this.bestConfiguration = new State(n/*, a*/,0);
		this.bestConfiguration.setConfiguration(new boolean[n]);
		this.bestConfiguration.setCountOfCommonEdges(Integer.MAX_VALUE);
	}
	
	/*
	 * BB-DFS implementation
	 */
	public void solveGeneralBisectionBreadthProblem()
	{
		while(!this.stateStack.empty())
		{
			State actualState = stateStack.pop();
			/*generating followers of actual state*/
			generateFollowers(actualState);
		}
	}

	
	
	private void generateFollowers(State actualState)
	{
		/*if is pointer of actual state at the end of configuration array
		 * or actual state has number of common edges greater or equal to best state's configuration,
		 * then cut off actual branch*/
		if(actualState.getPointer()==actualState.getConfiguration().length 
				|| actualState.getCountOfCommonEdges()>=this.bestConfiguration.getCountOfCommonEdges())
			return;
		/*create new state with actual position occupied*/
		createNewState(actualState, true);
		/*create new state with actual position unoccupied*/
		createNewState(actualState, false);
	}
	
	private void createNewState(State actualState, boolean occupied)
	{
		boolean [] configuration = actualState.getConfiguration();
		boolean [] newConfiguration = Arrays.copyOf(configuration, configuration.length);
		newConfiguration[actualState.getPointer()]=occupied;
		State newState = new State(this.n/*, this.a*/,actualState.getPointer()+1);
		newState.setConfiguration(newConfiguration);
		newState.setCountOfCommonEdges(countOfCommonEdges(newState,newState.countOfNodes()==this.a));
		/*if number of nodes in new state is equal to and best configuration has greater or equal number of common edges,
		 * then assign actual state as a best state*/
		if(newState.countOfNodes()==this.a && this.bestConfiguration.getCountOfCommonEdges()>=newState.getCountOfCommonEdges())
			this.bestConfiguration = newState;
		/*else push new state onto stack*/
		else if(newState.countOfNodes()<this.a)
			this.stateStack.push(newState);
		
	}
	
	private int countOfCommonEdges(State actualState, boolean finalState)
	{
		int countOfCommonEdges = 0;
		boolean [] configuration = actualState.getConfiguration();
		int length = 0;
		if(finalState)
			length = configuration.length;
		else
			length = actualState.getPointer();
		for(int i=0; i< length; i++)
		{
			if(configuration[i])
			{
				for(int j= 0; j < length; j++)
				{
					if(this.matrixOfIncidency[i][j] && (!configuration[j]))
						countOfCommonEdges++;
				}
			}
		}
		return countOfCommonEdges;
	}
	
	public State getBestConfiguration() {
		return bestConfiguration;
	}
	
	
}
