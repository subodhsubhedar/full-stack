<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container">
		<br>
		<div class="jumbotron">
			<h1 class="display-4">Login - Library App</h1>

			<div class="panel-body">
				<form:form method="post" action="login" name="loginForm">

					<fieldset>
						<legend>Please sign in</legend>
						<c:if test="${not empty errorMessge}">
							<div class="alert alert-danger" role="alert">${errorMessge}</div>
							<br />
						</c:if>
						<c:if test="${not empty logoutMsg}">
							<div class="alert alert-success" role="alert">${logoutMsg}</div>
							<br />
						</c:if>
						<c:if test="${not empty registerMsg}">
							<div class="alert alert-success" role="alert">${registerMsg}</div>
							<br />
						</c:if>


						<div class="form-group">
							<input class="form:input-large" placeholder="User Name"
								name='username' type="text">
						</div>
						<div class="form-group">
							<input class=" form:input-large" placeholder="Password"
								name='password' type="password" value="">
						</div>
						<input class="btn" type="submit" value="Login"> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</fieldset>
				</form:form>
					<br>
				<h4>New User ?</h4>
				Click to <a href="<c:url value="register" /> ">Register</a>
			</div>
		</div>
	</div>
</body>

</html>