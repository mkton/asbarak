package org.ow2.asbarak.auth;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Service;

@Service
public interface AsbarakAuthenticationService {

	public Subject authenticate(String login, String pwd);
	
}
