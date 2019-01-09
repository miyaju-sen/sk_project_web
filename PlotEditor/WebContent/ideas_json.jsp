<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Idea" %>
<%@ page import="java.util.*" %>
<%
	ArrayList<Idea> ideas = (ArrayList<Idea>)request.getAttribute("IDEAS");
	int cnt = ideas.size() - 1;
%>
{
	"ideas":[
		<% for(int i = 0; i < ideas.size(); i++) { %>
		{
			"ideaNo":"<%=ideas.get(i).getNo() %>",
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