package org.ow2.asbarak.apps.scaudio.ui;

import java.net.URI;
import java.net.URISyntaxException;

import org.osoa.sca.annotations.Service;

@Service
public interface AudioPlayerUIService {
	
	// audio player features provided by interface
	
	public void playAudioLocation(URI location) throws URISyntaxException; 
		
	public void increaseSoundLevel();
	
	public void decreaseSoundLevel();
	
	public int getSoundLevel();
	
}
