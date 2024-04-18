public class SkipList {

	/*
	 * class attributes: root Node & tail Node skip(root) SkipNode & skipTail
	 * SkipNode length for keeping track of the length of the linked list
	 * requiredLength is for restricting the recreation of Skip List isSkipCreated
	 * confirm if a skipList exists
	 */
	private Node root;
	private Node tail;
	private SkipNode skip;
	private SkipNode skipTail;
	private int length = 0;
	private int requiredLength = 0;
	private boolean isSkipCreated = false;

	/*
	 * class constructor: can accept with and without a parameter(Node)
	 */
	public SkipList() {
		this.root = null;
		this.tail = null;
		this.skip = null;
	}

	public SkipList(Node root) {
		this.root = root;
		this.tail = null;
		this.skip = null;
	}

	// SKIP LIST METHODS:

	/*
	 * addNode: it first checks if the root is empty if empty set the input node as
	 * the root and tail else check first if the new data to be added is less than
	 * the tail to avoid bubble sorting all the time then if it is greater than set
	 * the next of the tail the new node then adjust the tail to the new node
	 * afterwards increment length to match the number of nodes inside the list
	 */
	public void addNode(Node node) {
		if (root == null) {
			this.root = node;
			this.tail = root;
		} else {
			if (node.getData() < tail.getData()) {
				System.out.println("Node to be added cannot be less than " + tail.getData());
				return;
			}

			tail.setNext(node);
			tail = tail.getNext();
		}
		length++;
	}

	/*
	 * createSkipList: in creating a skip list first check if the required length is
	 * met and if a skip list is created if this results to false, end the method
	 * and show the required length.
	 * 
	 * if a skip list is not yet created get the square root of the current length
	 * of the linked list then set it as the number of nodes to skip. If the
	 * skip(root) is null get the root of the bottom list and set its data as the
	 * root then set the bottom of that skip node the root of the linked list,
	 * afterwards traverse the bottom list by counting the number of nodes to skip
	 * then if count is equal to the required nodes to skip reset the counter then
	 * continue until it reaches null. The purpose of set upper method is to have a
	 * connection to the upper list(skip list). then compute the next required
	 * length to prevent the creation of a skip list every time by adding the square
	 * root and adding to its 160% + the length of the current skip list. Finally,
	 * signify that a skip list has been created.
	 */
	public void createSkipList() {

		if (length != requiredLength && isSkipCreated) {
			System.out.println("The required length to create a new skip list is not met. (" + requiredLength + ")");
			return;
		}

		skip = null;
		int nodesToSkip = (int) Math.sqrt(length);

		if (skip == null) {
			skip = new SkipNode(this.root.getData());
			skip.setBottom(root);
			skipTail = skip;
		}

		Node currentNode = root.getNext();
		int count = 0;

		while (currentNode.getNext() != null) {

			if (count == nodesToSkip) {
				count = -1;

				SkipNode skipNode = new SkipNode(currentNode.getData());
				skipNode.setBottom(currentNode);
				skipTail.setNext(skipNode);
				this.skipTail = skipNode;

				currentNode.setUpper(skipNode);
			}
			count++;
			currentNode = currentNode.getNext();
		}

		requiredLength = (int) Math.sqrt(length) + (int) (Math.sqrt(length) * 1.60) + length;
		isSkipCreated = true;

		System.out.println("Skip List created succesfully!");
	}

	// displaySkipList: displays all the skip list node starting from the skip root
	public void displaySkipList() {
		SkipNode currentNode = skip;
		while (currentNode != null) {
			System.out.print(currentNode.getData() + " ");
			currentNode = currentNode.getNext();
		}
	}

	// displayList: display all the nodes in the normal linked list at the bottom
	// level
	public void displayList() {
		Node currentNode = root;
		while (currentNode != null) {
			System.out.print(currentNode.getData() + " ");
			currentNode = currentNode.getNext();
		}
	}

	/*
	 * search: traverse the skip list first then check the following conditions - if
	 * the data is equal to the data to be searched in the skip node go to the
	 * bottom and break the traversal in the upper list - if the current skip node
	 * in the upper list becomes null go to the bottom and continue the search there
	 * - if the current skip node data is less than its next got to the bottom and
	 * continue from there if none of the conditions are met in the skip list loop
	 * traversal just keep traversing until it reaches null
	 * 
	 * after traversing the skip list now traverse the bottom list that skipped the
	 * nodes based on the conditions earlier if the value is found within the square
	 * root of n range return that node otherwise if it reaches a node that has an
	 * upper skip list node it means that the data that needed to be searched does
	 * not exist in the list since it is sorted then return null
	 */
	public Node search(int data) {

		if (skip == null) {
			System.out.println("Create a skip list first.");
			return null;
		}

		SkipNode currentSkipNode = skip;
		Node bottom = null;

		while (currentSkipNode != null) {

			int currentData = currentSkipNode.getData();
			if (data == currentData) {
				bottom = currentSkipNode.getBottom();
				break;
			}

			if (currentSkipNode.getNext() == null) {
				bottom = currentSkipNode.getBottom();
				break;
			}

			int nextData = currentSkipNode.getNext().getData();
			if (data < nextData) {
				bottom = currentSkipNode.getBottom();
				break;
			}

			currentSkipNode = currentSkipNode.getNext();
		}

		while (bottom != null) {

			if (bottom.getData() == data) {
				return bottom;
			}

			if (bottom.getNext() != null && bottom.getNext().getUpper() != null) {
				return null;
			}
			bottom = bottom.getNext();
		}

		return null;
	}

	/*
	 * range query: first check if the lower bound is greater than the upper bound
	 * if true end the method and indicate that it should be lower.
	 * 
	 * traverse the upper list(skip list) by finding the lower bound if it is
	 * already less than a skip node go to the bottom so it does not need a specific
	 * node to go to the bottom it just needs to be less than then traverse the
	 * bottom list until it reaches the upper bound or it becomes greater than the
	 * upper bound if these conditions happen break the loop and end the method.
	 * 
	 */
	public void rangeQuery(int lowerBound, int upperBound) {

		if (skip == null) {
			System.out.println("Create a skip list first.");
			return;
		}

		if (lowerBound > upperBound) {
			System.out.println("\nlower bound must be less than the upper bound.");
			return;
		}

		SkipNode currentSkipNode = skip;
		Node bottom = null;

		while (currentSkipNode != null) {

			int currentData = currentSkipNode.getData();
			if (lowerBound == currentData) {
				bottom = currentSkipNode.getBottom();
				break;
			}

			if (currentSkipNode.getNext() == null) {
				bottom = currentSkipNode.getBottom();
				break;
			}

			int nextData = currentSkipNode.getNext().getData();
			if (lowerBound < nextData) {
				bottom = currentSkipNode.getBottom();
				break;
			}

			currentSkipNode = currentSkipNode.getNext();
		}

		System.out.println("Nodes in the range (" + lowerBound + ", " + upperBound + ")");
		while (bottom != null) {

			int bottomData = bottom.getData();

			if (bottomData == lowerBound || (bottomData > lowerBound && bottomData <= upperBound)) {
				System.out.print(bottom.getData() + " ");
			}

			if (bottomData == upperBound || bottomData > upperBound)
				break;
			bottom = bottom.getNext();
		}
	}
}
