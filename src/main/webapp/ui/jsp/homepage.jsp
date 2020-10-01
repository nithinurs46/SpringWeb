<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="commonUIFiles.jsp"%>
<script src="/PieChart.js"></script>
<script src="/BarChart.js"></script>
<title>Dashboard</title>

<!-- Custom styles for this template -->
<link href="/menu1.css" rel="stylesheet">
<link href="/d3Chart.css" rel="stylesheet">
<script src="/d3.min.js"></script>

</head>

<script type="text/javascript">
 var jsonPieChart= ${pieChartJson};
 var jsonBarChart= ${barChartJson};
 //alert(importJSON);
</script>

<body
	onload="showPieChart('pieExp',jsonPieChart);showPieChart('pieImp',jsonPieChart); showBarGraph('barExp',jsonBarChart); showBarGraph('barImp',jsonBarChart);">

	<div class="wrapper">
		<%@include file="navbar.jsp"%>

		<div id="content">
			<%@include file="menu_new.jsp"%>

			<div class="container">
				<div class="row" style="margin-top: 40px;">
					<div class="col" id="barExp"></div>
					<div class="col" id="barImp"></div>
				</div>

				<div class="row" style="margin-top: 40px;">
					<div class="col" id="pieExp"></div>
					<div class="col" id="pieImp"></div>
				</div>
			</div>


		</div>
	</div>
</body>
</html>