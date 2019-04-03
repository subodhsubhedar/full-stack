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
			<h1 class="display-4">Register to Library App</h1>

			<div class="panel-body">
				<form:form method="post" action="register" name="registration"
					modelAttribute="registrationForm">

					<fieldset>

						<c:if test="${not empty errorMessge}">
							<div class="alert alert-danger" role="alert">${errorMessge}</div>
							<br />
						</c:if>


						<spring:bind path="username">
							<div class="form-group">
								<label class="col-sm-2 control-label">User Name : </label>
								<div class="col-sm-10">
									<form:input path="username" type="text"
										class="form-control ${status.error ? 'is-invalid' : ''}"
										id="username" placeholder="User Name" size="100"
										maxlength="150" value="${personForm.username}" />
									<form:errors path="username" class="control-label text-danger" />
								</div>
							</div>
						</spring:bind>


						<spring:bind path="password">
							<div class="form-group">
								<label class="col-sm-2 control-label">Password : </label>
								<div class="col-sm-10">
									<form:input path="password" type="password"
										class="form-control ${status.error ? 'is-invalid' : ''}"
										id="password" placeholder="Password" size="100"
										maxlength="150" value="${personForm.password}" />
									<form:errors path="password" class="control-label text-danger" />
								</div>
							</div>
						</spring:bind>

						<spring:bind path="confirmPassword">
							<div class="form-group">
								<label class="col-sm-2 control-label">Confirm Password :
								</label>
								<div class="col-sm-10">
									<form:input path="confirmPassword" type="password"
										class="form-control ${status.error ? 'is-invalid' : ''}"
										id="confirmPassword" placeholder="Confirm Password" size="100"
										maxlength="150" value="${personForm.confirmPassword}" />
									<form:errors path="confirmPassword"
										class="control-label text-danger" />
								</div>
							</div>
						</spring:bind>

						<spring:bind path="roles.id">
							<div class="form-group">
								<label class="col-sm-2 control-label">Select Role : </label>
								<form:select path="roles.id"
									class="form-control ${status.error ? 'is-invalid' : ''}">
									<form:option value="9999" label="Select" />
									<form:options items="${availableRoleList}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="roles.id" class="control-label text-danger" />
							</div>
						</spring:bind>

						<spring:bind path="roles.name">
							<form:hidden path="roles.name" />
						</spring:bind>


						<input class="btn" type="submit" value="Register"> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</fieldset>
				</form:form>


			</div>
		</div>
	</div>
</body>

</html>