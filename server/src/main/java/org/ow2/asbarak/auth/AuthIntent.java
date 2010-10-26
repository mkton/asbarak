package org.ow2.asbarak.auth;

import javax.security.auth.Subject;

import org.ow2.frascati.tinfi.SecuritySubjectManager;
import org.ow2.frascati.tinfi.api.IntentHandler;
import org.ow2.frascati.tinfi.api.IntentJoinPoint;

public class AuthIntent implements IntentHandler {

	
	public Object invoke(IntentJoinPoint ijp) throws Throwable {
		
		// remember @Context
		// security provider / security controller / security subject
		//ContentController cc;
		//ComponentContext cc;
		//ijp.getComponent().  getRequestContext().getSecuritySubject();
		
		
		Subject subject = SecuritySubjectManager.get().getSecuritySubject();
		if (subject!=null) {
			AsbarakUserPrincipal uP = subject
				.getPrincipals(AsbarakUserPrincipal.class).iterator().next();
		
			// here we should verify more informations like rights
			if (uP != null){
				return ijp.proceed();
			}
		}
		
		//TODO raise exception
		return null;
	}
	
}
