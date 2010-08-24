package org.ow2.podcasti.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.ow2.podcasti.model.Episode;
import org.ow2.podcasti.model.Feed;
import org.ow2.podcasti.ui.PodcastiUIService;

public class PodcastiServlet implements Servlet {

	/*
	 * Action keywords
	 */
	public static final String action = "action";	
	public static final String getList = "getlist";
	public static final String add = "add";
	public static final String remove = "remove";
	public static final String archives = "archives";
	public static final String archive = "archive";
	public static final String play = "play";
	
	/*
	 * Action parameters
	 */
	public static final String location = "location";
	public static final String id = "id";
	
	/*
	 * Attributes
	 */
	public static final String list = "list";
	public static final String log = "log";
	public static final String error = "error";
	
	/*
	 * Pages
	 */
	public static final String main = "PodcastiItf.jsp";
	
	private PodcastiUIService ui;
	
	public void destroy() {
		// TODO Auto-generated method stub		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String formatServletUrl(String action){
		//FIXME beurk
		return "/web-gui-0.0.1-SNAPSHOT" + "/podcasti?" + PodcastiServlet.action + "=" + action;
	}
	
	public HashMap<Feed, HashSet<Episode>> createList(){
		HashMap<Feed, HashSet<Episode>> ret = new HashMap<Feed, HashSet<Episode>>(); 
		
		HashSet<Feed> feeds = ui.getFeeds();
		HashSet<Episode> episodes;
		for (Feed feed : feeds){
			episodes = ui.get3Last(feed.id);
			ret.put(feed, episodes);
		}
		 
		return ret;		
	}

	public void init(ServletConfig arg0) throws ServletException {		
		try {
			System.setSecurityManager(new java.rmi.RMISecurityManager());			
			this.ui = (PodcastiUIService) Naming.lookup("//localhost:1099/podcasti-rmi-service");
		} catch (Exception e) {			
			throw new ServletException(e);			
		}				
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter(PodcastiServlet.action);
		
		if ( (action == null) || (action.equals("")) )
			action = PodcastiServlet.getList;
		
		
		if ( action.equals(PodcastiServlet.getList) ){
			request.setAttribute(PodcastiServlet.list, createList());
			request.getRequestDispatcher(PodcastiServlet.main).forward(request, response);
			return;
		}		
		
		if ( action.equals(PodcastiServlet.add)) {
			try {
				String address = request.getParameter(PodcastiServlet.location);
				
				if (! ui.addFeed(new URI(request.getParameter(PodcastiServlet.location))))
					request.setAttribute(PodcastiServlet.error, "Problem when trying to add this URI : " + address);				
			} catch (URISyntaxException e) {
				request.setAttribute(PodcastiServlet.error, "This is is not a valid URI : " + location);
			}
		}
		
		if ( action.equals(PodcastiServlet.remove)) {
			String feedId = request.getParameter(PodcastiServlet.id);			
			ui.removeFeed(Integer.parseInt(feedId));
		}
		
		request.getRequestDispatcher("/podcasti?" + PodcastiServlet.action + "=" + PodcastiServlet.getList).forward(request, response);
	}
	
	public static String createFormAsLink(String action, String attr, String value, String label){
		String ret = "<form style=\"display:inline;\" action=\"podcasti\">";
		
		ret += "<input type=\"hidden\" name=\""; 
		ret += PodcastiServlet.action;
		ret += "\" value=\"" + action + "\"/>";
		
		ret += "<input type=\"hidden\" name=\""; 
		ret += attr + "\" value=\"" + value + "\"/>";
		
		ret += "<input type=\"submit\" class=\"simple_submit\" value=\""; 
		ret += label + "\"/>";
	
		ret += "</form>";		
		
		return ret;
	}	

}
