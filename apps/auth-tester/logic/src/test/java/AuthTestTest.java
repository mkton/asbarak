import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.ow2.asbarak.apps.authtest.AsbarakSession;
import org.ow2.asbarak.apps.authtest.AuthManagerService;
import org.ow2.asbarak.apps.authtest.AuthTestAdaptorService;
import org.ow2.frascati.FraSCAti;

public class AuthTestTest {
	
	AuthManagerService authManager;
	AuthTestAdaptorService authTest;
		
	public AuthTestTest() throws Exception {
		
		FraSCAti frascati = FraSCAti.newFraSCAti();
			
		// we create the component assembly
		Component composite = frascati.getComposite("auth-test.composite");
		
		// and retrieve the components we need
		this.authManager = frascati.getService(composite, "auth-manager", AuthManagerService.class);
		this.authTest = frascati.getService(composite, "auth-test", AuthTestAdaptorService.class);
	}
	

	@Test
	public void testFlow(){
		// we create 2 sessions
		AsbarakSession sessionChuck = authManager.createSession("chuck", "pwd");
		AsbarakSession sessionHomer = authManager.createSession("homer", "pwd");
		
		// then we try to call ui with a fake token
		String info = authTest.getUserInformations(sessionChuck.getUserId(), 0);
		assertTrue( info.equals("") );
		
		// now we try to call with a good token
		info = authTest.getUserInformations(sessionChuck.getUserId(), sessionChuck.getToken());
		
		// and we assert that we get the expected result
		assertTrue( !info.equals("") );
	}
	
}