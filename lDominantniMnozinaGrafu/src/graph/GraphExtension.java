package graph;

import java.util.Arrays;
import java.util.Stack;

public class GraphExtension extends Graph {

	private int l;
	
	private int treshold;
	
	private State bestConfiguration;
	
	private Stack<State> stateStack;
	
	public GraphExtension(int n, int l, int treshold, int maxGrade) {
		super(n, maxGrade);
		this.l = l;
		this.treshold = treshold;
		this.bestConfiguration = new State(this.n);
		State initState = new State(this.n);
		this.stateStack = new Stack<State>();
		this.stateStack.push(initState);
	}

	
	public void solveLDominantniMnozinaGrafuProblem()
	{
		while(!this.stateStack.empty())
		{
			State actualState = this.stateStack.pop();
			//System.out.println("---POPPING STATE---");
			//printStateInfo(actualState);
			generateFollowers(actualState);
		}
	}
	
	private void generateFollowers(State actualState)
	{
		if(this.bestConfiguration.countOfNodes()>0 
				&& actualState.countOfNodes()>=this.bestConfiguration.countOfNodes()
				&& !actualState.isCoveringAllNodes())
			return;
		boolean [] coverage = Arrays.copyOf(actualState.getCoverage(),actualState.getCoverage().length);
		boolean [] actStateConf = Arrays.copyOf(actualState.getConfiguration(),actualState.getConfiguration().length);
		int maxOccupiedPosition = getMaxOccupiedPosition(actualState.getConfiguration());
		for(int i=maxOccupiedPosition; i<coverage.length; i++)
		{
			if(!coverage[i])
			{
				boolean [] newConfiguration = Arrays.copyOf(actStateConf,actStateConf.length);
				boolean [] newCoverage = Arrays.copyOf(coverage,coverage.length);
				newConfiguration[i]=true;
				newCoverage[i]=true;
				boolean [] coverageTmp = Arrays.copyOf(newCoverage,newCoverage.length);
				State newState = new State(this.n);
				newState.setConfiguration(newConfiguration);
				for(int neighborhood = 0; neighborhood < this.l; neighborhood++)
				{
					for(int j = 0; j<coverageTmp.length; j++)
					{
						if(coverageTmp[j])
						{
							for(int k = 0; k < this.matrixOfIncidency[j].length; k++ )
								newCoverage[k] = newCoverage[k] || this.matrixOfIncidency[j][k];
						}
					}
					coverageTmp = Arrays.copyOf(newCoverage,newCoverage.length);
				}
				newState.setCoverage(newCoverage);
				//System.out.println("---PUSHING STATE---");
				//printStateInfo(newState);
				if(newState.isCoveringAllNodes() 
						&& newState.countOfNodes()<=//this.treshold
						this.bestConfiguration.countOfNodes()
						)
					this.bestConfiguration = newState;
				this.stateStack.push(newState);
			}
		}
	}
	
	private int getMaxOccupiedPosition(boolean [] configuration)
	{
		int max = 0;
		for(int i=0; i<configuration.length; i++)
		{
			if(configuration[i])
				max = i;
		}
		return max;
	}


	public State getBestConfiguration() {
		return bestConfiguration;
	}
	
	/*private void printStateInfo(State actualState)
	{
		System.out.println("1) ACTUAL CONFIGURATION");
		boolean [] configuration = actualState.getConfiguration();
		for(int i = 0; i<configuration.length;i++)
		{
			if(configuration[i])
				System.out.println("Node: "+i);
		}
		System.out.println();
		System.out.println("2) COVERAGE");
		boolean [] coverage = actualState.getCoverage();
		for(int i = 0; i<coverage.length;i++)
		{
			System.out.print((coverage[i]?1:0)+" ");
		}
		System.out.println();
		System.out.println();
	}*/
}
