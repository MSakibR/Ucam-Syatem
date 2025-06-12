package uap;

import java.util.ArrayList;

public class Instructor extends User {
	private ArrayList<String> courseRecordIds = new ArrayList<String>();	

	public Instructor(String name, int age) {
		super(name, age, "instructor");
	}

	public ArrayList<String> getCourseRecordIds() {
		return courseRecordIds;
	}
	
	public void registerFor(String courseRecordId) {
		courseRecordIds.add(courseRecordId);
	}
}
