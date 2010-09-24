<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.ow2.podcasti.web.PodcastiServlet"%>
<%@page import="org.ow2.podcasti.model.Feed"%>
<%@page import="org.ow2.podcasti.model.Episode"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.net.URI"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>
			podcasti
		</title>

		<script language="Javascript">
			
            function display_feed(id){
            
                    //first we hide all feeds
                    hide_feeds();
                    
                    //then we dsplay the right one
                    feed = this.document.getElementById(id);
                    feed.style.display = 'block';

                    feed_actions = this.document.getElementById("actions_" + id);
                    feed_actions.style.display = 'inline';
            }
            
            function hide_feeds(){
                    feeds = getElementsByClass("feed");                             
                    for(i=0; i<feeds.length; i++){
                            feeds[i].style.display = 'none';
                    }

                    feed_actions = getElementsByClass("feed_actions");                             
                    for(i=0; i<feed_actions.length; i++){
                            feed_actions[i].style.display = 'none';
                    }
            }
			
			function display_episode(feedId, episodeId){
						
				//first we hide all episodes
				hide_episodes();
				
				//then we display the right episode status
				episodeStatusId = "episode" + feedId + "_" + episodeId;
				episode = this.document.getElementById(episodeStatusId);
				episode.style.display = 'block';
			}
			
			function hide_episodes(){
				episodes = getElementsByClass("episode");
				for(i=0; i<episodes.length; i++){
					episodes[i].style.display = 'none';
				}				
			}
			
			
			function getElementsByClass( searchClass, domNode, tagName) { 
				if (domNode == null) domNode = document;
				if (tagName == null) tagName = '*';
				var el = new Array();
				var tags = domNode.getElementsByTagName(tagName);
				var tcl = " "+searchClass+" ";
				for(i=0,j=0; i<tags.length; i++) { 
					var test = " " + tags[i].className + " ";
					if (test.indexOf(tcl) != -1) 
						el[j++] = tags[i];
				} 
				return el;
			}
			 
			function remove_feed(){
				confirm("So, you no longer want to follow this feed, correct ? ");
			}
		</script>

		<style type="text/css">
			<!--
			html,body
			{
				margin-top:0;
				margin-right:1;
				margin-left:1;
				margin-bottom:0;
				font-size : 10pt;
				font-family: Courier New, sans-serif;
				height: 100%;
				padding:0;
				color : #2B3E42;
				background-color:#F7F3E8;
			}

			#frame{
				width:640px	;
				margin-left: auto;
				margin-right: auto;
			}

			#header{
				padding:10px;
				border: 1pt dashed #747E80;
				margin:15px;
				background-color:#D5E1DD;
			}	

			#main{
				border: 1pt dashed #747E80;
				margin:15px;
				background-color:#ffffff;
				padding:10px;	
				background-color:#D5E1DD;
			}
			
			.feed{
				display: none;
			}
			.feed_actions{
                display: none;
				text-align : right;
			}
			
			.episode{
				padding:10px;				
				display: none;
				font-size:8pt;
				border :1pt dashed #747E80;
				margin-bottom: 15px;
				margin-top: 5px;
				width : 320px;
				font-weight: bold; 
				background-color:#F7F3E8;
			}
			
			#log{
				color: #F2583E;
				font-size : 10px;
			}
			
			#podcast_list{
				margin-bottom : 40px;
			}
			
			li{
				margin : 10px;
			}

			.li_feed{
				margin-bottom : 15px;
			}

			a {
				text-decoration: none;
				color: #2B3E42;
			}
			a:visited {
				color: #2B3E42;
			}
			a:hover {
				color: #F2583E;
			}
			h4 {
				color :  #2B3E42;
			}
			.simple_submit{
                font-weight: bold; 
                font-size:8pt;
                color : #2B3E42;
				border:none;
				padding:0;
				margin:0;
				background-color:transparent;
				font-family: Courier New, sans-serif;
			} 
			.simple_submit:hover {
                color: #F2583E;
            }
			-->
		</style>

	</head>
	
