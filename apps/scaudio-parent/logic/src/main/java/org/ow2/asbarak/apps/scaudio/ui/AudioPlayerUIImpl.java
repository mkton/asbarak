package org.ow2.asbarak.apps.scaudio.ui;

import java.net.URI;
import java.net.URISyntaxException;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.apps.scaudio.alsamixer.AlsamixerManager;
import org.ow2.asbarak.apps.scaudio.vlc.VlcManager;

@Scope("COMPOSITE")
public class AudioPlayerUIImpl implements AudioPlayerUIService {
	
	@Reference(name="vlc-manager-reference")
	public VlcManager vlcManager;
	
	@Reference(name="alsamixer-manager-reference")
	public AlsamixerManager alsamixerManager;
	
	public static Integer defaultGain = 10;

	public void decreaseSoundLevel() {
		this.alsamixerManager.reduceSoundLevel(AudioPlayerUIImpl.defaultGain);		
	}

	public int getSoundLevel() {
		return alsamixerManager.getSoundLevel();
	}

	public void increaseSoundLevel() {
		this.alsamixerManager.increaseSoundLevel(AudioPlayerUIImpl.defaultGain);
	}

	public void playAudioLocation(URI location) throws URISyntaxException {
		vlcManager.play(location);
	}
	
}
