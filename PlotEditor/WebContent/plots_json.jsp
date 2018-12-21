<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Plot" %>
<%@ page import="java.util.*" %>
<%
	String newId = (String)request.getAttribute("NEWID");
	ArrayList<Plot> plots = (ArrayList<Plot>)request.getAttribute("PLOTS");
	int cnt = plots.size() - 1;
%>
{
	"newId":"<%=newId %>",
	"plots":[
		<% for(int i = 0; i < plots.size(); i++) { %>
		{
			"no":"<%=plots.get(i).getNo() %>",
			"title":"<%=plots.get(i).getTitle() %>",
			"slogan":"<%=plots.get(i).getSlogan() %>",
			"summary":"<%=plots.get(i).getSummary() %>",
			"created_at":"<%=plots.get(i).getCreatedAt() %>",
			"deleted":"<%=plots.get(i).isDeleted() %>"
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}