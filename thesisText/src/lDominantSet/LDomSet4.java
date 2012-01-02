@Ensures ( "all q1: return[int] | no q2: (return[int] - q1) |" +
			   	 "q1.value == q2.value")