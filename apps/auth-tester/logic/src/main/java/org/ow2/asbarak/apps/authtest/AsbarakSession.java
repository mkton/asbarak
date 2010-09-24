package org.ow2.asbarak.apps.authtest;

import java.util.Date;

public class AsbarakSession {

	public static final Integer SESSION_VALIDITY = 3600000;
	private Integer userId;
	private long token;
	private Date creationTime;
	
	public Integer getUserId(){
		return this.userId;
	}
	
	public long getToken(){
		return this.token;
	}
		
	public AsbarakSession(Integer userId, long token){
		this.userId = userId;
		this.token = token;
		this.creationTime = new Date();
	}
	
	public boolean isStillValid(){
		long expiration = creationTime.getTime() +
							AsbarakSession.SESSION_VALIDITY;		
		return ( expiration < (new Date()).getTime());
	}
}
