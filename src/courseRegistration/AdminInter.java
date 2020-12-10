package courseRegistration;

public interface AdminInter {

	public static void createCourse(String name, String id, int max, String instructor, String section, String location){}
	
	public static void deleteCourse(String id, String section){}
	
	public static void editCourse(String id, String changeVar, String changeTo) {}
		
	public static void display (String id, String section) {}
	
	public static void registerStud(String studUsername, String studPassword, String firstname, String lastname) {}
	
	public static void viewFullCourses() {}
	
	public static void sortByEnrollment() {}
	
}
