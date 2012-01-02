package cz.kozenym.main;

public class Node {

	private Node withActualThing;
	
	private Node withoutActualThing;
	
	
	private Integer xAxis;
	
	private Integer yAxis;
	
	private Integer weight;
	
	private Integer price;
	
	private int [] configuration;
	
	public Node(Integer axis, Integer axis2) {
		super();
		xAxis = axis;
		yAxis = axis2;
	}

	public Node getWithActualThing() {
		return withActualThing;
	}

	public void setWithActualThing(Node withActualThing) {
		this.withActualThing = withActualThing;
	}

	public Node getWithoutActualThing() {
		return withoutActualThing;
	}

	public void setWithoutActualThing(Node withoutActualThing) {
		this.withoutActualThing = withoutActualThing;
	}

	
	public Integer getXAxis() {
		return xAxis;
	}

	public void setXAxis(Integer axis) {
		xAxis = axis;
	}

	public Integer getYAxis() {
		return yAxis;
	}

	public void setYAxis(Integer axis) {
		yAxis = axis;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public int[] getConfiguration() {
		return configuration;
	}

	public void setConfiguration(int[] configuration) {
		this.configuration = configuration;
	}

	
	
	
}
