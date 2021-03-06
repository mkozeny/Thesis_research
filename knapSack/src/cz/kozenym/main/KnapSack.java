package cz.kozenym.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KnapSack {

	private int cnt;

	int[] configuration;

	int[] bestConfiguration;

	double actualWeight = 0;

	double actualPrice = 0;

	double bruteForcePrice = 0;

	double bruteForceWeight = 0;

	int maxWeight;

	Double[] v;

	Double[] c;

	static BufferedWriter out;

	PriceWeight pw = new PriceWeight();

	int countOfObjects;

	private String midlet(String fileName) {
		StringBuffer buffer = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			Class c = this.getClass();
			is = new FileInputStream(new File(fileName));
			if (is == null)
				throw new Exception(
						"Invalid value of first argument, file does not exist");

			isr = new InputStreamReader(is, "UTF8");

			buffer = new StringBuffer();
			int ch;
			while ((ch = isr.read()) > -1) {
				buffer.append((char) ch);
			}
			if (isr != null)
				isr.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return buffer.toString();
	}

	private List<String[]> parseInputFile(String text) {
		List<String> radky = new ArrayList<String>();
		StringTokenizer rowTokenizer = new StringTokenizer(text, "\n");

		while (rowTokenizer.hasMoreElements())
			radky.add(rowTokenizer.nextToken());

		List<String[]> hodnoty = new ArrayList<String[]>();

		String radek1 = radky.get(0);
		cnt = 0;
		StringTokenizer commaTokenizer = new StringTokenizer(radek1, " ");
		while (commaTokenizer.hasMoreElements()) {
			commaTokenizer.nextToken();
			cnt++;
		}

		String[] values = new String[cnt];
		for (String radek : radky) {
			commaTokenizer = new StringTokenizer(radek, " ");
			int i = 0;
			while (commaTokenizer.hasMoreElements()) {
				values[i] = commaTokenizer.nextToken();
				i++;
			}
			hodnoty.add(values);
			values = new String[cnt];
			i = 0;
		}
		return hodnoty;
	}

	private void solveProblem(List<String[]> hodnoty) throws IOException {
		countOfObjects = (cnt - 3) / 2;
		v = new Double[countOfObjects];
		c = new Double[countOfObjects];
		configuration = new int[countOfObjects];
		bestConfiguration = new int[countOfObjects];
		int pointer = 0;
		int id = 0;
		for (String[] hodnota : hodnoty) {
			id = Integer.parseInt(hodnota[0]);
			for (int j = 3; j < cnt; j++) {
				pointer = (j - 3) / 2;
				if (j % 2 == 1)
					v[pointer] = Double.parseDouble(hodnota[j]);
				else
					c[pointer] = Double.parseDouble(hodnota[j]);
			}
			maxWeight = Integer.parseInt(hodnota[2]);
			bruteForcePrice = 0;
			System.out.println("Continue after? (yes/no)");
			long parseTime = getCpuTime();
			countBruteForcePrice(0);
			long time = getCpuTime() - parseTime;
			System.out.print("Brute force configuration " + id + " : "
					+ outputConf(bestConfiguration) + ", price: "
					+ bruteForcePrice + ", weight: " + bruteForceWeight);
			out.write("Brute force configuration " + id + " : "
					+ outputConf(bestConfiguration) + ", price: "
					+ bruteForcePrice + ", weight: " + bruteForceWeight
					+ ", time: " + getTimeFormat().format(time) + " ns\r\n");
			System.out.println(", time: " + getTimeFormat().format(time)
					+ " ns");
		}
		out.close();
		
	}
	/*
	 * BB-DFS implementation
	 */
	private void countBruteForcePrice(int rank) {
		for (int i = rank; i < this.countOfObjects; i++) {
			putRestOfThingsIntoBag(this.configuration, i);
			this.actualPrice = this.pw.getPrice();
			this.actualWeight = this.pw.getWeight();
			/*trying if is possible to cut off actual branch*/
			if (this.actualPrice < this.bruteForcePrice)
				return;
			/*putting actual thing into knapsack*/
			this.configuration[i] = 1;
			recalculatePriceandWeightInBag(this.configuration);
			this.actualPrice = this.pw.getPrice();
			this.actualWeight = this.pw.getWeight();
			if (this.actualWeight <= this.maxWeight) {
				if (this.bruteForcePrice < this.actualPrice) {
					/*if actual weight is not greater than maximal weight of knapsack
					 * and actual best price is lower than actual price
					 * actual configuration is assigned as a best solution*/
					this.bruteForcePrice = this.actualPrice;
					this.bruteForceWeight = this.actualWeight;
					for (int j = 0; j < this.configuration.length; j++) {
						this.bestConfiguration[j] = this.configuration[j];
					}
				}
				/*if actual weight is not greater than maximal weight of knapsack,
				 * it is possible to continue searching state space*/
				countBruteForcePrice(i + 1);
			}
			this.configuration[i] = 0;
		}
		return;
	}

	private void recalculatePriceandWeightInBag(int[] conf) {
		pw.reset();
		for (int i = 0; i < conf.length; i++) {
			if (conf[i] == 1) {
				pw.addPrice(c[i]);
				pw.addWeight(v[i]);
			}
		}
	}

	private void putRestOfThingsIntoBag(int[] configuration, int i) {
		int[] conf = new int[configuration.length];
		for (int k = 0; k < conf.length; k++) {
			conf[k] = configuration[k];
		}
		for (int j = i; j < conf.length; j++) {
			conf[j] = 1;
		}
		recalculatePriceandWeightInBag(conf);
	}

	private String outputConf(int[] configuration) {
		String s = "";
		for (int i = 0; i < configuration.length; i++) {
			s += String.valueOf(configuration[i]);
		}
		return s;
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

	public static void main(String[] args) throws Exception {
		Thread.currentThread().setPriority(10);
		String numberOfThings = args.length == 1 ? args[0] : "4";
		String path = args.length >= 2 ? args[1]
				: "/home/kozenym/Desktop/DP/squander_workspace/knapSack/files/";
		String outputPath = args.length == 3 ? args[2]
				: "/home/kozenym/Desktop/DP/measurement/data/knapSack/imperatively/";
		if (args.length != 1 || args.length != 2 || args.length != 3) {
			System.out.println("First argument is number of things");
			System.out.println("Second optional is input graph filepath");
			System.out.println("Third optional is output filepath");
		}
		KnapSack pb = new KnapSack();
		out = new BufferedWriter(new FileWriter(outputPath + numberOfThings
				+ "/log"));
		String filename = "knap_" + numberOfThings + ".txt";
		List<String[]> hodnoty = pb.parseInputFile(pb.midlet(path + filename));
		pb.solveProblem(hodnoty);
	}

}
