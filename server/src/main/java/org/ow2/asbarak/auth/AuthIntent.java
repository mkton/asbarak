package org.ow2.asbarak.auth;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Reference;
import org.ow2.asbarak.auth.session.SessionManagerService;
import org.ow2.frascati.intent.authentication.AbstractAuthenticationIntent;

public class AuthIntent extends AbstractAuthenticationIntent {
	
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
