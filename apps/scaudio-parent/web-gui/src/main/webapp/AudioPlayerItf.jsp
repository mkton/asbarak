<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.ow2.asbarak.apps.scaudio.web.AudioPlayerServlet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.net.URI"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Scaudio</title>
	<style type="text/css">
		<!--
		html,body
		{
			margin-top:0;
			margin-right:1;
			margin-left:1;
			margin-bottom:0;
			font-size : 10pt;
			font-family:Arial,Helvetica,sans-serif;
			color:#2B3E42;
			height: 100%;
			padding:0;
			background-color:#F7F3E8;
		}
	
		#frame{
			width:60%;
			margin-left: auto;
			margin-right: auto;
		}
	
		#header{
			padding:10px;
	
			border: 2pt dotted;
			margin:15px;
			background-color:#D5E1DD;
		}	
	
		#main{
			border: 2pt dotted;
			margin:15px;
			text-align: center;
			background-color:#D5E1DD;
		}
	
		.btn{
	
			width:25px;
			font-size : 25pt;
			color: #F2583E;
			background-color:#2B3E42;
		}
	
		a {
			text-decoration: none;
		}
		-->
	</style>

</head>
<body>

	<!-- Manage sound level -->
	<%
		// forms for manage the sound level
	    String up = AudioPlayerServlet.createFormAsLink(
	    		AudioPlayerServlet.up,
	    		"+",
	    		null);
	
		String down = AudioPlayerServlet.createFormAsLink(
				AudioPlayerServlet.down,
	    		"-",
	    		null);
	%>	 

	<div id="frame">

		<div id="header">
			<h1>Audio Manager</h1>
		</div>

		<div id="main">
			<h3>Adjust sound level : </h3><br />
			
			<% out.println(up); %>
			&nbsp;&nbsp;
			<% out.println(down); %>
						
			<br /><br /><br />
			
			<!-- allow to play music from a location -->
			<h3>Play a song :</h3>
			<form action="scaudio">
				<input type="hidden" name="<% out.print(AudioPlayerServlet.action); %>" value="<%out.print(AudioPlayerServlet.play);%>" />
				<input type="text" name="<% out.print(AudioPlayerServlet.location); %>"></input>
				<input type="submit" value="play !"/>
			</form>	
			
			<br />
		</div>

	</div>
	
</body>
</html>