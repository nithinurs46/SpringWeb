<link href="/menu1.css" rel="stylesheet">

<nav id="sidebar">
	<div class="sidebar-header">
		<h3>Spring boot web app</h3>
		<strong>App</strong>
	</div>

	<ul class="list-unstyled components">
		<li><a href="/displayHomePage"> <i class="fas fa-question"></i> Dashboard
		</a></li>
		
		<li class="active"><a href="#submenu1" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle"> <i
				class="fas fa-home"></i> Menu 1
		</a>
			<ul class="collapse list-unstyled" id="submenu1">
				<li><a href="/submenu1">Sub Menu 1</a></li>
				<li><a href="#">Sub Menu 2</a></li>
				<li><a href="#">Sub Menu 3</a></li>
				<li><a href="#">Sub Menu 4</a></li>
			</ul></li>
			
		<li class="active"><a href="#submenu2" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle"> <i
				class="fas fa-home"></i> Menu 2
		</a>
			<ul class="collapse list-unstyled" id="submenu2">
				<li><a href="#">Sub Menu 1</a></li>
				<li><a href="#">Sub Menu 2</a></li>
				<li><a href="#">Sub Menu 3</a></li>
				<li><a href="#">Sub Menu 4</a></li>
			</ul></li>
				
		<li><a href="#"> <i class="fas fa-briefcase"></i> Report 1
		</a></li>
		
		<li><a href="#"> <i class="fas fa-paper-plane"></i> Report 2
		</a></li>
	</ul>

	<ul class="list-unstyled components">
		<li class="active"><a href="#adminSubmenu" data-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle"> <i
				class="fas fa-home"></i> Admin
		</a>
			<ul class="collapse list-unstyled" id="adminSubmenu">
				<li><a href="/showUsers">Users</a></li>
				<li><a href="/showRoles">Roles</a></li>
				<li><a href="#">Units</a></li>
			</ul></li>
	</ul>
	
	<ul class="list-unstyled CTAs">
		
	</ul>
</nav>