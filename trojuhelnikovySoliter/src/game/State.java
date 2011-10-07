package game;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class State {

	private Cell [] cells;
	
	private int n;
	
	public int s, v, d ;
	
	//TODO udelat hash mapou
	/*private Map<Cell, Cell> leftNeighbors;
	
	private Map<Cell, Cell> leftTopNeighbors;
	
	private Map<Cell, Cell> leftBottomNeighbors;
	
	private Map<Cell, Cell> rightNeighbors;
	
	private Map<Cell, Cell> rightTopNeighbors;
	
	private Map<Cell, Cell> rightBottomNeighbors;*/

	public State(Cell [] cells, int n) {
		super();
		this.cells = cells;
		this.n = n;
		/*this.leftNeighbors = new HashMap<Cell, Cell>();
		this.leftTopNeighbors = new HashMap<Cell, Cell>();
		this.leftBottomNeighbors = new HashMap<Cell, Cell>();
		this.rightNeighbors = new HashMap<Cell, Cell>();
		this.rightTopNeighbors = new HashMap<Cell, Cell>();
		this.rightBottomNeighbors = new HashMap<Cell, Cell>();*/
	}
	
	public State(int n) {
		super();
		this.cells = new Cell[n*(n+1)/2];
		this.n = n;
		/*this.leftNeighbors = new HashMap<Cell, Cell>();
		this.leftTopNeighbors = new HashMap<Cell, Cell>();
		this.leftBottomNeighbors = new HashMap<Cell, Cell>();
		this.rightNeighbors = new HashMap<Cell, Cell>();
		this.rightTopNeighbors = new HashMap<Cell, Cell>();
		this.rightBottomNeighbors = new HashMap<Cell, Cell>();*/
	}
	
	public void initState()
	{
		int cols = 1;
		Random random = new Random();
		int freeRow = random.nextInt(this.n);
		//System.out.println("Free row: "+freeRow);
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
		//int cellsSize = this.cells.size();
		/*for(Cell actualCell:this.cells)
		{
			for(Cell potentialNeighbor:this.cells)
			{
				if(potentialNeighbor.getRow() == actualCell.getRow() && potentialNeighbor.getColumn() == actualCell.getColumn() -1)
					leftNeighbors.put(actualCell, potentialNeighbor);
				else if(potentialNeighbor.getRow() == actualCell.getRow() && potentialNeighbor.getColumn() == actualCell.getColumn() +1)
					rightNeighbors.put(actualCell, potentialNeighbor);
				else if(potentialNeighbor.getRow() == actualCell.getRow() -1 && potentialNeighbor.getColumn() == actualCell.getColumn() -1)
					leftTopNeighbors.put(actualCell, potentialNeighbor);
				else if(potentialNeighbor.getRow() == actualCell.getRow() -1 && potentialNeighbor.getColumn() == actualCell.getColumn())
					rightTopNeighbors.put(actualCell, potentialNeighbor);
				else if(potentialNeighbor.getRow() == actualCell.getRow() +1 && potentialNeighbor.getColumn() == actualCell.getColumn())
					leftBottomNeighbors.put(actualCell, potentialNeighbor);
				else if(potentialNeighbor.getRow() == actualCell.getRow() +1 && potentialNeighbor.getColumn() == actualCell.getColumn() +1)
					rightBottomNeighbors.put(actualCell, potentialNeighbor);
			}
		}*/
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

	/*public Map<Cell, Cell> getLeftNeighbors() {
		return leftNeighbors;
	}

	public void setLeftNeighbors(Map<Cell, Cell> leftNeighbors) {
		this.leftNeighbors = leftNeighbors;
	}

	public Map<Cell, Cell> getLeftTopNeighbors() {
		return leftTopNeighbors;
	}

	public void setLeftTopNeighbors(Map<Cell, Cell> leftTopNeighbors) {
		this.leftTopNeighbors = leftTopNeighbors;
	}

	public Map<Cell, Cell> getLeftBottomNeighbors() {
		return leftBottomNeighbors;
	}

	public void setLeftBottomNeighbors(Map<Cell, Cell> leftBottomNeighbors) {
		this.leftBottomNeighbors = leftBottomNeighbors;
	}

	public Map<Cell, Cell> getRightNeighbors() {
		return rightNeighbors;
	}

	public void setRightNeighbors(Map<Cell, Cell> rightNeighbors) {
		this.rightNeighbors = rightNeighbors;
	}

	public Map<Cell, Cell> getRightTopNeighbors() {
		return rightTopNeighbors;
	}

	public void setRightTopNeighbors(Map<Cell, Cell> rightTopNeighbors) {
		this.rightTopNeighbors = rightTopNeighbors;
	}

	public Map<Cell, Cell> getRightBottomNeighbors() {
		return rightBottomNeighbors;
	}

	public void setRightBottomNeighbors(Map<Cell, Cell> rightBottomNeighbors) {
		this.rightBottomNeighbors = rightBottomNeighbors;
	}*/
	
	
}