@Requires("z.key !in this.nodes.key")
@Ensures("this.nodes = @old ( this.nodes + z)")
@Modifies({"Node.left [{ n: this.nodes | n.left == null}]", 
		"Node.right [{ n: this.nodes | n.right == null}]",
		"this.root"})
public void insert(Node z) {
	Squander.exe(this, z);
}