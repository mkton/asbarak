package org.ow2.asbarak.auth;

public class AsbarakPublicCredential {

	private long token;
	
	public AsbarakPublicCredential(long token){
		this.token = token;
	}
	
	public long getToken(){
		return this.token;
	}
	
}
