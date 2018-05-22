<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var allListElement = $(".lista").children();

		$.each(allListElement, function(index, value) {
			var parentId = $(value).attr('id');
			//console.log("parent id: " + parentId);
			var parentMargin = parseInt($(value).css("margin-left"));
			//console.log("parent margin: " + parentMargin);
			$.each(allListElement, function(k, v) {
				if ($(v).attr('parent_id') == parentId) {
					var childMargin = parentMargin + 10;
					//console.log("child margin: " + childMargin);
					$(v).css({
						"margin-left" : childMargin
					});
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>Lista</h1>
	<div class="lista">
		<c:forEach items="${messages}" var="message">
			<p id='<c:out value="${message.id}"></c:out>'
				parent_id='<c:out value="${message.parent.id}">0</c:out>' style="margin-left: 0px;">
				title=${message.title}, created=${message.created}</p>
		</c:forEach>
	</div>
	<p>-------------------------------------------------------------------------</p>
	<form action="addUser" method="get">
		<p>
			Imie:<input type="text" name="firstName">
		</p>
		<p>
			Nazwisko:<input type="text" name="lastName">
		</p>
		<p>
			Wiek:<input type="text" name="age">
		</p>
		<p>
			<button type="submit">Dodaj uzytkownika</button>
		</p>
	</form>
	<p>-------------------------------------------------------------------------</p>
	<form id="messageForm" action="addMessage" method="get">
		<p>
			Owner: <select name="owner">
				<c:forEach items="${users}" var="user">
					<option value="${user.id}">${user.firstName}
						${user.lastName}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			Topic: <select name="topic">
				<option value="POLICY">Policy</option>
				<option value="RUDE">Rude</option>
				<option value="GENERAL">General</option>
			</select>
		</p>
		<p>
			Title:<input type="text" name="title">
		</p>
		<p>
			<textarea name="content" rows="3" cols="100" form="messageForm">Content...</textarea>
		</p>
		<p>
			Parent: <select name="parent">
				<option value="null" selected="selected">null</option>
				<c:forEach var="message" items="${messages}">
					<option value="${message.id}">${message.title}owner_id:
						${message.id}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<button type="submit">Dodaj wiadomosc</button>
		</p>
	</form>
</body>
</html>