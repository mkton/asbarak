package org.ow2.podcasti.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashSet;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

import com.sun.syndication.io.FeedException;

public class PodcastiModelImpl implements PodcastiModelService {

	private PodcastiModel model;

	private Integer feedCurrentMaxId;
	private Integer archiveCurrentMaxId;
	
	@Reference(name="podcasti-db-reference")
	PodcastiDBService db;
	
	@Init
	public void init() throws IOException, ClassNotFoundException{
		this.model = db.read();
	}
	
	public HashSet<Feed> getFeeds(){
		return this.model.feeds;
	}

	public URI getFeed(Integer feedId) throws UnavailableElementException {
		return _getFeed(feedId).address;
	}

	private Feed _getFeed(Integer feedId) throws UnavailableElementException {
		for (Feed feed : model.feeds) {
			if (feed.id.equals(feedId))
				return feed;
		}

		throw new UnavailableElementException("Unknown feed with id " + feedId);
	}

	public void addFeed(URI address) throws IllegalArgumentException,
			MalformedURLException, FeedException, IOException {

		Feed newFeed = new Feed(this.createId(), address);
		this.model.feeds.add(newFeed);
		db.write(this.model);
	}

	public void removeFeed(Integer feedId) throws UnavailableElementException {

		Feed toRemove = this._getFeed(feedId);

		model.feeds.remove(toRemove);
		db.write(this.model);
	}

	private Integer createId() {
		// we may need to initialize it
		if (feedCurrentMaxId == null) {
			int i = 0;

			for (Feed feed : model.feeds) {
				if (feed.id > i)
					i = feed.id;
			}

			feedCurrentMaxId = i;
		}

		return ++feedCurrentMaxId;
	}

	public void archiveEpisode(Episode episode, URI location) {
		// TODO Auto-generated method stub
		
	}

	public void removeArchive(Integer archiveId) {
		// TODO Auto-generated method stub
		
	}
	
}
