/**
 * 
 */
package com.demo.sibblings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marklapointe
 *
 */
public class Main {


	/**
	 * 
	 */
	public Main() {

	}


	public static Node discoverSiblings(Node root) {
		if (root != null) {

			if (root.left != null) { // Check to see if we have left node
				root.left.nextSibling = root.right; // Place the right node as the next sibling, even if it is null, still will be correct
			}
			if (root.right != null) { // Check to see if we have a right node
				if (root.nextSibling != null) { // check if root has any sibling
					// This provides the reach around
					root.right.nextSibling = root.nextSibling.left;
				}
			}
			discoverSiblings(root.left);
			discoverSiblings(root.right);
			return root; 

		}
		return null;
	}

	public static void printChain(Node n) {
		while (n != null) {
			System.out.print(n.data + " ");
			n = n.nextSibling;
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		Node x = Main.discoverSiblings(root);
		Main.printChain(x);
		Main.printChain(x.left);
		Main.printChain(x.left.left);
		/**
					 1 
				   /   \
				  2      3
				/   \   /  \
			   4     5 6    7
		**/
		x.printInOrder();    // 4251637
		System.out.println();
		x.printPreOrder();  // 1245367
		System.out.println();
		x.printPostOrder(); // 4526731
		System.out.println();
		
		System.out.println("End");
	}

}

class Node {
	Integer data;
	Node left;
	Node right;
	Node nextSibling;

	public Node(int data) {
		this.data = data;
	}

	private void print() {
		System.out.print(this.data);
	}


	public void printInOrder() {
		if (this.left!= null) this.left.printInOrder();
		this.print();
		if (this.right!= null) this.right.printInOrder();
	}

	public List<Node> getInOrder() {
		List<Node> retVal = new ArrayList<Node>();
		if (this.left!= null) retVal.addAll(this.left.getInOrder());
		retVal.add(this);
		if (this.right!= null) retVal.addAll(this.right.getInOrder());
		return retVal;
	}


	private void ordered_insert_right (Integer data) {
		if (this.right == null) {
			this.right = new Node(data);
		} else {
			this.right.ordered_insert(data);
		}
	}



	public List<Node> getPreOrder() {
		List<Node> retVal = new ArrayList<Node>();
		retVal.add(this);
		if (this.left!= null) retVal.addAll(this.left.getPreOrder());
		if (this.right!= null) retVal.addAll(this.right.getPreOrder());
		return retVal;
	}

	public void printPostOrder() {
		if (this.left!= null) this.left.printPostOrder();
		if (this.right!= null) this.right.printPostOrder();
		this.print();
	}

	public void printPreOrder() {
		this.print();
		if (this.left!= null) this.left.printPreOrder();
		if (this.right!= null) this.right.printPreOrder();
	}



	public void ordered_insert(Integer data) {
		if (this.data == null) {
			this.data = data;
			return;
		} else if (data > this.data) {
			this.ordered_insert_right(data);
			return;
		} else if (data < this.data) {
			this.ordered_insert_left(data);
			return;
		}
		return;
	}

	private void ordered_insert_left (Integer data) {
		if (this.left == null) {
			this.left = new Node(data);
		} else {
			this.left.ordered_insert(data);
		}
	}

}
