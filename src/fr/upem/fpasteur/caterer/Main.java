package fr.upem.fpasteur.caterer;

import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.io.File;
import fr.upem.fpasteur.io.Window;
import fr.upem.fpasteur.io.window.DisplaySwing;



public class Main {

	public static void main(String[] args) {
		Graph graph = File.loadGraph("graph.txt");
		
		/*Window window = new Window(640, 480);
		window.drawGraph(graph);
		window.setVisible(true);*/
		DisplaySwing display = new DisplaySwing();
		display.setVisible(true);

	}

}
