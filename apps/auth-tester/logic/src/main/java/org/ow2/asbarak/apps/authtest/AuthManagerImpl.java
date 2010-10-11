package org.ow2.asbarak.apps.authtest;

import java.util.ArrayList;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class AuthManagerImpl implements AuthManagerService {
	
	@Reference(name="user-reference")
	AuthTestUIService ui;
	
	private ArrayList<AsbarakSession> sessions = new ArrayList<AsbarakSession>();
	
	public AsbarakSession createSession(String login, String password) {
		
		// we test login.pwd
		Integer userId = ui.checkIdentity(login, password);
		
		if ( userId != null) {
		
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
