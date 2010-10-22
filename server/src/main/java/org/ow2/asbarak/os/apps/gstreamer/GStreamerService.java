package org.ow2.asbarak.os.apps.gstreamer;

import java.net.URI;

import org.osoa.sca.annotations.Service;

@Service
public interface GStreamerService {

	public void play(URI location);
	
	public void resume();
	
	public void pause();
	
	public void stop();
	
}
