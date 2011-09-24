package game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game.Cell;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Invariant;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.FreshObjects;

public class SoliterSolver {

	private int n;
	
	
	public SoliterSolver(State startState, int n) {
		super();
		this.startState = startState;
		this.n = n;
		this.states = new HashSet<State>();
		this.leftNeighbors = new HashMap<Cell, Cell>();
		this.leftTopNeighbors = new HashMap<Cell, Cell>();
		this.leftBottomNeighbors = new HashMap<Cell, Cell>();
		this.rightNeighbors = new HashMap<Cell, Cell>();
		this.rightTopNeighbors = new HashMap<Cell, Cell>();
		this.rightBottomNeighbors = new HashMap<Cell, Cell>();
	}


	Set<State> states;
	
	State startState;
	
	private Map<Cell, Cell> leftNeighbors;
	
	private Map<Cell, Cell> leftTopNeighbors;
	
	private Map<Cell, Cell> leftBottomNeighbors;
	
	private Map<Cell, Cell> rightNeighbors;
	
	private Map<Cell, Cell> rightTopNeighbors;
	
	private Map<Cell, Cell> rightBottomNeighbors;
	
	
	/*@Ensures({"return[int] in this.states.elts",
		"return[0] = this.startState",
		"return.length = #this.states.elts",
		 "all i: int | i > 0 && i < return.length  =>" +
		 "(some src, via, dest : return[i-1].cells.elts | src.occupied=1 " +
		 "&& via.occupied=1 && dest.occupied=0  " +
		 //" => return[i] = return[i-1])"// +
		 "&& ((this.leftNeighbors.elts[src] = via && this.leftNeighbors.elts[via] = dest)" +
		 "|| (this.rightNeighbors.elts[src] = via && this.rightNeighbors.elts[via] = dest)" +
		 "|| (this.leftTopNeighbors.elts[src] = via && this.leftTopNeighbors.elts[via] = dest)" +
		 "|| (this.leftBottomNeighbors.elts[src] = via && this.leftBottomNeighbors.elts[via] = dest)" +
		 "|| (this.rightTopNeighbors.elts[src] = via && this.rightTopNeighbors.elts[via] = dest)" +
		 "|| (this.rightBottomNeighbors.elts[src] = via && this.rightBottomNeighbors.elts[via] = dest))" +
			      "=> (all cell1 : return[i-1].cells.elts | " +
			      "(all cell2 : return[i].cells.elts | (cell1 != src && cell1 != via && cell1 != dest)?(cell2.occupied=cell1.occupied):" +
			      "((cell2=src || cell2=via)?(cell2.occupied=0):(cell2.occupied=1)))))"})
	//@Modifies({"Cell.occupied"})
	@Modifies({"Cell.occupied"
		//,"return.elems","return.length"
		})
	@FreshObjects(cls = State[].class, num = 1)
	public State [] solveSolitergame()
	{
		return Squander.exe(this);
	}*/

	/*@Ensures({
		 "all i: int | i > 0 && i < states.length  =>" +
		 "(all src, via, dest : states[i-1].cells.elts | src.occupied=1 " +
		 "&& via.occupied=1 && dest.occupied=0  " +
		 "&& ((this.leftNeighbors.elts[src] = via && this.leftNeighbors.elts[via] = dest)" +
		 "|| (this.rightNeighbors.elts[src] = via && this.rightNeighbors.elts[via] = dest)" +
		 "|| (this.leftTopNeighbors.elts[src] = via && this.leftTopNeighbors.elts[via] = dest)" +
		 "|| (this.leftBottomNeighbors.elts[src] = via && this.leftBottomNeighbors.elts[via] = dest)" +
		 "|| (this.rightTopNeighbors.elts[src] = via && this.rightTopNeighbors.elts[via] = dest)" +
		 "|| (this.rightBottomNeighbors.elts[src] = via && this.rightBottomNeighbors.elts[via] = dest))" +
			      "=> (all cell1 : states[i-1].cells.elts | " +
			      "(all cell2 : states[i].cells.elts | (cell1 != src && cell1 != via && cell1 != dest)?(cell2.occupied=cell1.occupied):" +
			      "((cell2=src || cell2=via)?(cell2.occupied=0):(cell2.occupied=1)))))"})*/
	//@Invariant("this.startState.cells.elts")
	@Ensures({
		"states[0] = this.startState",
		"all v : {1} | one {c : states[states.length-1].cells.elts | c.occupied = v}",
		"all i: int | i>=0 && i<states.length => (all c : states[i].cells.elts | c.occupied in {0,1})",
		 "all i: int | i > 0 && i < states.length  => " +
		 //"(all src : states[i-1].cells.elts | all via : (states[i-1].cells.elts - src) | all dest : (states[i-1].cells.elts - src - via) | " +
		 "(some src, via ,dest : (states[i-1].cells.elts) | " +
		 "src.occupied=1 " +
		 "&& via.occupied=1 && dest.occupied=0  " +
		 "&& ((this.leftNeighbors.elts[src] = via && this.leftNeighbors.elts[via] = dest)" +
		 "|| (this.rightNeighbors.elts[src] = via && this.rightNeighbors.elts[via] = dest)" +
		 "|| (this.leftTopNeighbors.elts[src] = via && this.leftTopNeighbors.elts[via] = dest)" +
		 "|| (this.leftBottomNeighbors.elts[src] = via && this.leftBottomNeighbors.elts[via] = dest)" +
		 "|| (this.rightTopNeighbors.elts[src] = via && this.rightTopNeighbors.elts[via] = dest)" +
		 "|| (this.rightBottomNeighbors.elts[src] = via && this.rightBottomNeighbors.elts[via] = dest))" +
		//"=> states[i].cells.elts = states[i-1].cells.elts)"
			      "=> (all cell1 : states[i-1].cells.elts | " +
			      "(all cell2 : states[i].cells.elts |" +
			      " (cell1 != src && cell1 != via && cell1 != dest)?" +
			      "(cell2.occupied=cell1.occupied)" +
			      ":" +
			      "((cell2=src || cell2=via)?(cell2.occupied=0):(cell2.occupied=1)))))"
			      })
	//@Modifies({"Cell.occupied"})
	@Modifies({"State.cells.elts.occupied [{cell : Cell | cell !in this.startState.cells.elts}]"
		
		//,"return.elems","return.length"
		})
	//@FreshObjects(cls = State[].class, num = 1)
	public void solveSolitergame(State [] states)
	{
		Squander.exe(this, new Object[] {states});
	}
	
