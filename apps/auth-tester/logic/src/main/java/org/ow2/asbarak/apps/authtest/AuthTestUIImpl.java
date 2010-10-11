package org.ow2.asbarak.apps.authtest;

import java.util.HashMap;
import java.util.Map.Entry;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class AuthTestUIImpl implements AuthTestUIService {
	
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
		return users.get(id);
	}
	
	public Integer checkIdentity(String login, String pwd){
		for ( Entry<Integer, String> e : users.entrySet()){
			if (e.getValue().equals(login) && pwd.equals("pwd"))
				return e.getKey();
		}
		return null;
	}
}
