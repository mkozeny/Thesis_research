package main;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.squander.*;

public class NQueensProblemSolver {

	private int n;

	public NQueensProblemSolver(int n) {
		super();
		this.n = n;
	}

	@Requires ( " result.length == n" )
	@Ensures ( {
	" all k: int | k>=0 && k<n => lone (Cell@i) . k" ,
	" all k: int | k>=0 && k<n => lone (Cell@j) . k" ,
	" all q1: result.elts | no q2: result.elts - q1 |" +
	" q1.i = q2.i || q1.i - q1.j = q2.i - q2.j || q1.j = q2.j || q1.i + q1.j = q2.i + q2.j "
	})
	@Modifies ({
	" Cell.i[][] [{k: int | k>=0 && k<n}] " ,
	" Cell.j[][] [{k: int | k>=0 && k<n}] "
	})
	public static void nqueens ( int n , Set<Cell> result ) {
		Squander.exe( null , n , result ) ;
	}

	public int getN() {
		return n;
	}

	
}
