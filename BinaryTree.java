import java.util.*;
import java.util.Map.Entry;
class Node
 {
	int data;
	Node left, right;
	public Node(int data)
	{
		this.data = data;
		left = right = null;
	}
}

public class BinaryTree
{
	public static boolean RootToLeafGivenSum(Node root, int sum)
	{
		if(root == null)
		{	
			return false;
		}

		if(root.left == null && root.right == null && sum == root.data)
		{
			return true;
		}

		return RootToLeafGivenSum(root.left ,sum - root.data) || RootToLeafGivenSum(root.right, sum - root.data); 
	}

	public static int getSumOfAllNodes(Node root)
	{
		if(root == null)
		{
			return 0;
		}
		return root.data + getSumOfAllNodes(root.left) + getSumOfAllNodes(root.right); 
		
	}

	public static void preOrderRecursive(Node root)
	{
		if(root == null)
		{
			return;
		}
		System.out.print(root.data + " ");
		preOrderRecursive(root.left);
		preOrderRecursive(root.right);
	}

	
	public static void preOrderIterative(Node root)
	{

		Stack<Node> stack = new Stack<>();	
		if(root == null)
		{
			return;
		}
		stack.push(root);

		while(!stack.isEmpty())
		{
			Node node = stack.pop();
			System.out.print(node.data + " ");
			if(node.right != null)
			{
				stack.push(node.right);
			}
			if(node.left != null)
			{
				stack.push(node.left);
			}
			
		}
		

	}

	public static void TopViewOfBinaryTree(Node root)
	{
		class TopViewHeight
		{
			Node node;
			int height;

			public TopViewHeight(Node node, int height)
			{		
				this.node = node;
				this.height = height;
			}	
		}	

		if(root == null)
		{
			return;
		}

		Queue<TopViewHeight> q = new LinkedList<>();
		Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		q.add(new TopViewHeight(root, 0));
		while(!q.isEmpty())
		{
			TopViewHeight tempNodeTop = q.poll();
			if(!treeMap.containsKey(tempNodeTop.height))
			{
				treeMap.put(tempNodeTop.height, tempNodeTop.node.data);
			}
			if(tempNodeTop.node.left != null)
			{
				q.add(new TopViewHeight(tempNodeTop.node.left, tempNodeTop.height - 1));
			}
			if(tempNodeTop.node.right != null)
			{
				q.add(new TopViewHeight(tempNodeTop.node.right, tempNodeTop.height + 1));
			}
		}
		for(Entry<Integer, Integer> entrySet : treeMap.entrySet())
		{
			System.out.print(entrySet.getValue() + " ");
		}
	}
	

	public static void postOrderRecursive(Node root)
	{
		if(root == null)
		{
			return;
		}
		preOrderRecursive(root.left);
		preOrderRecursive(root.right);
		System.out.print(root.data + " ");
	}

	public static void InOrderIterative(Node root)
	{
		if(root == null)
		{
			return;
		}
		Stack<Node> stack = new Stack<>();
		while(root != null)
		{
			stack.push(root);
			root = root.left;
		}
		while(!stack.isEmpty())
		{
			Node node = stack.pop();
			System.out.print(node.data + " ");
			if(node.right != null)
			{
				Node nodeR = node.right;
				while(nodeR != null)
				{
					stack.push(nodeR);
					nodeR = nodeR.left;	
				}
			}
			

		}
	
	}

	public static void InOrderRecursive(Node root)
	{
		if(root == null)
		{
			return;
		}
		InOrderRecursive(root.left);
		System.out.print(root.data + " ");
		InOrderRecursive(root.right);
	}
	

	
	public static void main(String[] arv)
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.right.left = new Node(5);
		root.left.right = new Node(7);
		root.right.right = new Node(8);
		root.left.left.left = new Node(9);
		root.right.right.right = new Node(10);
		root.left.left.left.left = new Node(11);
		root.left.left.left.right = new Node(12);

		System.out.println("PreOrderUsingRecursion:");

		preOrderRecursive(root);
		
		System.out.println("\nPreOrderUsingIterative:");
		preOrderIterative(root);

		System.out.println("\nInOrderUsingRecursion:");

		InOrderRecursive(root);
		System.out.println("\nInOrderUsingIterative:");

		InOrderIterative(root);

		System.out.println("\nPostOrderUsingRecursion:");

		postOrderRecursive(root);

		

		System.out.println("\nSum of All nodes: " + getSumOfAllNodes(root));

		System.out.println("\nChecking Sum exists from root to leaf or not with sum  = 20");
		if(RootToLeafGivenSum(root, 20))
		{
			System.out.println("Sum exists from root to leaf");
		}

		else
		{
			System.out.println("Sum is not present from leaf to root");
		}
		System.out.println("Top View Of binary tree: ");
		TopViewOfBinaryTree(root);
		

	}

			//	1
			 //   2	    9
		        // 3    7  8   10
		  //   4	          12
		//  5	   6

}