package main;

import java.util.HashSet;
import java.util.Set;

public class Number {

	private int value;
	
	private Set<Number> followers;

	public Number(int value) {
		super();
		this.value = value;
		this.followers = new HashSet<Number>();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Set<Number> getFollowers() {
		/*this.followers.add(new Number(this.value+1));
		this.followers.add(new Number(this.value+2));*/
		return followers;
	}

	public void setFollowers(Set<Number> followers) {
		this.followers = followers;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o==null || this == null) return false;
		Number n = (Number)o;
		if(n.getValue() == this.getValue()) return true;
		return false;
	}
}
