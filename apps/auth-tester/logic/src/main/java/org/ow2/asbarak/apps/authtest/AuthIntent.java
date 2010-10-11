package org.ow2.asbarak.apps.authtest;

import org.ow2.frascati.tinfi.control.intent.IntentHandler;
import org.ow2.frascati.tinfi.control.intent.IntentJoinPoint;

public class AuthIntent implements IntentHandler {

	public Object invoke(IntentJoinPoint ijp) throws Throwable {
		// here !
		// remember @Context
		// security provider / security controller / security subject
		ijp.getComponentContext().getRequestContext().getSecuritySubject();
		return null;
	}
	
}
