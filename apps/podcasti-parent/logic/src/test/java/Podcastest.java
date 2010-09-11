import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.After;
import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.tinfi.control.content.SCAContentController;
import org.ow2.frascati.tinfi.control.property.SCAPropertyController;
import org.ow2.frascati.util.FrascatiException;
import org.ow2.podcasti.archive.PodcastiArchiveImpl;
import org.ow2.podcasti.model.Archive;
import org.ow2.podcasti.model.Episode;
import org.ow2.podcasti.model.Feed;
import org.ow2.podcasti.ui.PodcastiUIService;

public class Podcastest {

	// we are using heterogeneous podcast feeds
	public static String testFeed1 = "http://feeds2.feedburner.com/soulclap";
	public static String testFeed3 = "http://radiofrance-podcast.net/podcast09/rss_14864.xml";
	public static String testFeed2 = "http://jazzandbeyond.podbean.com/feed/";
	
	// path for archives extract from the property value in the composite
	public String archivesPath;
	
	// path to a small file for archive testing
	public static String filePath = "http://www.google.fr/intl/en_com/images/srpr/logo1w.png";
	
	PodcastiUIService ui;
	
	
	public Podcastest() throws FrascatiException, SecurityException, NoSuchFieldException, NoSuchInterfaceException{
				
		FraSCAti frascati = FraSCAti.newFraSCAti();
		
	    Component composite = frascati.getComposite("podcasti-test");
	    this.ui = frascati.getService(composite, "podcasti-ui", PodcastiUIService.class);
	    
		// we set DB location property for testing
	    SCAPropertyController c = (SCAPropertyController) composite.getFcInterface(SCAPropertyController.NAME);
	    this.archivesPath = (String) c.getValue("archive-path-composite");
	    	    
	    System.out.println(archivesPath);
	   // SCAContentController a = (SCAContentController) composite.getFcInterface(SCAContentController.NAME);
	    for (Object o : composite.getFcInterfaces()){
	    	System.out.println(o.toString());
	    }
	    
	    // we assert starting with an empty set
	    this.removeAll();
	}
	
	@After
	public void removeAll(){
		
		HashSet<Integer> ids = new HashSet<Integer>();
		
		// we collect ids
		for (Feed feed : ui.getFeeds()){
			ids.add(feed.id);
		}
		
		// then we remove associated archives
		for (Integer i : ids){
			HashSet<Archive> archs = ui.getArchives(i);
			for (Archive arch : archs){
				ui.removeArchive(arch.id);
			}
		}
		
		// and finally we remove the feeds
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
			
			// verify get3last, assert we get episodes for each feed
			// maybe not 3 because of mixed feeds (text and podcast)
			HashSet<Episode> last;
			for (Feed feed: ui.getFeeds()){
				last = ui.get3Last(feed.id);
				assertTrue(last.size() <= 3);
				for (Episode ep : last){
					assertTrue(ep.location != null);
					assertTrue(ep.title != null);
					assertTrue(ep.feedId != null);
				}
			}
			
			// now we ensure that update is ok
			// first we modify accessibility of the required fields
			
			
			
			// at first we change the update period
			
			// now we save the last update time
			
			// we call the get3last and we ensure that update has been done
			
		} catch (Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		} 
	}
	
	@Test
	public void archiveTest(){
		try {
			ui.addFeed(new URI(testFeed3));
			Integer feedId = ((Feed) ui.getFeeds().iterator().next()).id;
			Iterator<Episode> it = ui.get3Last(feedId).iterator();
			Episode ep = it.next();		
			ui.archive(feedId, ep.id);
			
			// we only test that the file exist, copying is supported by 
			// Apache common-io, and we really trust those guys.
			// we sleep 2 seconds in order to let time for file creation
			
			URI destination = 
				PodcastiArchiveImpl.createDestination(archivesPath, ep);
			
			Thread.sleep(2000);
			assertTrue((new File(destination)).exists());
			
			// we assert the archive has been save in the database
			
			HashSet<Archive> archives = ui.getArchives(feedId);
			assertTrue(archives.size() == 1);
			Archive archive = archives.iterator().next();
			assertTrue(archive.episode.title.equals(ep.title));
			
			// then we try to access to the archive
			assertTrue((new File(archive.location)).exists());
			
			// here we try to add a new one
			Episode nextEp = it.next();
			ui.archive(feedId, nextEp.id);
			archives = ui.getArchives(feedId);
			assertTrue(archives.size() == 2);
			
			// finally we try to remove the archive
			ui.removeArchive(archive.id);
			archives = ui.getArchives(feedId);
			// assert there is only one more archive, with another id
			assertTrue( archives.size() == 1 );
			assertTrue( (archives.iterator().next()).id == archive.id);
			
			// and we assert that file has been delete
			// TODO, at now we do not remove the file
			//assertFalse((new File(archive.location)).exists());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	
	
}
