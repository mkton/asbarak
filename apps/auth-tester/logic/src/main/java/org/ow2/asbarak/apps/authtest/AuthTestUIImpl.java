package org.ow2.asbarak.apps.authtest;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.security.auth.Subject;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.auth.session.AsbarakSession;
import org.ow2.frascati.tinfi.SecuritySubjectManager;

@Scope("COMPOSITE")
public class AuthTestUIImpl implements AuthTestUIService {
	
	@Reference(name = "auth-manager-reference")
	AuthManagerService authManagerService;
	
	public HashMap<Integer, String> users;

	@Init
	public void setUsers(){
		this.users = new HashMap<Integer,String>();
		this.users.put(1, "Mr Norris");
		this.users.put(2, "Shaun Palmer");
		this.users.put(3, "chuck");
		this.users.put(4, "homer");
	}

	public String getUserInformations(Integer id){
		
		Subject subject = SecuritySubjectManager.get().getSecuritySubject();
		
		TokenPrincipal tp = (TokenPrincipal) subject.getPrincipals().iterator().next();
		
		AsbarakSession session = authManagerService.getSession(tp.getToken());

		if (session != null && session.isStillValid()
				&& session.getUserId().equals(id)) {
			return users.get(id);
		} else {
			// TODO throw exception for unauthenticated
			return "";
		}
	}
	
	public Integer checkIdentity(String login, String pwd){
		for ( Entry<Integer, String> e : users.entrySet()){
			if (e.getValue().equals(login) && pwd.equals("pwd"))
				return e.getKey();
		}
		return null;
	}
}
