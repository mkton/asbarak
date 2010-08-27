package org.ow2.podcasti.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashSet;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;

import com.sun.syndication.io.FeedException;

@Scope("COMPOSITE")
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

		Feed newFeed = new Feed(this.createId(feedCurrentMaxId), address);
		this.model.feeds.add(newFeed);
		db.write(this.model);
	}

	public void removeFeed(Integer feedId) throws UnavailableElementException {

		Feed toRemove = this._getFeed(feedId);

		model.feeds.remove(toRemove);
		db.write(this.model);
	}

	private Integer createId(Integer currentMaxId) {
		// we may need to initialize it
		if (currentMaxId == null) {
			int i = 0;

			for (Feed feed : model.feeds) {
				if (feed.id > i)
					i = feed.id;
			}

			currentMaxId = i;
		}

		return ++currentMaxId;
	}

	public void archiveEpisode(Episode episode, URI location) {
		Archive archive = new Archive(
				episode, 
				location, 
				this.createId(archiveCurrentMaxId));
		
		model.archives.add(archive);
	}

	public void removeArchive(Integer archiveId) {
		for (Archive archive : model.archives){
			if (archive.id.equals(archiveId)){ 
				model.archives.remove(archive);
				return;
			}
		}
	}

	public HashSet<Archive> getArchives(Integer feedId) {
		HashSet<Archive> ret = new HashSet<Archive>();
		for (Archive archive : model.archives){
			if (archive.episode.feedId.equals(feedId)){ 
				ret.add(archive);
			}
		}
		
		return ret;
	}
	
}
