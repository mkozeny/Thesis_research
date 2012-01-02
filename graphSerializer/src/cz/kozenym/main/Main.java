package cz.kozenym.main;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import cz.kozenym.graph.Graph;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		int countOfNodes = args.length>=1?Integer.parseInt(args[0]):30;
		int maxGrade = args.length>=2?Integer.parseInt(args[1]):3;
		String path = args.length==3?args[2]:"/home/kozenym/Desktop/DP/measurement/graphs/";
		if(args.length!=2 || args.length!=3)
		{
			System.out.println("First argument is count of nodes");
			System.out.println("Second argument is max grade");
			System.out.println("Third optional is filepath");
		}
		System.out.println("Used arguments: countOfNodes="+countOfNodes+", maxGrade="+maxGrade);
		Graph graph = new Graph(countOfNodes, maxGrade);
		graph.generateGraph();

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				path+"graph_"+countOfNodes+"_"+maxGrade+".txt"));
		oos.writeObject(graph);
		oos.close();
	}

}
