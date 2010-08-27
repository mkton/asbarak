import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.util.HashSet;

import org.junit.After;
import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.util.FrascatiException;
import org.ow2.podcasti.archive.PodcastiArchiveImpl;
import org.ow2.podcasti.model.Episode;
import org.ow2.podcasti.model.Feed;
import org.ow2.podcasti.ui.PodcastiUIService;

public class Podcastest {

	// we are using heterogeneous podcast feeds
	public static String testFeed1 = "http://feeds2.feedburner.com/soulclap";
	public static String testFeed3 = "http://radiofrance-podcast.net/podcast09/rss_14864.xml";
	public static String testFeed2 = "http://jazzandbeyond.podbean.com/feed/";
	
	// path for archives
	public static String archivesPath = "/tmp/podcasti-archives-test/";
	
	// path to a small file for archive testing
	public static String filePath = "http://www.google.fr/intl/en_com/images/srpr/logo1w.png";
	
	PodcastiUIService ui;
	
	
	public Podcastest() throws FrascatiException, SecurityException, NoSuchFieldException{
				
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
	
	@Test
	public void archiveTest(){
		try {
			ui.addFeed(new URI(testFeed3));
			Integer feedId = ((Feed) ui.getFeeds().iterator().next()).id;
			Episode ep = ui.get3Last(feedId).iterator().next();
			ui.archive(feedId, ep.id);
			
			// we only test that the file exist, copying is supported by 
			// Apache common-io, and we really trust those guys.			
			
			URI destination = 
				PodcastiArchiveImpl.createDestination(archivesPath, ep);
			
			assertTrue((new File(destination)).exists());
			
			// we assert the archive has been save in the database
			
			
			
			// then we try to access to the archive
			
			
			// impossible because we cannot use reflection 
			// across FraSCAti services
			/*
			// we try to archive an episode			
			// we cannot wait until it has been downloaded,  
			// so we fake the url for a smaller file...			
			ui.addFeed(new URI(testFeed3));
			Integer feedId = ((Feed) ui.getFeeds().iterator().next()).id;
			HashSet<Episode> episodes = ui.get3Last(feedId);
			Episode ep = episodes.iterator().next();
			ep.location=new URI(Podcastest.filePath);
			
			// we make this fields accessible for faking it
			Field field = PodcastiCoreImpl.class.getDeclaredField("episodes");
			field.setAccessible(true);		
			ui.getFeeds();
			HashMap<Integer, LinkedHashSet<Episode>> episodes_mock = 
				new HashMap<Integer, LinkedHashSet<Episode>>();			
			episodes_mock.put(feedId, (LinkedHashSet) episodes);
			field.set(ui, episodes_mock);
			
			ui.archive(feedId, ep.id);
					
			// we assert the file is available and the same as we expect

			URI destination = 
				PodcastiArchiveImpl.createDestination(archivesPath, ep);
			
			File expected = new File(System.getProperty("java.io.tmpdir") 
					+ File.separator 
					+ "podcastest.tmp");
			
			FileUtils.copyURLToFile(
					(new URI(Podcastest.filePath)).toURL(), 
					expected);

			assertTrue(
					(new File(destination)).getTotalSpace() ==
						expected.getTotalSpace()
				);
			*/

		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
	
}
