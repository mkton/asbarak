package org.ow2.podcasti.ui;

import java.net.URI;
import java.util.HashSet;

import org.osoa.sca.annotations.Service;
import org.ow2.podcasti.model.Episode;
import org.ow2.podcasti.model.Feed;

@Service
public interface PodcastiUIService {
	
	//public HashMap<Integer, String> getFeeds();
	
	public HashSet<Feed> getFeeds();
	
	public boolean addFeed(URI address);
	
	public void removeFeed(Integer feedId);
	
	public HashSet<Episode> get3Last(Integer feedId);	
	
	public void playOnServer(URI podcastLocation);
}
