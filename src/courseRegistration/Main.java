package courseRegistration;

import java.util.*;
import java.io.*;

public class Main {

	static ArrayList<Course> courselist = new ArrayList <Course>();
	static ArrayList<Student> studentlist = new ArrayList <Student>();
	static Scanner sc = new Scanner(System.in);
	static int studIndex;
	
	public static void main (String[] args) {
		
		load();
		int flag = login();
		if (flag == 1) adminSession();
		else if (flag == 2) studentSession();
		else {System.out.println("Login Failed. Program Quiting."); return;}
		save();
	}
	
	@SuppressWarnings("unchecked")
	public static void load() {
		
		
		try {
			ObjectInputStream cois = new ObjectInputStream(new FileInputStream("courselist.ser"));
			ObjectInputStream sois = new ObjectInputStream(new FileInputStream("studentlist.ser"));
			courselist = (ArrayList<Course>) cois.readObject();
			studentlist = (ArrayList<Student>) sois.readObject();
			cois.close();
			sois.close();
			
			System.out.println("Deserialization complete.");
		}
		catch(Exception e1) {
			try {
				Scanner scanner = new Scanner(new File("MyUniversityCourses.csv"));
				scanner.nextLine();
				while(scanner.hasNext()){

					String line = scanner.nextLine();
					String[] spline = line.split(",", 0);
					Course a = new Course(spline[0], spline[1], Integer.parseInt(spline[2]), Integer.parseInt(spline[3]), spline[5], spline[6], spline[7]);
					courselist.add(a);

				}
				System.out.println("Finished reading from csv.");
				scanner.close();
			}
			catch(Exception e2) { System.out.println("Wrong ;<"); }
		}
		
	}

	public static int login() {        
		
		System.out.println("Welcome!");
		System.out.println("Please type in username: ");
		String username = sc.next();
		System.out.println("Please type in password: ");
		String password = sc.next();

		if(username.equals("Admin") && password.equals("Admin001")) return 1;

		for (int i = 0; i<studentlist.size(); i++) {
			System.out.println(studentlist.get(i).getUsername() + " " + studentlist.get(i).getPassword());
			if (username.equals(studentlist.get(i).getUsername()) && password.equals(studentlist.get(i).getPassword())) {
				studIndex = i;
				return 2;
			}
		}
		
		return 0;
	}

