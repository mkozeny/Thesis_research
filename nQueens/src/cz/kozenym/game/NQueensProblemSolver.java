package cz.kozenym.game;

public class NQueensProblemSolver {

	private int n;

	private Cell[] cells;

	private int pointer;

	public NQueensProblemSolver(int n) {
		super();
		this.n = n;
		this.cells = new Cell[n];
		this.pointer = 0;
	}

	/*
	 * Backtrack implementation
	 */
	public void solveNQueensProblemRecursively() {
		/*iterating through columns of game field*/
		for (int j = 0; j < n; j++) {
				/*if there are not queens in the same row or in the diagonal, then put this cell into result array*/
				if (!(isSomeQueenInSameRow(j) || isSomeQueenOnDiagonal(pointer,
						j))) {
					cells[pointer] = new Cell(pointer, j);
					/*if algorithm reach end of the game field, then return*/
					if (pointer == n - 1)
						return;
					++pointer;
					/*otherwise go to the next column*/
					solveNQueensProblemRecursively();
					/*if it is not possible to occupy position in actual column, then return back*/
					if (pointer < n - 1 || cells[pointer] == null)
						cells[--pointer] = null;

				}
			}

		
	}

	private boolean isSomeQueenInSameRow(int row) {
		for (int k = 0; k < pointer; k++) {
			if (cells[k].getJ() == row)
				return true;
		}
		return false;
	}

	private boolean isSomeQueenOnDiagonal(int row, int column) {
		for (int k = 0; k < pointer; k++) {
			if (cells[k].getI() - cells[k].getJ() == row - column
					|| cells[k].getI() + cells[k].getJ() == row + column)
				return true;
		}
		return false;
	}

	public Cell[] getCells() {
		return this.cells;
	}
}
