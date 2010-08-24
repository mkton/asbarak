import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.HashSet;

import org.junit.After;
import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.util.FrascatiException;
import org.ow2.podcasti.model.Episode;
import org.ow2.podcasti.model.Feed;
import org.ow2.podcasti.ui.PodcastiUIService;

public class Podcastest {

	// we are using heterogeneous podcast feeds
	public static String testFeed1 = "http://feeds2.feedburner.com/soulclap";
	public static String testFeed3 = "http://radiofrance-podcast.net/podcast09/rss_14864.xml";
	public static String testFeed2 = "http://jazzandbeyond.podbean.com/feed/";
	
	PodcastiUIService ui;
	
	
	public Podcastest() throws FrascatiException{
				
		FraSCAti frascati = FraSCAti.newFraSCAti();

	    Component composite = frascati.getComposite("podcasti-test");
	    this.ui = frascati.getService(composite, "podcasti-ui", PodcastiUIService.class);
	    
		// we set DB location property for testing	    
	    // TODO
	    
	    // we assert starting with an empty set
	    this.removeAll();
	}
	
	@After
	public void removeAll(){
		
		HashSet<Integer> ids = new HashSet<Integer>();
		
		for (Feed feed : ui.getFeeds()){
			ids.add(feed.id);
		}
		
		for (Integer i : ids){
			ui.removeFeed(i);
		}
		
		assertTrue(ui.getFeeds().size() == 0);
	}
	
	@Test
	public void testDB(){
		
		// then we try to add new feeds
		try {
			ui.addFeed(new URI(testFeed1));
			ui.addFeed(new URI(testFeed2));
			ui.addFeed(new URI(testFeed3));
		} catch (Exception e){
			fail(e.getMessage());
		}
		
		// we check if it is done
		assertTrue(ui.getFeeds().size() == 3);
		
		// then we remove one, and check we have three
		Integer id = ((Feed) ui.getFeeds().iterator().next()).id;
		ui.removeFeed(id);
		
		assertTrue(ui.getFeeds().size() == 2);

	}
	
	@Test
	public void testUI(){
		try {
			// verify getFeeds			
			ui.addFeed(new URI(testFeed1));
			assertTrue(ui.getFeeds().size() == 1);
			
			ui.addFeed(new URI(testFeed2));
			ui.addFeed(new URI(testFeed3));
			
			assertTrue(ui.getFeeds().size() == 3);			
			
			// we try to add an already followed feed
			assertFalse(ui.addFeed(new URI(testFeed1)));
			
			// verify get3last, assert we get 3 episode for each feed
			HashSet<Episode> last;
			for (Feed feed: ui.getFeeds()){
				last = ui.get3Last(feed.id);
				assertTrue(last.size() == 3);
				for (Episode ep : last){
					assertTrue(ep.location != null);
					assertTrue(ep.title != null);
					assertTrue(ep.feedId != null);
				}
			}
			
		} catch (Exception e){
			fail(e.getMessage());
		} 
	}
	
}
