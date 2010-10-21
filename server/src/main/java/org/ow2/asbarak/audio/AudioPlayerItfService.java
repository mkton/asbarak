package org.ow2.asbarak.audio;

import java.net.URI;

import org.osoa.sca.annotations.Service;

@Service
public interface AudioPlayerItfService {
	
	// audio player features provided by interface
	
	public void playAudioLocation(URI location); 
		
	public void increaseSoundLevel();
	
	public void decreaseSoundLevel();
	
	public int getSoundLevel();
	
}
