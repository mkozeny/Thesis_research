@Ensures ({"return[int] in this.extendedNodes.elts" ,
		    	 "return.length <= this.treshold" ,
      	 })
@Modifies ({ "return.length" , "return.elems" })
@FreshObjects ( cls = ExtendedNode[].class , num = 1 )
public ExtendedNode [] solveLDominantniMnozinaGrafuProblem()
{
	return Squander.exe(this);
}