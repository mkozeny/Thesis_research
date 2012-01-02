package cz.kozenym.graph;

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
	/*
	 * Backtrack implementation
	 */
	public void solveHamiltonianPathRecursively() {
		//iterate through matrix of followers
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				//if there is a edge between nodes i,j and 
				//these nodes are different and
				//recursion is at the beginning of resulted array or
				//is found node, that is not source or
				//destination node of any of edges in actual result array and
				//there is an edge between destination node j of last edge
				//in result array and the other one node i
				if (i != j
						&& this.matrixOfFollowers[j][i]
						&& ((this.result[0] == null) || (this.pointer > 0
								&& this.result[this.pointer - 1].getDest()
										.getValue() == j && doesResultNotContainNode(i)))) {
					//add edge to result array
					this.result[this.pointer] = findEdge(j, i);
					//Hamiltonian Path is complete
					if (this.pointer == this.n - 2)
						return;
					++this.pointer;
					//recursively find following edge
					solveHamiltonianPathRecursively();
					//if following edge does not exist, return back
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
