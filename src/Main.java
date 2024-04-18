import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static SkipList list = new SkipList();

	public static void main(String[] args) {

		System.out.print("How many nodes? ");
		int limit = checkUserInput_Menu("How many nodes? ");

		Node[] nodeArr = new Node[limit];
		int i = 0;
		while (i < limit) {
			System.out.print("\nEnter node #" + (i + 1) + " : ");
			int nodeData = checkUserInput_Menu("\nEnter node #" + (i + 1) + " : ");

			Node node = new Node(nodeData);

			nodeArr[i] = node;
			i++;
		}

		System.out.println("\nSorting the list...");
		nodeArr = bubbleSortArray(nodeArr);
		i = 0;

		while (i < limit) {
			list.addNode(nodeArr[i]);
			i++;
		}

		list.displayList();
		System.out.println();
		Menu();
	}// end main

	public static void Menu() {

		System.out.println("\n[1] Insert " + "\n[2] Create Skip List" + "\n[3] Search" + "\n[4] Range Query"
				+ "\n[5] Display List" + " \n[6] Exit\n");
		sc = new Scanner(System.in);
		System.out.print("Choose an operation: ");
		String input = sc.nextLine();

		switch (input) {
		case "1":
			System.out.print("\nEnter node: ");
			int nodeData = checkUserInput_Menu("\nEnter node: ");
			Node node = new Node(nodeData);
			list.addNode(node);
			break;
		case "2":
			list.createSkipList();
			break;
		case "3":
			System.out.print("\nEnter node to be searched: ");
			int nodeToBeSearched = checkUserInput_Menu("\nEnter node to be searched: ");

			Node nodeSearched = list.search(nodeToBeSearched);
			if (nodeSearched != null) {
				System.out.println(nodeSearched.getData() + " found.");
			} else {
				System.out.println("Node was not found.");
			}
			break;
		case "4":
			System.out.print("\nEnter lower boundary: ");
			int lowerBoundNode = checkUserInput_Menu("\nEnter lower boundary: ");
			System.out.print("Enter upper boundary: ");
			int upperBoundNode = checkUserInput_Menu("\nEnter upper boundary: ");

			list.rangeQuery(lowerBoundNode, upperBoundNode);
			System.out.println();
			break;
		case "5":
			System.out.println("\nSkip List:");
			list.displaySkipList();
			System.out.println("\nLinked List:");
			list.displayList();
			System.out.println();
			break;
		case "6":
			System.out.println("The program is exiting...");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Input!");
			break;
		}
		Menu();
	}// end method

	/*
	 * Method for checking the input if it is an integer
	 */
	public static int checkUserInput_Menu(String prompt) {
		sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			int value = sc.nextInt();
			return value;
		} // end if
		System.out.println(printCustomError("integer"));
		System.out.print(prompt);
		return checkUserInput_Menu(prompt);
	}// end if

	/*
	 * Method for printing the error message if the input is not an integer.
	 */
	public static String printCustomError(String type) {
		// @formatter:off
            return "\n" +
                        "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
                        "┇ Warning: Input is not a "+ type +" value. \n" +
                        "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
                        "┇ Notice: Please only enter a "+ type +" value.\n" +
                        "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n";
        // @formatter:on
	}// end method

	/*
	 * Method for sorting of the nodes
	 */
	public static Node[] bubbleSortArray(Node[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j].getData() > arr[j + 1].getData()) {
					Node temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			} // end for
		} // end for

		return arr;
	}// end method
}// end class
