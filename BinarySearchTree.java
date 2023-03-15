package box.cuny.util;

import box.cuny.entities.StudentRecord;

public class BinarySearchTree {
	private Node root;
	private int size = 0;
	public BinarySearchTree() {
		super();
	}
	public BinarySearchTree(Node root) {
		this.root = root;
	}
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public void insertSorted(StudentRecord studentRecord) {
		root = insert(getRoot(), studentRecord);
	}
	
	private Node insert(Node current, StudentRecord studentRecord) {
	    if (current == null) {
	    	size++;
	        return new Node(studentRecord);
	    }

	    if (studentRecord.compareTo(current.getData()) < 0) {
	        current.setLeft(insert(current.getLeft(), studentRecord));
	    } else if (studentRecord.compareTo(current.getData()) > 0) {
	        current.setRight(insert(current.getRight(), studentRecord));
	    } else {
	        return current;
	    }

	    return current;
	}


	public Node search(Node node) {
		Node current = getRoot();
		while (current != null) {
			if (node.getData().compareTo(current.getData()) == 0) {
				return current; 
			} else if (node.getData().compareTo(current.getData()) < 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return null;
	}
	public void delete(StudentRecord studentRecord) {
		setRoot(delete(getRoot(), studentRecord));
	}
	public Node delete(Node current, StudentRecord student) {
	    if (current == null) {
	    	size--;
	        return null;
	    }

	    if (student.compareTo(current.getData()) == 0) {
	    	if (current.getLeft() == null && current.getRight() == null) {
	    		size--;
	    	    return null;
	    	}
	    	if (current.getRight() == null) {
	    	    return current.getLeft();
	    	}

	    	if (current.getLeft() == null) {
	    	    return current.getRight();
	    	}
	    	StudentRecord smallestValue = findSmallestValue(current.getRight());
	    	current.setData(smallestValue);
	    	current.setRight(delete(current.getRight(), smallestValue));

	    } 
	    
	    if (student.compareTo(current.getData()) < 0) {
	        current.setLeft(delete(current.getLeft(), student));
	        return current;
	    }
        current.setRight(delete(current.getRight(), student));
        return current;
	}
	private StudentRecord findSmallestValue(Node root) {
	    return root.getLeft() == null ? root.getData() : findSmallestValue(root.getLeft());
	}
	
	public int size() {
		return size;
	}
	public String traverse(Node node) {
		StringBuffer sb = new StringBuffer("");
	    if (node != null) {
	        sb.append(traverse(node.getLeft()));
	        sb.append(" " + node.getData().toString());
	        sb.append(traverse(node.getRight()));
	    }
	    return sb.toString();
	}
	public String toString() {
		return traverse(getRoot());
	}
}
