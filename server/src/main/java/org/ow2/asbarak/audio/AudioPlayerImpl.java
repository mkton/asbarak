package org.ow2.asbarak.audio;

import java.net.URI;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.os.apps.AlsamixerManager;
import org.ow2.asbarak.os.apps.gstreamer.GStreamerService;

@Scope("COMPOSITE")
public class AudioPlayerImpl implements AudioPlayerItfService {
	
	@Reference(name="gstreamer-service")
	public GStreamerService gstreamer;
	
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
		gstreamer.play(location);
	}
	
	public void pause() {
		gstreamer.pause();
	}

	public void resume() {
		gstreamer.resume();
	}
	
	public void stop() {
		gstreamer.stop();
	}
	
}
