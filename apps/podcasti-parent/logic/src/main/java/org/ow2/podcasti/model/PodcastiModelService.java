package org.ow2.podcasti.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashSet;

import com.sun.syndication.io.FeedException;

public interface PodcastiModelService {

	public HashSet<Feed> getFeeds();
	
	public URI getFeed(Integer feedId) throws UnavailableElementException;
	
	public void addFeed(URI address) throws IllegalArgumentException, MalformedURLException, FeedException, IOException;
	
	public void removeFeed(Integer feedId) throws UnavailableElementException;
	
	public void archiveEpisode(Episode episode, URI location);
	
	public void removeArchive(Integer archiveId);
	
	public HashSet<Archive> getArchives(Integer feedId); 
}
