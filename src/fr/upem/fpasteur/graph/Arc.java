package fr.upem.fpasteur.graph;


public class Arc {

	private int cost;
	private Node dest;
	private Node src;
	
	
	
	public Arc(int cost, Node dest, Node src) {
		this.cost = cost;
		this.setDest(dest);
		this.setSrc(src);
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}
	
	
	
	public Node getSrc() {
		return src;
	}

	public void setSrc(Node src) {
		this.src = src;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("|").append(this.cost).append("| -> ").append(this.dest);
		return str.toString();
	}


	
}
