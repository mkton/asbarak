package org.ow2.podcasti.archive;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.osoa.sca.annotations.Property;
import org.ow2.podcasti.model.Archive;
import org.ow2.podcasti.model.Episode;

public class PodcastiArchiveImpl implements PodcastiArchiveService {

	@Property(name="archives-path")
	String archivesPath;
	
	public Archive archivePodcast(Episode episode) throws URISyntaxException {
		
		URI destination = 
			PodcastiArchiveImpl.createDestination(archivesPath, episode);
		
		// we start the copy thread
		(new Archiver(episode.location, destination)).run();
		
		return null;
	}

	public HashSet<Archive> getArchives(Integer feedId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static URI createDestination(String rootPath, Episode ep) 
		throws URISyntaxException{
		
		String strDate = (new SimpleDateFormat("yyyy-MM-dd")).format(ep.date);				
		String path = ep.location.getPath();
		
		// we may need to create the directory
		File dir = new File(rootPath + File.separator + ep.feedId);
		if (!dir.exists())
			dir.mkdirs();
		
		String extension = path.substring(path.lastIndexOf("."));
			
		File file = new File(rootPath + File.separator + 
				ep.feedId + File.separator + strDate + extension);
		
		return file.toURI();
	}

}
