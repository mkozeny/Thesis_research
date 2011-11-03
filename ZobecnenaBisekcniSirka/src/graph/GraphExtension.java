package graph;

import java.util.Arrays;
import java.util.Stack;

public class GraphExtension extends Graph{

	private int a;
	
	private Stack<State> stateStack;
	
	private State bestConfiguration;
	
	public GraphExtension(int n, int a) {
		super(n);
		this.a = a;
		State initState = new State(n/*, a*/);
		//initState.init();
		initState.setCountOfCommonEdges(countOfCommonEdges(initState));
		this.stateStack = new Stack<State>();
		this.stateStack.push(initState);
		this.bestConfiguration = new State(n/*, a*/);
		this.bestConfiguration.setConfiguration(new boolean[n]);
		this.bestConfiguration.setCountOfCommonEdges(Integer.MAX_VALUE);
	}
	
	public void solveZobecnenaBisekcniSirkaGrafuProblem()
	{
		
		while(!this.stateStack.empty())
		{
			State actualState = stateStack.pop();
			System.out.println("---POPPING STATE---");
			printStateInfo(actualState);
			generateFollowers(actualState);
		}
	}

	public int countOfCommonEdges(State actualState)
	{
		int countOfCommonEdges = 0;
		boolean [] configuration = actualState.getConfiguration();
		for(int i=0; i< configuration.length; i++)
		{
			if(configuration[i])
			{
				for(int j= 0; j < this.matrixOfIncidency[i].length; j++)
				{
					if(this.matrixOfIncidency[i][j] && !configuration[j])
						countOfCommonEdges++;
				}
			}
		}
		return countOfCommonEdges;
	}
	
	private void generateFollowers(State actualState)
	{
		if(actualState.countOfNodes()>=this.a || 
				actualState.getCountOfCommonEdges()>=this.bestConfiguration.getCountOfCommonEdges())
		{
			System.out.println(actualState.getCountOfCommonEdges()>=this.bestConfiguration.getCountOfCommonEdges()?("BRANCH AND BOUND: best conf: "+this.bestConfiguration.getCountOfCommonEdges()+", act conf: "+actualState.getCountOfCommonEdges()):"END STATE");
			return;
		}
		boolean [] configuration = actualState.getConfiguration();
		//boolean [] newConfiguration = Arrays.copyOf(configuration, configuration.length);
		for(int i=getMaxOccupiedPosition(configuration)+1; i<configuration.length-(this.a-actualState.countOfNodes()-1);i++)
		{
			boolean [] newConfiguration = Arrays.copyOf(configuration, configuration.length);
			newConfiguration[i]=true;
			State newState = new State(this.n/*, this.a*/);
			newState.setConfiguration(newConfiguration);
			newState.setCountOfCommonEdges(countOfCommonEdges(newState));
			System.out.println("---PUSHING STATE---");
			printStateInfo(newState);
			if(newState.countOfNodes()==this.a && this.bestConfiguration.getCountOfCommonEdges()>=newState.getCountOfCommonEdges())
				this.bestConfiguration = newState;
			else
				this.stateStack.push(newState);
		}
	}
	
	private int getMaxOccupiedPosition(boolean [] configuration)
	{
		int max = -1;
		for(int i=0; i<configuration.length; i++)
		{
			if(configuration[i])
				max = i;
		}
		return max;
	}
	
	private void printStateInfo(State actualState)
	{
		System.out.println("1) ACTUAL CONFIGURATION");
		boolean [] configuration = actualState.getConfiguration();
		for(int i = 0; i<configuration.length;i++)
		{
			if(configuration[i])
				System.out.println("Node: "+i);
		}
		System.out.println("2) COUNT OF COMMON EDGES: "+actualState.getCountOfCommonEdges());
		System.out.println();
	}

	public State getBestConfiguration() {
		return bestConfiguration;
	}
	
	
}
