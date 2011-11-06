package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import graph.Graph;
import graph.GraphExtension;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		int countOfNodes = args.length>=1?Integer.parseInt(args[0]):30;
		int l = args.length>=2?Integer.parseInt(args[1]):3;
		int treshold = args.length>=3?Integer.parseInt(args[2]):10;
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
		if(!useInputFile.equals("yes"))
		{
			ge.generateGraph();
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
		}
		ge.solveLDominantniMnozinaGrafuProblem();
		boolean [] bestConfiguration = ge.getBestConfiguration().getConfiguration();
		System.out.println("---BEST CONFIGURATION---");
		for(int i = 0; i<bestConfiguration.length;i++)
		{
			if(bestConfiguration[i])
				System.out.println("Node: "+i);
		}
		System.out.println();
	}

}
