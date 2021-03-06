	private void solveKnapSackProblem(int rank) {
		for (int i = rank; i < this.countOfObjects; i++) {
			putRestOfThingsIntoKnapsack(i);
			this.actualPrice = this.pw.getPrice();
			this.actualWeight = this.pw.getWeight();/*trying if is possible to cut off actual branch*/
			if (this.actualPrice < this.bruteForcePrice)
				return;
			this.configuration[i] = 1;/*putting actual thing into knapsack*/
			recalculatePriceandWeightInBag();
			this.actualPrice = this.pw.getPrice();
			this.actualWeight = this.pw.getWeight();
			if (this.actualWeight <= this.maxWeight) {
				if (this.bruteForcePrice < this.actualPrice) {
					/*if actual weight is not greater than maximal weight of knapsack
					 * and actual best price is lower than actual price
					 * actual configuration is assigned as a best solution*/
					this.bruteForcePrice = this.actualPrice;
					this.bruteForceWeight = this.actualWeight;
					for (int j = 0; j < this.configuration.length; j++) {
						this.bestConfiguration[j] = this.configuration[j];
					}
					if (this.bruteForcePrice >= this.treshold)
						return;
				}
				/*if actual weight is not greater than maximal weight of knapsack,
				 * it is possible to continue searching state space*/
				solveKnapSackProblem(i + 1);
			}
			this.configuration[i] = 0;
		}
		return;
	}