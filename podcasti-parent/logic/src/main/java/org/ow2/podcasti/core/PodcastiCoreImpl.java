package org.ow2.podcasti.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;

import org.osoa.sca.annotations.Reference;
import org.ow2.podcasti.archive.PodcastiArchiveService;
import org.ow2.podcasti.model.Episode;
import org.ow2.podcasti.model.Feed;
import org.ow2.podcasti.model.PodcastiDBService;
import org.ow2.podcasti.model.UnavailableElementException;
import org.ow2.podcasti.ui.PodcastiUIService;

import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class PodcastiCoreImpl implements PodcastiUIService {

	@Reference(name="podcasti-db-reference")
	PodcastiDBService db;
	
	@Reference(name="podcasti-archives-reference")
	PodcastiArchiveService archive;
	
	//@Reference(name="audio-player")
	

	/*ublic HashMap<Integer, String> getFeeds() {
		return db.getFeeds();
	}*/
	
	// we save feed construction, in order to not rebuilt it each time
	//TODO, create a timer for update it
	private HashMap<Integer, HashSet<Episode>> episodes = 
		new HashMap<Integer, HashSet<Episode>>();

	public HashSet<Feed> getFeeds(){
		return db.getFeeds();
	}
	
	public boolean addFeed(URI address) {

		try {

			// Feed verification
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(address.toURL()));
			String title = feed.getTitle();
			
			// we assert that we don't yet follow it :
			for (Feed f: db.getFeeds())				
				if (f.name.equals(title))
					return false;
			
			// Add to database
			db.addFeed(address);

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void removeFeed(Integer feedId) {
		try {
			db.removeFeed(feedId);
		} catch (UnavailableElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashSet<Episode> get3Last(Integer feedId) {

		// first, we try to find it in the save
		HashSet<Episode> ret = episodes.get(feedId);
		if (ret != null)
			return ret;
		else
			ret = new HashSet<Episode>();

		try {
						
			URI address = db.getFeed(feedId);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed;
			feed = input.build(new XmlReader(address.toURL()));

			int i = 3;
			SyndEntry sEntry;
			Episode ep;
			URI uri;
			for (Object entry : feed.getEntries()) {
				// we iterate 3 times on entries
				if (i-- <= 0)
					break;
				
				try {
					sEntry = ((SyndEntry) entry);
					
					uri = new URI(
							((SyndEnclosure) sEntry.getEnclosures()
									.iterator().next()).getUrl());

					ep = new Episode(sEntry.getTitle(),
							sEntry.getPublishedDate(),
							uri,
							feedId,
							i);

					ret.add(ep);
					
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (UnavailableElementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// we save it for next calls
		episodes.put(feedId, ret);
		
		return ret;
	}

	public void playOnServer(URI podcastLocation) {
		// TODO Auto-generated method stub

	}

	public void archive(Integer feedId, Integer episodeId) {
		
		HashSet<Episode> eps = get3Last(feedId);
		
		for (Episode episode : eps){
			if (episode.id.equals(episodeId))
				try {
					archive.archivePodcast(episode);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
