package cz.kozenym.main;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;

import cz.kozenym.graph.Graph;
import cz.kozenym.graph.GraphExtension;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		int countOfNodes = args.length>=1?Integer.parseInt(args[0]):30;
		int a = args.length>=2?Integer.parseInt(args[1]):6;
		int treshold = args.length>=3?Integer.parseInt(args[2]):6;
		int maxGrade = args.length>=4?Integer.parseInt(args[3]):4;
		String useInputFile = args.length>=5?args[4]:"yes";
		String path = args.length>=6?args[5]:"/home/kozenym/Desktop/DP/measurement/graphs/";
		String outputPath = args.length==7?args[6]:"/home/kozenym/Desktop/DP/measurement/data/generalBisectionBreadth/imperatively/";
		if(args.length!=5 || args.length!=6 || args.length!=7)
		{
			System.out.println("First argument is count of nodes");
			System.out.println("Second argument is a number - size of node set a");
			System.out.println("Third argument is treshold");
			System.out.println("Fourth argument is max grade of teh node in the graph");
			System.out.println("Fifth argument is whether to use graph from input file");
			System.out.println("Sixth optional is input graph filepath");
			System.out.println("Seventh optional is output filepath");
		}
		System.out.println("Used arguments: countOfNodes="+countOfNodes+", a="+a+", treshold="+treshold+", maxGrade="+maxGrade);
		GraphExtension ge = new GraphExtension(countOfNodes,a, maxGrade);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputPath+countOfNodes+"_"+maxGrade+"/log"));
		if(!useInputFile.equals("yes"))
		{
			ge.generateGraph();
		}
		else
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				path+"graph_"+countOfNodes+"_"+maxGrade+".txt"));
			Graph inputGraph = (Graph) ois.readObject();
			ois.close();
			ge.setNodes(inputGraph.getNodes());
			ge.setEdges(inputGraph.getEdges());
			ge.setMatrixOfIncidency(inputGraph.getMatrixOfIncidency());
			ge.setMatrixOfFollowers(inputGraph.getMatrixOfFollowers());
		}
		long parseTime = getCpuTime();
		ge.solveGeneralBisectionBreadthProblem();
		long time=getCpuTime() - parseTime;
		out.write("TIME: "
				+ getTimeFormat().format(time) + " ns");
		out.close();
		System.out.println("TIME: "
				+ getTimeFormat().format(time) + " ns");
		boolean [] bestConfiguration = ge.getBestConfiguration().getConfiguration();
		System.out.println("---BEST CONFIGURATION---");
		for(int i = 0; i<bestConfiguration.length;i++)
		{
			if(bestConfiguration[i])
				System.out.println("Node: "+i);
		}
		System.out.println();
		System.out.println("---COUNT OF COMMON EDGES---");
		System.out.println(ge.getBestConfiguration().getCountOfCommonEdges());
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
