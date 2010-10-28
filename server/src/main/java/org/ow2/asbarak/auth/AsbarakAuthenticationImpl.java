package org.ow2.asbarak.auth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;

import javax.naming.AuthenticationException;
import javax.security.auth.Subject;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.auth.session.AsbarakSession;
import org.ow2.asbarak.auth.session.SessionManagerService;
import org.ow2.asbarak.user.AsbarakUsersService;
import org.ow2.asbarak.user.AsbarakUsersImpl.AsbarakUser;

@Scope("COMPOSITE")
public class AsbarakAuthenticationImpl implements AsbarakAuthenticationService,
		SessionManagerService, AsbarakSubjectFactoryService {

	@Reference(name = "users-reference")
	AsbarakUsersService users;

	@Property(name = "session-validity")
	Integer sessionValidity;

	private ArrayList<AsbarakSession> sessions = new ArrayList<AsbarakSession>();

	public AsbarakSession createSession(String login, String password)
			throws AuthenticationException {

		// we test login.pwd
		AsbarakUser user = users.getUser(login, password);

		if (user == null) {
			throw new AuthenticationException("user unknown");
		}

		int token = login.toString().hashCode();
		AsbarakSession newS = new AsbarakSession(user.getId(), token,
				this.sessionValidity);
		sessions.add(newS);
		return newS;

	}

	public AsbarakSession getSession(long token) {
		for (AsbarakSession s : sessions) {
			if (s.getToken() == token)
				return s;
		}
		// FIXME use exception
		return null;
	}

	public Subject createSubject(AsbarakSession session) {

		AsbarakUser user = users.getUser(session.getUserId());

		HashSet<Principal> principals = new HashSet<Principal>();
		HashSet<AsbarakPublicCredential> pubCredentials = new HashSet<AsbarakPublicCredential>();
		HashSet<String> privCredentials = new HashSet<String>();

		// we create principal with the user informations
		AsbarakUserPrincipal principal = new AsbarakUserPrincipal(user.getId(),
				user.getLogin());
		principals.add(principal);

		// then we instantiate a public credential where we put the session
		// token
		AsbarakPublicCredential pubCredential = new AsbarakPublicCredential(
				session.getToken());
		pubCredentials.add(pubCredential);

		Subject subject = new Subject(true, principals, pubCredentials,
				privCredentials);

		return subject;
	}

	public Subject authenticate(String login, String pwd) throws AuthenticationException {
		AsbarakSession session = this.createSession(login, pwd);
		Subject subject = this.createSubject(session);
		return subject;
	}

}
