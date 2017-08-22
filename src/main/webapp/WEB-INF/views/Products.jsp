<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<section id="content">
<div class="container">
	<div class="row">
		<c:forEach var="product" items="${productList}">
			<div class="col-lg-3">
				<div class="pricing-box-alt">
					<div class="pricing-heading">
						<h3>
							Product <strong>Picture</strong>
						</h3>
					</div>
					<div class="pricing-terms">
						<h6>${product.name}</h6>
					</div>
					<div class="pricing-content">
						<ul>
							<li><i class="icon-ok"></i>${product.id}</li>
							<li><i class="icon-ok"></i> <strong>${product.name}</strong></li>
							<li><i class="icon-ok"></i>${product.price}</li>
						</ul>
					</div>
					<div class="pricing-action">
						<a href="addToCart" class="btn btn-medium btn-theme"><i
							class="fa fa-heart"></i> Add To Cart </a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</section>
</head>
<body>

</body>
</html>