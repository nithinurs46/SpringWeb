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
			<h6 class="errormsg"  id="errorInfo"></h6>
					<div class="row">
						<div class="col">Users List</div>
						<div class="col" align="right" style="margin-bottom: 2px">
							<button type="button" class="btn btn-outline-primary btn-sm"
								onclick="location.href='/addUser'">Add User</button>
						</div>
					</div>
					<table class="table table-sm table-striped" id = "usersTable">
			 <thead class="thead-light">
				<tr>
					<th  scope="col">User Id</th>
					<th  scope="col">Mobile No</th>
					<th  scope="col">Email Id</th>
					<th  scope="col">City</th>
					<th  scope="col">State</th>
					<th>Country</th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<c:forEach var="user" items="${usersList}">
					<tr>
						<td id="userId">${user.userId}</td>
						<td>${user.mobileNo}</td>
						<td>${user.emailId}</td>
						<td>${user.city}</td>
						<td>${user.state}</td>
						<td>${user.country}</td>
						<td><a href="editUser/${user.userId}">Edit</a></td>
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
var msgBody = "Are you sure you want to delete the user?"
$("#usersTable").on('click','#btnDelete',function(){
	var currentRow = $(this).closest("tr");
	var userId = currentRow.find("#userId").html();
	
		confirmDialog(header,msgBody, function(){
			$.ajax({
				type : "POST",
				//contentType : "application/json",
				url : "deleteUser/"+userId,
				//data : JSON.stringify(data),
				success : function(data) {
					window.location.href = "showUsers";
				},
				error : function(e) {
					console.log("ERROR: ", e);
						$("#errorInfo").html("User does not have permission to delete").show();
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