package org.ow2.asbarak.auth.frascati;

import javax.security.auth.Subject;

import org.ow2.frascati.tinfi.SecuritySubjectManager;
import org.ow2.frascati.tinfi.api.IntentHandler;
import org.ow2.frascati.tinfi.api.IntentJoinPoint;

public abstract class AuthenticationIntent implements IntentHandler {

	public Object invoke(IntentJoinPoint ijp) throws Throwable {
		
		// remember @Context
		// ijp.getComponent().getRequestContext -> no more available
		// getRequestContext().getSecuritySubject();
		
		// we retrieve the security subject
		Subject subject = SecuritySubjectManager.get().getSecuritySubject();
		
		
		// and check its consistency
		if (subject!=null && isAuthenticated(subject)) {
			// that's correct, so can we proceed the request
			return ijp.proceed();
		}
		 
		throw new AuthenticationException();
		/*
			AsbarakUserPrincipal uP = subject
				.getPrincipals(AsbarakUserPrincipal.class).iterator().next();
		
			// here we should verify more informations like rights
			if (uP != null){
				return ijp.proceed();
			}
		}
		
		//TODO raise exception
		return null;
		*/
	}
	
	public abstract boolean isAuthenticated(Subject subject);
	
}
