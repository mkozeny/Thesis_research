	public void solveGeneralBisectionBreadthProblem()
	{
		while(!this.stateStack.empty())
		{
			State actualState = stateStack.pop();
			generateFollowers(actualState);
		}
	}
	private void generateFollowers(State actualState)
	{
		if(actualState.getPointer()==actualState.getConfiguration().length 
			|| actualState.getCountOfCommonEdges()>=this.bestConfiguration.getCountOfCommonEdges())
			return;
		createNewState(actualState, true);
		createNewState(actualState, false);
	}
	private void createNewState(State actualState, boolean occupied)
	{
		boolean [] configuration = actualState.getConfiguration();
		boolean [] newConfiguration = Arrays.copyOf(configuration, configuration.length);
		newConfiguration[actualState.getPointer()]=occupied;
		State newState = new State(this.n, actualState.getPointer()+1);
		newState.setConfiguration(newConfiguration);
		newState.setCountOfCommonEdges(countOfCommonEdges(newState,newState.countOfNodes()==this.a));
		if(newState.countOfNodes()==this.a && this.bestConfiguration.getCountOfCommonEdges()>=newState.getCountOfCommonEdges())
			this.bestConfiguration = newState;
		else if(newState.countOfNodes()<this.a)
			this.stateStack.push(newState);
	}