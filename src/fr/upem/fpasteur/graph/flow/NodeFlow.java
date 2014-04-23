package fr.upem.fpasteur.graph.flow;

import fr.upem.fpasteur.graph.Node;

public class NodeFlow extends Node {
	
	private int w;
	
	public NodeFlow(int value, int w) {
		super(value);
		this.setW(w);
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}
	

	
}
