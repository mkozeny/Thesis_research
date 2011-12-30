package cz.kozenym.main;

public class Thing implements Comparable<Thing>{

	private int v;
	
	private int c;
	
	private boolean choosed;
	
	private int position;

	public Thing(int v, int c, int position) {
		super();
		this.v = v;
		this.c = c;
		this.position = position;
		this.choosed = false;
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

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public int compareTo(Thing o) {
		return this.position - o.getPosition();
	}


	
	
}
