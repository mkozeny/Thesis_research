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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import cz.kozenym.graph.Edge;
import cz.kozenym.graph.Graph;
import cz.kozenym.graph.GraphExtension;
import cz.kozenym.graph.Node;


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
		int a = args.length>=2?Integer.parseInt(args[1]):3;
		int treshold = args.length>=3?Integer.parseInt(args[2]):3;
		int maxGrade = args.length>=4?Integer.parseInt(args[3]):4;
		String useInputFile = args.length==5?args[4]:"yes";
		if(args.length!=5)
		{
			System.out.println("First argument is count of nodes");
			System.out.println("Second argument is a number - size of node set a");
			System.out.println("Third argument is treshold");
			System.out.println("Fourth argument is max grade of teh node in the graph");
			System.out.println("Fifth argument is whether to use graph from input file");
		}
		System.out.println("Used arguments: countOfNodes="+countOfNodes+", a="+a+", treshold="+treshold+", maxGrade="+maxGrade);
		GraphExtension ge = new GraphExtension(countOfNodes, maxGrade);
		BufferedWriter out = new BufferedWriter(new FileWriter("/home/kozenym/Desktop/DP/measurement/data/zobecnenaBisekcniSirka/squander/"+countOfNodes+"_"+maxGrade+"/log.txt"));
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
		Set<Node> resultA = new HashSet<Node>();
		Set<Node> resultN = new HashSet<Node>();
		Set<Edge> commonEdges = new HashSet<Edge>();
		long parseTime = getCpuTime();
		ge.solveZobecnenaBisekcniSirkaGrafuProblem(resultA, resultN, commonEdges, a, treshold);
		long time=0L;
		out.write("TIME: "
				+ getTimeFormat().format(time=getCpuTime() - parseTime) + " ns");
		System.out.println("TIME: "
				+ getTimeFormat().format(time) + " ns");
		out.close();
		System.out.println("---SET A---");
		for(Node n:resultA)
		{
			System.out.print("Node: "+n.getValue()+" ");
		}
		System.out.println();
		System.out.println("---SET N---");
		for(Node n:resultN)
		{
			System.out.print("Node: "+n.getValue()+" ");
		}
		System.out.println();
		System.out.println("---COMMON EDGES---");
		for(Edge e:ge.getEdges())
		{
			if((resultA.contains(e.getSrc()) && resultN.contains(e.getDest()))
					|| (resultN.contains(e.getSrc()) && resultA.contains(e.getDest())))
			{
				System.out.print("Src node: "+e.getSrc().getValue()+" ");
				System.out.print("Dest node: "+e.getDest().getValue()+", ");
				System.out.println();
			}
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