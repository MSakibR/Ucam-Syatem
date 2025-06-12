package uap;
import java.util.HashMap;

public class Student extends User {
	private HashMap<String, String> courseRecordIds = new HashMap<>();	

	public Student(String name, int age) {
		super(name, age, "student");
	}

	public HashMap<String, String> getCourseRecordIds() {
		return courseRecordIds;
	}
	
	public void registerFor(String courseRecordId) {
		courseRecordIds.put(courseRecordId, "registered");
	}
	
	public void attendClass(String courseRecordId) throws InvalidCourseException {
		String courseStatus = courseRecordIds.get(courseRecordId);
		if (courseStatus == null)
			throw new InvalidCourseException(courseRecordId);
		
		if (courseStatus.equalsIgnoreCase("registered") || courseStatus.equalsIgnoreCase("in-progress"))
				courseRecordIds.put(courseRecordId, "in-progress");
	}
	
	public void completeCourse(String courseRecordId) throws InvalidCourseException {
		String courseStatus = courseRecordIds.get(courseRecordId);
		if (courseStatus == null)
			throw new InvalidCourseException(courseRecordId);
		
		if (courseStatus.equalsIgnoreCase("registered") || courseStatus.equalsIgnoreCase("in-progress"))
				courseRecordIds.put(courseRecordId, "completed");
	}

}
