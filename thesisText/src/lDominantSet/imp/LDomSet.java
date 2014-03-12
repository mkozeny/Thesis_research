	public void solveLDominantSetOfGraphProblem() {
		while (!this.stateStack.empty()) {
			State actualState = this.stateStack.pop();
			/*generating followers of actual state*/
			generateFollowers(actualState);
		}
	}
	private void generateFollowers(State actualState) {
		boolean[] coverage = actualState.getCoverage();
		int maxOccupiedPosition = getMaxOccupiedPosition(actualState
				.getConfiguration());
		/*create new state*/
		for (int i = maxOccupiedPosition; i < coverage.length; i++) {
			if (!coverage[i]) {
				boolean[] newConfiguration = Arrays.copyOf(
						actualState.getConfiguration(),
						actualState.getConfiguration().length);
				newConfiguration[i] = true;
				boolean[] newCoverage = Arrays.copyOf(newConfiguration,
						newConfiguration.length);
				boolean[] coverageTmp = Arrays.copyOf(newConfiguration,
						newConfiguration.length);
				State newState = new State(this.n);
				newState.setConfiguration(newConfiguration);
				/*create new coverage by ORing*/
				for (int neighborhood = 0; neighborhood < this.l; neighborhood++) {
					for (int j = 0; j < coverageTmp.length; j++) {
						if (coverageTmp[j]) {
							for (int k = 0; k < this.matrixOfAdjacency[j].length; k++)
								newCoverage[k] = newCoverage[k]
										|| this.matrixOfAdjacency[j][k];
						}
					}
					coverageTmp = Arrays
							.copyOf(newCoverage, newCoverage.length);
				}
				newState.setCoverage(newCoverage);
				/*if new state contains more nodes than state with best configuration and new state is not covering all nodes,
				 * then skip following code in the cycle*/
				if (newState.countOfNodes() >= this.bestConfiguration
						.countOfNodes() && !newState.isCoveringAllNodes())
					continue;
				/*if new state is covering all nodes and number of nodes in this state is lower than number of nodes in best state
				 * or configuration of best state is not covering all nodes in the graph, then set actual state as a best state*/
				if (newState.isCoveringAllNodes() && newState.countOfNodes() < 
						this.bestConfiguration.countOfNodes())
					this.bestConfiguration = newState;
				this.stateStack.push(newState);
			}
		}
	}