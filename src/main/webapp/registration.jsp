<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create Account</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

	<div class="container register-main">
		<h1 class="form-signin-heading">CREATE ACCOUNT</h1>
		<form:form method="POST" modelAttribute="userForm" class="form-signin" enctype="multipart/form-data">
			<spring:bind path="username">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="username" class="form-control"
						placeholder="Username" autofocus="true"></form:input>
					<form:errors path="username"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="password" class="form-control"
						placeholder="Password"></form:input>
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="passwordConfirm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="passwordConfirm"
						class="form-control" placeholder="Confirm your password"></form:input>
					<form:errors path="passwordConfirm"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="name">

				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" class="form-control register-text"
						placeholder="First Name + Last Name" path="name" required="required"></form:input>
					<form:errors path="name"></form:errors>

				</div>
			</spring:bind>
			<spring:bind path="biography">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:textarea class="form-control register-textarea" path="biography"
						placeholder="Biography ! Tell Your Story" rows="6"
						required="required"></form:textarea>
					<form:errors path="biography"></form:errors>
				</div>
			</spring:bind>

			<div class="form-group">
				<label class="image-upload"> <input type="file" name="photo"
					accept="image/*" id="imgInp" /> Select Image
				</label> <img id="blah" height="100px" width="175px"
					src="${contextPath}/resources/img/blank-profile-picture-973460.png" />
			</div>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		</form:form>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]); // convert to base64 string
			}
		}

		$("#imgInp").change(function() {
			readURL(this);
		});
	</script>
</body>
</html>
