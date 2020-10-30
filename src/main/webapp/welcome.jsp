<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>View Profile</title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>
<body>
	<div class="container welcome">
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<div class="view-welcome">
			<h2 class="welcome-banner">
				Welcome <b>${viewUsername}</b> !.
			</h2>
			<a onclick="document.forms['logoutForm'].submit()"
				class="btn btn-default btn-lg active logout-btn" role="button">Logout</a>
		</div>
		<div class="view-details">
			<img src="${viewImageLink}" 
				width="300px" height="300px" />
						<label for="Name">Name:</label>	
			
			<input  type="text" name="Name" style="text-align:center;font-size:16pt;"   class="details-text" placeholder="dd" value="${viewName}" readonly="readonly">
			<label for="Bio">Biography:</label>	
			<textarea name="Bio"rows="9" readonly="readonly" class="details-textarea">          ${viewBiography}</textarea>
		</div>
		
		<div class="Edit-Profile">
			<button class="btn btn-lg btn-primary btn-block" onclick="location.href='editProfile'">Edit Profile</button>
		</div>
	
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
