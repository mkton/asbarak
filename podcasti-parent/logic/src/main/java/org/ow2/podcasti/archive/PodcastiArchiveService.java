package org.ow2.podcasti.archive;

import java.net.URISyntaxException;
import java.util.HashSet;

import org.osoa.sca.annotations.Service;
import org.ow2.podcasti.model.Archive;
import org.ow2.podcasti.model.Episode;

@Service
public interface PodcastiArchiveService {

	public Archive archivePodcast(Episode episode) throws URISyntaxException;
	
	public HashSet<Archive> getArchives(Integer feedId);
	
}
