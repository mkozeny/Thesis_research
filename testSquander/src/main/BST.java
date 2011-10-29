package main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Invariant;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.annotations.SpecField;
import edu.mit.csail.sdg.squander.Squander;

@SpecField("this.nodes = this.root.∗(left + right) − null")
public class BST {
	@Invariant({/* format tree */
			"this ! in this.ˆparent",
			/* left sorted */"all x : this.left.∗(left + right) − null | x.key < this.key",
			/* right sorted */"all x : this.right.∗(left + right) − null | x.key > this.key" })
	public static class Node {
		Node left, right, parent;
		int key;
	}
	
	private Node root;

	@Requires("z.key ! in this.nodes.key")
	@Ensures("this.nodes = @old ( this.nodes + z)")
	@Modifies({"Node.left [{ n: this.nodes | n.left == null}]", 
			"Node.right [{ n: this.nodes | n.right == null}]",
			"this.root"})
	public void insert(Node z) {
		Squander.exe(this, z);
	}

	@Requires("z in this.nodes")
	@Ensures("this.nodes = @old (this.nodes) − z")
	@Modifies("Node.left, Node.right, this.root")
	public void remove(Node z) {
		Squander.exe(this, z);
	}

}
