package org.ow2.asbarak.auth;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Service;
import org.ow2.asbarak.auth.session.AsbarakSession;

@Service
public interface AsbarakSubjectFactoryService {
	
	public Subject createSubject(AsbarakSession session);
	
}
