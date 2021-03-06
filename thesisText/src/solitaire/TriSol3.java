@Invariant("one {i : {0 ... return[0].cells.length - 1} |"+
			     "!return[states.length-1].cells[i].occupied}")
@Requires("this.n >= 5")
@Ensures({"return = states",
		      "return[0] = this.startState",
		      "one {i : {0 ... return[0].cells.length - 1} |"+
		      "return[states.length-1].cells[i].occupied}",
			    "all i: {1 ... return.length-1} |" +
		 	    "(exists src, via, dest : {0 ... return[i-1].cells.length - 1} | " +
		 	    "src!=via && src!=dest && via!=dest " +
		 	    "&& (return[i-1].cells[src].occupied " +
		 	    "&& return[i-1].cells[via].occupied "+
		 	    "&& !return[i-1].cells[dest].occupied) " +
		 	    "&& (this.adj[src][via] !=  0) " +
		 	    "&& (this.adj[via][dest] !=  0) " +
		 	    "&& (this.adj[src][via] =  this.adj[via][dest]) " +
		 	    "&& !return[i].cells[src].occupied " +
		 	    "&& !return[i].cells[via].occupied " +
		 	    "&& return[i].cells[dest].occupied "+
			    "&& (all j:{0 ... return[i-1].cells.length - 1} |"+
			    "(j!= src && j!= via && j!= dest)=>" +
			    "return[i-1].cells[j].occupied=return[i].cells[j].occupied))"})
@Modifies("Cell.occupied [{cell : Cell |"+
			    "(all i:int|return[0].cells[i]!=cell)}]")
public State [] solveSolitergame(State [] states)
{
	return Squander.exe(this, new Object[] {states});
}