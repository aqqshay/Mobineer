<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Main content -->
<div class="templatemo-content col-1 light-gray-bg">
	<div class="templatemo-top-nav-container">
		<div class="row">
			<nav class="templatemo-top-nav col-lg-12 col-md-12">
				<ul class="text-uppercase">
					<li><a href="manageCategories" class="active">Manage
							Categories</a></li>
					<li><a href="manageProducts">Manage Products</a></li>
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
				<h2 class="text-uppercase">Add</h2>
				<h3 class="text-uppercase">Category</h3>
				<hr>
				<form action="manage_category_add" method="post"
					class="templatemo-login-form">
					<div class="form-group">
						<label for="inputEmail">Category ID</label> <input type="name"
							name="id" class="form-control" id="categoryId"
							placeholder="Enter Category ID" required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Category Name</label> <input type="name"
							name="name" class="form-control"
							placeholder="Enter Category Name" required>
					</div>

					<div class="form-group">
						<button type="submit" class="templatemo-blue-button">Add</button>
					</div>
				</form>
			</div>
			<div class="templatemo-content-widget white-bg col-1">
				<h2 class="text-uppercase">Update</h2>
				<h3 class="text-uppercase">Category</h3>
				<hr>
				<form action="manage_category_update" class="templatemo-login-form">
					<div class="form-group">
						<label for="inputEmail">Category ID</label> <input type="name"
							name="id" value="${selectedCategory.id}" class="form-control"
							placeholder="Enter Category ID" required>
					</div>
					<div class="form-group">
						<label for="inputEmail">Category Name</label> <input type="name"
							name="name" value="${selectedCategory.name}" class="form-control"
							placeholder="Enter Category Name" required>
					</div>

					<div class="form-group">
						<button type="submit" value="Update Category"
							class="templatemo-blue-button">Update</button>
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
							<td><a href="" class="white-text templatemo-sort-by">Category
									ID <span class="caret"></span>
							</a></td>
							<td><a href="" class="white-text templatemo-sort-by">Category
									Name <span class="caret"></span>
							</a></td>
							<td>Edit</td>
							<td>Delete</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="category" items="${categoryList}">
							<tr>
								<td>${category.id}</td>
								<td>${category.name}</td>
								<td><a href="manage_category_edit/${category.id}"
									class="templatemo-edit-btn">Edit</a></td>
								<td><a href="manage_category_delete/${category.id}"
									class="templatemo-edit-btn">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Second row ends -->