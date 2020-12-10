package courseRegistration;

public interface StudInter {
	
	public static void viewOpenCourse() {}
	
	public void register(String courseName, String courseSection);
	
	public void withdraw(String courseName, String courseSection);

}
