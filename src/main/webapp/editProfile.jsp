<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/img/logo-dark.png" />

<meta charset="utf-8">
<title>EDIT PROFILE</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

	<div class="container editprofile">
		<h1 class="form-signin-heading">EDIT PROFILE</h1>
		<form:form method="POST" action="${contextPath}/editProfile" modelAttribute="userForm" enctype="multipart/form-data">
			<div class="view-details">
				<img src="${viewImageLink}" 
					width="250px" height="250px" id="blah1" />
				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" name="Name"
							style="text-align: center; font-size: 16pt;" class="details-text"
							value="${viewName}" path="name" required="required"/>
						<form:errors path="name"></form:errors>

					</div>
				</spring:bind>
				<spring:bind path="biography">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:textarea name="Bio" rows="9" path="biography"
							class="details-textarea" value="${viewBiography}" required="required"></form:textarea>
						<form:errors path="biography"></form:errors>
					</div>
				</spring:bind>

			</div>
			<br />
			<br />
			<div class="image-pg">
				<label class="image-upload btn btn-lg btn-primary" > <input type="file" name="photo" src="${viewImageLink}" 
					accept="image/*" id="imgInp" style="padding: 5em;"/> Select Image
				</label>
			</div>
			<div class="Edit-Profile">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Save
					Changes</button>
			</div>
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
					$('#blah1').attr('src', e.target.result);
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