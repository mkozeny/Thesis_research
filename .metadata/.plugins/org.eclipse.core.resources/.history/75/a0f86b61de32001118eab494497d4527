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

public class Main {

	//private static final int capacity = 100;
	//private static final int n = 4;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int [] countOfThings = {15,20,22,25,27,32,35,37,40};
		
		for(int w=0; w<countOfThings.length;w++)
		{
		
		//String numberOfThings = args.length == 1 ? args[0]:"30";
		String numberOfThings = String.valueOf(countOfThings[w]);
		String inputTaskFile = "knap_"+numberOfThings+".txt";
		//String inputTaskFile = "files/knap_40.txt";
		String inputResFile = "knap_"+numberOfThings+"_res.txt";
		//String inputResFile = "files/knap_40_res.txt";
		String outputFile = numberOfThings+"_log.txt";
		
		//String outputFile = "files/knap_40_sol.txt";
		BufferedReader inTask = new BufferedReader(new FileReader("/home/kozenym/Desktop/DP/squander_workspace/knapSack-squander-II/files/"+inputTaskFile));
		BufferedReader inResult = new BufferedReader(new FileReader("/home/kozenym/Desktop/DP/squander_workspace/knapSack-squander-II/files/"+inputResFile));
		BufferedWriter out = new BufferedWriter(new FileWriter("/home/kozenym/Desktop/DP/measurement/data/knapSack/squander/"+numberOfThings+"/"+outputFile));
		
		String inputLine = "";
		String inputResLine = "";
		long totalTime=0L;
		int cnt=0;
		long [][] frequency = null;
		while ((inputLine = inTask.readLine()) != null && (inputResLine = inResult.readLine()) != null) {
			cnt++;
			//Thing [] result = new Thing[10];
			Set<Thing> result = new HashSet<Thing>();
			StringTokenizer tokenizer = new StringTokenizer(inputLine," ");
			StringTokenizer tokenizerRes = new StringTokenizer(inputResLine," ");
			int capacity = 0 ;
			if(tokenizer.hasMoreElements())
				capacity = Integer.parseInt(tokenizer.nextToken());
			int pos = 0;
			while (tokenizer.hasMoreElements()) {
				//result[pos++] = new Thing(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()),pos);
				result.add(new Thing(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()),pos++));
			}
			if(frequency==null)
				frequency = new long[pos][2];
			int resCost = 0;
			if(tokenizerRes.hasMoreElements())
				resCost = Integer.parseInt(tokenizerRes.nextToken());
			//if(resCost>=510)
			//	continue;
			KnapSack kn = new KnapSack();
			try
			{
				//String s = 	sc.nextLine();
				/*System.out.println("Continue after? (yes/no)");
				String s = 	sc.nextLine();*/
				long parseTime = getCpuTime();
				kn.solveKnapSackProblem(result, result.size(), capacity, resCost-1);
				long afterTime = getCpuTime();
				//kn.solveKnapSackProblem(result, result.length, capacity, resCost-1);
				//Collections.sort((List<Thing>) result);
				int price = 0;
				int weight = 0;
				Thing [] resultArr = new Thing[result.size()];
				result.toArray(resultArr);
				Arrays.sort(resultArr);
				int choosed = 0;
				for(Thing t:resultArr)
				{
					if(t.isChoosed())
					{
						price+=t.getC();
						weight+=t.getV();
						choosed++;
					}
					System.out.print((t.isChoosed()?1:0)+" ");
					out.write((t.isChoosed()?1:0)+" ");
				}
				frequency[choosed-1][0]++;
				System.out.println();
				System.out.println("PRICE: "+price);
				System.out.println("WEIGHT: "+weight);
				System.out.println("RESULT PRICE: "+resCost);
				long time=0L;
				System.out.println("TIME: "
						+ getTimeFormat().format(time=afterTime - parseTime) + " ns");
				out.write("PRICE: "+price +", WEIGHT: "+weight+", TIME: "
						+ getTimeFormat().format(time) + " ns\r\n");
				totalTime+=time;
				frequency[choosed-1][1]+=time;
				/*if(s.equals("no"))
					break;*/
			}catch(Exception e)
			{
				out.write("ERROR\r\n");
			}
		}
		out.write("TOTAL AVG. TIME: "+(totalTime/cnt)+"\r\n");
		out.write("---FREQUENCY---\r\n");
		for(int i = 0; i<frequency.length; i++)
		{
			out.write("COUNT: "+(i+1)+", FREQUENCY: "+frequency[i][0]+", TIME: "+frequency[i][1]+", AVG. TIME: "+(frequency[i][0]!=0?(frequency[i][1]/frequency[i][0]):0)+"\r\n");
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
