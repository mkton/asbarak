package org.ow2.podcasti.model;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Feed implements Serializable {
	
	/**
	 * Generated serial ID 
	 */
	private static final long serialVersionUID = 6534762930210362276L;
	
	public String name;
	public URI address;
	public Integer id;
	
	public Feed(Integer id, URI address) throws IllegalArgumentException, MalformedURLException, FeedException, IOException{
		this.address = address;
		this.id = id;
		
		SyndFeedInput input = new SyndFeedInput();		
		SyndFeed feed = input.build(new XmlReader(address.toURL()));
		
		this.name = feed.getTitle();
	}

}
