package main;

public class Thing {

	private int v;
	
	private int c;
	
	private int choosed;

	public Thing(int v, int c) {
		super();
		this.v = v;
		this.c = c;
		this.choosed = 0;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getChoosed() {
		return choosed;
	}

	public void setChoosed(int choosed) {
		this.choosed = choosed;
	}
	
	
}
