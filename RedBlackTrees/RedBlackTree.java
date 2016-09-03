

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTree 
{
	Node rootnode;
	Node nil;
	
	RedBlackTree()
	{
		nil = new Node(null,0);
		rootnode = nil;
	}
	
	
	public void leftRotate(Node a)
	{
		Node b = a.right;
		a.right = b.left;
		
		if(!nil.equals(b.left))
		{
			b.left.parent = a;
		}
		
		b.parent = a.parent;
		
		if(a.parent.equals(nil))
		{
			rootnode = b;
		}
		else if(a.equals(a.parent.left))
		{
			a.parent.left = b;
		}
		else
		{
			a.parent.right = b;
		}
		b.left = a;
		a.parent = b;
	}
	
	public void rightRotate(Node a)
	{
		Node b = a.left;
		a.left = b.right;
		
		if(!nil.equals(b.right))
		{
			b.right.parent = a;
		}
		
		b.parent = a.parent;
		
		if(a.parent.equals(nil))
		{
			rootnode = b;
		}
		else if(a.equals(a.parent.right))
		{
			a.parent.right = b;
		}
		else
		{
			a.parent.left = b;
		}
		b.right = a;
		a.parent = b;
	}
	
	public void insert(Node c)
	{
		Node b = nil;
		Node a = rootnode;
		
		while(!nil.equals(a))
		{
			b = a;
			if(c.data < a.data)
			{
				a = a.left;
			}
			else
			{
				a = a.right;
			}
		}
		
		c.parent = b;
		
		if(nil.equals(b))
		{
			rootnode = c;
		}
		else if(c.data < b.data)
		{
			b.left = c;
		}
		else
		{
			b.right = c;
		}
		
		c.left = nil;
		c.right = nil;
		c.isColorRed = true;
		insertFixHelper(c);
	}
	
	public void insertFixHelper(Node c)
	{
		while(c.parent.isColorRed)
		{
			if(c.parent.equals(c.parent.parent.left))
			{
				Node b = c.parent.parent.right;
				if(b.isColorRed)
				{
					c.parent.isColorRed = false;
					b.isColorRed = false;
					c.parent.parent.isColorRed = true;
					c = c.parent.parent;
				}
				else 
				{
					if(c.equals(c.parent.right))
					{
						c = c.parent;
						leftRotate(c);
					}
					c.parent.isColorRed = false;
					c.parent.parent.isColorRed = true;
					rightRotate(c.parent.parent);
				}
			}
			else
			{
				Node b = c.parent.parent.left;
				if(b.isColorRed)
				{
					c.parent.isColorRed = false;
					b.isColorRed = false;
					c.parent.parent.isColorRed = true;
					c = c.parent.parent;
				}
				else 
				{
					if(c.equals(c.parent.left))
					{
						c = c.parent;
						rightRotate(c);
					}
					c.parent.isColorRed = false;
					c.parent.parent.isColorRed = true;
					leftRotate(c.parent.parent);
				}
			}
		}
		rootnode.isColorRed = false;
	}
	
    public void transplant(Node e, Node f) 
    {
    	if(e.parent.equals(nil))
    	{
    		rootnode = f;
    	}
    	else if(e.equals(e.parent.left))
    	{
    		e.parent.left = f;
    	}
    	else
    	{
    		e.parent.right = f;
    	}
    	f.parent = e.parent;
    }
    
    public void delete(Node c) 
    {
    	Node b = c;   	
    	boolean isBRed = b.isColorRed;  	
    	Node a;   
    	
    	if (c.left.equals(nil)) 
    	{    	
    		a = c.right;
    		transplant(c, c.right);             	
    	}
    	else if (c.right.equals(nil)) 
    	{ 	
    		a = c.left;
    		transplant(c, c.left);              	
    	}
    	else 
    	{
    		b = min(c.right);
    		isBRed = b.isColorRed;
    		a = b.right;
			  
    		if (b.parent.equals(c))
    		{
    			a.parent = b;
    		}
    		else 
    		{
    			transplant(b, b.right);
    			b.right = c.right;
    			b.right.parent = b;
    		}
			
    		transplant(c, b);
    		b.left = c.left;
    		b.left.parent = b;
    		b.isColorRed = c.isColorRed;
    	}
			
    	if (!isBRed)	
    	{
    		deleteHelper(a);
    	}
    }

    protected void deleteHelper(Node a) 
    {
    	Node d = null;

    	while(!a.equals(rootnode) && !a.isColorRed) 
    	{
    		if(a.equals(a.parent.left)) 
    		{
    			d = a.parent.right;
    			if(d.isColorRed) 
    			{
    				d.isColorRed = false;
    				a.parent.isColorRed = true;
    				leftRotate(a.parent);
    				d = a.parent.right;
    			}
    			if (!d.left.isColorRed &&  !d.right.isColorRed) 
    			{
    				d.isColorRed = true;
    				a = a.parent;
    			}
    			else 
    			{
    				if(!d.right.isColorRed) 
    				{
    					d.left.isColorRed = false;
    					d.isColorRed = true;
    					rightRotate(d);
    					d = a.parent.right;
    				}
    				
    				d.isColorRed = a.parent.isColorRed;
    				a.parent.isColorRed = false;
    				d.right.isColorRed = false;
    				leftRotate(a.parent);
    				a = rootnode;
    			}
    		}
    		else 
    		{
    			d = a.parent.left;
    			if (d.isColorRed) 
    			{
    				d.isColorRed = false;
    				a.parent.isColorRed = true;
    				rightRotate(a.parent);
    				d = a.parent.left;
    			}
    			if (!d.right.isColorRed &&  !d.left.isColorRed) 
    			{
    				d.isColorRed = true;
    				a = a.parent;
    			}
    			else 
    			{
    				if(!d.left.isColorRed) 
    				{
    					d.right.isColorRed = false;
    					d.isColorRed = true;
    					leftRotate(d);
    					d = a.parent.left;
    				}
    				
    				d.isColorRed = a.parent.isColorRed;
    				a.parent.isColorRed = false;
    				d.left.isColorRed = false;
    				rightRotate(a.parent);
    				a = rootnode;
    			}
    		}
    	}

    	a.isColorRed = false;
    }

    public void printTree() 
    {
        int maxLevel = maxLevel(rootnode);
        printNodeHelper(Collections.singletonList(rootnode), 1, maxLevel);
    }

    private void printNodeHelper(List<Node> nodes, int level, int maxLevel) 
    {
        if (nodes.isEmpty() || isEmptyHelper(nodes))
        {
        	return;
        }

        int floor = maxLevel - level;
        int edges = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        insertSpace(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes)
        {
            if (node != null) 
            {	
            	System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } 
            else 
            {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            insertSpace(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= edges; i++) 
        {
            for (int j = 0; j < nodes.size(); j++) 
            {
                insertSpace(firstSpaces - i);
                if (nodes.get(j) == null) 
                {
                    insertSpace(edges + edges + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                {
                	System.out.print("/");
                }
                else
                {
                	insertSpace(1);
                }

                insertSpace(i + i - 1);

                if (nodes.get(j).right != null)
                {
                	System.out.print("\\");
                }
                else
                {
                	insertSpace(1);
                }

                insertSpace(edges + edges - i);
            }

            System.out.println("");
        }

        printNodeHelper(newNodes, level + 1, maxLevel);
    }

    private void insertSpace(int count) 
    {
        for (int i = 0; i < count; i++)
        {
        	System.out.print(" ");
        }
    }

    public int maxLevel(Node node) 
    {
        if (node == null)
        {
        	return 0;
        }

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }
    
    private boolean isEmptyHelper(List<Node> list) 
    {
        for (Object object : list) 
        {
            if (object != null)
            {
            	return false;
            }
        }
        return true;
    }
    

	public void sort(Node node)
	{
		if(nil != node)
		{
			sort(node.left);
			System.out.print(node.data + "  ");
			sort(node.right);
		}
	}
	
	public Node search(Node node, int data)
	{
		while(nil != node && data != node.data)
		{
			if(data < node.data)
			{
				node = node.left;
			}
			else
			{
				node = node.right;
			}
		}
		return node;
	}
	
	public Node min(Node node)
	{
		while(nil != node.left)
		{
			node = node.left;
		}
		return node;
	}
	
	public Node max(Node node)
	{
		while(nil != node.right)
		{
			node = node.right;
		}
		return node;
	}
	
	public Node successor(Node node)
	{
		Node successor = null;
		
		if(nil != node.right)
		{
			successor = min(node.right);
		}
		else
		{
			successor = node.parent;
			
			while(nil != successor && node.equals(successor.right))
			{
				node = successor;
				successor = successor.parent;
			}
		}
		
		return successor;
	}
	
	public Node predecessor(Node node)
	{
		Node predecessor = null;
		
		if(nil != node.left)
		{
			predecessor = max(node.left);
		}
		else
		{
			predecessor = node.parent;
			
			while(nil != predecessor && node.equals(predecessor.left))
			{
				node = predecessor;
				predecessor = predecessor.parent;
			}
		}
		
		return predecessor;
	}
    
 

}
