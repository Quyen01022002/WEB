<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Fesh food.vn</title>
	<link rel="stylesheet" href="css/3.css">
	<script type="module"
			src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
			src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
			crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/common/header.jsp"></jsp:include>
<ul class="link">
	<li><a href="home">Trang Chủ ></a></li>
	<li><a href="">Đăng ký</a></li>
</ul>

<div class="row justify-content-lg-center">
	<div class="col-lg-6 col-md-4">
		<h2>Đăng Ký Tài Khoản</h2>
		<form action="dangky" method="post">
			<c:if test="${not empty error}">
				<div class="col-md-12">
					<div class="alert alert-danger" role="alert">${error}</div>
				</div>
			</c:if>
			<div class="col-md-12">
				<div class="form-group" style="margin-top: 20px;">
					<label for="exampleInputEmail1">Nhập Họ Và Tên<span class="required">*</span></label>
					<input type="text"
						   name="ten"
						   class="form-control"
						   placeholder="Nhập Họ và tên"
						   class="form-control form-control-lg"
						   data-validation="ten"
						   data-validation-error-msg="Trường họ và tên có tối đa 20 ký tự"
						   value=""
						   id="email exampleInputEmail1"
						   required
						   maxlength="20"
						   pattern="[a-zA-Z0-9 _]{8,20}">
				</div>
			</div>
			<div class="col-md-12" style="margin-top: 20px;">
				<div class="form-group">
					<label for="exampleInputEmail1">Nhập Số Điện Thoại<span class="required">*</span></label>
					<input type="text"
						   name="sdt"
						   class="form-control"
						   placeholder="Nhập Số Điện Thoại"
						   class="form-control form-control-lg"
						   data-validation="sdt"
						   data-validation-error-msg="Số điện thoại phải có 10 chữ số"
						   value=""
						   id="email exampleInputEmail1"
						   required
						   maxlength="10"
						   pattern="[0-9]{10,10}">
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group" style="margin-top: 20px;">
					<label for="exampleInputEmail1">Nhập Email<span class="required">*</span></label>
					<input type="email"
						   name="email"
						   placeholder="Nhập Địa chỉ Email"
						   class="form-control form-control-lg"
						   data-validation="email"
						   data-validation-error-msg="Email sai định dạng"
						   value=""
						   id="email exampleInputEmail1"
						   required>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group" style="margin-top: 20px;">
					<label for="exampleInputPassword1">Nhập Mật Khẩu<span class="required">*</span></label>
					<input type="password"
						   class="form-control"
						   id="exampleInputPassword1"
						   required
						   placeholder="Password"
						   name="pass">
				</div>
			</div>
			<div class="d-grid gap-2">
				<button class="btn btn-success" style="margin-top: 20px;"
						type="submit">Tạo tài khoản</button>
				<p>
					Bạn đã có tài khoản? Đăng nhập <a href="login">tại đây</a>
				</p>
			</div>

		</form>
		<div class="clearfix"></div>
		<div class="row justify-content-lg-center">
			<div class="col-lg-6 col-md-4">
				<div class="line-break" style="text-align: center;">
					<span>hoặc đăng nhập qua</span>
				</div>
			</div>
		</div>

		<div class="social-login text-center">
			<a href="" class="social-login--facebook" onclick="loginFacebook()">
				<img width="129px" height="37px" alt="facebook-login-button"
					 src="//bizweb.dktcdn.net/assets/admin/images/login/fb-btn.svg">
			</a> <a href="" class="social-login--google" onclick="loginGoogle()">
			<img width="129px" height="37px" alt="google-login-button"
				 src="//bizweb.dktcdn.net/assets/admin/images/login/gp-btn.svg">
		</a>
		</div>

	</div>
</div>
</body>
</html>