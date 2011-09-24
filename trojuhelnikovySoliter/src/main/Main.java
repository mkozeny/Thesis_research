package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import game.Cell;
import game.GameField;
import game.SoliterSolver;
import game.State;

public class Main {

	static final int triangleSide= 3;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int [] [] gameField = new int[2][2];
		
		State s = new State(triangleSide);
		s.initState();
		/*Set<State> states = new HashSet<State>();
		states.add(s);*/
		SoliterSolver ss = new SoliterSolver(s,triangleSide);
		//ss.generateStates();
		//ss.generateGameField();
		State [] states = ss.generateStates();
		ss.solveSolitergame(states);
		int cnt=1;
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
		/*GameField gf = new GameField(4);
		gf.initGameField();
		gf.generateGameField();
		for(int i = 0; i < gf.getGameField().length; i++)
		{
			for(int j = 0; j < gf.getGameField()[i].length; j++)
				System.out.print(gf.getGameField()[i][j]);
			System.out.println();
		}*/
		/*int [] [] gameField = ss.getGameField();
		for(int i = 0; i < gameField.length; i++)
		{
			for(int j = 0; j < gameField[i].length; j++)
				System.out.print(gameField[i][j]);
			System.out.println();
		}*/
	}

}
