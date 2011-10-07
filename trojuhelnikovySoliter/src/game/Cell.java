package game;

import java.util.Comparator;

public class Cell implements Comparable<Cell>{

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
