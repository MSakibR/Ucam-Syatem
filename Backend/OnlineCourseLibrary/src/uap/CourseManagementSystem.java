package uap;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CourseManagementSystem implements Serializable{
	private String name;
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<CourseRecord> courseRecords = new ArrayList<CourseRecord>();

	public CourseManagementSystem(String name) {
		super();
		this.name = name;
	}
	
	//--------- Add related methods----------------------------------------//
	//---------------------------------------------------------------------//
	public String addCourse(String title) {
		Course course = new Course(title);
		courses.add(course);
		return course.getId();

	}

	public String addCourse(String title, ArrayList<String> topics) {
		Course course = new Course(title);
		courses.add(course);
		return course.getId();
	}

	private String addUser(String name, int age, String role) {

		switch (role.toLowerCase()){
		case "admin": {

			Admin admin = new Admin(name, age);
			users.add(admin);
			return admin.getId();
		}
		case "instructor": {

			Instructor ins = new Instructor(name, age);
			users.add(ins);
			return ins.getId();
		}
		case "student": {

			Student st = new Student(name, age);
			users.add(st);
			return st.getId();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + role.toLowerCase());
		}
	}

	public String addAdmin(String name, int age) {
		return addUser(name, age, "admin");
	}

	public String addInstructor(String name, int age) {
		return addUser(name, age, "instructor");
	}

	public String addStudent(String name, int age) {
		return addUser(name, age, "student");
	}

	public String signUp(String name, int age) {
		return addStudent(name, age);
	}

	//--------- Offer course  --------------------------------------------//
	//---------------------------------------------------------------------//
	public void offerCourse(Course course, Instructor instructor, String start_date, int duration) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate startDate =  LocalDate.parse(start_date, formatter);
		CourseRecord record = new CourseRecord(course.getId(), instructor.getId(), startDate, duration);
		courseRecords.add(record);;
		course.setLatestSessionId(record.getId());
		instructor.registerFor(record.getId());
	}
	
	public void offerCourse(String courseId, String instructorId, String start_date, int duration) throws InvalidCourseException, InvalidUserException {
		Course course = findCourse(courseId);
		User user = findUser(instructorId);
		if (user instanceof Instructor) {
			Instructor ins = (Instructor)user;
			offerCourse(course, ins, start_date, duration);
		}
		else
			throw new InvalidUserException(instructorId);		
		
	}

	//-------------- Getter/Search methods----------------------------------------//
	//---------------------------------------------------------------------//
	public String getName() {
		return name;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Student> getStudents(){
		ArrayList<Student> sts = new ArrayList<Student>();
		for(User user : users) {
			if (user instanceof Student) {
				sts.add((Student)user);
			}
		}

		return sts;		
	}

	public ArrayList<Student> getStudents(String courseRecordId){
		ArrayList<Student> sts = new ArrayList<Student>();
		for(User user : users) {
			if (user instanceof Student) {
				Student s = (Student)user;
				if (s.getCourseRecordIds().containsKey(courseRecordId))
					sts.add(s);
			}
		}
		
		return sts;	

	}

	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public ArrayList<Course> getCourses(String title) throws NotAvailableException {
		ArrayList<Course> matchedCourses = new ArrayList<>();
		
		for(Course course: courses) {
			if (course.getTitle().toLowerCase().contains(title.toLowerCase()))
				matchedCourses.add(course);
				
		}
		
		if (matchedCourses.size()==0)
			throw new NotAvailableException(String.format("No offered course found with title '%s'", title));
		
		return matchedCourses;
	} 

	public ArrayList<CourseRecord> getOfferedCourses() {
		return courseRecords;
	}
	
	public ArrayList<CourseRecord> getOfferedCourses(String courseId) throws NotAvailableException {
		ArrayList<CourseRecord> offerredCourses = new ArrayList<CourseRecord>();
	
		for(CourseRecord courseRec: courseRecords) {
			if (courseRec.getCourseId().equals(courseId))
				offerredCourses.add(courseRec);
				
		}
		
		if (offerredCourses.size()==0)
			throw new NotAvailableException("No offered course found for id="+courseId);
		
		return offerredCourses;
	}
	
	public ArrayList<CourseRecord> getOfferedCoursesWithTitle(String courseTitle) throws NotAvailableException {
		ArrayList<Course> matchedCourses = getCourses(courseTitle);		
		
		ArrayList<CourseRecord> offerredCourses = new ArrayList<CourseRecord>();
		
	
		for(Course course: matchedCourses) {
			try {
				offerredCourses.addAll(getOfferedCourses(course.getId()));
			} catch (NotAvailableException e) {
			}
		}
		
		if (offerredCourses.size()==0)
			throw new NotAvailableException(String.format("No offered course found with title '%s'", courseTitle));
		
		return offerredCourses;
	}
	
	public ArrayList<CourseRecord> getRegisteredCourses(User user) throws NotAvailableException {
		ArrayList<CourseRecord> regCourses = new ArrayList<CourseRecord>();
		String role = user.getRole().toLowerCase();
		if (role.equals("student")) {
			for(CourseRecord course: courseRecords) {
				ArrayList<String> ids = course.getRegisteredStudentIds();
				if (ids != null && ids.size()>0 && ids.contains(user.getId()))
					regCourses.add(course);
			}
			
			if (regCourses.size()==0)
				throw new NotAvailableException("Student has not registered for any course.");
			
		}
		else if(role.equals("instructor")) {
			for(CourseRecord course: courseRecords) {
				if (course.getInstructorId().equals(user.getId()))
					regCourses.add(course);
			}
			if (regCourses.size()==0)
				throw new NotAvailableException("The instructor is not conducting any course.");
		}
		
		
		
		return regCourses;
	}
	
	public ArrayList<CourseRecord> getRegisteredCourses(String userId) throws NotAvailableException, InvalidUserException {
		User user = findUser(userId);
		return getRegisteredCourses(user);
	}

	//--------- Find specific item ---------------------------------------//
	//---------------------------------------------------------------------//
	public CourseRecord findCourseRecord(String recordId) throws InvalidCourseException {
		for(CourseRecord session: courseRecords) {
			if (session.getId().equals(recordId))
				return session;
		}

		throw new InvalidCourseException(recordId);
	}
	
	public User findUser(String userId) throws InvalidUserException {
		for (User user: users) {
			if (user.getId().equals(userId))
				return user;
		}
		
		throw new InvalidUserException(userId);		
	}
	
	public Course findCourse(String courseId) throws InvalidCourseException {
		for (Course course: courses) {
			if (course.getId().equals(courseId))
				return course;
		}
		
		throw new InvalidCourseException(courseId);		
	}

	//--------- Online Course Conduct related methods----------------------------------------//
	//---------------------------------------------------------------------//
	public void registerStudent(Student st,CourseRecord record) {
		st.registerFor(record.getId());		
	}
	
	public void registerStudent(Student st,String courseRecordId) throws InvalidCourseException {
		CourseRecord record = findCourseRecord(courseRecordId);
		registerStudent(st, record);		
	}

	public void attendClass(Student st, String courseRecordId) throws InvalidCourseException {
		CourseRecord record = findCourseRecord(courseRecordId);
		st.attendClass(courseRecordId);
	}

	public void completeCourse(Student st, String courseRecordId) throws InvalidCourseException {
		CourseRecord record = findCourseRecord(courseRecordId);
		st.completeCourse(courseRecordId);
	}
	
	public boolean isSuperAdmin(String id) {
		return id.equals("123");

	}

}
