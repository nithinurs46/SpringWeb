<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<%@include file="commonUIFiles.jsp"%>

<title>Login</title>

<style type="text/css">
.errormsg {
	color: red;
}
</style>
</head>
<body>
	<form:form method="post" modelAttribute="loginForm"
		action="/login">


		<div class="container">
			<br>
			<p class="text-center"></p>
			

			<div class="row">
				<aside class="col-sm-4"></aside>
				<aside class="col-sm-4">
					<br> <br> <br>
					<h6 class="errormsg"> ${errorMessages} </h6>
					<c:if test="${param.error != null}">
						<div id="error">
							<h6 class="errormsg">Invalid Login Credentials</h6>
						</div>
					</c:if>
					<div class="card ">
						<article class="card-body">
							<a href="/registerUser" class="float-right btn btn-outline-primary">Sign
								up</a>
							<h4 class="card-title mb-4 mt-1">Sign in</h4>
							<form>
								<div class="form-group">
									<form:label path="userId">User ID</form:label>
									<form:input path="userId" class="form-control"
										placeholder="User ID" type="text" required="true" />
								</div>
								<div class="form-group">
									<form:label path="password">Password</form:label>
									<form:input path="password" class="form-control"
										placeholder="Password" type="password" required="true" />
								</div>
								<br>
								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block">
										Login</button>
								</div>
							</form>
						</article>
					</div>
				</aside>
				<aside class="col-sm-4"></aside>
			</div>
		</div>
	</form:form>
</body>
</html>