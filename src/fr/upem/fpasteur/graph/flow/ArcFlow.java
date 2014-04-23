package fr.upem.fpasteur.graph.flow;

import fr.upem.fpasteur.graph.Arc;
import fr.upem.fpasteur.graph.Node;

public class ArcFlow extends Arc {

	private int flowMin;
	private int flowMax;
	private int flow;
	
	public ArcFlow(int cost, Node dest, Node src, int flowMin, int flowMax, int flow) {
		super(cost, dest, src);
		this.setFlowMax(flowMax);
		this.setFlowMin(flowMin);
		this.setFlow(flow);
	}

	public int getFlowMin() {
		return flowMin;
	}

	public void setFlowMin(int flowMin) {
		this.flowMin = flowMin;
	}

	public int getFlowMax() {
		return flowMax;
	}

	public void setFlowMax(int flowMax) {
		this.flowMax = flowMax;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public int cost() {
		return this.flow * this.getCost();
	}

}
