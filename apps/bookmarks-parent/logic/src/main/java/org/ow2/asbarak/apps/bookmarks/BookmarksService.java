package org.ow2.asbarak.apps.bookmarks;

import java.util.HashSet;

import org.osoa.sca.annotations.Service;

@Service
public interface BookmarksService {

	public HashSet<String> getBookmarks();
	
	public void addBookmark(String url);
}
