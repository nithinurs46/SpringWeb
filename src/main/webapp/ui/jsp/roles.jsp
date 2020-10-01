<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="commonUIFiles.jsp"%>

<title>Users</title>

<!-- Custom styles for this template -->
<link href="/menu1.css" rel="stylesheet">

</head>
<body>
<form:form>
	<div class="wrapper">
		<%@include file="navbar.jsp"%>

		<div id="content">
			<%@include file="menu_new.jsp"%>
			<br>
			<div class = "table-responsive " style = "padding-left:20px; padding-right:20px;height: 400px;">
			<h6 class="errormsg">${saveStatus}</h6>
					<div class="row">
						<div class="col">Roles List</div>
						<div class="col" align="right" style="margin-bottom: 2px">
							<button type="button" class="btn btn-outline-primary btn-sm"
								onclick="location.href='/addRole'">Add Role</button>
						</div>
					</div>
					<table class="table table-sm table-striped" id = "rolesTable">
			 <thead class="thead-light">
				<tr>
					<th  scope="col">Role Name</th>
					<th  scope="col">Update User</th>
					<th  scope="col">Delete User</th>
					<th  scope="col">Import Listing</th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<c:forEach var="role" items="${rolesList}">
					<tr>
						<td id="roleName">${role.roleName}</td>
						<td>${role.updateUserPriv}</td>
						<td>${role.deleteUserPriv}</td>
						<td>${role.importPriv}</td>
						<td><a href="editRole/${role.roleName}">Edit</a></td>
						<td id="btnDelete"><a href="#">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
	
	<div>
		<jsp:include page="confirmation_modal.jsp"></jsp:include>
	</div>

</form:form>
</body>
<script>

var header = "Confirm";
var msgBody = "Are you sure you want to delete the role?"
$("#rolesTable").on('click','#btnDelete',function(){
	var currentRow = $(this).closest("tr");
	var roleName = currentRow.find("#roleName").html();
	
		confirmDialog(header,msgBody, function(){
			$.ajax({
				type : "POST",
				//contentType : "application/json",
				url : "deleteRole/"+roleName,
				//data : JSON.stringify(data),
				success : function(data) {
					window.location.href = "showRoles";
				},
				error : function(e) {
					console.log("ERROR: ", e);
					alert("error while deleting role");
				}
				
			});
    		console.log("deleted!");
		});
	});

  function confirmDialog(header,message, onConfirm){
	    var fClose = function(){
			  modal.modal("hide");
	    };
	    var modal = $("#confirmModal");
	    modal.modal("show");
	    $("#msgBody").empty().append(message);
	    $("#confirmMessage").empty().append(header);
	    $("#confirmOk").unbind().one('click', onConfirm).one('click', fClose);
	    $("#confirmCancel").unbind().one("click", fClose);
  }

</script>
</html>