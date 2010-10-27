package org.ow2.asbarak.user;

import java.util.HashMap;
import java.util.Map.Entry;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class AsbarakUsersImpl implements AsbarakUsersService {
	
	public class AsbarakUser {
		
		private String login;
		private Integer id;
		
		public String getLogin(){
			return this.login;
		}		

		public Integer getId() {
			return id;
		}
		
		public AsbarakUser(Integer id, String login){
			this.login = login;
			this.id = id;
		}
	}

	public HashMap<Integer, AsbarakUser> users;

	@Init
	public void setUsers(){
		this.users = new HashMap<Integer,AsbarakUser>();
		this.users.put(1, new AsbarakUser(1, "pim"));
		this.users.put(2, new AsbarakUser(2, "pam"));
		this.users.put(3, new AsbarakUser(3, "poum"));
		this.users.put(4, new AsbarakUser(4, "pouf"));
	}
	
	public AsbarakUser getUser(String login, String pwd){
		for ( Entry<Integer, AsbarakUser> e : users.entrySet()){
			if (e.getValue().getLogin().equals(login) && pwd.equals("pwd"))
				return e.getValue();
		}
		return null;
	}
	
	public AsbarakUser getUser(Integer id){
		//TODO throw an exception if user unknown
		return users.get(id);
	}
	
}
