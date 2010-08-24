package org.ow2.podcasti.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Scope;

import com.sun.syndication.io.FeedException;

@Scope("COMPOSITE")
public class PodcastiDBImpl implements PodcastiDBService {

	@Property(name = "db-path")
	String dbPath;

	public HashSet<Feed> feeds;

	private Integer currentMaxId;
/*
	public HashMap<Integer, String> getFeeds() {

		HashMap<Integer, String> ret = new HashMap<Integer, String>();

		for (Feed feed : feeds) {
			ret.put(feed.id, feed.name);
		}

		return ret;
	}*/
	
	public HashSet<Feed> getFeeds(){
		return this.feeds;
	}

	public URI getFeed(Integer feedId) throws UnavailableElementException {
		return _getFeed(feedId).address;
	}

	private Feed _getFeed(Integer feedId) throws UnavailableElementException {
		for (Feed feed : feeds) {
			if (feed.id.equals(feedId))
				return feed;
		}

		throw new UnavailableElementException("Unknown feed with id " + feedId);
	}

	public void addFeed(URI address) throws IllegalArgumentException,
			MalformedURLException, FeedException, IOException {

		Feed newFeed = new Feed(this.createId(), address);
		this.feeds.add(newFeed);
		this.write();
	}

	public void removeFeed(Integer feedId) throws UnavailableElementException {

		Feed toRemove = this._getFeed(feedId);

		feeds.remove(toRemove);
		this.write();
	}

	@Init
	public void read() throws IOException, ClassNotFoundException {

		// read file, de-serialize object
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(this.dbPath));
		} catch (FileNotFoundException e) {
			this.feeds = new HashSet<Feed>();
			return;
		}

		ObjectInputStream ois = new ObjectInputStream(fis);

		this.feeds = (HashSet<Feed>) ois.readObject();

	}

	private void write() {

		// serialize object
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(dbPath));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.feeds);
			oos.flush();
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Integer createId() {
		// we may need to initialize it
		if (currentMaxId == null) {
			int i = 0;

			for (Feed feed : feeds) {
				if (feed.id > i)
					i = feed.id;
			}

			currentMaxId = i;
		}

		return ++currentMaxId;
	}

}
