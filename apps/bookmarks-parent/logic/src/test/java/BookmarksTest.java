import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.ow2.asbarak.Asbarak;
import org.ow2.asbarak.apps.bookmarks.BookmarksService;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.tinfi.SecuritySubjectManager;

public class BookmarksTest {

	BookmarksService ui;
	
	
	public BookmarksTest() throws Exception{

		FraSCAti frascati = FraSCAti.newFraSCAti();
				
	    Asbarak asbarak = new Asbarak(frascati);

	    asbarak.getDeployer().deploy("bookmarks");
	
	    Component bm = frascati.getComposite("bookmarks");	    
	    this.ui = frascati.getService(bm, "bookmarks-service", BookmarksService.class);
	    
	    Component aB = frascati.getComposite("asbarak");
	    this.
	    
	}
	
	
	@Test
	public void generalOperations(){
		
		// we try to use it without authentication
		HashSet<String> bm = this.ui.getBookmarks();
		
		// before exception management
		assertTrue(bm == null);
		
		// now we log in
		subject
		SecuritySubjectManager.get().setSecuritySubject(subject);
		
		// and try again
		
		
		// we log someone else
		
		
		// and assert we are not dealing with the same data
		
		
	}
	
	
}
