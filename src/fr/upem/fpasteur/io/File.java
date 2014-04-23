package fr.upem.fpasteur.io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import fr.upem.fpasteur.graph.Arc;
import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;
import fr.upem.fpasteur.graph.flow.ArcFlow;

public class File {

	private static Scanner scanner;

	public static Graph loadGraph(String src) {
		Graph graph = null;
		InputStream flux;
		int i;
		Node node_alpha, node_beta;
		int cost;
		Arc arc;
		ArcFlow flow;
		
		
		try{
			flux = new FileInputStream(src); 
			scanner = new Scanner(flux);
			
			graph = new Graph(scanner.nextInt());
			
			scanner.nextLine();
			
			for (i = 0; i < graph.getNbNode(); i++) {
				graph.addNode(new Node(scanner.nextInt(), i));
			}
			
			scanner.nextLine();
			while(scanner.hasNext()) {
				node_alpha = graph.getNode(scanner.nextInt());
				node_beta  = graph.getNode(scanner.nextInt());
				cost = scanner.nextInt();
				arc = new Arc(cost, node_beta, node_alpha);
				flow = new ArcFlow(cost, node_beta, node_alpha, 0, Integer.MAX_VALUE, 0);
				node_alpha.addArc(arc);
				graph.addArc(arc);
				graph.addFlow(flow);
				scanner.nextLine();
			}
			
			
		}		
		catch (Exception e){
			System.out.println(e);
		}
		
		return graph;
	}
	
	
}
