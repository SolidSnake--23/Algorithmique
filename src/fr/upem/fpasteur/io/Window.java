package fr.upem.fpasteur.io;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {

	private Graph graph;
	private int width, height;
	
	
	public Window(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.setTitle("Caterer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(false);
		this.width = width;
		this.height = height;
	}
	
	public void drawGraph(Graph graph) {
		WindowPanel panel = new WindowPanel(graph);
		this.graph = Objects.requireNonNull(graph);
		this.setContentPane(panel);
		
	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
