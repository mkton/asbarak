package org.ow2.asbarak.apps.authtest;

import java.util.HashMap;

public class AuthTestUIImpl implements AuthTestUIService {
	
	public HashMap<Integer, String> users;

	public AuthTestUIImpl(){
		this.users = new HashMap<Integer,String>();
		this.users.put(1, "Mr Norris");
		this.users.put(2, "Shaun Palmer");
	}

	public String getUserInformations(Integer id){
		return users.get(id);
	}
}
