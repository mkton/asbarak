package org.ow2.asbarak.audio;

import java.net.URI;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class AudioPlayerImpl implements AudioPlayerItfService {
	
	@Reference(name="vlc-manager-service")
	public VlcManager vlcManager;
	
	@Reference(name="alsamixer-manager-service")
	public AlsamixerManager alsamixerManager;
	
	public static Integer defaultGain = 10;

	public void decreaseSoundLevel() {
		this.alsamixerManager.reduceSoundLevel(AudioPlayerImpl.defaultGain);		
	}

	public int getSoundLevel() {
		return alsamixerManager.getSoundLevel();
	}

	public void increaseSoundLevel() {
		this.alsamixerManager.increaseSoundLevel(AudioPlayerImpl.defaultGain);
	}

	public void playAudioLocation(URI location) {
		vlcManager.play(location);
	}
	
}
