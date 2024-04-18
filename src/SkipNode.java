public class SkipNode {

	// class attributes
	private int data;
	private Node bottom;
	private SkipNode next;

	// class constructor
	public SkipNode(int data) {
		this.data = data;
		this.bottom = null;
		this.next = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	// to have a connection to the bottom list(linked list)
	public Node getBottom() {
		return bottom;
	}

	public void setBottom(Node bottom) {
		this.bottom = bottom;
	}

	public SkipNode getNext() {
		return next;
	}

	public void setNext(SkipNode next) {
		this.next = next;
	}
}
