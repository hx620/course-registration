package courseRegistration;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Course implements java.io.Serializable, Comparable<Course>{
	String name;
	String id;
	int max;
	int current;
	ArrayList<Student> roster; 
	String instructor; 
	String section;
	String location;
	
	public Course(String name, String id, int max, int current, String instructor, String section, String location) {
		this.name = name;
		this.id = id;
		this.max = max;
		this.current = current;
		this.roster = new ArrayList<Student>();
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public int getCurrent() {
		return current;
	}

	public ArrayList<Student> getRoster() {
		return roster;
	}

	public void setRoster(ArrayList<Student> roster) {
		this.roster = roster;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public int compareTo(Course o) {
		if (this.current == o.current) return 0;
		else if (this.current > o.current) return 1;
		else return -1;
	}
}
