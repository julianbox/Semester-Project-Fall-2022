/**
 * 
 */
package box.cuny.entities;

import java.util.Arrays;

/**
 * class defining sortedLinkedList.
 * @author Julian Box
 * @version October 24, 2022
 */
public class StudentRecord extends Student {
	private Course[] courses;
	/**
	 * @param IDNo
	 * @param firstName
	 * @param lastName
	 * @param courses
	 */
	public StudentRecord() {
		super();
	}

	public StudentRecord(String firstName, String lastName, String IDNo) {
		super(firstName, lastName, IDNo);
		courses = new Course[5];
	}

	public void add(CourseRoster course) {
		if(courses.length == 0) {
			if(course.getCount() < course.getLimit()) {
				courses[0] = course;
				course.insert(this);
			} 
		} else {
			boolean in = false;
			for(int i = 0 ; i < courses.length; i++) {
				if(courses[i] != null && courses[i].equals(course)) {
					in = true;
					break;
				}
			}
			if(!in) {
				if(course.getCount() < course.getLimit()) {
					for(int i = 0 ; i < courses.length; i++) {	
						if(courses[i] == null) {
							courses[i] = course;	
							break;
						}
					}
					course.insert(this);
				} 
			}
		}
	}
	
	public void remove(Course course) {
		int indexOf = -1;
		for(int i = 0 ; i < courses.length; i++) {
			if(courses[i] == course) {
				indexOf = i;
				break;
			}
		}
		if(indexOf > -1) {
			//remove the student from the courses studentRoster
		}
		for(int i = indexOf; i < courses.length; i++) {
			if(i == courses.length -1) {
				courses[i] = null;
			} else {
				courses[i] = courses[i + 1];
			}
		}
	}
	public boolean registered(CourseRoster course) {
		for(int i = 0 ; i < courses.length; i++) {
			if(courses[i] != null && courses[i].equals(course)) {
				return true;
			}
		}
		return false;
	}
	private String getCoursesString() {
		String coursesString = "";
		for(int i = 0 ; i < courses.length; i++) {
			if(courses[i] != null) {
				coursesString = coursesString + courses[i] + ", ";
			}else {
				break;
			}
		}
		return coursesString;
	}
	public String isStudentRegisteredForCourse(CourseRoster c) {
		String resultString = "Student not registered for course.\n";
		for(int i = 0 ; i < courses.length; i++) {
			if(courses[i] != null) {
				if (courses[i].equals(c)) {
					resultString = "Student registered for course.\n";
				}
			}
		}
		return resultString;
	}
	public boolean dropCourse(CourseRoster c) {
		for(int i = 0 ; i < courses.length; i++) {
			if(courses[i] != null) {
				if (courses[i].equals(c)) {
					courses[i] = null;
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return super.toString() + " Courses: " + getCoursesString();
	}
}
