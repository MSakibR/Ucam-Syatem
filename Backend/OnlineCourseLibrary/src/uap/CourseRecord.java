package uap;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class CourseRecord implements Serializable{
	private String id, courseId, instructorId ;
	private LocalDate start_date;//, reservation_end_date;
	private int duration; // in days
	private ArrayList<String> registeredStudentIds= new ArrayList<String>();
	
	

	public CourseRecord(String courseId, String instructorId, LocalDate start_date, int duration) {
		super();
		this.id = "00-" + String. format("%04d", new Random().nextInt(10000));
		this.courseId = courseId;
		this.instructorId = instructorId;
		this.start_date = start_date;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public ArrayList<String> getRegisteredStudentIds() {
		return registeredStudentIds;
	}
	
	public void addStudent(String studentId) throws Exception {
		if(registeredStudentIds.contains(studentId))
			throw new Exception("Already registered for the course.");
		
		registeredStudentIds.add(studentId);
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public LocalDate getEnd_date() {
		return start_date.plusDays(duration);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}	
}
