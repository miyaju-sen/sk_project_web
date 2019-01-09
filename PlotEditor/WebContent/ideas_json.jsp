<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Idea" %>
<%@ page import="java.util.*" %>
<%
	String storyFlag = (String)request.getAttribute("FLAG");
	ArrayList<Idea> ideas = (ArrayList<Idea>)request.getAttribute("IDEAS");
	int cnt = ideas.size() - 1;
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
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}