<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
	<!-- Required meta tags-->
	<!-- Title Page-->
	<title>Dashboard</title>

	<!-- Fontfaces CSS-->
	<link href="/view/admin/template/css/font-face.css" rel="stylesheet"
		  media="all">
	<link
			href="/view/admin/template/vendor/font-awesome-4.7/css/font-awesome.min.css"
			rel="stylesheet" media="all">
	<link
			href="/view/admin/template/vendor/font-awesome-5/css/fontawesome-all.min.css"
			rel="stylesheet" media="all">
	<link
			href="/view/admin/template/vendor/mdi-font/css/material-design-iconic-font.min.css"
			rel="stylesheet" media="all">

	<!-- Bootstrap CSS-->
	<link
			href="/view/admin/view/admin/template/vendor/bootstrap-4.1/bootstrap.min.css"
			rel="stylesheet" media="all">

	<!-- Vendor CSS-->
	<link href="/view/admin/template/vendor/animsition/animsition.min.css"
		  rel="stylesheet" media="all">
	<link
			href="/view/admin/template/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css"
			rel="stylesheet" media="all">
	<link href="/view/admin/template/vendor/wow/animate.css"
		  rel="stylesheet" media="all">
	<link
			href="/view/admin/template/vendor/css-hamburgers/hamburgers.min.css"
			rel="stylesheet" media="all">
	<link href="/view/admin/template/vendor/slick/slick.css"
		  rel="stylesheet" media="all">
	<link href="/view/admin/template/vendor/select2/select2.min.css"
		  rel="stylesheet" media="all">
	<link
			href="/view/admin/template/vendor/perfect-scrollbar/perfect-scrollbar.css"
			rel="stylesheet" media="all">

	<!-- Main CSS-->
	<link href="/view/admin/template/css/theme.css" rel="stylesheet"
		  media="all">


	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<link href="<c:url value="/css/admin/style.css"/>" rel="stylesheet"
		  type="text/css">

	<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.0.1/chart.min.js"></script>
</head>
<body>
<%@ include file='/view/admin/template/left-tool.jsp'%>

