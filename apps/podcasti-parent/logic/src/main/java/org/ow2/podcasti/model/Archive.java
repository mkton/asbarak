package org.ow2.podcasti.model;

import java.io.Serializable;

public class Archive implements Serializable {
	
	private static final long serialVersionUID = 4833977952436858568L;
	
	public Episode episode;
	public StoredDocument document;	
	
	public Archive (Episode episode) {
		this.episode = episode;
	}
}
