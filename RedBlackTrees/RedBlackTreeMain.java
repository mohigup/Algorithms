
import java.io.FileReader;
import java.util.Scanner;

public class RedBlackTreeMain {
	
	private enum Operation {
		sort, search , min, max,
		post, pre , insert, delete , exit
	}
	
	public static void main(String args[]) {
		RedBlackTree tree = new RedBlackTree();
		Node newnode;

		try {
			FileReader file = new FileReader("input.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					newnode = new Node(tree.nil, scanner.nextInt());
					tree.insert(newnode);
				} else {
					scanner.next();
				}
			}
			scanner.close();
		} catch (Exception e) {
			System.err.println("Error reading file." + e);
		}

		System.out.println("TREE IS  ::");
		tree.printTree();

		
		
		Scanner scan = new Scanner(System.in);
		System.out.println(
				"Enter any following commands :: sort, search {x}, min, max, "
				+ "post {x}, pre {x}, insert {x}, delete {x}  exit.");
		System.out.print("Please enter the command :: ");
		String input = scan.nextLine().toLowerCase();
		System.out.println(input.split(" ")[0]);
		Operation op = Operation.valueOf(input.split(" ")[0]);
		
		
		
		while (!input.equalsIgnoreCase("exit")) {
			Boolean isPrintNeeded = true;

				switch (Operation.valueOf(input.split(" ")[0])) {

				case sort:
					System.out.println("Sorted Order :: ");
					tree.sort(tree.rootnode);
					System.out.println();
					isPrintNeeded = false;
					break;
				case search:
					String[] sa = input.split(" ");
					Node t = tree.search(tree.rootnode, Integer.parseInt(sa[1]));
					if (tree.nil == t || null == t) {
						System.out.println("Node Not Found.");
					} else {
						System.out.println("Node Found.");
					}
					isPrintNeeded = false;
					break;
				case min:
					if (tree.nil == tree.rootnode || null == tree.rootnode) {
						System.out.println("No Node Exists");
					} else {
						System.out.println("min value of Node is :: " + tree.min(tree.rootnode).data);
					}
					isPrintNeeded = false;
					break;
				case max:
					if (tree.nil == tree.rootnode || null == tree.rootnode) {
						System.out.println("No Node Exists");
					} else {
						System.out.println("max value of node is :: " + tree.max(tree.rootnode).data);
					}
					isPrintNeeded = false;
					break;
				case post:
					String[] sas = input.split(" ");
					Node ts = tree.search(tree.rootnode, Integer.parseInt(sas[1]));
					if (tree.nil == ts || null == ts) {
						System.out.println("node Not Found.");
					} else {
						ts = tree.successor(ts);
						if (tree.nil == ts || null == ts) {
							System.out.println("Successor Not Found.");
						} else {
							System.out.println("Successor is :: " + ts.data);
						}
					}
					isPrintNeeded = false;
					break;
				case pre:
					String[] sap = input.split(" ");
					
					Node tp = tree.search(tree.rootnode, Integer.parseInt(sap[1]));
					if (tree.nil == tp || null == tp) {
						System.out.println("data Not Found.");
					} else {
						tp = tree.predecessor(tp);
						if (tree.nil == tp || null == tp) {
							System.out.println("Predecessor Not Found.");
						} else {
							System.out.println("Predecessor is :: " + tp.data);
						}
					}
					isPrintNeeded = false;
					break;
				case insert:
					String[] sai = input.split(" ");
					Node ti = new Node(tree.nil, Integer.parseInt(sai[1]));
					tree.insert(ti);
					System.out.println("Node Inserted .");
					isPrintNeeded = true;
					break;
				case delete:

					String[] sad = input.split(" ");
					Node td = tree.search(tree.rootnode, Integer.parseInt(sad[1]));
					if (tree.nil == td || null == td) {
						System.out.println("Node Not Found.");
					} else {
						tree.delete(td);
						System.out.println("Node Deleted .");
					}
					isPrintNeeded = true;
					break;
				default:
					System.out.println("Invalid Choice, Enter again");
				 
					
				}

			
			if (isPrintNeeded) {
				System.out.println("Updated Tree after the " + input + " operation :: ");
				tree.printTree();

				int height = tree.maxLevel(tree.rootnode) - 2;
				if (height < 0) {
					height = 0;
				}
				System.out.println("New Tree height after the " + input + " operation :: " + height);
			}

			System.out.print("Please enter the command :: ");
			input = scan.nextLine();
		}

		scan.close();
		System.out.println("End of Program");
	}

}
