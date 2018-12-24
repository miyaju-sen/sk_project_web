<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Character" %>
<%@ page import="java.util.*" %>
<%
	String newId = (String)request.getAttribute("NEWID");
	ArrayList<Character> characters = (ArrayList<Character>)request.getAttribute("CHARACTERS");
	int cnt = characters.size() - 1;
%>
{
	"newId":"<%=newId %>",
	"characters":[
		<% for(int i = 0; i < characters.size(); i++) { %>
		{
			"no":"<%=characters.get(i).getNo() %>",
			"plot":"<%=characters.get(i).getPlot() %>",
			"phonetic":"<%=characters.get(i).getPhonetic() %>",
			"name":"<%=characters.get(i).getName() %>",
			"another":"<%=characters.get(i).getAnother() %>",
			"image_path":"<%=characters.get(i).getImagePath() %>",
			"age":"<%=characters.get(i).getAge() %>",
			"gender":"<%=characters.get(i).getGender() %>",
			"birthday":"<%=characters.get(i).getBirthday() %>",
			"height":"<%=characters.get(i).getHeight() %>",
			"weight":"<%=characters.get(i).getWeight() %>",
			"first_person":"<%=characters.get(i).getFirstPerson() %>",
			"second_person":"<%=characters.get(i).getSecondPerson() %>",
			"belongs":"<%=characters.get(i).getBelongs() %>",
			"skill":"<%=characters.get(i).getSkill() %>",
			"profile":"<%=characters.get(i).getProfile() %>",
			"lived_process":"<%=characters.get(i).getLivedProcess() %>",
			"personality":"<%=characters.get(i).getPersonality() %>",
			"appearance":"<%=characters.get(i).getAppearance() %>",
			"other":"<%=characters.get(i).getOther() %>",
			"deleted":"<%=characters.get(i).isDeleted() %>"
		<% if(i != cnt) { %>
		},
		<% }else {%>
		}
		<% }%>
		<% } %>
	]
}