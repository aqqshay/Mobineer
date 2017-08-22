<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Web Cafe Admin</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="description" content="">
<meta name="author" content="templatemo">
<!-- 
    Visual Admin Template
    http://www.templatemo.com/preview/templatemo_455_visual_admin
    -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700'
	rel='stylesheet' type='text/css'>
<link href="resources/css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/templatemo-style.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<header class="templatemo-site-header">
				<h1>Web Cafe Admin Panel</h1>
			</header>
			<div class="profile-photo-container">
				<img src="resources/images/profile-photo.jpg" alt="Profile Photo"
					class="img-responsive">
				<div class="profile-photo-overlay"></div>
			</div>
			<!-- Search box -->
			<form class="templatemo-search-form" role="search">
				<div class="input-group"></div>
			</form>
			<div class="mobile-menu-icon">
				<i class="fa fa-bars"></i>
			</div>
			<nav class="templatemo-left-nav">
				<ul>
					<li><a href="#" class="active"><i class="fa fa-home fa-fw"></i>Dashboard</a></li>
					<li><a href="manageCategories"><i
							class="fa fa-bar-chart fa-fw"></i>Manage Categories</a></li>
					<li><a href="manageProducts"><i
							class="fa fa-database fa-fw"></i>Manage Products</a></li>
					<li><a href="logoutAdmin"><i class="fa fa-eject fa-fw"></i>Sign
							Out</a></li>
				</ul>
			</nav>
		</div>

		<!-- Main content -->

		<c:if test="${isAdminClickedCategories=='true'}">
			<jsp:include page="Category.jsp"></jsp:include>
			<br>
		</c:if>

		<c:if test="${isAdminClickedProducts=='true'}">
			<jsp:include page="Product.jsp"></jsp:include>
			<br>
		</c:if>

		<!-- Footer Start -->

		<footer class="text-right">
			<p>
				Copyright &copy; 2017 Mobineer Web Cafe | Designed by <a
					href="http://www.mobineer.com" target="_parent">Mobineer Web
					Technologies Co.</a>
			</p>
		</footer>
	</div>
	</div>
	</div>

	<!-- JS -->
	<script src="resources/js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="resources/js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	<script src="https://www.google.com/jsapi"></script>
	<!-- Google Chart -->
	<script>
		/* Google Chart 
		-------------------------------------------------------------------*/
		// Load the Visualization API and the piechart package.
		google.load('visualization', '1.0', {
			'packages' : [ 'corechart' ]
		});

		// Set a callback to run when the Google Visualization API is loaded.
		google.setOnLoadCallback(drawChart);

		// Callback that creates and populates a data table,
		// instantiates the pie chart, passes in the data and
		// draws it.
		function drawChart() {

			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Topping');
			data.addColumn('number', 'Slices');
			data.addRows([ [ 'Mushrooms', 3 ], [ 'Onions', 1 ],
					[ 'Olives', 1 ], [ 'Zucchini', 1 ], [ 'Pepperoni', 2 ] ]);

			// Set chart options
			var options = {
				'title' : 'How Much Pizza I Ate Last Night'
			};

			// Instantiate and draw our chart, passing in some options.
			var pieChart = new google.visualization.PieChart(document
					.getElementById('pie_chart_div'));
			pieChart.draw(data, options);

			var barChart = new google.visualization.BarChart(document
					.getElementById('bar_chart_div'));
			barChart.draw(data, options);
		}

		$(document).ready(function() {
			if ($.browser.mozilla) {
				//refresh page on browser resize
				// http://www.sitepoint.com/jquery-refresh-page-browser-resize/
				$(window).bind('resize', function(e) {
					if (window.RT)
						clearTimeout(window.RT);
					window.RT = setTimeout(function() {
						this.location.reload(false); /* false to get page from cache */
					}, 200);
				});
			} else {
				$(window).resize(function() {
					drawChart();
				});
			}
		});
	</script>
	<script type="text/javascript" src="resources/js/templatemo-script.js"></script>
	<!-- Templatemo Script -->

</body>
</html>