package fr.upem.fpasteur.graph;

import java.util.ArrayList;

public class Node {
	private int value;
	private String name;
	private ArrayList<Arc> arcs;
	
	
	
	public Node(int value) {
		this.value = value;
		this.setName("Node "+value);
		this.arcs = new ArrayList<Arc>();
	}
	
	public Node(int value, int name) {
		this(value);
		this.setName(""+name);
	}

	public void addArc(Arc arc) {
		this.arcs.add(arc);
	}
	
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}


	public ArrayList<Arc> getArcs() {
		return arcs;
	}


	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}
	
	@Override 
	public boolean equals(Object o) {
		Node node;
		if(!(o instanceof Node)) {
			return false;
		}
		node = (Node) o;
		if(node.value != this.value
				|| node.arcs.equals(this.arcs)) {
			return false;
		}
		return true;
	}
	
	@Override 
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(").append(this.value).append(") : ").append(arcs);
		return str.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
