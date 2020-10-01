
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<link href="/menu1.css" rel="stylesheet">



<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">

		<button type="button" id="sidebarCollapse" class="btn btn-info">
			<i class="fas fa-align-left"></i> <span>Toggle Sidebar</span>
		</button>
		<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
			type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-align-justify"></i>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="nav navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Logged in as: 
						<security:authorize access="isAuthenticated()">
    								<security:authentication property="principal.username" />
    					</security:authorize>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Help</a></li>
				<li class="nav-item"><a class="nav-link" href="/signout">Sign Off</a></li>
			</ul>
		</div>
	</div>
</nav>