package org.ow2.asbarak.auth.session;

import java.util.ArrayList;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.user.AsbarakUsersImpl.AsbarakUser;
import org.ow2.asbarak.user.AsbarakUsersService;

@Scope("COMPOSITE")
public class SessionManagerImpl implements SessionManagerService {
	
	@Reference(name="users-reference")
	AsbarakUsersService users;
	
	private ArrayList<AsbarakSession> sessions = new ArrayList<AsbarakSession>();
	
	public AsbarakSession createSession(String login, String password) {
		
		// we test login.pwd
		AsbarakUser user  = users.getUser(login, password);
		
		if ( user != null) {
		
			int token = login.toString().hashCode();
			AsbarakSession newS = new AsbarakSession(userId, token);
			sessions.add(newS);
			return newS;
		}
		
		// FIXME
		return null;
	}

	public AsbarakSession getSession(long token) {
		for (AsbarakSession s : sessions){
			if (s.getToken() == token)
				return s;
		}
		// FIXME
		return null;
	}

}
