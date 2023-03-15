/**
 * CSCI 313 Data Structures
 */
package box.cuny.entities;

/**
 * Class defining the student.
 * @author Julian Box
 */
public class Student implements Comparable<Student> {
	private String IDNo;
	private String firstName;
	private String lastName;
	
	public Student() {
		super();
	}
	public Student(String firstName, String lastName, String IDNo) {
		super();
		this.IDNo = IDNo;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getIDNo() {
		return IDNo;
	}

	public void setIDNo(String IDNo) {
		this.IDNo = IDNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean equals(String IDNo) {
		return this.getIDNo().equals(IDNo);	
	}
	@Override
	public int compareTo(Student student) {
		if (this.getLastName().equals(student.getLastName())) {
			return this.getFirstName().compareTo(student.getFirstName());
		}else {
			return this.getLastName().compareTo(student.getLastName());
		}
	}
	public String toString(){
		return this.lastName+" "+this.firstName+" "+this.IDNo;
	}
	
	

	
}
