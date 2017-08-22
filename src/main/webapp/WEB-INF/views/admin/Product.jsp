<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Main content -->
<div class="templatemo-content col-1 light-gray-bg">
	<div class="templatemo-top-nav-container">
		<div class="row">
			<nav class="templatemo-top-nav col-lg-12 col-md-12">
				<ul class="text-uppercase">
					<li><a href="manageCategories">Manage Categories</a></li>
					<li><a href="manageProducts" class="active">Manage
							Products</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<div class="col-1">
		<div class="templatemo-content-widget pink-bg">
			<i class="fa fa-times"></i>
			<p class="margin-bottom-0">${message}</p>
		</div>
	</div>



	<div class="templatemo-content-container">
		<div class="templatemo-flex-row flex-content-row">

			<div class="templatemo-content-widget white-bg col-1 ">
				<h2 class="text-uppercase">ADD</h2>
				<h3 class="text-uppercase">Product</h3>
				<hr>
				<form action="manage_product_add" class="templatemo-login-form">
					<div class="form-group">
						<label for="inputEmail">Product ID</label> <input type="name"
							name="id" class="form-control" placeholder="Enter Product ID"
							required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Product Name</label> <input type="name"
							name="name" class="form-control" placeholder="Enter Product Name"
							required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Product Price</label> <input type="name"
							name="price" class="form-control"
							placeholder="Enter Product Price" required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Product Description</label> <input
							type="name" name="description" class="form-control"
							placeholder="Enter Product Description" required>
					</div>
					<div class="form-group">
						<label class="control-label templatemo-block">Associate
							Category</label> <select class="form-control" name="category_id">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<button type="submit" class="templatemo-blue-button">ADD</button>
					</div>
				</form>
			</div>
			<div class="templatemo-content-widget white-bg col-1">
				<h2 class="text-uppercase">UPDATE</h2>
				<h3 class="text-uppercase">Product</h3>
				<hr>
				<form action="manage_product_update" class="templatemo-login-form">
					<div class="form-group">
						<label for="inputEmail">Product ID</label> <input type="name"
							name="id" value="${selectedProduct.id}" class="form-control"
							placeholder="Enter Product ID" required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Product Name</label> <input type="name"
							name="name" value=""
							${selectedProduct.name}" class="form-control"
							placeholder="Enter Product Name" required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Product Price</label> <input type="name"
							name="price" value="${selectedProduct.price}"
							class="form-control" placeholder="Enter Product Price" required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Product Description</label> <input
							type="name" name="description"
							value="${selectedProduct.description}" class="form-control"
							placeholder="Enter Product Description" required>
					</div>
					<div class="form-group">
						<label class="control-label templatemo-block">Associate	Category</label> 
							<select class="form-control" name="category_id">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>

					</div>

					<div class="form-group">
						<button type="submit" class="templatemo-blue-button">UPDATE</button>
					</div>
				</form>
			</div>
		</div>

		<div class="templatemo-content-widget no-padding">
			<div class="panel panel-default table-responsive">
				<table
					class="table table-striped table-bordered templatemo-user-table">
					<thead>
						<tr>
							<td><a href="" class="white-text templatemo-sort-by">Product
									ID <span class="caret"></span>
							</a></td>
							<td><a href="" class="white-text templatemo-sort-by">Product
									Name <span class="caret"></span>
							</a></td>
							</a>
							</td>
							<td><a href="" class="white-text templatemo-sort-by">Product
									Price <span class="caret"></span>
							</a></td>
							<td><a href="" class="white-text templatemo-sort-by">Product
									Description <span class="caret"></span>
							</a></td>
							<td><a href="" class="white-text templatemo-sort-by">Associated
									Category <span class="caret"></span>
							</a></td>
							<td>Edit</td>
							<td>Delete</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${productList}">
								<tr>
									<td>${product.id}</td>
									<td>${product.name}</td>
									<td>${product.price}</td>
									<td>${product.description}</td>
									<td>${product.category}</td>
									<td><a href="manage_product_edit/${product.id}"
										class="templatemo-edit-btn">Edit</a></td>
									<td><a href="manage_product_delete/${product.id}"
										class="templatemo-edit-btn">Delete</a></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Second row ends -->