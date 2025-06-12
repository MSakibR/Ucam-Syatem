package uap;


import java.io.Serializable;
import java.util.Random;

public abstract class User implements Serializable{
	private String name, id, role; // possible roles are admin, instructor, student
	private  int age;
	
	
	public User(String name, int age, String role) {
		super();
		this.name = name;
		this.age = age;
		this.role = role;
		
		String tId = String. format("%04d", new Random().nextInt(10000));
		
		switch(role.toLowerCase()) {
		case "admin":
			this.id = "a-"+tId;
			break;
		case "instructor":
			this.id = "i-"+tId;
			break;
		case "student":
			this.id = "s-"+tId;
			break;
		}
	}	
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public String getRole() {
		return role;
	}

	public abstract void registerFor(String courseRecordId);

	@Override
	public String toString() {
			return String.format("Role:%s- id:%s, name:%s, age:%d", role,id, name, age);
	}

}
