<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	function goBack() {
		location.href = "<c:url value='/mainList.do'/>";
	}
	
	function validateForm() {
		var currentPassword = $("#currentPassword").val();
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		
		if (currentPassword.trim() == "") {
			alert("현재 비밀번호를 입력하세요");
			$("#currentPassword").focus();
			return false;
		}
		if (newPassword.trim() == "") {
			alert("새 비밀번호를 입력하세요");
			$("#newPassword").focus();
			return false;
		}
		if (confirmPassword.trim() == "") {
			alert("새 비밀번호 확인을 입력하세요");
			$("#confirmPassword").focus();
			return false;
		}
		if (newPassword !== confirmPassword) {
			alert("새 비밀번호와 확인 비밀번호가 일치하지 않습니다");
			$("#confirmPassword").focus();
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
						<h3 class="mb-0">마이페이지</h3>
					</div>
					<div class="card-body p-4">
						<form action="updatePassword.do" method="post" onsubmit="return validateForm();">
							<div class="mb-3">
								<label for="userId" class="form-label fw-bold">아이디</label>
								<input type="text" class="form-control" id="userId" name="userId" 
									value="${sessionScope.userId}" readonly>
							</div>
							<div class="mb-3">
								<label for="userName" class="form-label fw-bold">이름</label>
								<input type="text" class="form-control" id="userName" name="userName" 
									value="${sessionScope.userName}" readonly>
							</div>
							<div class="mb-3">
								<label for="currentPassword" class="form-label fw-bold">현재 비밀번호</label>
								<input type="password" class="form-control" id="currentPassword" name="currentPassword" 
									placeholder="현재 비밀번호를 입력하세요" required>
							</div>
							<div class="mb-3">
								<label for="newPassword" class="form-label fw-bold">새 비밀번호</label>
								<input type="password" class="form-control" id="newPassword" name="newPassword" 
									placeholder="새 비밀번호를 입력하세요" required>
							</div>
							<div class="mb-4">
								<label for="confirmPassword" class="form-label fw-bold">새 비밀번호 확인</label>
								<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" 
									placeholder="새 비밀번호를 다시 입력하세요" required>
							</div>
							
							<c:if test="${not empty msg}">
								<div class="alert alert-danger" role="alert">
									${msg}
								</div>
							</c:if>
							
							<c:if test="${not empty successMsg}">
								<div class="alert alert-success" role="alert">
									${successMsg}
								</div>
							</c:if>
							
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<button type="button" class="btn btn-secondary me-md-2" onclick="goBack();">
									취소
								</button>
								<button type="submit" class="btn btn-primary">
									비밀번호 변경
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