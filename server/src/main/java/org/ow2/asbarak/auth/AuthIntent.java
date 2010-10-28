package org.ow2.asbarak.auth;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Reference;
import org.ow2.asbarak.auth.frascati.AuthenticationIntent;
import org.ow2.asbarak.auth.session.SessionManagerService;

public class AuthIntent extends AuthenticationIntent{
	
	@Reference(name="session-reference")
	SessionManagerService sessionManager;
	
	@Override
	public boolean isAuthenticated(Subject subject) { 
		
		AsbarakPublicCredential pubCredential = subject
			.getPublicCredentials(AsbarakPublicCredential.class).iterator().next();
		
		// we assert that the session is still valid
		return (sessionManager.getSession(pubCredential.getToken()).isStillValid());

		// here we should verify more informations like rights (aka habilitations)
		//AsbarakUserPrincipal uP = subject
		//	.getPrincipals(AsbarakUserPrincipal.class).iterator().next();

	}
	
}
