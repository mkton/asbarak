package org.ow2.asbarak.os.apps.gstreamer;

import java.net.URI;

import org.gstreamer.Gst;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin;
import org.osoa.sca.annotations.Scope;

@Scope("Composite")
public class GStreamerImpl implements GStreamerService {

	private GStreamerThread thread;
	private PlayBin playbin;
	
	public GStreamerImpl(){
		Gst.init();
		this.playbin = new PlayBin("AudioPlayer");
	}
	
	public void play(URI location) {
		
		if (this.playbin.getState().equals(State.PLAYING))
			this.stop();
		
		this.playbin.setURI(location);
		this.thread = new GStreamerThread();		
		this.playbin.setState(State.PLAYING);
		this.thread.start();
	}
	
	public void resume(){
		this.playbin.setState(State.PLAYING);
	}

	public void pause() {
		this.playbin.setState(State.PAUSED);
	}

	public void stop() {
		if (this.thread != null) {
			playbin.setState(State.NULL);
			this.thread.stop_();
			this.thread = null;
		}
	}

}
