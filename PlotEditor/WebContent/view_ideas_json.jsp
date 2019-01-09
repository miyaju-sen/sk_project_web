<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.ViewIdea" %>
<%@ page import="java.util.*" %>
<%
	ArrayList<ViewIdea> viewIdeas = (ArrayList<ViewIdea>)request.getAttribute("IDEAS");
	int cnt = viewIdeas.size() - 1;
%>
{
	"ideas":[
		<% for(int i = 0; i < viewIdeas.size(); i++) { %>
		{
			"ideaNo":"<%=viewIdeas.get(i).getIdeaNo() %>",
			"plot":"<%=viewIdeas.get(i).getPlot() %>",
			"idea":"<%=viewIdeas.get(i).getIdea() %>",
			"note":"<%=viewIdeas.get(i).getNote() %>",
			"story_no":"<%=viewIdeas.get(i).getStoryNo() %>",
			"title":"<%=viewIdeas.get(i).getTitle() %>",
			"story":"<%=viewIdeas.get(i).getStory() %>",
			"deleted":"<%=viewIdeas.get(i).isDeleted() %>"
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}