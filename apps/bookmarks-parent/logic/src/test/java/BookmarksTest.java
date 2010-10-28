import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;

import javax.naming.AuthenticationException;
import javax.security.auth.Subject;

import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.ContentController;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.objectweb.fractal.api.control.LifeCycleController;
import org.objectweb.fractal.api.control.NameController;
import org.ow2.asbarak.Asbarak;
import org.ow2.asbarak.apps.bookmarks.BookmarksService;
import org.ow2.asbarak.auth.AsbarakAuthenticationService;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.tinfi.SecuritySubjectManager;
import org.ow2.frascati.tinfi.api.control.SCAContentController;
import org.ow2.frascati.tinfi.api.control.SCAPropertyController;

public class BookmarksTest {

	BookmarksService ui;
	AsbarakAuthenticationService auth;
	Component asbarakComponent;

	
	public BookmarksTest() throws Exception{

		FraSCAti frascati = FraSCAti.newFraSCAti();
				
	    Asbarak asbarak = new Asbarak(frascati);

	    asbarak.getDeployer().deploy("bookmarks");
	
	    // we retrieve the logic service
	    Component bm = frascati.getComposite("bookmarks");	    
	    this.ui = frascati.getService(bm, "bookmarks-service", BookmarksService.class);
	    
	    // and the authentication service
	    this.asbarakComponent = frascati.getComposite("asbarak");
	    this.auth = frascati.getService(this.asbarakComponent, "authentication-service", AsbarakAuthenticationService.class);
	    	    
	}
	
	
	@Test
	public void checkPwd() {

		// we try to use a fake password
		try {
			auth.authenticate("pim", "blabla");
			fail();
		} catch (Exception e) {
			assertTrue(e.getCause().getClass().equals(AuthenticationException.class));
		}		
		
		// now we log in
		try {
			auth.authenticate("pim", "pwd");
			assertTrue(true);
		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void generalFlow() throws AuthenticationException {
		
		// we try to use it without authentication
		try {
			this.ui.getBookmarks();
			// we shouldn't be be there...
			fail();
		} catch (Exception e){
			// expected : do nothing
			assertTrue(true);
		}
		
		// now we log in
		Subject pim = auth.authenticate("pim", "pwd");
		SecuritySubjectManager.get().setSecuritySubject(pim);
		
		// and try again
		HashSet<String> bm = this.ui.getBookmarks();
		
		// it should be ok at now
		assertTrue(bm != null);
		
		this.ui.addBookmark("http://www.google.fr");
		this.ui.addBookmark("http://www.mediapart.fr");
		
		assertTrue(this.ui.getBookmarks().size() == 2);
		
		// we log someone else
		Subject pam = auth.authenticate("pam", "pwd");
		SecuritySubjectManager.get().setSecuritySubject(pam);
		
		// and assert we are not dealing with the same data
		assertTrue(this.ui.getBookmarks().size() == 0);
		
	}
	
	@Test
	public void sessionValidity() throws NoSuchInterfaceException, IllegalLifeCycleException, InterruptedException, AuthenticationException{
		
		ContentController a = (ContentController) asbarakComponent.getFcInterface("content-controller");
		
		Component authComponent = null;
		
		// we check the auth component
		for (Component c : a.getFcSubComponents()){
			NameController nc = (NameController) c.getFcInterface("name-controller");
			if (nc.getFcName().equals("authentication-manager")) {
				authComponent = c;
				continue;
			}
		}
		
		if (authComponent == null)
			fail();
				
		// we change the session validity to 2 seconds
		LifeCycleController lc = (LifeCycleController) authComponent.getFcInterface("lifecycle-controller");
		lc.stopFc();
		
		SCAPropertyController pc = (SCAPropertyController) authComponent.getFcInterface(SCAPropertyController.NAME);
		pc.setValue("session-validity", 2000 );
		
		lc.startFc();
		
		// we log pim
		Subject pim = auth.authenticate("poum", "pwd");
		SecuritySubjectManager.get().setSecuritySubject(pim);
		
		// try to call a service
		this.ui.getBookmarks();
		
		// wait for 3 seconds
		System.out.println("wait for 3 seconds...");
		Thread.sleep(3000);
		
		// and try again, assert that the session is no more valid
		try {
			this.ui.getBookmarks();
			fail();
		} catch (Exception e){
			assertTrue(true);
		}		
		
	}
}
