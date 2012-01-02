package cz.kozenym.game;

import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.squander.Squander;

public class NQueensProblemSolver {

	@Ensures({
			"all k : {0 ... n-1} | lone (Cell@i) . k",
			"all k : {0 ... n-1} | lone (Cell@j) . k",
			/*none of the queens can endanger themselves*/
			"all q1 : result.elts | no q2 : result.elts - q1 | "
					+ "  q1.i == q2.i || q1.i - q1.j == q2.i - q2.j || "
					+ "  q1.j == q2.j || q1.i + q1.j == q2.i + q2.j" })
	/*we allow to modify cell coordinations*/
	@Modifies({ "Cell.i [result.elts][{0 ... n-1}]",
			"Cell.j [result.elts][{0 ... n-1}]" })
	public static void nqueens(int n, Set<Cell> result) {
		Squander.exe(null, n, result);
	}

}
