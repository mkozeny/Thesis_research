package cz.kozenym.graph;

/*import java.util.Arrays;
import java.util.Collections;*/

public class State {

	/*private int n;
	
	private int a;*/
	
	private boolean [] configuration;
	
	private int countOfCommonEdges;
	
	private int pointer;

	public State(int n/*, int a*/, int pointer) {
		super();
		/*this.n = n;
		this.a = a;*/
		this.configuration = new boolean[n];
		this.pointer = pointer;
	}

	public boolean[] getConfiguration() {
		return configuration;
	}

	public void setConfiguration(boolean[] configuration) {
		this.configuration = configuration;
	}
	
	
	
	public int getCountOfCommonEdges() {
		return countOfCommonEdges;
	}

	public void setCountOfCommonEdges(int countOfCommonEdges) {
		this.countOfCommonEdges = countOfCommonEdges;
	}
	
	public int countOfNodes()
	{
		int cnt=0;
		for(int i=0; i<this.configuration.length; i++)
		{
			if(this.configuration[i])
				cnt++;
		}
		return cnt;
		//return Collections.frequency(Arrays.asList(this.configuration), true);
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
	
	

	/*public void init()
	{
		for(int i=0; i<this.n;i++)
		{
			if(i<this.a)
				this.configuration[i] = true;
			else
				break;
		}
	}*/
	
}
