package org.ow2.asbarak.apps.bookmarks;

import java.util.HashMap;
import java.util.HashSet;

import org.oasisopen.sca.ComponentContext;
import org.osoa.sca.annotations.Context;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.auth.AsbarakUserPrincipal;
import org.ow2.frascati.tinfi.SecuritySubjectManager;

@Scope("COMPOSITE")
public class BookmarksImpl implements BookmarksService {

	@Context
	ComponentContext context;
	
	public HashMap<Integer, HashSet<String>> map;
	
	/**
	 * Temp, we're waiting for database management
	 */
	@Init
	public void initMap(){
		this.map = new HashMap<Integer, HashSet<String>>();
	}
	
	public HashSet<String> getBookmarks() {
		AsbarakUserPrincipal principal = this.getPrincipal();
		
		HashSet<String> userBM = this.map.get(principal.getId());
				
		if ( userBM == null ){
			userBM = new HashSet<String>();
			map.put(principal.getId(), userBM);
		}
		
		return userBM;
	}

	public void addBookmark(String url) {

		HashSet<String> userBM = this.getBookmarks();
		
		userBM.add(url);
		
		return;		
	}
	
	private AsbarakUserPrincipal getPrincipal(){
		// it shouldn't be null because of the auth verification
		AsbarakUserPrincipal principal = 
			//context.getRequestContext()
			SecuritySubjectManager.get().getSecuritySubject()
				.getPrincipals(AsbarakUserPrincipal.class).iterator().next();
		
		return principal;
	}

}
