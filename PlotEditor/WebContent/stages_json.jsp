<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Stage" %>
<%@ page import="java.util.*" %>
<%
	ArrayList<Stage> stages = (ArrayList<Stage>)request.getAttribute("STAGES");
	int cnt = stages.size() - 1;
%>
{
	"stages":[
		<% for(int i = 0; i < stages.size(); i++) { %>
		{
			"no":"<%=stages.get(i).getNo() %>",
			"plot":"<%=stages.get(i).getPlot() %>",
			"stage":"<%=stages.get(i).getStage() %>",
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}