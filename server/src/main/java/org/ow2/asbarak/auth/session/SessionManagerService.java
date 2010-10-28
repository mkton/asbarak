package org.ow2.asbarak.auth.session;

import javax.naming.AuthenticationException;

import org.osoa.sca.annotations.Service;

@Service
public interface SessionManagerService {

	public AsbarakSession createSession(String login, String password) throws AuthenticationException;
	
	public AsbarakSession getSession(long token);
		
}
