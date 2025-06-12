package uap;

public class InvalidUserException extends Exception {

	public InvalidUserException(String id) {
		super("User with id "+ id +" is not a valid user.");
	}
}
