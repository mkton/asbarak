package org.ow2.podcasti.model;

import java.io.Serializable;
import java.net.URI;

public class StoredDocument implements Serializable {

	private static final long serialVersionUID = -5427521036702250524L;
	
	public Integer id;
	public URI uri;
	
	public StoredDocument(URI location, URI destination, Integer id){
		
	}
}
