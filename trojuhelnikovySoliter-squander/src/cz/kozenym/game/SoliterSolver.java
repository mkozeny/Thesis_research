package cz.kozenym.game;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Invariant;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.Squander;

public class SoliterSolver {

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
	
	/*in started state has to be only one position unoccupied*/
	@Invariant("one {i : {0 ... return[0].cells.length - 1} | !return[0].cells[i].occupied}")
	/*to keep sense of the game, it is necessary to have size of side of the game field greater or equal to 5*/
	@Requires("this.n >= 5")
	@Ensures({
		"return = states", /*return array contains all states*/
		"return[0] = this.startState", /*first state is start state*/
		"one {i : {0 ... return[0].cells.length - 1} | return[states.length-1].cells[i].occupied}", /*in last state has to be only one position occupied*/
		/*transition constraint - if is possible 45 degree or 135 degree transition,
		 * then do the transition and copy occupation of other position*/
		"all i: {1 ... return.length-1} |  " +
		 "(exists src, via, dest : {0 ... return[i-1].cells.length - 1} | " +
		 "src!=via && src!=dest && via!=dest &&" +
		 " (return[i-1].cells[src].occupied " +
		 "&& return[i-1].cells[via].occupied && !return[i-1].cells[dest].occupied)  " +
		 "&& (this.adj[src][via] !=  0) " +
		 "&& (this.adj[via][dest] !=  0) " +
		 "&& (this.adj[src][via] =  this.adj[via][dest])" +
		 "&& !return[i].cells[src].occupied" +
		 "&& !return[i].cells[via].occupied" +
		 "&& return[i].cells[dest].occupied && " +
		"(all j:{0 ... return[i-1].cells.length - 1} | (j!= src && j!= via && j!= dest)=>" +
		"return[i-1].cells[j].occupied=return[i].cells[j].occupied))"})
	@Modifies({"Cell.occupied [{cell : Cell | (all i:int|return[0].cells[i]!=cell)}]"
		/*it is possible to modify boolean property of class Cell, when it is not in start state*/})
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
}
