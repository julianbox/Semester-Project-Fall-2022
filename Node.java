/**
 * 
 */
package box.cuny.util;

import box.cuny.entities.StudentRecord;

/**
 * class defining BinarySearchTree.
 * @author Julian Box
 * @version DDecember 4, 2022
 */
public class Node {

	StudentRecord data;
	private Node left;
	private Node right;
	private Node parent;
//constructors
	public Node() {
		data = null; left = null; right =null;
	}
	
	public Node(StudentRecord data)	{
		this.data = data; 
		this.left = null;
		this.right = null;
	}
	
	public void setData(StudentRecord data) {
		this.data = data;
	}
	public StudentRecord getData() {
		return this.data;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getLeft() {
		return this.left;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getRight() {
		return this.right;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getParent() {
		return this.parent;
	}
}
