package org.ow2.podcasti.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

public class Episode implements Serializable {

	private static final long serialVersionUID = 6740967231982126943L;
	
	public Integer feedId;
	public String title;
	public URI location;
	public Integer id;
	public Date date;
	public String desc;
	
	public Episode(String title, Date date, URI location, String desc, 
			Integer feedId, Integer id){
		this.location = location;
		this.title = title;
		this.feedId = feedId;
		this.id = id;
		this.date = date;
		this.desc = desc;
	}
	
	public String toString(){
		return title + " from feed n°" + feedId;
	}
	
}
