package org.ow2.asbarak.apps.scaudio.gstreamer;

import java.net.URI;

import org.gstreamer.Gst;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin;

public class GstreamerManager {
	
	MainThread thread;
	
	public void play(URI location){
		
		Gst.init("AudioPlayer", null);
		
		
		PlayBin pb = new PlayBin("blabla", location);
		
		pb.setState(State.PLAYING);
		
		Gst.main();
	
	}
	
}