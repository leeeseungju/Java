<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
pageContext.setAttribute("crcn", "\r\n"); // Space, Enter 
pageContext.setAttribute("br", "<br/>"); //br태그
%>

<!DOCTYPE html>
<html>
<head>
<title>상세화면</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap CSS & JS CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	function list() {
		location.href = "<c:url value='/mainList.do'/>";
	}

	function add() {
		if ($("#writer").val() == '') {
			alert("작성자를 입력하세요");
			$("#writer").focus();
			return;
		}
		if ($("#reply").val() == '') {
			alert("댓글을 입력하세요");
			$("#title").focus();
			return;
		}
		if (!confirm("댓글을 작성하시겠습니까?")) {
			return;
		}
		document.form2.action = "<c:url value='reply.do'/>?idx=${boardVO.idx}";
		document.form2.submit();
	}

	function modify() {
		location.href = "<c:url value='/mgmt.do'/>?idx=${boardVO.idx}";
	}

	function del() {
		
/* 		var cnt = ${ fn:length(resultList) };
		if( cnt > 0 ){
		alert("댓글이 있는 게시물은 삭제할 수 없습니다");
		} */
		if (!confirm("삭제하시겠습니까?")) {
			return;
		}
		document.form1.action = "<c:url value='mgmt.do'/>?mode=del&idx=${boardVO.idx}";
		document.form1.submit();
	}
</script>
</head>
<body>
	<div class="container mt-4">
		<h2 class="mb-4 fw-bold text-center">상세화면</h2>
		<div class="card mb-4">
			<div class="card-header">
				<c:if test="${!empty sessionScope.userId && !empty sessionScope.userName}">
					<label> ${sessionScope.userName} 님이 로그인 하셨습니다.</label>
				</c:if>
			</div>
			<div class="card-body">
				<form name="form1" method="post" action="/">
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">게시물아이디:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="idx"
								placeholder="자동발번" value="${boardVO.idx}" readonly>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">제목:</label>
						<div class="col-sm-10 d-flex align-items-center">
							<input type="text" class="form-control" id="title" name="title"
								placeholder="제목을 입력하세요" maxlength="100" value="${boardVO.title}"
								readonly>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">등록자/등록일:</label>
						<div class="col-sm-10 d-flex align-items-center">
							<div>${boardVO.writerNm}/${fn:substring(boardVO.indate, 0, 16)}
							</div>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label">내용:</label>
						<div class="col-sm-10 d-flex align-items-center">
							<c:out value="${fn:replace(boardVO.contents, crcn, br)}"
								escapeXml="false" />
						</div>
					</div>
				</form>
			</div>
			<div class="card-footer text-end">
				<c:if test="${!empty sessionScope.userId && sessionScope.userId == boardVO.writer}">
				<button type="button" class="btn btn-secondary" onclick="modify();">수정</button>
				<button type="button" class="btn btn-secondary" onclick="del();">삭제</button>
				</c:if>
			<button type="button" class="btn btn-secondary" onclick="list();">목록</button>
			</div>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<div class="card mb-3 border rounded shadow-sm">
					<div class="card-body">
						<h6 class="card-subtitle mb-2 text-muted">
							<c:out value="${result.writer}" />
							/
							<c:out value="${result.indate}" />
							<%-- <fmt:formatDate value="${result.indate}" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
						</h6>
						<p class="card-text">
							<c:out value="${fn:replace(result.reply, crcn, br)}"
								escapeXml="false" />
						</p>
					</div>
				</div>
			</c:forEach>

			<div class="mt-3 p-2 bg-light border rounded">댓글 등록</div>

			<div class="mt-3 p-4 bg-body-tertiary border rounded">
				<form name="form2" method="post" action="">
					<div class="row mb-3">
						<label for="writer" class="col-sm-2 col-form-label">작성자/작성일:</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="writer" name="writer"
								placeholder="작성자를 입력하세요" maxlength="15"
								value=${sessionScope.userName }>
						</div>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="indate" name="indate"
								placeholder="작성일을 입력하세요" maxlength="10" readonly
								style="width: 100%;" value="${strToday}">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="reply" class="col-sm-2 col-form-label">내용:</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="3" id="reply" name="reply"
								maxlength="300"></textarea>
						</div>
					</div>

					<div class="text-end">
						<button type="submit" class="btn btn-primary" onclick="add();">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>