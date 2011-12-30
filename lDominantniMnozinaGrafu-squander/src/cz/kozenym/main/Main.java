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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cz.kozenym.graph.ExtendedNode;
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
		Scanner sc = new Scanner(System.in);
		int countOfNodes = args.length>=1?Integer.parseInt(args[0]):30;
		int l = args.length>=2?Integer.parseInt(args[1]):3;
		int treshold = args.length>=3?Integer.parseInt(args[2]):3;
		int maxGrade = args.length>=4?Integer.parseInt(args[3]):3;
		String useInputFile = args.length==5?args[4]:"no";
		if(args.length!=5)
		{
			System.out.println("First argument is count of nodes");
			System.out.println("Second argument is l number - length of neighborhood");
			System.out.println("Third argument is treshold");
			System.out.println("Fourth argument is max grade of teh node in the graph");
			System.out.println("Fifth argument is whether to use graph from input file");
		}
		System.out.println("Used arguments: countOfNodes="+countOfNodes+", l="+l+", treshold="+treshold+", maxGrade="+maxGrade);
		GraphExtension ge = new GraphExtension(countOfNodes,l,treshold, maxGrade);
		BufferedWriter out = new BufferedWriter(new FileWriter("/home/kozenym/Desktop/DP/measurement/data/lDominantniMozinaGrafu/squander/"+countOfNodes+"_"+maxGrade+"/log.txt"));
		if(!useInputFile.equals("yes"))
		{
			ge.generateGraph(true);
		}
		else
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"/home/kozenym/Desktop/DP/measurement/graphs/graph_"+countOfNodes+"_"+maxGrade+".txt"));
			Graph inputGraph = (Graph) ois.readObject();
			ois.close();
			ge.setNodes(inputGraph.getNodes());
			ge.setEdges(inputGraph.getEdges());
			ge.setMatrixOfIncidency(inputGraph.getMatrixOfIncidency());
			ge.setMatrixOfFollowers(inputGraph.getMatrixOfFollowers());
			ge.generateGraph(false);
		}
		//GraphExecutor grex = new GraphExecutor(2,ge.getExtendedNodes());
		System.out.println("Waiting...");
		sc.nextLine();
		Set<ExtendedNode> result = new HashSet<ExtendedNode>();
		long parseTime = getCpuTime();
		ge.solveLDominantniMnozinaGrafuProblem(result);
		//Set<ExtendedNode> result = ge.solveLDominantniMnozinaGrafuProblem();
		long time=0L;
		out.write("TIME: "
				+ getTimeFormat().format(time=getCpuTime() - parseTime) + " ns");
		out.close();
		System.out.println("TIME: "
				+ getTimeFormat().format(time) + " ns");
		//System.out.println("Size: "+result.length);
		Set<ExtendedNode> coveredNodes = new HashSet<ExtendedNode>();
		System.out.println("---CHOOSED NODES("+result.size()+")---");
		for(ExtendedNode node:result)
		{
			System.out.println("Node: "+node.getValue());
			coveredNodes.addAll(node.getNeighbourhood());
		}
		System.out.println("---COVERED NODES("+coveredNodes.size()+")---");
		List<ExtendedNode> outputNodes = new ArrayList<ExtendedNode>();
		outputNodes.addAll(coveredNodes);
		Collections.sort(outputNodes);
		for(ExtendedNode node:outputNodes)
		{
			System.out.print(node.getValue()+", ");
		}
		System.out.println();
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
