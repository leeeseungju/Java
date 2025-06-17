<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mypage.css" />
</head>
<body>
    <div class="container">
        <h2>내 정보 수정</h2>
        
        <div class="user-info">
            <h3>현재 정보</h3>
            <p><strong>아이디:</strong> ${user.id}</p>
            <p><strong>이름:</strong> ${user.name}</p>
            <p><strong>전화번호:</strong> ${user.phone}</p>
            <p><strong>주소:</strong> ${user.address}</p>
        </div>
        
        <form action="${pageContext.request.contextPath}/mypage_action" method="post">
            <div class="form-group">
                <label>아이디</label>
                <input type="text" name="id" value="${user.id}" readonly>
            </div>
            
            <div class="form-group">
                <label>이름</label>
                <input type="text" name="name" value="${user.name}" required>
            </div>
            
            <div class="form-group">
                <label>전화번호</label>
                <input type="tel" name="phone" value="${user.phone}" required>
            </div>
            
            <div class="form-group">
                <label>주소</label>
                <input type="text" name="address" value="${user.address}" required>
            </div>
            
            <button type="submit" class="submit-btn">정보 수정</button>
        </form>
        
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/">← 메인으로 돌아가기</a>
        </div>
    </div>
</body>
</html>
