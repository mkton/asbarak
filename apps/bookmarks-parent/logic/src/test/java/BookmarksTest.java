import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import javax.security.auth.Subject;

import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.ow2.asbarak.Asbarak;
import org.ow2.asbarak.apps.bookmarks.BookmarksService;
import org.ow2.asbarak.auth.AsbarakAuthenticationService;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.tinfi.SecuritySubjectManager;

public class BookmarksTest {

	BookmarksService ui;
	AsbarakAuthenticationService auth;
	
	public BookmarksTest() throws Exception{

		FraSCAti frascati = FraSCAti.newFraSCAti();
				
	    Asbarak asbarak = new Asbarak(frascati);

	    asbarak.getDeployer().deploy("bookmarks");
	
	    // we retrieve the logic service
	    Component bm = frascati.getComposite("bookmarks");	    
	    this.ui = frascati.getService(bm, "bookmarks-service", BookmarksService.class);
	    
	    // and the authentication service
	    Component aB = frascati.getComposite("asbarak");
	    this.auth = frascati.getService(aB, "authentication-service", AsbarakAuthenticationService.class);
	    
	}
	
	
	@Test
	public void generalOperations(){
		
		// we try to use it without authentication
		HashSet<String> bm = this.ui.getBookmarks();
		
		// before exception management
		assertTrue(bm == null);
		
		// now we log in
		Subject subject = auth.authenticate("pim", "pwd");
		SecuritySubjectManager.get().setSecuritySubject(subject);
		
		// and try again
		bm = this.ui.getBookmarks();
		
		// it should be ok at now
		assertTrue(bm != null);
		
		// we log someone else
		
		
		// and assert we are not dealing with the same data
		//HashSet<String> bm2 = this.ui.getBookmarks();
		//assertTrue(!bm.equals(bm2));
		
	}
}
