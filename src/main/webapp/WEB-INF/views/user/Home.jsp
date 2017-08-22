<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<title>Web Café</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="http://bootstraptaste.com" />
<!-- css -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="resources/css/jcarousel.css" rel="stylesheet" />
<link href="resources/css/flexslider.css" rel="stylesheet" />
<link href="resources/css/style.css" rel="stylesheet" />

<!-- Theme skin -->
<link href="resources/skins/default.css" rel="stylesheet" />

</head>
<body>
	<div id="wrapper">
		<!-- start header -->
		<header>
		<div class="navbar navbar-default navbar-static-top">
			<div class="container">
			${message}
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"><span>M</span>obineer</a>
				</div>
				<div class="navbar-collapse collapse ">
					<ul class="nav navbar-nav">
						<li class="active"><a href="LoginPage">Logout</a></li>
						<li class="active"><a href="CartPage">Cart</a></li>
					</ul>
				</div>
			</div>
		</div>
		</header>
		<!-- end header -->
		<section class="callaction">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="big-cta">
						<div class="cta-text">
							<h2>
								<span>Mobineer</span> Web Café
							</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>

		<!-- Menu Inclusion -->
		<jsp:include page="Menu.jsp"></jsp:include>

		<!-- Slider Inclusion -->
		<jsp:include page="Slider.jsp"></jsp:include>

		<!-- Products Inclusion -->
		<jsp:include page="Products.jsp"></jsp:include>

		<!-- Footer Inclusion -->
		<jsp:include page="Footer.jsp"></jsp:include>

		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/jquery.js"></script>
		<script src="resources/js/jquery.easing.1.3.js"></script>
		<script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/jquery.fancybox.pack.js"></script>
		<script src="resources/js/jquery.fancybox-media.js"></script>
		<script src="resources/js/google-code-prettify/prettify.js"></script>
		<script src="resources/js/portfolio/jquery.quicksand.js"></script>
		<script src="resources/js/portfolio/setting.js"></script>
		<script src="resources/js/jquery.flexslider.js"></script>
		<script src="resources/js/animate.js"></script>
		<script src="resources/js/custom.js"></script>
</body>
</html>