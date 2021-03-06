package cz.kozenym.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import cz.kozenym.solver.KnapSack;
import cz.kozenym.solver.Thing;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String numberOfThings = args.length >= 1 ? args[0]:"30";
		String path = args.length>=2?args[1]:"/home/kozenym/Desktop/DP/squander_workspace/knapSack-squander-II/files/";
		String outputPath = args.length==3?args[2]:"/home/kozenym/Desktop/DP/measurement/data/knapSack/squander/";
		if(args.length!=1 || args.length!=2 || args.length!=3)
		{
			System.out.println("First argument is number of things");
			System.out.println("Second optional is input graph filepath");
			System.out.println("Third optional is output filepath");
		}
		String inputTaskFile = "knap_"+numberOfThings+".txt";
		String inputResFile = "knap_"+numberOfThings+"_res.txt";
		String outputFile = "log";
		
		BufferedReader inTask = new BufferedReader(new FileReader(path+inputTaskFile));
		BufferedReader inResult = new BufferedReader(new FileReader(path+inputResFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputPath+numberOfThings+"/"+outputFile));
		
		String inputLine = "";
		String inputResLine = "";
		
		while ((inputLine = inTask.readLine()) != null && (inputResLine = inResult.readLine()) != null) {
			Set<Thing> result = new HashSet<Thing>();
			StringTokenizer tokenizer = new StringTokenizer(inputLine," ");
			StringTokenizer tokenizerRes = new StringTokenizer(inputResLine," ");
			int capacity = 0 ;
			if(tokenizer.hasMoreElements())
				capacity = Integer.parseInt(tokenizer.nextToken());
			int pos = 0;
			while (tokenizer.hasMoreElements()) {
				result.add(new Thing(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()),pos++));
			}
			int resCost = 0;
			if(tokenizerRes.hasMoreElements())
				resCost = Integer.parseInt(tokenizerRes.nextToken());
			KnapSack kn = new KnapSack();
			try
			{
				long parseTime = getCpuTime();
				kn.solveKnapSackProblem(result, result.size(), capacity, resCost-1);
				long afterTime = getCpuTime();
				int price = 0;
				int weight = 0;
				Thing [] resultArr = new Thing[result.size()];
				result.toArray(resultArr);
				Arrays.sort(resultArr);
				for(Thing t:resultArr)
				{
					if(t.isChoosed())
					{
						price+=t.getC();
						weight+=t.getV();
					}
					System.out.print((t.isChoosed()?1:0)+" ");
					out.write((t.isChoosed()?1:0)+" ");
				}
				System.out.println();
				System.out.println("PRICE: "+price);
				System.out.println("WEIGHT: "+weight);
				System.out.println("RESULT PRICE: "+resCost);
				long time=0L;
				System.out.println("TIME: "
						+ getTimeFormat().format(time=afterTime - parseTime) + " ns");
				out.write("PRICE: "+price +", WEIGHT: "+weight+", TIME: "
						+ getTimeFormat().format(time) + " ns\r\n");
			}catch(Exception e)
			{
				out.write("ERROR\r\n");
			}
		
		
		inTask.close();
		inResult.close();
		out.close();
		}

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
