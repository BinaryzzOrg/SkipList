public class Node {

	// class attributes
	private int data;
	private Node next;
	private SkipNode upper;

	// class constructor
	public Node(int data) {
		this.data = data;
		this.next = null;
		this.upper = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	// to have a connection to the upper node(skip list node)
	public SkipNode getUpper() {
		return upper;
	}

	public void setUpper(SkipNode upper) {
		this.upper = upper;
	}
}
