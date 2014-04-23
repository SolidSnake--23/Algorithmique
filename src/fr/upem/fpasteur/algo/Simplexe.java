package fr.upem.fpasteur.algo;

import java.util.Iterator;

import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.flow.ArcFlow;
import fr.upem.fpasteur.graph.flow.GraphFlow;

public class Simplexe {
	
	private GraphFlow graph;
	
	public Simplexe(Graph graph) {
		this.graph = GraphFlow.initialize(graph);
	}
	
	public int costSolution() {
		int cost = 0;
		Iterator<ArcFlow> it;
		ArcFlow flow;
		
		it = this.graph.getFlows().iterator();
		
		while(it.hasNext()) {
			flow = it.next();
			cost += flow.cost();
		}
		
		return cost;
	}
	
	public void updateDualVar() {
		
	}
	
	public void updateVarBase() {
		// TODO
		Iterator<ArcFlow> it;
		ArcFlow flow;
		
		it = this.graph.getFlows().iterator();
		
		while(it.hasNext()) {
			flow = it.next();
			if (flow.getFlow() == 0) {
				this.graph.getVarOffBase().add(flow);
			} else { 
				this.graph.getVarBase().add(flow);
			}
		}
	}
}
