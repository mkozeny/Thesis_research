package cz.kozenym.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
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
		String path = args.length>=2?args[1]:"/home/kozenym/Desktop/DP/squander_workspace/knapSack-squander-II/files/";
		String outputPath = args.length==3?args[2]:"/home/kozenym/Desktop/DP/measurement/data/knapSack/squander/";
		if(args.length!=1 || args.length!=2 || args.length!=3)
		{
			System.out.println("First argument is number of things");
			System.out.println("Second optional is input graph filepath");
			System.out.println("Third optional is output filepath");
		}
		
		
		String inputTaskFile = path+"knap_"+numberOfThings+".txt";
		String inputResFile = path+"knap_"+numberOfThings+"_res.txt";
		String outputFile = outputPath+numberOfThings+"/log";
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
			KnapSack kn = new KnapSack(capacity,n, c, v);
			try
			{
				long parseTime = getCpuTime();
				Boolean [] result = kn.solveKnapSackProblem(resCost);
				long time = getCpuTime()-parseTime;
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
				System.out.println("TIME: "+getTimeFormat().format(time));
				out.write("PRICE: "+price +", WEIGHT: "+weight+", TIME: "+getTimeFormat().format(time)+"; "+inputResLine+ "\r\n");
			}catch(Exception e)
			{
				out.write("ERROR\r\n");
			}
		}
		inTask.close();
		inResult.close();
		out.close();

	}
	/** Get CPU time in nanoseconds. */
	private static long getCpuTime() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		return bean.isCurrentThreadCpuTimeSupported() ? bean
				.getCurrentThreadCpuTime() : 0L;
	}

	private static DecimalFormat getTimeFormat() {
		return new DecimalFormat("###,###,##0.00;'-'###,###,##0.00");
	}
}
