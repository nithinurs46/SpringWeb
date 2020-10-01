<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<html>
<head>
<%@include file="commonUIFiles.jsp" %>

<title>Listing page</title>

<style>

.main{
padding-right : 40px;
padding-left : 40px;
}

.navbar-fixed-top{
border:0px;
}
</style>
</head>
<body>

<div class="wrapper">
<%@include file="navbar.jsp" %>

<div id="content">
<%@include file="menu_new.jsp" %>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Menu 1</a></li>
    <li class="breadcrumb-item active" aria-current="page">Section 1</li>
  </ol>
</nav>

<div class = "table-responsive " style = "padding-left:20px; padding-right:20px;height: 400px;">
				<table class="table table-hover table-sm">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">First</th>
							<th scope="col">Last</th>
							<th scope="col">Handle</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td colspan="2">Larry the Bird</td>
							<td>@twitter</td>
						</tr>
					</tbody>
				</table>
			</div>
<div style = "padding-top:20px; padding-left:20px; padding-right:20px;">
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-end">
    <li class="page-item disabled">
      <a class="page-link" href="#" id="btn_prev" onclick="prevPage()">Previous</a>
    </li>
     <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" id="btn_next" onclick="nextPage()">Next</a>
    </li>
  </ul>
</nav></div>
</div>
</div>
</body>
</html>