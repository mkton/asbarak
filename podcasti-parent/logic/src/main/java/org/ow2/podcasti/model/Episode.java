package org.ow2.podcasti.model;

import java.io.Serializable;
import java.net.URI;

public class Episode implements Serializable {

	private static final long serialVersionUID = 6740967231982126943L;
	
	public Integer feedId;
	public String title;
	public URI location;
	public Integer id;
	
	public Episode(String title, URI location, Integer feedId, Integer id){
		this.location = location;
		this.title = title;
		this.feedId = feedId;
		this.id = id;
	}
	
	public String toString(){
		return title + " from feed n°" + feedId;
	}
	
}
