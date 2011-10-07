package main;

import game.SoliterSolver;
import game.State;

public class Main {

	static final int triangleSide= 5;
	
	public static void main(String[] args) {
		State s = new State(triangleSide);
		s.initState();
		SoliterSolver ss = new SoliterSolver(s,triangleSide);
		State [] states = ss.generateStates();
		State [] result = ss.solveSolitergame(states);
		int position = 0;
		int cols;
		for(int i =0; i < result.length; i++)
		{
			System.out.println("---STATE: "+(i+1)+"---");
			cols = 1;
			position = 0;
			for(int j = 0; j< triangleSide; j++)
			{
				for(int l = 0; l< triangleSide-j; l++)
					System.out.print(" ");
				for(int k = 0; k< cols; k++)
				{
					System.out.print(((result[i].getCells()[position].getOccupied())?1:0)+" ");
					position++;
				}
				System.out.println();
				cols++;
			}
			System.out.println("S: "+result[i].s+", V: "+result[i].v+", D: "+result[i].d);
		}
				
	}

	
    
}
