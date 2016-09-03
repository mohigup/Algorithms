

public class Node 
{
	Node left;
	Node right;
	Node parent;
	int data;
	boolean isColorRed;
	
	Node(Node nil, int d)
	{
		this.left = nil;
		this.right = nil;
		this.parent = nil;
		this.data = d;
		this.isColorRed = false;
	}
}


