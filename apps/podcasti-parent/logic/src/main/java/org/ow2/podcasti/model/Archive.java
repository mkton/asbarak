package org.ow2.podcasti.model;

import java.io.Serializable;
import java.net.URI;

public class Archive implements Serializable {
	
	private static final long serialVersionUID = 4833977952436858568L;
	
	public Integer id;
	public Episode episode;
	public URI location;
	
	public Archive (Episode episode, URI location, Integer archiveId) {
		this.episode = episode;
		this.location = location;		
		this.id = archiveId;
	}
}
