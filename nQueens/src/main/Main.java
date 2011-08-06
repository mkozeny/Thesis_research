package main;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NQueensProblemSolver solver = new NQueensProblemSolver(30);
		solver.solveNQueensProblemRecursively();
		for(Cell cell:solver.getCells())
		{
			System.out.println("Cell coordinations: x: "+cell.getI()+", y: "+cell.getJ());
		}
	}

	
	
}
