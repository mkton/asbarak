package org.ow2.podcasti.model;

import java.io.IOException;

import org.osoa.sca.annotations.Service;

@Service
public interface PodcastiDBService {

	public PodcastiModel read() throws IOException, ClassNotFoundException;
	
	public void write(PodcastiModel model);
	
}
