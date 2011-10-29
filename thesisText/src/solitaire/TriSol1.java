@Invariant("one {i : {0 ... return[0].cells.length - 1} |"+
			     "return[states.length-1].cells[i].occupied}")
@Requires("this.n >= 5")
@Modifies("Cell.occupied [{cell : Cell |"+
			    "(all i:int|return[0].cells[i]!=cell)}]")
public State [] solveSolitergame(State [] states)
{
	return Squander.exe(this, new Object[] {states});
}