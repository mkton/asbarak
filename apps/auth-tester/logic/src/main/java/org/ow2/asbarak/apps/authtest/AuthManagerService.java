package org.ow2.asbarak.apps.authtest;

import org.osoa.sca.annotations.Service;

@Service
public interface AuthManagerService {

	public AsbarakSession createSession(String login, String password);
	
	public AsbarakSession getSession(long token);
		
}