<div class="page-container">
	<!-- HEADER DESKTOP-->
	<%@ include file='/view/admin/template/header.jsp'%>
	<!-- HEADER DESKTOP-->

	<!-- MAIN CONTENT-->
	<div class="main-content">
		<div class="section__content section__content--p30">
			<div class="container-fluid">

				<div class="row" style="width: 100%">
					<div class="col-md-12">
						<label>Kết quả tìm kiếm cho từ khóa ${keyss }: </label>

						<div class="row">
							<c:if test="${countS!=0 }">
								<div class="col-lg-8">
									<div class="table-responsive table--no-card m-b-30">
										<table
												class="table table-borderless table-striped table-earning">
											<thead>
											<tr>
												<th>Mã sản phẩm</th>
												<th>Tên sản phẩm</th>
												<th>Ảnh</th>
												<th class="text-right">giá</th>
											</tr>
											</thead>
											<tbody>
											<c:if test="${countP==0 }"><p>Không có sản phẩm nào với từ khóa tìm kiếm</p></c:if>
											<c:forEach var="item" items="${products}">
												<tr class="odd gradeX" >
													<td><a href="../admin/product-edit?id=${item.productId }">${item.productId }</a></td>
													<td>${item.productName }</td>
													<td><c:url value="/image?fname=${item.img }"
															   var="imgUrl"></c:url> <img width="50px" height="50px"
																						  src="${imgUrl}"></td>
													<td>${item.price}</td>
												</tr>

											</c:forEach>
											</tbody>
										</table>
										<div class="col-md-6 col-sm-12" style="padding-top: 0">
											<ul class="pagination pull-right">
												<c:if test="${tagP>1}">
													<li><a
															href="${pageContext.request.contextPath }/admin/search?keysearch=${keyss }&index_p=${tagP-1}">&laquo;</a></li>
												</c:if>
												<c:forEach begin="1" end="${endP}" var="p">
													<li class='${tagP==i?"active":"" }'><a
															href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_p=${p}">${p}</a></li>
												</c:forEach>
												<c:if test="${tagP<endP}">
													<li><a
															href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_p=${tagP+1}">&raquo;</a></li>
												</c:if>
											</ul>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${countS==0 }">
								<div class="col-lg-12">
									<div class="table-responsive table--no-card m-b-30">
										<table
												class="table table-borderless table-striped table-earning">
											<thead>
											<tr>
												<th>Mã sản phẩm</th>
												<th>Tên sản phẩm</th>
												<th>Ảnh</th>
												<th class="text-right">giá</th>
											</tr>
											</thead>
											<tbody>
											<c:if test="${countP==0 }"><tr><td></td><td>Không có sản phẩm nào với từ khóa tìm kiếm</td><td></td><td></td></tr></c:if>
											<c:forEach var="item" items="${products}">
											<tr class="odd gradeX">
												<td><a href="../admin/product-edit?id=${item.productId }">${item.productId }</a></td>
												<td>${item.productName }</td>
												<td><c:url value="/image?fname=${item.img }"
														   var="imgUrl"></c:url> <img width="50px" height="50px"
																					  src="${imgUrl}"></td>
												<td>${item.price}</td>
												</c:forEach>
											</tbody>
										</table>
										<div class="col-md-6 col-sm-12" style="padding-top: 0">
											<ul class="pagination pull-right">
												<c:if test="${tagP>1}">
													<li><a
															href="${pageContext.request.contextPath }/admin/search?keysearch=${keyss }&index_p=${tagP-1}">&laquo;</a></li>
												</c:if>
												<c:forEach begin="1" end="${endP}" var="p">
													<li class='${tagP==i?"active":"" }'><a
															href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_p=${p}">${p}</a></li>
												</c:forEach>
												<c:if test="${tagP<endP}">
													<li><a
															href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_p=${tagP+1}">&raquo;</a></li>
												</c:if>
											</ul>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${countS!=0 }">
								<div class="col-lg-4">
									<div
											class="au-card au-card--bg-blue au-card-top-countries m-b-30">
										<div class="au-card-inner">
											<div class="table-responsive">
												<table class="table table-top-countries">
													<tbody>
													<c:forEach var="o" items="${stores}">
													<tr class="odd gradeX">
														<td><a href="../admin/stores-detail?storeID=${o.storeId}" style="color: white">${o.storeName }</a></td>
														<td><a href="../admin/user-detail?userId=${o.ownerID}" style="color: white">${o.ownerName}</a></td>
														</c:forEach>
													</tbody>
												</table>
												<div class="col-md-6 col-sm-12" style="padding-top: 0">
													<ul class="pagination pull-right">
														<c:if test="${tagS>1}">
															<li><a
																	href="${pageContext.request.contextPath }/admin/search?keysearch=${keyss }&index_s=${tagS-1}">&laquo;</a></li>
														</c:if>
														<c:forEach begin="1" end="${endS}" var="s">
															<li class='${tagS==s?"active":"" }'><a
																	href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_s=${s}">${s}</a></li>
														</c:forEach>
														<c:if test="${tagS<endS}">
															<li><a
																	href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_s=${tagS+1}">&raquo;</a></li>
														</c:if>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</div>
						<div class="row">
							<c:if test="${countU!=0 and countO!=0 }">
								<div class="col-lg-6">
									<!-- USER DATA-->
									<div class="user-data m-b-30">
										<h3 class="title-3 m-b-30">
											<i class="zmdi zmdi-account-calendar"></i>Người dùng
										</h3>

										<div class="table-responsive table-data">
											<table class="table">
												<thead>
												<tr>
													<td>Mã người dùng</td>
													<td>Tên người dùng</td>
													<td>Phone</td>
													<td>Email</td>
													<td>Vai trò</td>
												</tr>
												</thead>
												<tbody>
												<c:forEach var="ue" items="${users}">
													<tr class="odd gradeX">
														<td><a href="../admin/user-detail?userId=${ue.userID}" >${ue.userID}</a></td>
														<td>${ue.name }</td>
														<td>${ue.phone }</td>
														<td>${ue.email }</td>
														<td><c:if test="${ue.role==1 }">Người dùng</c:if>
															<c:if test="${ue.role==2 }">Shipper</c:if> <c:if
																	test="${ue.role==3 }">Admin</c:if></td>
													</tr>
												</c:forEach>


												</tbody>
											</table>
											<div class="col-md-6 col-sm-12" style="padding-top: 0">
												<ul class="pagination pull-right">
													<c:if test="${tagU>1}">
														<li><a
																href="${pageContext.request.contextPath }/admin/search?keysearch=${keyss }&index_u=${tagU-1}">&laquo;</a></li>
													</c:if>
													<c:forEach begin="1" end="${endU}" var="u">
														<li class='${tagU==u?"active":"" }'><a
																href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_u=${u}">${u}</a></li>
													</c:forEach>
													<c:if test="${tagU<endU}">
														<li><a
																href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_u=${tagU+1}">&raquo;</a></li>
													</c:if>
												</ul>
											</div>
										</div>
									</div>
									<!-- END USER DATA-->
								</div>
							</c:if>
							<c:if test="${countU!=0 and countO==0 }">
								<div class="col-lg-12">
									<!-- USER DATA-->
									<div class="user-data m-b-30">
										<h3 class="title-3 m-b-30">
											<i class="zmdi zmdi-account-calendar"></i>Người dùng
										</h3>

										<div class="table-responsive table-data">
											<table class="table">
												<thead>
												<tr>
													<td>Mã người dùng</td>
													<td>Tên người dùng</td>
													<td>Phone</td>
													<td>Email</td>
													<td>Vai trò</td>
												</tr>
												</thead>
												<tbody>
												<c:forEach var="ue" items="${users}">
													<tr class="odd gradeX">
														<td><a href="../admin/user-detail?userId=${ue.userID}" >${ue.userID}</a></td>
														<td>${ue.name }</td>
														<td>${ue.phone }</td>
														<td>${ue.email }</td>
														<td><c:if test="${ue.role==1 }">Người dùng</c:if>
															<c:if test="${ue.role==2 }">Shipper</c:if> <c:if
																	test="${ue.role==3 }">Admin</c:if></td>
													</tr>
												</c:forEach>


												</tbody>
											</table>
											<div class="col-md-6 col-sm-12" style="padding-top: 0">
												<ul class="pagination pull-right">
													<c:if test="${tagU>1}">
														<li><a
																href="${pageContext.request.contextPath }/admin/search?keysearch=${keyss }&index_u=${tagU-1}">&laquo;</a></li>
													</c:if>
													<c:forEach begin="1" end="${endU}" var="u">
														<li class='${tagU==u?"active":"" }'><a
																href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_u=${u}">${u}</a></li>
													</c:forEach>
													<c:if test="${tagU<endU}">
														<li><a
																href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_u=${tagU+1}">&raquo;</a></li>
													</c:if>
												</ul>
											</div>
										</div>
									</div>
									<!-- END USER DATA-->
								</div>
							</c:if>
							<c:if test="${countO!=0 }">
								<div class="col-lg-6">
									<!-- TOP CAMPAIGN-->
									<div class="top-campaign">
										<h3 class="title-3 m-b-30">Hóa đơn</h3>
										<div class="table-responsive">
											<table class="table table-top-campaign">
												<tbody>

												<c:forEach var="ord" items="${orders}">
													<tr class="odd gradeX">

														<td><%-- <a href="../admin/order-detail?orderId=${ord.oderid}" > --%>${ord.oderid }</a></td>
														<td>${ord.usersName }</td>
														<td>${ord.tong }</td>
														<td>${ord.ngayup }</td>
													</tr>
												</c:forEach>



												</tbody>
											</table>
											<div class="col-md-6 col-sm-12" style="padding-top: 0">
												<ul class="pagination pull-right">
													<c:if test="${tagO>1}">
														<li><a
																href="${pageContext.request.contextPath }/admin/search?keysearch=${keyss }&index_o=${tagO-1}">&laquo;</a></li>
													</c:if>
													<c:forEach begin="1" end="${endO}" var="o">
														<li class='${tagO==o?"active":"" }'><a
																href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_o=${o}">${o}</a></li>
													</c:forEach>
													<c:if test="${tagO<endO}">
														<li><a
																href="${pageContext.request.contextPath}/admin/search?keysearch=${keyss }&index_o=${tagO+1}">&raquo;</a></li>
													</c:if>
												</ul>
											</div>
										</div>
									</div>
									<!--  END TOP CAMPAIGN-->
								</div>
							</c:if>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>




	<!-- END MAIN CONTENT-->
	<!-- END PAGE CONTAINER-->
</div>































<script src="../template/vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="../template/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="../template/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="../template/vendor/slick/slick.min.js">

</script>
<script src="../template/vendor/wow/wow.min.js"></script>
<script src="../template/vendor/animsition/animsition.min.js"></script>
<script
		src="../template/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">

</script>
<script src="../template/vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="vendor/counter-up/jquery.counterup.min.js">

</script>
<script src="../template/vendor/circle-progress/circle-progress.min.js"></script>
<script src="../template/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="../template/vendor/chartjs/Chart.bundle.min.js"></script>
<script src="../template/vendor/select2/select2.min.js">

</script>

<!-- Main JS-->
<script src="../template/js/main.js"></script>





</body>
</html>