package org.ow2.asbarak.auth;

import java.security.Principal;

public class AsbarakUserPrincipal implements Principal {

	private Integer id;
	private String login;

	public AsbarakUserPrincipal(Integer id, String login){
		this.id = id;
		this.login = login;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public String getName() {
		return "AsbarakUserPrincipals";
	}

}
