<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap CSS & JS CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	function goBack() {
		location.href = "<c:url value='/mainList.do'/>";
	}
	
	function validateForm() {
		var userName = $("#userName").val();
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		if (userName.trim() == "") {
			alert("이름을 입력하세요");
			$("#userName").focus();
			return false;
		}
		if (userId.trim() == "") {
			alert("아이디를 입력하세요");
			$("#userId").focus();
			return false;
		}
		if (password.trim() == "") {
			alert("비밀번호를 입력하세요");
			$("#password").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header bg-primary text-white text-center">
						<h3 class="mb-0">회원가입</h3>
					</div>
					<div class="card-body p-4">
						<form action="join.do" method="post" onsubmit="return validateForm();">
							<div class="mb-3">
								<label for="userName" class="form-label fw-bold">이름</label>
								<input type="text" class="form-control" id="userName" name="userName" 
									placeholder="이름을 입력하세요" required>
							</div>
							<div class="mb-3">
								<label for="userId" class="form-label fw-bold">아이디</label>
								<input type="text" class="form-control" id="userId" name="userId" 
									placeholder="아이디를 입력하세요" required>
							</div>
							<div class="mb-4">
								<label for="password" class="form-label fw-bold">비밀번호</label>
								<input type="password" class="form-control" id="password" name="password" 
									placeholder="비밀번호를 입력하세요" required>
							</div>
							
							<c:if test="${not empty msg}">
								<div class="alert alert-danger" role="alert">
									${msg}
								</div>
							</c:if>
							
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<button type="button" class="btn btn-secondary me-md-2" onclick="goBack();">
									취소
								</button>
								<button type="submit" class="btn btn-primary">
									회원가입
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>