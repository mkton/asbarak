package org.ow2.asbarak.os.apps.gstreamer;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * At now, this is only for test
 * @author mkton
 *
 */
public class GStreamerMain {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		
		
		GStreamerService service = new GStreamerImpl();
		
		URI uri2 = new URI("http://mp3.live.tv-radio.com/franceinter/all/franceinterhautdebit.mp3");
		
		service.play(uri2);
		
		Thread.sleep(2000);
		
		service.pause();
		
		Thread.sleep(2000);
		
		service.resume();
		
		Thread.sleep(1000);
		
		// we should restart here
		
		service.play(uri2);
		
		Thread.sleep(3000);
		
		service.stop();

	}

}
