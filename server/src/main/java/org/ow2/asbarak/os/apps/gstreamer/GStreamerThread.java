package org.ow2.asbarak.os.apps.gstreamer;

import org.gstreamer.Gst;

public class GStreamerThread extends Thread {
	
	@Override
	public void run(){
		Gst.main();
	}

	public void stop_() {
		Gst.quit();
	}

}
