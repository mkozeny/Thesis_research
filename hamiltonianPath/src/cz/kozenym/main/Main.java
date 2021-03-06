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

import cz.kozenym.graph.Edge;
import cz.kozenym.graph.Graph;
import cz.kozenym.graph.GraphExecutor;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {
		int countOfNodes = args.length>=1?Integer.parseInt(args[0]):30;
		int maxGrade = args.length>=2?Integer.parseInt(args[1]):3;
		String useInputFile = args.length>=3?args[2]:"no";
		String path = args.length>=4?args[3]:"/home/kozenym/Desktop/DP/measurement/graphs/";
		String outputPath = args.length==5?args[4]:"/home/kozenym/Desktop/DP/measurement/data/hamiltonianPath/imperatively/";
		if(args.length!=3 || args.length!=4 || args.length!=5)
		{
			System.out.println("First argument is count of nodes");
			System.out.println("Second argument is max grade of the node in the graph");
			System.out.println("Third argument is whether to use graph from input file");
			System.out.println("Fourth optional is input graph filepath");
			System.out.println("Fifth optional is output filepath");
		}
		System.out.println("Used arguments: countOfNodes="+countOfNodes+", maxGrade="+maxGrade);
		GraphExecutor graph = new GraphExecutor(countOfNodes, maxGrade);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputPath+countOfNodes+"_"+maxGrade+"/log"));
		if(!useInputFile.equals("yes"))
		{
			graph.generateGraph();
		}
		else
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				path+"graph_"+countOfNodes+"_"+maxGrade+".txt"));
			Graph inputGraph = (Graph) ois.readObject();
			ois.close();
			graph.setNodes(inputGraph.getNodes());
			graph.setEdges(inputGraph.getEdges());
			graph.setMatrixOfIncidency(inputGraph.getMatrixOfIncidency());
			graph.setMatrixOfFollowers(inputGraph.getMatrixOfFollowers());
			
		}
		long preparationTime = getCpuTime();
		graph.solveHamiltonianPathRecursively();
		long time=getCpuTime() - preparationTime;
		out.write("TIME: "
				+ getTimeFormat().format(time) + " ns");
		out.close();
		System.out.println("EXECUTION TIME: "+getTimeFormat().format(time));
		for(Edge e:graph.getResult())
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+", dest: "+e.getDest().getValue());
		}
	}
	private static long getCpuTime( ) {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	    		bean.getCurrentThreadCpuTime() : 0L;
	}
	private static DecimalFormat getTimeFormat() {
		return new DecimalFormat("###,###,##0.00;'-'###,###,##0.00");
	}
}
