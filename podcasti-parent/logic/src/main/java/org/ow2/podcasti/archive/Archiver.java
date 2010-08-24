package org.ow2.podcasti.archive;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class Archiver extends Thread {

	private URI location;
	private URI destination;	

	public Archiver(URI location, URI destination){
		this.location = location;
		this.destination = destination;		
	}
	
	public void run(){
		// start copy
		try {
			URL from = this.location.toURL();
			File to = new File(this.destination);
			FileUtils.copyURLToFile(from, to);
		} catch (IOException e) {
			// TODO manage copy errors.
			e.printStackTrace();
		}
	}
	
}
