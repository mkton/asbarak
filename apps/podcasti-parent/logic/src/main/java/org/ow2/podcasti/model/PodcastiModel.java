package org.ow2.podcasti.model;

import java.io.Serializable;
import java.util.HashSet;

public class PodcastiModel implements Serializable {

	private static final long serialVersionUID = -7930988629904698663L;
	
	public HashSet<Feed> feeds;
	public HashSet<Archive> archives;
	
	public PodcastiModel() {
		this.feeds = new HashSet<Feed>();
		this.archives = new HashSet<Archive>();
	}
	
}
