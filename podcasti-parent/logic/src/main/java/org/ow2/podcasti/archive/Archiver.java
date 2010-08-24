package org.ow2.podcasti.archive;

import java.net.URI;

public class Archiver extends Thread {

	private URI location;
	private URI destination;

	public Archiver(URI location, URI destination){
		this.location = location;
		this.destination = destination;		
	}
	
	public void run(){
		
	}
}
