package cz.kozenym.main;

import java.util.HashSet;
import java.util.Set;

import cz.kozenym.game.Cell;
import cz.kozenym.game.NQueensProblemSolver;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Cell> cells = new HashSet<Cell>();
		for (int i = 0; i < 8; i++)
            cells.add(new Cell(i, 0));
		NQueensProblemSolver.nqueens(8, cells);
		for(Cell cell:cells)
		{
			System.out.println("Cell coordinations: x: "+cell.getI()+", y: "+cell.getJ());
		}

	}

}
