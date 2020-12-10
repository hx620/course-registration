package courseRegistration;

import java.util.Collections;

public class Admin extends User implements AdminInter{

	public Admin(String username, String password, String firstname, String lastname) {
		super(username, password, firstname, lastname);
	}

	public static void createCourse(String name, String id, int max, String instructor, String section, String location){
		
		Main.courselist.add( new Course(name, id, max, 0, instructor, section, location) );
		System.out.println("New course created.");
	}
	
	public static void deleteCourse(String id, String section){
		for (int i=0; i<Main.courselist.size(); i++) {
			if ( id.equals(Main.courselist.get(i).getId()) && section.equals(Main.courselist.get(i).getSection()) ) {
				Main.courselist.remove(i);
				System.out.println("Course deleted successfully.");
			}
		}
	}
	
	public static void editCourse(String id, String changeVar, String changeTo) {
		for (int i=0; i<Main.courselist.size(); i++) {
			if ( id.equals(Main.courselist.get(i).getId()) ) {
				if (changeVar.equals("capacity")){
					int newMax = Integer.parseInt(changeTo);
					(Main.courselist.get(i)).setMax(newMax);
				}
				if (changeVar.equals("instructor")){
					(Main.courselist.get(i)).setInstructor(changeTo);
				}
				if (changeVar.equals("section number")){
					(Main.courselist.get(i)).setSection(changeTo);
				}
				if (changeVar.equals("location")){
					(Main.courselist.get(i)).setLocation(changeTo);
				}
			}
		}
	}
	
	public static void display (String id, String section) {
		for (int i=0; i<Main.courselist.size(); i++) {
			if ( id.equals(Main.courselist.get(i).getId()) && section.equals(Main.courselist.get(i).getSection()) ) {
				System.out.println("Name: " + Main.courselist.get(i).getName());
				System.out.println("Id: " + Main.courselist.get(i).getId());
				System.out.println("Section: " + Main.courselist.get(i).getSection());
				System.out.println("Capacity: " + Main.courselist.get(i).getMax());
				System.out.println("Current # of enrollment: " + Main.courselist.get(i).getCurrent());
				System.out.println("Instructor: " + Main.courselist.get(i).getInstructor());
				System.out.println("Location: " + Main.courselist.get(i).getLocation());
				for (Student a : Main.courselist.get(i).getRoster()) {
					System.out.println(a.firstname + " " + a.lastname + "\n" );
				}
			}
		}
	}
	
	public static void registerStud(String studUsername, String studPassword, String firstname, String lastname) {
		Main.studentlist.add( new Student(studUsername, studPassword, firstname, lastname));
		System.out.println("New Student successfully registered.");
	}
	
	public static String viewFullCourses() {
		String fullCourses = "";
		for (int i=0; i< Main.courselist.size(); i++) {
			if ( Main.courselist.get(i).getCurrent() == Main.courselist.get(i).getMax() )
				 fullCourses +=  Main.courselist.get(i).getName() + " ";
		}
		return fullCourses;
	}

	public static void sortByEnrollment() {
		Collections.sort(Main.courselist);
		for (Course course : Main.courselist) {
			System.out.println(course.name);
		}
	}
	
	
}
