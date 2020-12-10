package courseRegistration;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Student extends User implements StudInter, java.io.Serializable {

	public ArrayList<Course> currentCourses = new ArrayList<>();
	String username;
	String password;
	String firstname;
	String lastname;

	public Student(String username, String password, String firstname, String lastname) {
		//super(username, password, firstname, lastname);
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.currentCourses = new ArrayList<Course>();
	}

	public ArrayList<Course> getCurrentCourses() {
		ArrayList<Course> validCurrentCourses = new ArrayList<>();
		for (int i=0; i<currentCourses.size(); i++) {
			for (int j=0; j<Main.courselist.size(); j++) {
				if( ((currentCourses.get(i).getId())).equals( (Main.courselist.get(j).getId()) ) 
					&& (currentCourses.get(i).getSection()).equals( (Main.courselist.get(j).getSection()) ) )
						validCurrentCourses.add( (currentCourses.get(i)) );
			}
		}
		return validCurrentCourses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setCurrentCourses(ArrayList<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}
	

	
	
	
	public static String viewOpenCourse() {
		String openCourses = "";
		for (int i=0; i< Main.courselist.size(); i++) {
			if ( Main.courselist.get(i).getCurrent() < Main.courselist.get(i).getMax() )
				 openCourses +=  Main.courselist.get(i).getName() + " ";
		}
		return openCourses;
	}
	
	public void register(String courseName, String courseSection) {
		for (Course a : Main.courselist) {
			if ((a.name).equals(courseName) && (a.section).equals(courseSection)) {
				if (a.current + 1 > a.max) System.out.println("Sorry the class is full.");
				else {
					(a.roster).add(this);
					a.current += 1;
					currentCourses.add(a);
				}
			}
		}
	}
	
	public void withdraw(String courseName, String courseSection) {
		for (Course a : Main.courselist) {
			if ((a.name).equals(courseName) && (a.section).equals(courseSection)) {
					(a.roster).remove(this);
					a.current -= 1;
					currentCourses.remove(a);
					System.out.println("Course removed successfully.");
			}
		}
	}
		
}
