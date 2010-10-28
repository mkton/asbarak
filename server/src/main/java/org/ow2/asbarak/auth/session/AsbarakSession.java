package org.ow2.asbarak.auth.session;

import java.util.Date;

public class AsbarakSession {

	private Integer sessionValidity;
	private Integer userId;
	private long token;
	private Date creationTime;
	
	public Integer getUserId(){
		return this.userId;
	}
	
	public long getToken(){
		return this.token;
	}
		
	public AsbarakSession(Integer userId, long token, Integer validity){
		this.userId = userId;
		this.token = token;
		this.creationTime = new Date();
		this.sessionValidity = validity;
	}
	
	public boolean isStillValid(){
		long expiration = creationTime.getTime() +
							this.sessionValidity;		
		return ( expiration > (new Date()).getTime());
	}
}
