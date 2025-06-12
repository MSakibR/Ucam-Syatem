package uap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Course implements Serializable{
	private String id, title,latestSessionId;
	ArrayList<String> topics;
	
	
	public Course(String title) {
		super();
		this.title = title;
		topics = new ArrayList<>();
		this.id = "c-"+String. format("%04d", new Random().nextInt(10000));		
	}

	public Course(String title, ArrayList<String> topics) {
		super();
		this.title = title;
		this.id = "c-"+String. format("%04d", new Random().nextInt(10000));	
	}

	public String getLatestSessionId() {
		return latestSessionId;
	}

	public void setLatestSessionId(String latestSessionId) {
		this.latestSessionId = latestSessionId;
	}

	public ArrayList<String> getTopics() {
		return topics;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void addTopics(String... topics) {
		for (String topic: topics)
			this.topics.add(topic);
	}
	
	public int noOfClasses() {
		return topics.size();
	}


}
