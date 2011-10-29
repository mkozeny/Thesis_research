@Requires("z.key !in this.nodes.key")
@Ensures("this.nodes = @old (this.nodes + z)")
@Modifies("Node.left, Node.right, this.root")
public void insert(Node z) {
	Squander.exe(this, z);
}

@Requires("z in this.nodes")
@Ensures("this.nodes = @old(this.nodes) - z")
@Modifies("Node.left, Node.right, this.root")
public void remove(Node z) {
	Squander.exe(this, z);
}