package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import graph.Edge;
import graph.Graph;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {
		Thread.sleep(10000);
		int countOfNodes = args.length>=1?Integer.parseInt(args[0]):30;
		int maxGrade = args.length>=2?Integer.parseInt(args[1]):3;
		String useInputFile = args.length==3?args[2]:"no";
		if(args.length!=3)
		{
			System.out.println("First argument is count of nodes");
			System.out.println("Second argument is max grade of teh node in the graph");
			System.out.println("Third argument is whether to use graph from input file");
		}
		System.out.println("Used arguments: countOfNodes="+countOfNodes+", maxGrade="+maxGrade);
		GraphExecutor graph = new GraphExecutor(countOfNodes, maxGrade);
		if(!useInputFile.equals("yes"))
		{
			graph.generateGraph();
		}
		else
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"/home/kozenym/Desktop/DP/measurement/graphs/graph_"+countOfNodes+"_"+maxGrade+".txt"));
			Graph inputGraph = (Graph) ois.readObject();
			ois.close();
			graph.setNodes(inputGraph.getNodes());
			graph.setEdges(inputGraph.getEdges());
			graph.setMatrixOfIncidency(inputGraph.getMatrixOfIncidency());
			graph.setMatrixOfFollowers(inputGraph.getMatrixOfFollowers());
			
		}
		long preparationTime = getCpuTime();
		Edge [] edges = graph.solveHamiltonianPath();
		System.out.println("EXECUTION TIME: "+(getCpuTime()-preparationTime));
		for(Edge e:edges)
		{
			System.out.println("Edge src: "+e.getSrc().getValue()+", dest: "+e.getDest().getValue());
		}
	}

	private static long getCpuTime( ) {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	    		bean.getCurrentThreadCpuTime() : 0L;
	}
}
