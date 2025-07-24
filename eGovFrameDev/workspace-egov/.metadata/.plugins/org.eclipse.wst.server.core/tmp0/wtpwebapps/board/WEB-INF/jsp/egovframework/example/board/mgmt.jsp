<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 등록</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap CSS & JS CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	function cancel() {
		location.href = "<c:url value='/mainList.do'/>";
	}

	$(document).ready(function() {
		$("#idx").attr("readonly", true);
		$("#writerNm").attr("readonly", true);
		$("#indate").attr("readonly", true);
	})

	function add() {
		if ($("#title").val() == '') {
			alert("제목을 입력하세요");
			$("#title").focus();
			return;
		}
		if ($("#contents").val() == '') {
			alert("내용을 입력하세요");
			$("#contents").focus();
			return;
		}
		if (!confirm("등록하시겠습니까?")) {
			return;
		}
		document.boardRegForm.action = "<c:url value='/mgmt.do'/>?mode=add";
		document.boardRegForm.submit();
	}

	function modify() {
		if ($("#title").val() == '') {
			alert("제목을 입력하세요");
			$("#title").focus();
			return;
		}
		if ($("#contents").val() == '') {
			alert("내용을 입력하세요");
			$("#contents").focus();
			return;
		}
		if (!confirm("수정 하시겠습니까?")) {
			return;
		}
		document.boardRegForm.action = "<c:url value='mgmt.do'/>?mode=modify";
		document.boardRegForm.submit();
	}
</script>
</head>
<body>
	<div class="container mt-4">
		<h2 class="mb-4 fw-bold text-center">게시글 등록</h2>
		<div class="card mb-4">
			<div class="card-header">
				<label>게시글 등록</label>
			</div>
			<div class="card-body">
				<form class="row g-3" id="boardRegForm" name="boardRegForm"
					method="post" action="/">
					<div class="row mb-3">
						<label for="idx" class="col-sm-2 col-form-label">게시물아이디:</label>
						<div class="col-sm-10 d-flex align-items-center">
							<input type="text" class="form-control" id="idx" name="idx"
								placeholder="자동발번" value="${boardVO.idx}" readonly>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">제목:</label>
						<div class="col-sm-10 d-flex align-items-center">
							<input type="text" class="form-control" id="title" name="title"
								placeholder="제목을 입력하세요" maxLength="100" value="${boardVO.title}">
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">등록자/등록일:</label>
						<div class="col-sm-10 d-flex align-items-center flex-wrap">
							<input type="hidden" class="form-control me-2 mb-1" id="writer"
								name="writer" placeholder="등록자를 입력하세요" maxlength="15"
								value="${boardVO.writer}" style="width: 40%;"> <input
								type="text" class="form-control me-2 mb-1" id="writerNm"
								name="writerNm" placeholder="등록자를 입력하세요" maxlength="15"
								value="${boardVO.writerNm}" style="width: 40%;"> <input
								type="text" class="form-control" id="indate" name="indate"
								placeholder="등록일을입력하세요" maxLength="10"
								style="width:40%;" value="${strToday}" />
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">내용:</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="contents"
								name="contents" maxlength="1000">${boardVO.contents}</textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="card-footer text-end">
				<c:if test="${!empty sessionScope.userId }">
					<button type="button" class="btn btn-secondary" onclick="add();">등록</button>
					<button type="button" class="btn btn-secondary" onclick="modify();">수정</button>
				</c:if>
				<button type="button" class="btn btn-secondary" onclick="cancel();">취소</button>
			</div>
		</div>
	</div>
</body>
</html>