	/*public void generateStates()
	{
		int statesCount = 0;
		int lastRow = 0;
		for(int i =0; i < this.n; i++)
		{
			lastRow +=1;
			statesCount += lastRow;
		}
		for(Cell actualCell:startState.getCells())
		{
			for(Cell potentialNeighbor:startState.getCells())
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
		}
		this.states.add(startState);
		for(int i =0; i < statesCount-2; i++)
		{
			State s = new State(this.n);
			int cols = 1;
			for(int j = 0; j< this.n; j++)
			{
				for(int k = 0; k< cols; k++)
				{
					s.getCells().add(new Cell(i,j,0));
				}
				cols++;
			}
			//s.setLeftNeighbors(this.startState.getLeftBottomNeighbors());
			this.states.add(s);
		}
		System.out.println("---COUNT OF STATES: "+this.states.size()+"---");
	}*/
	public State [] generateStates()
	{
		
		int statesCount = this.n*(this.n+1)/2;
		/*int lastRow = 0;
		for(int i =0; i < this.n; i++)
		{
			lastRow +=1;
			statesCount += lastRow;
		}*/
		State [] states = new State [statesCount-1];
		for(Cell actualCell:startState.getCells())
		{
			for(Cell potentialNeighbor:startState.getCells())
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
		}
		/*for(Cell cell:startState.getCells())
		{
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Left: "+(leftNeighbors.get(cell)!=null?leftNeighbors.get(cell).getRow():"-")+", "+(leftNeighbors.get(cell)!=null?leftNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Left top: "+(leftTopNeighbors.get(cell)!=null?leftTopNeighbors.get(cell).getRow():"-")+", "+(leftTopNeighbors.get(cell)!=null?leftTopNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Right top: "+(rightTopNeighbors.get(cell)!=null?rightTopNeighbors.get(cell).getRow():"-")+", "+(rightTopNeighbors.get(cell)!=null?rightTopNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Right: "+(rightNeighbors.get(cell)!=null?rightNeighbors.get(cell).getRow():"-")+", "+(rightNeighbors.get(cell)!=null?rightNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Right bottom: "+(rightBottomNeighbors.get(cell)!=null?rightBottomNeighbors.get(cell).getRow():"-")+", "+(rightBottomNeighbors.get(cell)!=null?rightBottomNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Left bottom: "+(leftBottomNeighbors.get(cell)!=null?leftBottomNeighbors.get(cell).getRow():"-")+", "+(leftBottomNeighbors.get(cell)!=null?leftBottomNeighbors.get(cell).getColumn():"-"));
			System.out.println("--------------------");
		}
		System.out.println("--------------------");*/
		states[0] = startState;
		for(int i =0; i < statesCount-2; i++)
		{
			State s = new State(this.n);
			int cols = 1;
			for(int j = 0; j< this.n; j++)
			{
				for(int k = 0; k< cols; k++)
				{
					s.getCells().add(new Cell(j,k));
				}
				cols++;
			}
			//s.setLeftNeighbors(this.startState.getLeftBottomNeighbors());
			states[i+1]=s;
		}
		/*for(Cell cell:states[1].getCells())
		{
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Left: "+(leftNeighbors.get(cell)!=null?leftNeighbors.get(cell).getRow():"-")+", "+(leftNeighbors.get(cell)!=null?leftNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Left top: "+(leftTopNeighbors.get(cell)!=null?leftTopNeighbors.get(cell).getRow():"-")+", "+(leftTopNeighbors.get(cell)!=null?leftTopNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Right top: "+(rightTopNeighbors.get(cell)!=null?rightTopNeighbors.get(cell).getRow():"-")+", "+(rightTopNeighbors.get(cell)!=null?rightTopNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Right: "+(rightNeighbors.get(cell)!=null?rightNeighbors.get(cell).getRow():"-")+", "+(rightNeighbors.get(cell)!=null?rightNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Right bottom: "+(rightBottomNeighbors.get(cell)!=null?rightBottomNeighbors.get(cell).getRow():"-")+", "+(rightBottomNeighbors.get(cell)!=null?rightBottomNeighbors.get(cell).getColumn():"-"));
			System.out.println("Cell: "+cell.getRow()+", "+cell.getColumn()+"; Left bottom: "+(leftBottomNeighbors.get(cell)!=null?leftBottomNeighbors.get(cell).getRow():"-")+", "+(leftBottomNeighbors.get(cell)!=null?leftBottomNeighbors.get(cell).getColumn():"-"));
			System.out.println("--------------------");
			
		}*/
		System.out.println("---COUNT OF STATES: "+states.length+"---");
		int cnt = 1;
		for(State state:states)
		{
			System.out.println("---STATE: "+cnt+"---");
			Cell [] cells = new Cell[state.getCells().size()];
			state.getCells().toArray(cells);
			Arrays.sort(cells);
			for(Cell cell:cells)
				System.out.print(cell.getOccupied()+" ");
			System.out.println();
					cnt++;
		}
		return states;
	}
}
