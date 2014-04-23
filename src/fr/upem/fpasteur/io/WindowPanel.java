package fr.upem.fpasteur.io;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Objects;

import javax.swing.JPanel;

import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;



@SuppressWarnings("serial")
public class WindowPanel extends JPanel {
	
	private Graph graph;

	public WindowPanel(Graph graph) {
		this.graph = Objects.requireNonNull(graph);;
	}

	public void paintComponent(Graphics g){
	        paintNodes(g);
	        super.paintComponent(g);
	  }
 
	private void paintNodes(Graphics g) {
		Iterator<Node> it = graph.getNodes().iterator();
		Node node;
		while(it.hasNext()) {
			node = it.next();
			g.drawString(node.getName(), 10 , 10);
		}
	}
	
}
