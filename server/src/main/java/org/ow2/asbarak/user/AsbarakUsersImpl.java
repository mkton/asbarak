package org.ow2.asbarak.user;

import java.util.HashMap;
import java.util.Map.Entry;

import org.osoa.sca.annotations.Init;

public class AsbarakUsersImpl {
	
	public class AsbarakUser {
		
		private String login;
		private Integer id;
		
		public String getLogin(){
			return this.login;
		}		

		public Integer getId() {
			return id;
		}
		
		public AsbarakUser(String login, Integer id){
			this.login = login;
			this.id = id;
		}
	}

	public HashMap<Integer, String> users;

	@Init
	public void setUsers(){
		this.users = new HashMap<Integer,String>();
		this.users.put(1, "blabla");
		this.users.put(2, "monsieur");
		this.users.put(3, "chuck");
		this.users.put(4, "homer");
	}
	
	public AsbarakUser getUser(String login, String pwd){
		for ( Entry<Integer, String> e : users.entrySet()){
			if (e.getValue().equals(login) && pwd.equals("pwd"))
				return new AsbarakUser(e.getValue(), e.getKey());
		}
		return null;
	}
	
}
