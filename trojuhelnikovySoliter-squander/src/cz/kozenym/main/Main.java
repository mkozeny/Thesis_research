package cz.kozenym.main;

import cz.kozenym.game.SoliterSolver;
import cz.kozenym.game.State;

public class Main {

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

	
    
}
