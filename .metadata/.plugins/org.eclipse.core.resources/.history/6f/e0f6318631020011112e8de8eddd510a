package main;

import java.util.Random;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Invariant;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;

public class SoliterSolver {

static final int triangleSide= 5;
	
	public static void main(String[] args) {
		State s = new State(triangleSide);
		s.initState();
		SoliterSolver ss = new SoliterSolver(s,triangleSide);
		State [] states = ss.generateStates();
		State [] result = ss.solveSolitergame(states);
		for(int i =0; i < result.length; i++)
		{
			System.out.println("---STATE: "+(i+1)+"---");
			result[i].toString();
		}
				
	}
	
	private int n;
	
	//left=1
	//right=2
	//left top=3
	//right top=4
	//left bottom=5
	//right bottom=6
	private int [] [] adj;
	
	public SoliterSolver(State startState, int n) {
		super();
		this.startState = startState;
		this.n = n;
	}


	State startState;
	
	@Invariant("one {i : {0 ... return[0].cells.length - 1} | return[states.length-1].cells[i].occupied}")
	@Requires("this.n >= 5")
	@Ensures({
		"return = states",
		"return[0] = this.startState",
		//"one {i : {0 ... return[0].cells.length - 1} | return[states.length-1].cells[i].occupied}",
		"all i: {1 ... return.length-1} |  " +
		 "(exists src, via, dest : {0 ... return[i-1].cells.length - 1} | " +
		 "src!=via && src!=dest && via!=dest &&" +
		 " (return[i-1].cells[src].occupied " +
		 "&& return[i-1].cells[via].occupied && !return[i-1].cells[dest].occupied)  " +
		 /*"&& return[i-1].src = src "+
		 "&& return[i-1].via = via "+
		 "&& return[i-1].dest = dest "+*/
		 "&& (this.adj[src][via] !=  0) " +
		 "&& (this.adj[via][dest] !=  0) " +
		 "&& (this.adj[src][via] =  this.adj[via][dest])" +
		 "&& !return[i].cells[src].occupied" +
		 "&& !return[i].cells[via].occupied" +
		 "&& return[i].cells[dest].occupied && " +
		"(all j:{0 ... return[i-1].cells.length - 1} | (j!= src && j!= via && j!= dest)=>" +
		"return[i-1].cells[j].occupied=return[i].cells[j].occupied))"})
	@Modifies({"Cell.occupied [{cell : Cell | (all i:int|return[0].cells[i]!=cell)}]"//,"State.src"
			//,"State.src", "State.via", "State.dest"
		})
	public State [] solveSolitergame(State [] states)
	{
		return Squander.exe(this, new Object[] {states});
	}
	
	public State [] generateStates()
	{
		
		int statesCount = this.n*(this.n+1)/2;
		this.adj = new int [statesCount][statesCount];
		State [] states = new State [statesCount-1];
		for(int i=0; i<startState.getCells().length; i++)
		{
			Cell actualCell = startState.getCells()[i];
			for(int j=0; j< startState.getCells().length; j++)
			{
				Cell potentialNeighbor = startState.getCells()[j];
				if(potentialNeighbor.getRow() == actualCell.getRow() && potentialNeighbor.getColumn() == actualCell.getColumn() -1)
					adj[i][j] = 1;
				else if(potentialNeighbor.getRow() == actualCell.getRow() && potentialNeighbor.getColumn() == actualCell.getColumn() +1)
					adj[i][j] = 2;
				else if(potentialNeighbor.getRow() == actualCell.getRow() -1 && potentialNeighbor.getColumn() == actualCell.getColumn() -1)
					adj[i][j] = 3;
				else if(potentialNeighbor.getRow() == actualCell.getRow() -1 && potentialNeighbor.getColumn() == actualCell.getColumn())
					adj[i][j] = 4;
				else if(potentialNeighbor.getRow() == actualCell.getRow() +1 && potentialNeighbor.getColumn() == actualCell.getColumn())
					adj[i][j] = 5;
				else if(potentialNeighbor.getRow() == actualCell.getRow() +1 && potentialNeighbor.getColumn() == actualCell.getColumn() +1)
					adj[i][j] = 6;
			}
		}
		states[0] = startState;
		int position = 0;
		int cols;
		for(int i =0; i < statesCount-2; i++)
		{
			State s = new State(this.n);
			cols = 1;
			position = 0;
			for(int j = 0; j< this.n; j++)
			{
				for(int k = 0; k< cols; k++)
				{
					s.getCells()[position] = new Cell(j,k);
					position++;
				}
				cols++;
			}
			states[i+1]=s;
		}
		
		for(int i =0; i < statesCount-1; i++)
		{
			System.out.println("---STATE: "+(i+1)+"---");
			states[i].toString();
		}
		
		return states;
	}
	
	public static class State {

		int n, src, via, dest;
		
		private Cell [] cells;
		
		public State(Cell [] cells, int n) {
			super();
			this.cells = cells;
			this.n = n;
			/*this.src = 0;
			this.via = 0;
			this.dest = 0;*/
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
			System.out.println("SRC: "+this.src+", VIA: "+this.via+", DEST: "+this.dest);
			return sb.toString();
		}
		}
	public static class Cell implements Comparable<Cell>{

		public Cell() {
			super();
		}
		
		public Cell(int row, int column) {
			super();
			this.row = row;
			this.column = column;
			this.occupied = false;
		}
		
		public Cell(int row, int column, boolean occupied) {
			super();
			this.row = row;
			this.column = column;
			this.occupied = occupied;
		}



		private int row;
		
		private int column;
		
		private boolean occupied;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		public boolean getOccupied() {
			return occupied;
		}

		public void setOccupied(boolean occupied) {
			this.occupied = occupied;
		}

		@Override
		public boolean equals(Object o)
		{
			if(o==null || this == null) return false;
			Cell c = (Cell)o;
			if(c.getRow() == this.getRow() && c.getColumn() == this.getColumn()) return true;
			return false;
		}
		@Override
		public int hashCode()
				{
					int hash = 7;
					hash = 31 * hash + this.row + this.column;
					return hash;
				}



		



		@Override
		public int compareTo(Cell o2) {
			if(this.getRow()<o2.getRow())
				return -1;
			else if(this.getRow()>o2.getRow())
				return 1;
			else if(this.getRow()==o2.getRow())
			{
				if(this.getColumn()<o2.getColumn())
					return -1;
				else if(this.getColumn()>o2.getColumn())
					return 1;
				return 0;
			}
			return 0;
		}
		
		
	}
}
