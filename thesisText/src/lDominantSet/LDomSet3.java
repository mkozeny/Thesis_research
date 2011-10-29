@Ensures ({"return[int] in this.extendedNodes.elts" ,
		    	 "return.length <= this.treshold" ,
		    	 "return[int].neighborhood.elts == this.extendedNodes.elts",
				   "all q1: return[int] | no q2: (return[int] - q1) |" +
			   	 "q1.value == q2.value",
				   "all q1: return[int] | all q2: (return[int] - q1) |" +
					 " (all n: q2.neighborhood.elts | n.value!=q1.value)"
      	 })
@Modifies ({ "return.length" , "return.elems" })
@FreshObjects ( cls = ExtendedNode[].class , num = 1 )
public ExtendedNode [] solveLDominantniMnozinaGrafuProblem()
{
	return Squander.exe(this);
}