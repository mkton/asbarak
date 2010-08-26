package org.ow2.podcasti.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashSet;

import org.osoa.sca.annotations.Service;

import com.sun.syndication.io.FeedException;

@Service
public interface PodcastiDBService {

	public HashSet<Feed> getFeeds();
	
	public URI getFeed(Integer feedId) throws UnavailableElementException;
	
	public void addFeed(URI address) throws IllegalArgumentException, MalformedURLException, FeedException, IOException;
	
	public void removeFeed(Integer feedId) throws UnavailableElementException;
	
}
