<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Idea" %>
<%@ page import="entities.ViewIdea" %>
<%@ page import="java.util.*" %>
<%
	String storyFlag = (String)request.getAttribute("FLAG");
	ArrayList<Idea> ideas = (ArrayList<Idea>)request.getAttribute("IDEAS");
	ArrayList<ViewIdea> vIdeas = (ArrayList<ViewIdea>)request.getAttribute("VIDEAS");
	int ideaCnt = ideas.size() - 1;
	int storyCnt = vIdeas.size() - 1;
%>
{
	"storyFlag":"<%=storyFlag %>",
	"ideas":[
		<% for(int i = 0; i < ideas.size(); i++) { %>
		{
			"idea_no":"<%=ideas.get(i).getNo() %>",
			"plot":"<%=ideas.get(i).getPlot() %>",
			"idea":"<%=ideas.get(i).getIdea() %>",
			"note":"<%=ideas.get(i).getNote() %>"
		<% if(i != ideaCnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	],
	"stories":[
		<% for(int i = 0; i < vIdeas.size(); i++) { %>
		{
			"idea":"<%=vIdeas.get(i).getIdea() %>",
			"story_no":"<%=vIdeas.get(i).getStoryNo() %>",
			"title":"<%=vIdeas.get(i).getTitle() %>",
			"story":"<%=vIdeas.get(i).getStory() %>",
			"deleted":"<%=vIdeas.get(i).isDeleted() %>"
		<% if(i != storyCnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}