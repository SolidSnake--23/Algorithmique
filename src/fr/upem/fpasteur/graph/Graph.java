package fr.upem.fpasteur.graph;

import java.util.ArrayList;

import fr.upem.fpasteur.graph.flow.ArcFlow;

public class Graph {
	private final int nbNode;
	private ArrayList<Node> nodes;
	private ArrayList<Arc> arcs;

	public Graph(int nbNode) {
		this.nbNode = nbNode;
		this.nodes = new ArrayList<Node>();
		this.arcs  = new ArrayList<Arc>();
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public Node getNode(int id) {
		// TODO Sécurité accès
		return this.nodes.get(id);
	}
	
	public void addArc(Arc arc) {
		this.arcs.add(arc);
	}
	
	public void addFlow(ArcFlow flow) {
		this.arcs.add(flow);
	}

	public int getNbNode() {
		return nbNode;
	}


	public ArrayList<Node> getNodes() {
		return nodes;
	}


	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	
	public ArrayList<Arc> getArcs() {
		return arcs;
	}


	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Graphe de ").append(this.nbNode).append(" noeuds. :").append(this.nodes);
		return str.toString();
	}

}
