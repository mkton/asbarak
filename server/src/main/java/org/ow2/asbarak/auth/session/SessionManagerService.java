package org.ow2.asbarak.auth.session;

import org.osoa.sca.annotations.Service;

@Service
public interface SessionManagerService {

	public AsbarakSession createSession(String login, String password);
	
	public AsbarakSession getSession(long token);
		
}
