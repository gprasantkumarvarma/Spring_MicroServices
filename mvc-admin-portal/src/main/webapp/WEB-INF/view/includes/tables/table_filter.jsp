
<html xmlns:th="thhp://www.thymeleaf.org">
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>DataTables</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">DataTables</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">

					<div class="card">
						<div class="card-header">
							<h3 class="card-title">DataTable with default features</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
						

							<input type="button" value="Add Customer" 
								onclick="window.location.href='showFormForAdd'; return false;"
								class="btn btn-app" />
							
							<table id="example1" class="table table-bordered table-striped">
							
								<thead>
									<tr>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Email</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
								
								
								
								<tr th:each="tempCustomer :${customer}">
					           <td th:text="${tempCustomer.firstName}" />
					           <td th:text="${tempCustomer.lastName}" />
					<td th:text="${tempCustomer.email}" />
					<td>
					<a th:href="@{~/customer/showFormForUpdate}">Update</a>
					<a th:href="@{~/customer/delete}">Delete</a>
					
					</td>
				         </tr>
								
								
								
								
								
<!-- 								
								
									loop over and print our customers
									<c:forEach var="tempCustomer" items="${customers}">

										construct an "update" link with customer id
										<c:url var="updateLink" value="/customer/showFormForUpdate">
											<c:param name="customerId" value="${tempCustomer.id}" />
										</c:url>

										construct an "delete" link with customer id
										<c:url var="deleteLink" value="/customer/delete">
											<c:param name="customerId" value="${tempCustomer.id}" />
										</c:url>

										<tr>
											<td>${tempCustomer.firstName}</td>
											<td>${tempCustomer.lastName}</td>
											<td>${tempCustomer.email}</td>

											<td>
												display the update link <a href="${updateLink}">Update</a>
												| <a href="${deleteLink}"
												onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
											</td>

										</tr>

									</c:forEach>
 -->
								</tbody>
								<tfoot>
									<!--                   <tr>
                    <th>Rendering engine</th>
                    <th>Browser</th>
                    <th>Platform(s)</th>
                    <th>Engine version</th>
                    <th>CSS grade</th>
                  </tr> -->
								</tfoot>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script>

</script>