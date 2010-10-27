package org.ow2.asbarak.auth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.auth.session.AsbarakSession;
import org.ow2.asbarak.auth.session.SessionManagerService;
import org.ow2.asbarak.user.AsbarakUsersService;
import org.ow2.asbarak.user.AsbarakUsersImpl.AsbarakUser;

@Scope("COMPOSITE")
public class AsbarakAuthenticationImpl implements AsbarakAuthenticationService, SessionManagerService, AsbarakSubjectFactoryService {

	@Reference(name="users-reference")
	AsbarakUsersService users;
	
	private ArrayList<AsbarakSession> sessions = new ArrayList<AsbarakSession>();
	
	public AsbarakSession createSession(String login, String password) {
		
		// we test login.pwd
		AsbarakUser user  = users.getUser(login, password);
		
		//FIXME use exception instead of null
		if ( user != null) {
		
			int token = login.toString().hashCode();
			AsbarakSession newS = new AsbarakSession(user.getId(), token);
			sessions.add(newS);
			return newS;
		}
		
		// FIXME use exception
		return null;
	}

	public AsbarakSession getSession(long token) {
		for (AsbarakSession s : sessions){
			if (s.getToken() == token)
				return s;
		}
		// FIXME use exception
		return null;
	}

	public Subject createSubject(AsbarakSession session) {
		
		AsbarakUser user = users.getUser(session.getUserId());
		
		HashSet<Principal> principals = new HashSet<Principal>();
		HashSet<String> pubCredentials = new HashSet<String>();
		HashSet<String> privCredentials = new HashSet<String>();
		
		AsbarakUserPrincipal principal = new AsbarakUserPrincipal(user.getId(), user.getLogin());
		principals.add( principal );
		
		Subject subject = new Subject(
				true,
				principals,
				pubCredentials,
				privCredentials);
		
		return subject;
	}

	public Subject authenticate(String login, String pwd) {
		AsbarakSession session = this.createSession(login, pwd);
		Subject subject = this.createSubject(session);
		return subject;
	}

	
}
