package cz.kozenym.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import cz.kozenym.solver.KnapSack;

public class Main {

	private static final int capacity = 100;
	private static final int n = 4;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int [] v = new int[n];
		int [] c = new int[n];
		String numberOfThings = args.length == 1 ? args[0]:"4";
		String inputTaskFile = "files/knap_"+numberOfThings+".txt";
		String inputResFile = "files/knap_"+numberOfThings+"_res.txt";
		String outputFile = "files/knap_"+numberOfThings+"_sol.txt";
		BufferedReader inTask = new BufferedReader(new FileReader(inputTaskFile));
		BufferedReader inResult = new BufferedReader(new FileReader(inputResFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

		String inputLine = "";
		String inputResLine = "";
		while ((inputLine = inTask.readLine()) != null && (inputResLine = inResult.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(inputLine," ");
			StringTokenizer tokenizerRes = new StringTokenizer(inputResLine," ");
			int k = 0;
			while (tokenizer.hasMoreElements()) {
				v[k] = Integer.parseInt(tokenizer.nextToken());
				c[k] = Integer.parseInt(tokenizer.nextToken());
				k++;
			}
			int resCost = Integer.parseInt(tokenizerRes.nextToken());
			//if(resCost>=510)
			//	continue;
			KnapSack kn = new KnapSack(capacity,n, c, v);
			try
			{
				Boolean [] result = kn.solveKnapSackProblem(resCost);
				int price = 0;
				int weight = 0;
				for(int i=0; i < result.length; i++)
				{
					if(result[i])
					{
						price+=c[i];
						weight+=v[i];
					}
					System.out.print((result[i]?1:0)+" ");
					out.write((result[i]?1:0)+" ");
				}
				System.out.println();
				System.out.println("PRICE: "+price);
				System.out.println("WEIGHT: "+weight);
				System.out.println("RESULT PRICE: "+resCost);
				out.write("PRICE: "+price +", WEIGHT: "+weight+"; "+inputResLine+ "\r\n");
			}catch(Exception e)
			{
				out.write("ERROR\r\n");
			}
		}
		inTask.close();
		inResult.close();
		out.close();

		//27 38 2 86 41 112 1 0 25 66 1 97 34 195 3 85 50 42 12 223
		/*int [] v = new int[10];
		v[0]=27;
		v[1]=2;
		v[2]=41;
		v[3]=1;
		v[4]=25;
		v[5]=1;
		v[6]=34;
		v[7]=3;
		v[8]=50;
		v[9]=12;*/
		//18 114 42 136 88 192 3 223
		/*int [] v = new int[4];
		v[0]=18;
		v[1]=42;
		v[2]=88;
		v[3]=3;*/
		
		//27 38 2 86 41 112 1 0 25 66 1 97 34 195 3 85 50 42 12 223
		/*int [] c = new int[10];
		c[0]=38;
		c[1]=86;
		c[2]=112;
		c[3]=0;
		c[4]=66;
		c[5]=97;
		c[6]=195;
		c[7]=85;
		c[8]=42;
		c[9]=223;*/
		//18 114 42 136 88 192 3 223
		/*int [] c = new int[4];
		c[0]=114;
		c[1]=136;
		c[2]=192;
		c[3]=223;*/
		
		/*KnapSack kn = new KnapSack(230,10, c, v);*/
		/*KnapSack kn = new KnapSack(capacity,n, c, v);
		
		Boolean [] result = kn.solveKnapSackProblem(470);
		int price = 0;
		int weight = 0;
		for(int i=0; i < result.length; i++)
		{
			if(result[i])
			{
				price+=c[i];
				weight+=v[i];
			}
			System.out.print((result[i]?1:0)+" ");
		}
		System.out.println();
		System.out.println("PRICE: "+price);
		System.out.println("WEIGHT: "+weight);*/
	}

}
