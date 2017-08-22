<!DOCTYPE html>
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>

	<section id="inner-headline">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse ">
				<ul class="nav navbar-nav">
					<c:forEach var="category" items="${categoryList}">
						<li class="dropdown">
							<a href="#" class="active"	data-toggle="dropdown" data-hover="dropdown" data-delay="0"	data-close-others="false">${category}
								<b class=" icon-angle-down"></b></a> 
									<c:forEach var="category" items="${productList}">
										<ul class="dropdown-menu">
											<li><a href="${product}">${product}</a></li>
										</ul>
									</c:forEach>
						</li>

					</c:forEach>
				</ul>
			</div>

		</div>
	</section>



</body>
</html>
