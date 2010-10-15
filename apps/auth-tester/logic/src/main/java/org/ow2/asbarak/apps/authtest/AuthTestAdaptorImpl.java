package org.ow2.asbarak.apps.authtest;

import java.security.Principal;
import java.util.HashSet;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.frascati.tinfi.SecuritySubjectManager;

@Scope("COMPOSITE")
public class AuthTestAdaptorImpl implements AuthTestAdaptorService {

	@Reference(name = "user-reference")
	AuthTestUIService ui;

	public String getUserInformations(Integer id, long token) {

		HashSet<Principal> principals = new HashSet<Principal>();
		principals.add( new TokenPrincipal(token) );
		
		Subject subject = new Subject(
				true,
				principals,
				null,
				null);
		
		SecuritySubjectManager.get().setSecuritySubject(subject);
		
		return ui.getUserInformations(id);
		
		// Should be done into the intent and
		// we should use "roles & habilitations" for rules
		// --
		/*AsbarakSession session = authManagerService.getSession(token);

		if (session != null && session.isStillValid()
				&& session.getUserId().equals(id)) {
			return ui.getUserInformations(id);
		} else {
			// TODO throw exception for unauthenticated
			return "";
		}*/
		// --

		// TODO we'll set the token into the security context in order to be
		// able to identify the session

		// then we can call the logic, auth will automatically check if user
		// associated
		// to the token is habilitate to do that action
		// return ui.getUserInformations(id);
	}
}
