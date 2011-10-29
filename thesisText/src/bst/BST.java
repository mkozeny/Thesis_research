@SpecField("this.nodes = this.root.*(left + right) - null")
public class BST {
	@Invariant({
			/*format tree*/"this !in this.^parent",
			/*left sorted*/"all x: this.left.*(left + right) - null | " +
					"x.key < this.key",
			/*right sorted*/"all x: this.right.*(left + right) - null | " +
					"x.key > this.key" })
	public static class Node {
		Node left, right, parent;
		int key;
	}

	private Node root;
}
