package main;

import java.util.HashSet;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*int [] v = new int[4];
		v[0]=18;
		v[1]=42;
		v[2]=88;
		v[3]=3;
		int [] c = new int[4];
		c[0]=114;
		c[1]=136;
		c[2]=192;
		c[3]=223;
		kn.setV(v);
		kn.setC(c);
		Integer [] result = kn.solveKnapSackProblem(470);*/
		//KnapSack kn = new KnapSack(200, 15);
		
		Set<Thing> result = new HashSet<Thing>();
		result.add(new Thing(6, 247));
		result.add(new Thing(2, 210));
		result.add(new Thing(8, 163));
		result.add(new Thing(1, 93));
		result.add(new Thing(44, 255));
		result.add(new Thing(7, 244));
		result.add(new Thing(28, 190));
		result.add(new Thing(5, 117));
		result.add(new Thing(16, 234));
		result.add(new Thing(9, 105));
		result.add(new Thing(3, 29));
		/*result.add(new Thing(12, 135));
		result.add(new Thing(35, 96));
		result.add(new Thing(18, 203));
		result.add(new Thing(45, 238));*/
		KnapSack kn = new KnapSack();
		/*int [] v = new int[10];
		v[0]=6;
		v[1]=2;
		v[2]=8;
		v[3]=1;
		v[4]=44;
		v[5]=7;
		v[6]=28;
		v[7]=5;
		v[8]=16;
		v[9]=9;
		v[10]=3;
		v[11]=12;
		v[12]=35;
		v[13]=18;
		v[14]=45;
		int [] c = new int[10];
		c[0]=247;
		c[1]=210;
		c[2]=163;
		c[3]=93;
		c[4]=255;
		c[5]=244;
		c[6]=190;
		c[7]=117;
		c[8]=234;
		c[9]=105;
		c[10]=29;
		c[11]=135;
		c[12]=96;
		c[13]=203;
		c[14]=238;
		kn.setV(v);
		kn.setC(c);
		//Integer [] result = kn.solveKnapSackProblem(2350);
		Integer [] result = kn.solveKnapSackProblem(520);
*/		
		kn.solveKnapSackProblem(result, result.size(), 250, 1020);
		for(Thing t:result)
		{
			System.out.print(t.getChoosed()+" ");
		}

	}

}