	public static void save() {
		try {
			ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("courselist.ser"));
			ObjectOutputStream soos = new ObjectOutputStream(new FileOutputStream("studentlist.ser"));
			//Writes the specific object to the OOS
			coos.writeObject(courselist);
			soos.writeObject(studentlist);
			//Close both streams
			coos.close();
			soos.close();
			System.out.println("Serialization complete.");
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}	
	}

	public static void adminSession() {
		while(true) {
			System.out.println("\nWhat would you like to do? Type in task number. \n"+
					"1.Create a new course\n" + 
					"2.Delete a course\n" + 
					"3.Edit a course\n" + 
					"4.Display information for a given course\n" + 
					"5.Register a student\n"+
					"6.View all courses\n"+
					"7.View all courses that are full\n"+
					"8.Write to a file the list of courses that are full\n"+
					"9.View the names of the students that are registered in a specific course\n"+
					"10.View the list of courses that a given student is registered in\n"+
					"11.Sort\n"+
					"12.Exit Program\n");
			int choice = sc.nextInt();
			if (choice == 1) {
				sc.nextLine();
				System.out.println("Enter new course name: ");
				String name = sc.nextLine();
				System.out.println("Enter new course id: ");
				String id = sc.nextLine();
				System.out.println("Enter new course capacity: ");
				int max = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter new course instructor: ");
				String instructor = sc.nextLine();
				System.out.println("Enter new course section number: ");
				String section = sc.nextLine();
				System.out.println("Enter new course location: ");
				String location = sc.nextLine();
				Admin.createCourse(name, id, max, instructor, section, location);
			}	
				
			if (choice == 2) {
				sc.nextLine();
				System.out.println("Enter course id: ");
				String id=sc.nextLine();
				System.out.println("Enter course section: ");
				String section = sc.nextLine();
				Admin.deleteCourse(id, section);
			}
			if (choice == 3) {
				sc.nextLine();
				System.out.println("Enter the id of the course of which you would like to edit: ");
				String id = sc.nextLine();
				System.out.println("What would you like to edit? Please enter one of the following: capacity, instructor, section number, or location: ");
				String changeVar = sc.nextLine();
				System.out.println("Enter what you want it to be changed to: ");
				String changeTo = sc.nextLine();
				Admin.editCourse(id, changeVar, changeTo);
			}
			if (choice == 4) {
				sc.nextLine();
				System.out.println("Enter course id: ");
				String id = sc.nextLine();
				System.out.println("Enter course section: ");
				String section = sc.nextLine();
				Admin.display(id, section);
			}
			if (choice == 5) {
				sc.nextLine();
				System.out.println("Enter student's username: ");
				String studUsername = sc.nextLine();
				System.out.println("Enter student's password: ");
				String studPassword = sc.nextLine();
				System.out.println("Enter student's firstname: ");
				String firstname = sc.nextLine();
				System.out.println("Enter student's lastname: ");
				String lastname = sc.nextLine();
				Admin.registerStud(studUsername, studPassword, firstname, lastname);
			}
			if (choice == 6) {
				for (int i=0; i< courselist.size(); i++) {
					Admin.display(courselist.get(i).getId(), courselist.get(i).getSection());
					System.out.println("");
				}
			}
			
			if (choice == 7) {
				System.out.println(Admin.viewFullCourses());
			}
			
			if (choice == 8) {
				try {
					FileWriter fullcourse = new FileWriter( new File("full_courses.csv") );
					fullcourse.append( Admin.viewFullCourses() );
					fullcourse.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (choice == 9) {
				sc.nextLine();
				System.out.println("Enter course id: ");
				String id = sc.nextLine();
				System.out.println("Enter course section: ");
				String section = sc.nextLine();
				for (int i=0; i< courselist.size(); i++) 
					if ( (courselist.get(i).getId()).equals(id) && (courselist.get(i).getSection()).equals(section) ) 
						for (Student a : courselist.get(i).getRoster())
							System.out.println( a.firstname + " " + a.lastname + "\n" );
			}
				
			if (choice == 10) {
				sc.nextLine();
				System.out.println("Enter student's first name: ");
				String firstname = sc.nextLine();
				System.out.println("Enter student's last name: ");
				String lastname = sc.nextLine();
				for (int i=0; i<studentlist.size(); i++) {
					if ((firstname.equals(studentlist.get(i).firstname)) && (lastname.equals(studentlist.get(i).lastname))) {
						//same process as task 5 in student session
						ArrayList<Course> readyToPrint = (studentlist.get(studIndex)).getCurrentCourses();
						for (int j=0; j<readyToPrint.size(); j++)
							System.out.println((readyToPrint.get(j)).getName());
					}
				}
			}
			
			if (choice == 11) {
				Admin.sortByEnrollment();
			}
			
			if (choice == 12) {
				System.out.println("Bye!");
				break;
			}
			
		}
	}

	public static void studentSession() {
		while(true) {
			System.out.println("\nWhat would you like to do? Type in task number. \n"+
					"1. View all courses\n"+
					"2. View all courses that are not full\n"+
					"3. Register in a course\n"+
					"4. Withdraw from a course\n"+
					"5. View all courses that are currently registered\n"+
					"6. Exit Program\n");
			int choice = sc.nextInt();
			
			if (choice == 1) {
				for (int i=0; i< courselist.size(); i++) {
					Admin.display(courselist.get(i).getId(), courselist.get(i).getSection());
					System.out.println("");
				}
			}
			
			if (choice == 2) {
				System.out.println(Student.viewOpenCourse());
			}
			
			if (choice == 3) {
				sc.nextLine();
				System.out.println("Enter course name: ");
				String courseName = sc.nextLine();
				System.out.println("Enter course section: ");
				String courseSection = sc.nextLine();
				studentlist.get(studIndex).register(courseName, courseSection);
			}
			
			if (choice == 4) {
				sc.nextLine();
				System.out.println("Enter course name: ");
				String courseName = sc.nextLine();
				System.out.println("Enter course section: ");
				String courseSection = sc.nextLine();
				studentlist.get(studIndex).withdraw(courseName, courseSection);
			}
			
			if (choice == 5) {
				//store the returned array list for printing
				ArrayList<Course> readyToPrint = (studentlist.get(studIndex)).getCurrentCourses();
				//print
				for (int i=0; i<readyToPrint.size(); i++)
					System.out.println((readyToPrint.get(i)).getName());
			}
			
			if (choice == 6) {
				System.out.println("Bye!");
				break;
			}
		}
	}
}
