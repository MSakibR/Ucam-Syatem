package uap;

public class InvalidCourseException extends Exception {

	public InvalidCourseException(String id) {
		super(String.format("Course with id:%s is not a valid course.", id));
	}

}
