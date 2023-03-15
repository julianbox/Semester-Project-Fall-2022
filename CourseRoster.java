/**
 * 
 */
package box.cuny.entities;

import box.cuny.util.BinarySearchTree;
import box.cuny.util.Node;

/**
 * @author julianbox
 *
 */
public class CourseRoster extends Course {
//	private LinkedStudentRecordList studentRoster;
	private BinarySearchTree studentRoster;
	private StudentRecord[] waitingList;
	/**
	 * @param courseName
	 * @param courseNumber
	 * @param limit
	 */
	public CourseRoster() {
		super();
	}
	public CourseRoster(String courseName, String courseNumber, Integer limit) {
		super(courseName, courseNumber, limit);
		studentRoster = new BinarySearchTree();
		waitingList = new StudentRecord[5];
	}
	
	public StudentRecord[] getWaitingList() {
		return waitingList;
	}
	public void setWaitingList(StudentRecord[] waitingList) {
		this.waitingList = waitingList;
	}
	public boolean addToWaitingList(StudentRecord s) {
		return enque(s);
	}
	public boolean studentWentToWaitingList(StudentRecord s) {
		for(int i = 0; i < waitingList.length; i++) {
			if(waitingList[i] != null ) {
				if(waitingList[i].compareTo(s) == 0) {
					return true;
				}	
			}
		}
		return false;	
	}

	private boolean enque(StudentRecord studentRecord) {
		if(waitingList[waitingList.length - 1] != null ) {
			return false;
		} else { 
			for(int i = 0; i < waitingList.length; i++) {
				if (waitingList[i] == null) {
					waitingList[i] = studentRecord;
				}
			}
			return true;
		}
	}
	public StudentRecord deque() {
		StudentRecord s = waitingList[0];
		for(int i = 0; i < waitingList.length-1; i++) {
			if( i > 0) {
				if(waitingList[i] != null) {
					waitingList[i - 1] = waitingList[i];
				} else {
					waitingList[i - 1] = null;
				}
			} 
		}
		return s;
	}
	public StudentRecord peek() {
		StudentRecord s = waitingList[0];
		if(s != null) {
			for(int i = 0; i < waitingList.length-1; i++) {
				if( i > 0) {
					if(waitingList[i] == null) {
						s = waitingList[i - 1];
					} 
				} 
			}
		} 
		return s;
	}
	
	public BinarySearchTree getStudentRoster() {
		return studentRoster;
	}
	public void setStudentRoster(BinarySearchTree studentRoster) {
		this.studentRoster = studentRoster;
	}
	
	public void insert(StudentRecord studentRecord) {
		getStudentRoster().insertSorted(studentRecord);
	}
	public boolean isFull() {
		return studentRoster.size() == super.getLimit(); 
	}
	public boolean isStudentInClass(StudentRecord s) {
		Node n = new Node(s);
		Node found = getStudentRoster().search(n);
		if(found != null) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return super.toString() + " " ;
	}

}
