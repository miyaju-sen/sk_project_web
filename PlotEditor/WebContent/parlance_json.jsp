<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Parlance" %>
<%@ page import="java.util.*" %>
<%
	String newId = (String)request.getAttribute("NEWID");
	ArrayList<Parlance> parlances = (ArrayList<Parlance>)request.getAttribute("PARLANCES");
	int cnt = parlances.size() - 1;
%>
{
	"newId":"<%=newId %>",
	"parlances":[
		<% for(int i = 0; i < parlances.size(); i++) { %>
		{
			"no":"<%=parlances.get(i).getNo() %>",
			"plot":"<%=parlances.get(i).getPlot() %>",
			"name":"<%=parlances.get(i).getName() %>",
			"explanation":"<%=parlances.get(i).getExplanation() %>",
			"deleted":"<%=parlances.get(i).isDeleted() %>"
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}