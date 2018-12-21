<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	boolean result = (boolean)request.getAttribute("RESULT");
	String no = (String)request.getAttribute("No");
	String table = (String)request.getAttribute("TABLE");
%>
{
	"result":"<%=result %>",
	"no":"<%=no %>",
	"table":"<%=table %>"
}