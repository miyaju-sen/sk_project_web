<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Story" %>
<%@ page import="java.util.*" %>
<%
	String newId = (String)request.getAttribute("NEWID");
	ArrayList<Story> stories = (ArrayList<Story>)request.getAttribute("STORIES");
	int cnt = stories.size() - 1;
%>
{
	"newId":"<%=newId %>",
	"stories":[
		<% for(int i = 0; i < stories.size(); i++) { %>
		{
			"no":"<%=stories.get(i).getNo() %>",
			"idea":"<%=stories.get(i).getIdea() %>",
			"story":"<%=stories.get(i).getStory() %>",
			"deleted":"<%=stories.get(i).isDeleted() %>"
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}