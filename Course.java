/**
 * CSCI 313
 */
package box.cuny.entities;

/**
 * class defining Course.
 * @author Julian Box
 * @version sep 24, 2022
 */
public class Course implements Comparable<Course> {
	private String courseName;
	private String	courseNumber;
	private int	limit = 5;
	private int	count = 0;
	//going to be a queue

	public Course() {
		super();
	}

	public Course(String courseName, String courseNumber, Integer limit) {
		super();
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		if(limit != null) {
			this.limit = limit.intValue();
		}

	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean equals(Course course) {
		return this.getCourseNumber().equals(course.getCourseNumber());
	}
	@Override
	public int compareTo(Course course) {
		return this.getCourseNumber().compareTo(course.getCourseNumber());
	}
	public String toString(){

		return this.courseName+" "+this.courseNumber;

	}
	

}
