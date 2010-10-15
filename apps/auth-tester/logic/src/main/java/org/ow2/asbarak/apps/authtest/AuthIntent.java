package org.ow2.asbarak.apps.authtest;

import javax.security.auth.Subject;

import org.ow2.frascati.tinfi.SecuritySubjectManager;
import org.ow2.frascati.tinfi.api.IntentHandler;
import org.ow2.frascati.tinfi.api.IntentJoinPoint;

public class AuthIntent implements IntentHandler {

	public Object invoke(IntentJoinPoint ijp) throws Throwable {
		// here !
		// remember @Context
		// security provider / security controller / security subject
		//ContentController cc;
		//ComponentContext cc;
		//ijp.getComponent().  getRequestContext().getSecuritySubject();
		//Subject s = SecuritySubjectsManager.get().getSecuritySubject();
		return null;
	}
	
}
