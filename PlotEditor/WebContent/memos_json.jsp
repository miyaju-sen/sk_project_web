<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Memo" %>
<%@ page import="java.util.*" %>
<%
	String newId = (String)request.getAttribute("NEWID");
	ArrayList<Memo> memos = (ArrayList<Memo>)request.getAttribute("MEMOS");
	int cnt = memos.size() - 1;
%>
{
	"newId":"<%=newId %>",
	"memos":[
		<% for(int i = 0; i < memos.size(); i++) { %>
		{
			"no":"<%=memos.get(i).getNo() %>",
			"plot":"<%=memos.get(i).getPlot() %>",
			"note":"<%=memos.get(i).getNote() %>",
			"deleted":"<%=memos.get(i).isDeleted() %>"
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}