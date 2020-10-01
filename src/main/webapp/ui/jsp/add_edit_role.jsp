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
			<c:url var="actionUrl" value="/saveRole?opType=add"></c:url>
		</c:when>
		<c:otherwise>
			<c:url var="actionUrl" value="/saveRole?opType=update"></c:url>
		</c:otherwise>
	</c:choose>

	<form:form method="post" modelAttribute="roleUpdateForm"
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
								<div class="card-header">Manage Role</div>
								<div class="card-body">

									<div class="form-group row">
										<div class="col">
											<form:label path="roleName">Role Name</form:label>
											<form:input path="roleName" class="form-control"
												placeholder="Role Name" type="text" />
											<form:errors path="roleName" cssClass="error" />
										</div>
									</div>

									<div class="form-group row">
										<div class="col">
											<div class="form-group">
												<form:label path="updateUserPriv">Update User Privilege</form:label>
												<form:checkbox path="updateUserPriv" value="01" />
											</div>
										</div>
										<div class="col">
											<div class="form-group">
												<form:label path="deleteUserPriv">Delete User Privilege</form:label>
												<form:checkbox path="deleteUserPriv" value="01" />
											</div>
										</div>
										<div class="col">
											<div class="form-group">
												<form:label path="importPriv">Import Listing Privilege</form:label>
												<form:checkbox path="importPriv" value="01" />
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<c:choose>
											<c:when test="${addOrUpdate == 'add'}">
												<button type="submit" class="btn btn-primary">Create
													Role</button>
											</c:when>
											<c:otherwise>
												<button type="submit" class="btn btn-primary">Update
													Role</button>
											</c:otherwise>
										</c:choose>
										<button type="button" class="btn btn-secondary" onclick="location.href='/showRoles'">Back</button>
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