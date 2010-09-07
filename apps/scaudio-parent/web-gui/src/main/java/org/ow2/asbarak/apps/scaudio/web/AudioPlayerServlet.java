package org.ow2.asbarak.apps.scaudio.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.Naming;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.ow2.asbarak.apps.scaudio.ui.AudioPlayerUIService;

public class AudioPlayerServlet implements Servlet {

	/*
	 * Action keywords
	 */
	public static final String action = "action";
	public static final String index = "index";
	public static final String add = "add";
	public static final String play = "play";
	public static final String up = "up";
	public static final String down = "down";
	
	/*
	 * Action parameters
	 */
	public static final String id = "id";
	public static final String location = "location";
	
	/*
	 * Attributes
	 */
	public static final String list = "list";
	
	/*
	 * Pages
	 */
	public static final String main = "AudioPlayerItf.jsp";
	
	public static final String servlet_id = "scaudio";
	
	private AudioPlayerUIService audioPlayerItfService;
	
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

	public void init(ServletConfig arg0) throws ServletException {		
		try {
			System.setSecurityManager(new java.rmi.RMISecurityManager());
			
			audioPlayerItfService = (AudioPlayerUIService)Naming.lookup("//localhost:1099/scaudio-rmi-service");
		} catch (Exception e) {			
			throw new ServletException(e);			
		}				
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter(AudioPlayerServlet.action);
		
		if ( (action == null) || (action.equals("")) )
			action = AudioPlayerServlet.index;
		
		
		if ( action.equals(AudioPlayerServlet.index) ){
			request.getRequestDispatcher(AudioPlayerServlet.main).forward(request, response);
			return;
		}		
		
		if (action.equals(AudioPlayerServlet.up))
			audioPlayerItfService.increaseSoundLevel();
						
		if (action.equals(AudioPlayerServlet.down))
			audioPlayerItfService.decreaseSoundLevel(); 

		
		if ( action.equals(AudioPlayerServlet.play) ){
			
			String location = request.getParameter(AudioPlayerServlet.location);			
			try {				
				if ((location != null) && (!location.equals(""))) {
					audioPlayerItfService.playAudioLocation(new URI(location));
				}				
			} catch (NumberFormatException e) {
				throw new ServletException(e);
			} catch (URISyntaxException e) {
				throw new ServletException(e);
			}			
			
		}
		
		request.getRequestDispatcher("/" + AudioPlayerServlet.servlet_id + 
				"?" + AudioPlayerServlet.action + 
				"=" + AudioPlayerServlet.index).forward(request, response);
	}
	
	public static String createFormAsLink(String action, String label, HashMap<String, String> attrs){
		String ret = "<form style=\"display:inline;\" action=\"scaudio\">";
		
		ret += "<input type=\"hidden\" name=\""; 
		ret += AudioPlayerServlet.action;
		ret += "\" value=\"" + action + "\"/>";
		
		
		if (attrs != null) {
			for (String attr : attrs.keySet()){		
				ret += "<input type=\"hidden\" name=\""; 
				ret += attr + "\" value=\"" + attrs.get(attr) + "\"/>";
			}
		}
		
		ret += "<input type=\"submit\" class=\"simple_submit\" value=\""; 
		ret += label + "\"/>";
	
		ret += "</form>";		
		
		return ret;
	}	

}
