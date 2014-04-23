package fr.upem.fpasteur.graph.flow;

import java.util.ArrayList;
import java.util.Iterator;

import fr.upem.fpasteur.graph.Arc;
import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;

public class GraphFlow extends Graph {

	private ArrayList<ArcFlow> varBase;
	private ArrayList<ArcFlow> varOffBase;

	private ArrayList<ArcFlow> flows;
	private ArrayList<NodeFlow> nodes;
	
	private Node start;
	private Node end;
	
	
	private GraphFlow(Graph graph) {
		super(graph.getNbNode());
		this.setVarBase(new ArrayList<ArcFlow>());
		this.setVarOffBase(new ArrayList<ArcFlow>());
		this.flows = new ArrayList<ArcFlow>();
	}

	public static GraphFlow initialize(Graph graph) {
		GraphFlow graphf = new GraphFlow(graph);
		Iterator<Arc> itArc = graph.getArcs().iterator();
		Iterator<Node> itNode = graph.getNodes().iterator();
		Arc arc;
		ArcFlow flow;
		Node node;
		NodeFlow nodeFlow;
		
		while(itArc.hasNext()) {
			arc = itArc.next();
			flow = new ArcFlow(arc.getCost(), arc.getDest(), arc.getSrc(), 0, Integer.MAX_VALUE, 0);
			graphf.flows.add(flow);
		}
		
		while(itNode.hasNext()) {
			node = itNode.next();
			nodeFlow = new NodeFlow(node.getValue(), 0);
			graphf.nodes.add(nodeFlow);
		}
		
		return graphf;
	}
	
	public ArrayList<ArcFlow> getVarBase() {
		return varBase;
	}

	public void setVarBase(ArrayList<ArcFlow> varBase) {
		this.varBase = varBase;
	}

	public ArrayList<ArcFlow> getVarOffBase() {
		return varOffBase;
	}

	public void setVarOffBase(ArrayList<ArcFlow> varOffBase) {
		this.varOffBase = varOffBase;
	}
	
	public ArrayList<ArcFlow> getFlows() {
		return flows;
	}


	public void setFlows(ArrayList<ArcFlow> flows) {
		this.flows = flows;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}	

}