<body>

	<div id="frame">

		<div id="header">				
			<p><h2>/home<span style="color:#F2583E;">/podcast</span></h2></p>
		</div>

		<div id="main">
			
			<div id="log">
				<%
				String error = (String) request.getAttribute(PodcastiServlet.error);
				if (error!=null) out.println(error);
				%>
			</div>

			<div class="margin_div">
				<h4>Podcast Feeds</h4>
			      <%
			      		HashMap<Feed, HashSet<Episode>> list =
			      			(HashMap<Feed, HashSet<Episode>>) request.getAttribute(PodcastiServlet.list);
		
		                if (list == null) {
		                	request.getRequestDispatcher("/" + PodcastiServlet.servlet_id + "?" + PodcastiServlet.action + "=" + PodcastiServlet.getList).forward(request, response);
		                }
		
		                // we start the list creation
		                String ret = "<ul>\n";
		                
						Feed feed;
						HashSet<Episode> episodes;
		                for (Entry<Feed, HashSet<Episode>> e : list.entrySet()){
		                				                		
		                		feed = e.getKey();
		                		
		                		// we create the feed's html representation
		                        ret += "<li class=\"li_feed\">\n";
		                        ret += "<span onclick=\"display_feed(";
		                        ret += feed.id;
		                        ret += ")\">";
		                        ret += "<a href=\"#\">"+ feed.name + "</a>";
		                        ret += "</span>\n";
		                        ret += "<span class=\"feed_actions\" id=\"actions_" + feed.id +"\">";
		                        
		                        // forms for manage the feed : remove and archive
		                        HashMap<String, String> remAttr = new HashMap<String, String>();
		                        remAttr.put(PodcastiServlet.feedId, feed.id.toString());
		                        ret += PodcastiServlet.createFormAsLink(
		                        		PodcastiServlet.remove,
		                        		"remove",
		                        		remAttr);
		                        		                        
		                      	// ret += ret += PodcastiServlet.createFormAsLink(
		                        //		PodcastiServlet.archives,		                        		
		                        //		"archives",
		                        //		null);
		                        
		                        ret += "</span>\n";
		                        
		                        ret += "<ul id=\"" + feed.id + "\" class=\"feed\">";
		                        
		                        // then we create the associated episodes
		                        episodes = e.getValue();
		                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		                        for (Episode episode : episodes){
		                        	ret += "<li>\n\t";
		                        	ret += "<span onclick=\"display_episode(";
		                        	ret += feed.id +"," + episode.id + ");\">";
		                        	ret += "<a href=\"#\"> <b>" + formatter.format(episode.date) + "</b> " + episode.title + "</a>";
		                        	ret += "</span>\n";
		                        	
		                        	// here we create the features for an episode
		                        	
		                        	ret += "<div class=\"episode\" id=\"episode";
		                        	ret += feed.id + "_" + episode.id + "\">";		                        	
		                        			                        	
		                        	// play via the navigator
		                        	ret += "<a target=\"_blank\" href=\"" + episode.location + "\">link</a>";

		                        	ret += " .. ";
		                        	
		                        	// play on the media server
		                        	HashMap<String, String> attrs = new HashMap<String, String>();
		                        	attrs.put(PodcastiServlet.location, episode.location.toString());
		                        	ret += PodcastiServlet.createFormAsLink(
		                        		PodcastiServlet.play,
		                        		"play on media server",
		                        		attrs);
		                        	
		                        	ret += "&nbsp;..&nbsp;";
		                        			                        	
		                        	// archive on the storage server
		                        	HashMap<String, String> archAttr = new HashMap<String, String>();
		                        	archAttr.put(PodcastiServlet.feedId, feed.id.toString());
		                        	archAttr.put(PodcastiServlet.episodeId, episode.id.toString());
		                        	ret += PodcastiServlet.createFormAsLink(
		                        		PodcastiServlet.archive,
		                        		"archive",
		                        		archAttr);
		                        	
		                        	ret += "&nbsp;..&nbsp;";
		                        	
		                        	// link to the html description
		                        	ret += "<a target=\"_blank\" href=" + episode.desc + ">see</a>";
		                        	
		                        	ret += "</div>\n";
		                        	ret += "</li>\n";
		                        }
		                        ret += "<li> <a href=#>older episodes ... </a> </li>";
		                        ret += "</ul>\n";
		                }
		
		                
		                ret += "</ul>\n";
		
		                out.println(ret);
		        %>
			</div>			
						
			<div class="margin_div">
				<!-- allow to add a new Feed -->
				<h4>Add a new feed :</h4>
				<form action="podcasti">
					<input type="hidden" name="<% out.print(PodcastiServlet.action); %>" value="<%out.print(PodcastiServlet.add);%>" />
					<input type="text" name="<% out.print(PodcastiServlet.location); %>"></input>
					<input type="submit" value="add !"/>
				</form>	
			</div>
		
		</div>

	</div>
 
	
</body>
</html>