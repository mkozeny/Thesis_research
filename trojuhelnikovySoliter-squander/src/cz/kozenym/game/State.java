package cz.kozenym.game;


import java.util.Random;


public class State {

	int n;
	
	private Cell [] cells;
	
	public State(Cell [] cells, int n) {
		super();
		this.cells = cells;
		this.n = n;
	}
	
	public State(int n) {
		super();
		this.cells = new Cell[n*(n+1)/2];
		this.n = n;
	}
	
	public void initState()
	{
		int cols = 1;
		Random random = new Random();
		int freeRow = random.nextInt(this.n);
		int freeColumn = 0;
		if(freeRow >0)
			freeColumn = random.nextInt(freeRow);
		int position = 0;
		for(int i = 0; i< this.n; i++)
		{
			for(int j = 0; j< cols; j++)
			{
				if(i==freeRow && j==freeColumn)
					this.cells[position] = new Cell(i,j);
				else
					this.cells[position] = new Cell(i,j,true);
				position++;
			}
			cols++;
		}
	}

	

	public Cell [] getCells() {
		return cells;
	}

	public void setCells(Cell [] cells) {
		this.cells = cells;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int cols = 1;
		int position = 0;
		for(int j = 0; j< this.n; j++)
		{
			for(int l = 0; l< this.n-j; l++)
				System.out.print(" ");
			for(int k = 0; k< cols; k++)
			{
				System.out.print(((this.getCells()[position].getOccupied())?1:0)+" ");
				position++;
			}
			System.out.println();
			cols++;
		}
		return sb.toString();
	}

	
	
}
