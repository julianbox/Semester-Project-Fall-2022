/**
 * CSCI 313
 */
package box.cuny.main;

/**
 * class defining Course.
 * @author Julian Box
 * @version 10/21, 2022
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import box.cuny.entities.CourseRoster;
import box.cuny.entities.StudentRecord;

public class Registration {
	//instance variables
	private static List<CourseRoster> courseMaster = new ArrayList<>();
	private static List<StudentRecord> students = new ArrayList<>();
	private static StudentRecord[] MasterByID = new StudentRecord[100];
	
	private static String prompt = "Select one of the following: \n" +
        	"1. Add student to the file\n" +
            "2. Add course to the file\n" +
            "3. Search for student\n" +
            "4. Search for course\n" +
        	"5. Register student for course\n" +
        	"6. Display student roster for course\n" + //displayStudentRosterForCourse()
        	"7. Display the master list of courses\n" + //displayCourseMasterList()
        	"8. Display a master list of students\n" +
        	"9. Drop course\n" +
        	"10. Search for course in student record\n" +//searchForCourseInStudentRecor
        	"11. Search for student in course roster\n" +//searchForStudentInCourseRoster
        	"12. Exit Program\n";
	

	private static String addStudentPrompt = "Enter last name, first name, id number: \n";
	private static String addCoursePrompt = "Enter course name, course number: \n";
	private static String searchStudentPrompt = "Enter last name, first name, or student ID(Only Numbers): \n";
	private static String searchCoursePrompt = "Enter course number: \n";
	private static String studentRegisterPrompt1 = "Enter student last name, first name, or studdent ID(Only Numbers): \n";
	private static String studentRegisterPrompt2 = "Enter course number: \n";
	
	private static void addCourseToMaster(CourseRoster course) {
		courseMaster.add(course);
	}
	
	private static void addStudentToStudentList(StudentRecord student) {
		students.add(student);
	}
	private static void addStudentToMasterByIDList(StudentRecord student) {
		int studentIdAsNumber = Integer.valueOf(student.getIDNo());
		int index = studentIdAsNumber%100;
		MasterByID[index] = student;
	}
	/**
	 * @param args
	 */
	public static void addStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter first name: ");
		String  firstName = scanner.next();
		System.out.println("Enter last name: ");
		String  lastName = scanner.next();
		System.out.println("Enter student ID: ");
		String  iD = scanner.next();
		StudentRecord s = new StudentRecord(firstName, lastName, iD);
		addStudentToStudentList(s);
		addStudentToMasterByIDList(s);
		System.out.println("Added Student: " + s.toString()+ "\n");
	}
	
	public static void addCourse() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter course name: ");
		String  courseName = scanner.next();
		System.out.println("Enter course number: ");
		String  courseNumber = scanner.next();
		CourseRoster c = new CourseRoster(courseName, courseNumber, null);
		addCourseToMaster(c);
		System.out.println("Added Course: " + c.toString() + "\n");
	}
	public static StudentRecord searchForStudent(String prompt) {
		System.out.println(prompt);
		Scanner scanner = new Scanner(System.in);
		String searchString = scanner.nextLine();
		String[] studentData = searchString.split(",");
		Integer n = null;
		if(studentData.length == 1) {
			try {
				n = Integer.valueOf(studentData[0]);
			} catch(Exception e) {
				System.out.println("Error reading student ID(Not a number)");
				searchForStudent(prompt);
			}
		}
		
		StudentRecord searchStudent = new StudentRecord();
		if (studentData.length == 2) {
			searchStudent.setLastName(studentData[0].trim());
			searchStudent.setFirstName(studentData[1].trim());
		} else if(studentData.length == 1 && n != null){
			searchStudent = MasterByID[n%100];
			return searchStudent;
		} else {
			searchForStudent(prompt);
		}
		StudentRecord found = null;
		for (StudentRecord s : students) {
			if (s.compareTo(searchStudent) == 0) {
				found = s;
			}
		}
		if (found == null) {
			System.out.println("Student Not Found\n");
			return null;
		} else {
			return found;
		}
	}
	public static CourseRoster searchForCourse(String prompt) {
		System.out.println(prompt);
		Scanner scanner = new Scanner(System.in);
		String searchString = scanner.nextLine();
		CourseRoster searchCourse = new CourseRoster();
		if (searchString != null) {
			searchCourse.setCourseNumber(searchString);
		} else {
			searchForCourse(prompt);
		}
		CourseRoster found = null;
		for (CourseRoster c : courseMaster) {
			if (c.compareTo(searchCourse) == 0) {
				found = c;
			}
		}
		if (found == null) {
			System.out.println("Course Not Found\n");
			return null;
		} else {
			return found;
		}		
	}
	public static void registerStudentForCourse() {
		StudentRecord student = searchForStudent(studentRegisterPrompt1);
		CourseRoster course = null;
		if(student != null) {
			course = searchForCourse(studentRegisterPrompt2);
			if(course == null) {
				registerStudentForCourse();
			}
		} else {
			registerStudentForCourse();
		}
		if(!course.isFull() && !student.registered(course)){
//			course.insert(student);
			student.add(course);
			System.out.println("Success!\n");
		} else {
			if(course.isFull() && !student.registered(course)) {
				course.addToWaitingList(student);
				if(course.studentWentToWaitingList(student)) {
					System.out.println("Course is full, student has been added to the waiting list.\n");
				} else {
					System.out.println("Course is full, waiting list is full please try again at another time.\n");
				}
			} else if(!course.isFull() && student.registered(course)) {
				System.out.println("Student is already registered for this course.\n");
			} else if(course.isFull() && student.registered(course)) {
				System.out.println("Student is already registered for this course.\n");
			}
		}
	}
	public static void searchForCourseInStudentRecord() {
		StudentRecord s = searchForStudent(searchStudentPrompt);
		if(s == null) {
			System.out.println("Student not found.\n");
			return;
		} else {
    		CourseRoster c =searchForCourse(searchCoursePrompt);
    		if (c == null) {
    			System.out.println("Course not in master course list\n");
    			return;
    		} else {
    			System.out.println(s.isStudentRegisteredForCourse(c));
    		}
		}
	}
	
	public static void searchForStudentInCourseRoster() {
  		CourseRoster c = searchForCourse(searchCoursePrompt);
		if(c == null) {
			System.out.println("Course not in master course list\\n");
			return;
		} else {
    		StudentRecord s = searchForStudent(searchStudentPrompt);
    		if (s == null) {
    			System.out.println("Student not found.\n");
    			return;
    		} else {
    			if(c.isStudentInClass(s)) {
    				System.out.println("Student is in course roster.\n");
    			}else {
    				System.out.println("Student is not in course roster.\n");
    			}
    		}
		}
	}
	public static void displayCourseMasterList() {
		for(CourseRoster c : courseMaster) {
			System.out.println(c.toString());
		}
		System.out.println("\n");		
	}
	public static void displayStudentList() {
		for(StudentRecord s : students) {
			System.out.println(s.toString());
		}
		System.out.println("\n");	
	}
	public static void displayStudentRosterForCourse() {
		CourseRoster c =searchForCourse(searchCoursePrompt);
		if (c != null) {
			System.out.println(c.getStudentRoster().toString());
		}
		System.out.println("\n");
	}
	public static void dropCourse() {
		StudentRecord s = searchForStudent(searchStudentPrompt);
		if(s == null) {
			System.out.println("Student not found.\n");
			return;
		} else {
    		CourseRoster c =searchForCourse(searchCoursePrompt);
    		if (c == null) {
    			System.out.println("Course not in master course list\n");
    			return;
    		} else {
    			if(s.dropCourse(c)) {
    				c.getStudentRoster().delete(s);
    				System.out.println("Succesfully dropped course.\n");
    			} else {
    				System.out.println("Unable to drop course.\n");
    			}
    		}
		}
	}
	public static void main(String[] args) {
        int option = -1;
        System.out.println(prompt);
        while (option != 12) {
	            Scanner scanner = new Scanner(System.in);
	            option = scanner.nextInt();
			switch (option) {
	    		case 1 : {
	    			addStudent();
	    			break;
	    		}
	        	case 2 : {
	        		addCourse();
	        		break;
	        	}
	        	case 3 : {
	        		StudentRecord s = searchForStudent(searchStudentPrompt);
	        		if (s != null) {
	        			System.out.println("Found: " + s.toString() + "\n");
	        		}
	        		break;
	        		}
	        	case 4 : {
	        		CourseRoster c =searchForCourse(searchCoursePrompt);
	        		if (c != null) {
	        			System.out.println("Found: " + c.toString() + "\n");
	        		}
	        		break;
	        	}
	        	case 5 : {
	        		registerStudentForCourse();
	        		break;
	        	}
	        	case 6 : {
	        		displayStudentRosterForCourse();
	        		break;
	        	}
	        	case 7 : {
	        		displayCourseMasterList();
	        		break;
	        	}
	        	case 8 : {	        	
	        		displayStudentList();
	        		break;
	        	}
	        	case 9 : {
	        		dropCourse();
	        		break;
	        	}
	        	case 10 : {
	        		searchForCourseInStudentRecord();
	        		break;
	        	}
	        	case 11 : {
	        		searchForStudentInCourseRoster();
	        		break;
	        	}
	        	case 12 : {
	        		System.out.println("Goodbye");
	        		return;
	        	}
	        	default : System.out.println("Invalid choice\n");
	        }
	        System.out.println(prompt);
		}
	}

}
