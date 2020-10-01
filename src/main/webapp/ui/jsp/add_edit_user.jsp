<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="commonUIFiles.jsp"%>

<title>Edit User</title>

<!-- Custom styles for this template -->
<link href="/menu1.css" rel="stylesheet">

</head>
<body>
<c:choose>
		<c:when test="${addOrUpdate == 'add'}">
			<c:url var="actionUrl" value = "/saveUser?opType=add"></c:url>
		</c:when>
		<c:otherwise>
			<c:url var="actionUrl" value = "/saveUser?opType=update"></c:url>
		</c:otherwise>
	</c:choose>
	<form:form method="post" modelAttribute="userUpdateForm"
		action="${actionUrl}">
		<div class="wrapper">
			<%@include file="navbar.jsp"%>

			<div id="content">
				<%@include file="menu_new.jsp"%>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-8">
							<h6 class="errormsg">${saveStatus}</h6>
							<div class="card">
								<div class="card-header">Manage User</div>
								<div class="card-body">

									<div class="form-group row">
										<div class="col">
											<form:label path="userId">User ID</form:label>
											<form:input path="userId" class="form-control"
												placeholder="User ID" type="text" />
										</div>
										<div class="col">
											<div class="form-group">
												<form:label path="password">Password</form:label>
												<form:input path="password" class="form-control"
													placeholder="Password" type="password" required="true" />
												<form:errors path="password" cssClass="error" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col">
											<form:label path="mobileNo">Mobile No</form:label>
											<form:input path="mobileNo" class="form-control"
												placeholder="Mobile No" type="text" required="true" />
											<form:errors path="mobileNo" cssClass="error" />
										</div>

										<div class="col">
											<div class="form-group">
												<form:label path="emailId">Email ID</form:label>
												<form:input path="emailId" class="form-control"
													placeholder="Email ID" type="email" required="true" />
											</div>
										</div>
									</div>



									<div class="form-group row">
										<div class="col">
											<form:label path="address1">Address Line 1</form:label>
											<form:input path="address1" class="form-control"
												placeholder="Address Line 1" type="text" />
										</div>

										<div class="col">
											<div class="form-group">
												<form:label path="address2">Address Line 2</form:label>
												<form:input path="address2" class="form-control"
													placeholder="Address Line 2" type="text" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col">
											<form:label path="pincode">Pincode</form:label>
											<form:input path="pincode" class="form-control"
												placeholder="Pincode" type="text" />
										</div>

										<div class="col">
											<div class="form-group">
												<form:label path="city">City</form:label>
												<form:input path="city" class="form-control"
													placeholder="City" type="text" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col">
											<form:label path="state">State</form:label>
											<form:input path="state" class="form-control"
												placeholder="state" type="text" />
										</div>

										<div class="col">
											<div class="form-group">
												<form:label path="country">Country</form:label>
												<form:input path="country" class="form-control"
													placeholder="Country" type="text" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col">
											<form:label path="selectedRoles">Role</form:label>
											<form:select path="selectedRoles" class="form-control"
												multiple="true" itemValue = "roles">
												<form:option value="" label="--- Select ---" />
												<form:options items="${rolesList}" />
											</form:select>
										</div>

										<div class="col"></div>
									</div>

									<!-- <div class="col-md-6 offset-md-4">
										<button type="submit" class="btn btn-primary">Update
										</button>
									</div> -->
									<div class="col-md-6 offset-md-4">
										<c:choose>
											<c:when test="${addOrUpdate == 'add'}">
												<button type="submit" class="btn btn-primary">Create User</button>
											</c:when>
											<c:otherwise>
												<button type="submit" class="btn btn-primary">Update User</button>
											</c:otherwise>
										</c:choose>
										<button type="button" class="btn btn-secondary" onclick="location.href='/showUsers'">Back</button>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>