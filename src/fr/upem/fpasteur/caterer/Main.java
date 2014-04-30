package fr.upem.fpasteur.caterer;

import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.io.File;
import fr.upem.fpasteur.io.window.Frame;



public class Main {

	public static void main(String[] args) {
		Graph graph = File.loadGraph("graph.txt");
			Frame window = new Frame(graph);
	}

}
