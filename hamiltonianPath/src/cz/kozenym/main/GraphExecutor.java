package cz.kozenym.main;

import cz.kozenym.graph.Edge;
import cz.kozenym.graph.Graph;

public class GraphExecutor extends Graph {

	private static final long serialVersionUID = -222623699624761928L;

	private Edge[] result;

	private int pointer;

	public GraphExecutor(int n, int maxGrade) {
		super(n, maxGrade);
		this.pointer = 0;
		this.result = new Edge[n - 1];
	}

	public void solveHamiltonianPathRecursively() {
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				if (i != j
						&& this.matrixOfFollowers[j][i]
						&& ((this.result[0] == null) || (this.pointer > 0
								&& this.result[this.pointer - 1].getDest()
										.getValue() == j && doesResultNotContainNode(i)))) {
					this.result[this.pointer] = findEdge(j, i);
					if (this.pointer == this.n - 2)
						return;
					++this.pointer;
					solveHamiltonianPathRecursively();
					if (this.pointer < this.n - 2
							|| this.result[this.pointer] == null)
						this.result[--this.pointer] = null;
				}
			}
		}
	}

	private boolean doesResultNotContainNode(int value) {
		for (int i = 0; i < this.n - 1; i++) {
			if (this.result[i] != null) {
				if (this.result[i].getSrc().getValue() == value
						|| this.result[i].getDest().getValue() == value)
					return false;
			} else
				break;
		}
		return true;
	}

	private Edge findEdge(int srcValue, int destValue) {
		for (Edge e : this.edges) {
			if (e.getSrc().getValue() == srcValue
					&& e.getDest().getValue() == destValue)
				return e;

		}
		return null;
	}

	public Edge[] getResult() {
		return this.result;
	}

}
